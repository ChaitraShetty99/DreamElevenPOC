package actions;

import TestAnnotation.TestInfo;
import base.FilePath;
import com.google.inject.Injector;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

import io.appium.java_client.touch.WaitOptions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;

import static io.appium.java_client.touch.offset.PointOption.point;
import io.appium.java_client.TouchAction;
import pages.LandingPage;
import org.openqa.selenium.*;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

/**
 * @author Test yantra
 */
public class CommonAction {

    public Injector propertyInjector;
    public static Logger logger;
    public SoftAssert softAssert=new SoftAssert();

    /**
     * @param driver
     * @param testcaseName
     * @return Destination of screen shot
     * This method takes screenshot
     */
    public static String takeScreenshotAtEndOfTest(AppiumDriver driver, String testcaseName) throws  IOException {
        logger.info("Taking the screen shot at the end");
        String dateName = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = FilePath.SCREEN_SHOT_PATH + testcaseName + dateName + ".png";
        File finalDestination = new File(destination);
        FileHandler.copy(source, finalDestination);
        return destination;
    }

    /**
     * @return system date and time
     * This method returns current system date
     */
    public static String getSystemDate() {
        logger.info("Getting the system date and time");
        SimpleDateFormat formater = new SimpleDateFormat("dd_MMM_YYYY_hh_mm_ss");
        String date = formater.format(new Date());
        return date;
    }

    /**
     * @param value
     * @param message
     * This method verifies with soft assert
     */
	public void softAssert(boolean value,String message){
        logger.info("Verifying with boolean value in an assert true");
        softAssert.assertTrue(value,message);
        softAssert.assertAll();
    }

    /**
     *
     *
     * @param actual
     * @param expected
     * @param message
     */
    public void softAssert(int actual,int expected,String message){
        logger.info("Verifying with boolean value in an assert true");
        softAssert.assertEquals(actual,expected,message);
        softAssert.assertAll();
    }
    /**
     *
     * @param message
     * @param value
     * This method verifies with soft assert
     */
    public void softAssert(String message,boolean value){
        logger.info("Verifying with boolean value in an assert false");
        softAssert.assertFalse(value,message);
        softAssert.assertAll();
    }

    /**
     * @param actual
     * @param expected
     * @param message
     * This method verifies with soft assert
     */
    public void softAssert(String actual,String expected,String message){
        logger.info("Verifying with string actual and expected");
        softAssert.assertEquals(actual,expected,message);
        softAssert.assertAll();
    }


