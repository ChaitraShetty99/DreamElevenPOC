package base;

import actions.CommonAction;
import cleanRepository.CleanDirectory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.inject.Guice;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.NetworkSpeed;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.LandingPage;
import pages.LoginPage;
import pages.RegistrationPage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest extends CommonAction {

    private static ThreadLocal<AppiumDriverLocalService> thread= new ThreadLocal<>();
    private AppiumDriverLocalService appiumDriverLocalService;
    public static AppiumDriver driver;
    public static ExtentHtmlReporter extentHtmlReporter;
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;
    public String sheetName;
    public SoftAssert softAssert= new SoftAssert();


    @BeforeSuite(alwaysRun = true)
    public void configBS(ITestContext result) {
        logger();
        this.appiumDriverLocalService = initiateAppiumServer(setupAppiumServer(result.getName()));
        thread.set(this.appiumDriverLocalService);

    }

    @BeforeClass(alwaysRun = true)
    public void configBC() {
        propertyInjector= Guice.createInjector(new PropertiesUtility());//reading from file
        propertyInjector.getInstance(FileUtility.class);
    }


    @BeforeTest(alwaysRun = true)
    public  void initiateReportTrack(){
        extentHtmlReporter=new ExtentHtmlReporter(FilePath.REPORT_PATH+"/reportGenerated "+getSystemDate()+".html");
        extentHtmlReporter.config().setDocumentTitle("Dream 11 POC Automation Report");
        extentHtmlReporter.config().setReportName("Functional Testing Report");
        extentHtmlReporter.config().setTheme(Theme.STANDARD);

        extentReports= new ExtentReports();
        extentReports.attachReporter(extentHtmlReporter);
        extentReports.setSystemInfo("HostName","Windows 10");
        extentReports.setSystemInfo("OS version",System.getProperty("os"));
        extentReports.setSystemInfo("Tester","Test Yantra");

    }

    @BeforeMethod(alwaysRun = true)
    public void launchApp(ITestResult result) throws MalformedURLException {
        driver = launchDriver(thread.get().getUrl(), Platform.ANDROID);
        if(sheetName==null) {
            sheetName="dataSheet1";
        }
        if (driver != null) {
            try {
                RegistrationPage registrationPage = new RegistrationPage(driver);
                try {
                    try {
                        if (registrationPage.selectEnglishLanguage.isDisplayed()) {
                            clickOnElement(registrationPage.selectEnglishLanguage);
                            awaitForElement(driver, registrationPage.continueButton);
                            clickOnElement(registrationPage.continueButton);
                        }
                        }catch(Exception e){
                            logger.info("Language selection is not displayed");
                        }
                        registrationPage.clickOnRegisterButton();
                        registrationPage.clickOnElement(registrationPage.mobileNoTextField);
                        registrationPage.enterMobileNumber(FileUtility.mobileNumber);
                        registrationPage.clickOnRegisterButton();
                        LandingPage landingPage = new LandingPage(driver);
                        awaitForElement(driver,landingPage.balanceButton);
                        softAssert(landingPage.balanceButton.isDisplayed(),"Balance button did not displayed");
                    } catch (Exception e) {
                        logger.info("Number is already registered");
                        LoginPage loginPage = new LoginPage(driver);
                        loginPage.clickOnLoginButton();
                        loginPage.clickOnElement(loginPage.mobileOrEmailTextField);
                        waitOrPause(3);
                        loginPage.enterMobileOrEmailTextField(FileUtility.mobileNumber);
                        hideKeyboard(driver);
                        loginPage.clickOnNextButton();
                        waitOrPause(20);

                    }
                } catch (Exception e) {
                    logger.info("App starting from Landing page");
                }
            }

    }

    @AfterMethod(alwaysRun = true)
    public void closeApp(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, result.getName()+" test case is passed.");
            logger.info(result.getName()+" test case is Passed.");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            if (driver != null) {
                extentTest.log(Status.FAIL,"Test case is failed "+ result.getName());
                extentTest.log(Status.FAIL,"Test case failed because of "+ Thread.currentThread().getStackTrace());
                String screenshotFilePath=takeScreenshotAtEndOfTest(driver,result.getName());
                logger.info("Test case is failed and screen shot path "+screenshotFilePath);
                extentTest.log(Status.FAIL,"Test case is failed and screen shot path "+screenshotFilePath);
            }

        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.log(Status.SKIP,result.getName()+" is skipped.");
        }

        if (driver != null) {
            driver.closeApp();
        }

    }

    @AfterTest(alwaysRun = true)
    public void declineReportTrack(){
        extentReports.flush();
    }

    @AfterClass(alwaysRun = true)
    public void configAC() {
        CleanDirectory.deleteFile();
    }

    @AfterSuite(alwaysRun = true)
    public void configAS() {
        stopAppiumServer();
    }


/**
 *  Setting up the Capabilities
 */

    public AppiumDriverLocalService setupAppiumServer(String testcasename) {
        String operatingSystem = System.getProperty("os");
        logger.info("OPERATING OS "+operatingSystem);
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.withAppiumJS(new File(FilePath.MAIN_JS));
        builder.usingDriverExecutable(new File(FilePath.NODE_JS));
        builder.usingAnyFreePort();
        builder.withArgument(GeneralServerFlag.RELAXED_SECURITY);
        builder.withLogFile(new File(FilePath.SERVERLOG+"appium " + testcasename+ getSystemDate() + ".log"));
        appiumDriverLocalService = AppiumDriverLocalService.buildService(builder);
        return appiumDriverLocalService;
    }

    public AppiumDriverLocalService initiateAppiumServer(AppiumDriverLocalService service) {
        if (service != null) {
            service.start();
        }
        return  appiumDriverLocalService;
    }

    public AppiumDriver launchDriver(URL url, Platform platForm) {
        logger.info("launching app: server url: " + url + "\n platform: " + platForm);
        DesiredCapabilities capabilities = null;
        switch (platForm) {
            case ANDROID:
                capabilities = setDesiredCapability("android");
                String deviceUDID = System.getProperty("Device");
                if (deviceUDID == null) {
                    logger.info(FileUtility.UDID);
                    capabilities.setCapability(MobileCapabilityType.UDID, FileUtility.UDID);
                }
                driver = new AndroidDriver(url, capabilities);
                break;
            case IOS:
                driver = new IOSDriver(url, capabilities);
                break;
            default:
                logger.info("Invalid url: " + url + " platform: " + platForm + " capabilities: " + capabilities.toJson().toString());
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public DesiredCapabilities setDesiredCapability(String platform) {
        logger.info("Setting device capability");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platform", platform);
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, FileUtility.appPackage);
         capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,FileUtility.appActivity);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
//        capabilities.setCapability(MobileCapabilityType.APP, FilePath.APK_PATH);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
//        capabilities.setCapability(MobileCapabilityType.FULL_RESET,true);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        capabilities.setCapability(MobileCapabilityType.SUPPORTS_NETWORK_CONNECTION, NetworkSpeed.FULL);
        logger.info("capabilities: " + capabilities.toString());
        return capabilities;
    }

    public void stopAppiumServer() {
        if (appiumDriverLocalService != null) {
            appiumDriverLocalService.stop();
        }
    }

}