    /**
     * @param  seconds
     *  Hard wait or Thread.sleep
     */
    public void waitOrPause(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pass class name and method name in string to extract testcase id
     *
     * @param className
     * @param testMethodName
     * @return testCaseId from annotation eg:
     *         getTestCaseId(Testscript.class,"TestRunMethod")
     */
    public String getTestCaseId(Class className, String testMethodName) {
        try {
            Method method = className.getMethod(testMethodName);
            TestInfo testinfo = (TestInfo) method.getAnnotation(TestInfo.class);
            if (testinfo != null) {
                logger.info("returns test case id");
                return testinfo.testcaseID();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        logger.info("No Testcase id found");
        return "No Testcase id found: " + testMethodName;
    }

    /**
     * @param element
     * This method used to click on element
     */
    public void clickOnElement(WebElement element) {
        logger.info("clicking on element: " + element);
        element.click();
    }

    /**
     * @param element
     * @param text to be passed
     * This method is used to type
     */
    public void type(WebElement element, String text) {
        logger.info("typing into text field: " + element + " with text: " + text);
        element.sendKeys(text);
    }

    /**
     * @param driver
     * This method is used to back key
     */
    public void pressNavigationBack(AppiumDriver driver) {
        logger.info("Pressing android navigate back button");
        AndroidDriver androidDriver = (AndroidDriver) driver;
        waitOrPause(4);
        androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    /**
     * @param driver
     * This method is used hide the keyboard
     */
    public void hideKeyboard(AppiumDriver driver) {
        logger.info("Hiding keyboard");
        driver.hideKeyboard();
    }

    /**
     * @param driver
     * @param element
     * This method is used to scroll horizontal and click
     */
    public void scrollHorizontalAndClickElement(AppiumDriver driver, WebElement element) {
        Dimension windowSize = driver.manage().window().getSize();
        int yscreen= (int) (windowSize.getHeight()*0.2);
        int xstart=(int)(windowSize.getWidth()*0.8);
        int xend=(int)(windowSize.getWidth()*0.1);
        for (int swipeCount = 0; swipeCount < 10; swipeCount++) {
            try {
                logger.info("Tapping on the element");
                clickOnElement(element);
                break;
            } catch (Exception e) {
                logger.info("Performing the swipe action");
                TouchAction touch = new TouchAction(driver);
                touch.longPress(point(xstart, yscreen)).moveTo(point(xend, yscreen)).release().perform();
            }
        }
    }

    /**
     * @param driver
     * @param howManySwipes number of swipes
     * @param gameSection
     * This method is used to scroll left and right
     */
    public void scrollAndClick(AppiumDriver driver, int howManySwipes, String gameSection){
        LandingPage landingPage=new LandingPage(driver);
        Dimension windowSize = driver.manage().window().getSize();
        int yscreen= landingPage.gamesSection.getLocation().getY()+100;
        WebElement element=null;
        try{
            element=driver.findElementByXPath("//android.widget.LinearLayout/child::android.widget.ImageView/following-sibling::android.widget.TextView[@text='"+gameSection+"']");
            awaitForElement(driver,element);
            if(element.isDisplayed()){
                logger.info("Tap on game section");
                clickOnElement(element);
                softAssert(element.getText().equalsIgnoreCase(gameSection),gameSection+" did not match");
                softAssert(Boolean.parseBoolean(element.getAttribute("selected")),element.getText()+" is not selected");
                return;
            }
        }catch (Exception exception){
            int xstart=(int)(windowSize.getWidth()*0.8);
            int xend=(int)(windowSize.getWidth()*0.1);
            for (int swipeCount = 0; swipeCount < howManySwipes; swipeCount++) {
                logger.info("Performing swipe action to left");
                TouchAction touch = new TouchAction(driver);
                touch.longPress(point(xstart, yscreen)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(5))).release().perform();//.moveTo(point(xend, yscreen)).release().perform();
                JavascriptExecutor js = (JavascriptExecutor) driver;
                HashMap<String, String> scrollObject = new HashMap<String, String>();
                scrollObject.put("direction", "left");
            }
        }
        try{
            element=driver.findElementByXPath("//android.widget.LinearLayout/child::android.widget.ImageView/following-sibling::android.widget.TextView[@text='"+gameSection+"']");
            awaitForElement(driver,element);
            if(element.isDisplayed()){
                logger.info("Tap on game section");
                clickOnElement(element);
                softAssert(element.getText().equalsIgnoreCase(gameSection),gameSection+" did not match");
                softAssert(Boolean.parseBoolean(element.getAttribute("selected")),element.getText()+" is not selected");
                return;
            }
        }catch (Exception exception){
            int xstartpoint=(int)(windowSize.getWidth()*0.1);
            int xendpoint=(int)(windowSize.getWidth()*0.8);
            for (int swipeCount = 0; swipeCount < howManySwipes; swipeCount++) {
                logger.info("Performing swipe action to right");
                TouchAction touch = new TouchAction(driver);
                touch.longPress(point(xstartpoint, yscreen)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(5))).release().perform();
                JavascriptExecutor js = (JavascriptExecutor) driver;
                HashMap<String, String> scrollObject = new HashMap<String, String>();
                scrollObject.put("direction", "right");
                //.moveTo(point(xendpoint, yscreen)).release().perform();
            }
        }
        try {
            element=driver.findElementByXPath("//android.widget.LinearLayout/child::android.widget.ImageView/following-sibling::android.widget.TextView[@text='"+gameSection+"']");
            if(element.isDisplayed()){
                logger.info("Tap on game section");
                clickOnElement(element);
                softAssert(element.getText().equalsIgnoreCase(gameSection),gameSection+" did not match");
                softAssert(Boolean.parseBoolean(element.getAttribute("selected")),element.getText()+" is not selected");
                return;
            }}catch (Exception exception){
            softAssert(Boolean.FALSE,gameSection+" Element didn't displayed");
        }
    }

    /**
     * This method perform scroll vertical action
     * @param driver
     */
    public void scrollUp(AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int startVerticalY = (int) (size.height * 0.8);
        int endVerticalY = (int) (size.height * 0.21);
        int startVerticalX = (int) (size.width / 2);
        try {
            logger.info("Performing scroll action");
            new TouchAction<>(driver).longPress(point(startVerticalX, startVerticalY))
                    .moveTo(point(startVerticalX, endVerticalY)).release().perform();
        } catch (Exception e) {
            logger.info("not scrolled");
        }
    }

    /**
     * @param driver
     * @param element
     * This method is used to provide explicit wait
     */
    public void awaitForElement(AppiumDriver driver, WebElement element) {
        logger.info("waiting for visibility of Element: " + element);
        WebDriverWait wait = new WebDriverWait(driver, 12);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            wait.until(ExpectedConditions.visibilityOf(element));
        }
    }

    /**
     * @param driver
     * @param visibleText
     * click on the element by text
     */
    public void clickOnElementByVisibleText(AppiumDriver driver, String visibleText) {
        logger.info("Clicking on element by using visible text: " + visibleText);
        driver.findElement(By.xpath("//*[@text='" + visibleText + "']")).click();
    }

    /**
     * @param driver
     * @param howManySwipes number of swipes
     * @param text
     * scroll till the element's text is visible
     */
    public void scrollUpTillVisibilityOfElement(AppiumDriver driver, int howManySwipes, String text) {
        logger.info("scroll up to visibility of an element");
        Dimension size = driver.manage().window().getSize();
        int startVerticalY = (int) (size.height * 0.8);
        int endVerticalY = (int) (size.height * 0.21);
        int startVerticalX = (int) (size.width / 2);
        for (int i = 0; i < howManySwipes; i++)
        {
            try {
                logger.info("click on element");
                clickOnElementByVisibleText(driver, text);
                break;
            } catch (Exception e) {
                logger.info("performing swipe action");
                new TouchAction<>(driver).longPress(point(startVerticalX, startVerticalY))
                        .moveTo(point(startVerticalX, endVerticalY)).release().perform();
            }
        }
    }

    /**
     * Logger
     */
    public static void logger() {
        logger = logger == null ? Logger.getLogger(CommonAction.class) : logger;
        try {
            PropertyConfigurator.configure(new FileInputStream(FilePath.LOG4J_PROPERTIES));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param xpathLocator
     * @param args acts as an array
     *  This method generates a xpath
     */
    public String generateXpath(String xpathLocator, Object ...args){
        String xpathExpression=null;
        for(int i=0;i<args.length;i++){
            if(xpathExpression==null)
                xpathExpression=xpathLocator.replace("{"+i+"}",(CharSequence) args[i]);
        }
        logger.info("Generated an xpath "+xpathExpression);
        return xpathExpression;
    }

    /**
     * @param driver
     * @param xpath
     * This method generates a webelement
     */
    public WebElement generateWebElement(AppiumDriver driver,String xpath){
        WebDriverWait wait= new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        WebElement element= driver.findElementByXPath(xpath);
        logger.info("Generated a web element "+element);
        return element;
    }

    /**
     * @param driver
     * This method swipes the profile menu bar
     */
    public void swipeAction(AppiumDriver driver)
    {
        Dimension windowSize = driver.manage().window().getSize();
        int yscreen= (int) (windowSize.getHeight()*0.5);
        int xstart=(int)(windowSize.getWidth()*0.8);
        int xend=(int)(windowSize.getWidth()*0.2);
        logger.info("Performing the swipe action");
        TouchAction touch = new TouchAction(driver);
        touch.longPress(point(xstart, yscreen)).moveTo(point(xend, yscreen)).release().perform();
    }

}
