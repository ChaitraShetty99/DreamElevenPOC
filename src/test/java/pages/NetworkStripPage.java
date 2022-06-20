package pages;

import actions.CommonAction;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
public class NetworkStripPage extends CommonAction
{
    AppiumDriver driver;
    public NetworkStripPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @FindBy(xpath = "//android.widget.LinearLayout/android.widget.TextView[@text='nagdhoni2197 is playing']")
    public WebElement stripNetwork;

    @FindBy(xpath = "//*[contains(@text,'Your network in this match')]")
    public WebElement afterOpeningNetworkStrip;

    @FindBy(xpath = "//*[@text='Your network in this match (1)']/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[@clickable='true']")
    public WebElement closeBtn;

    public void clickOnNetworkStrip() {
        logger.info("Swipes till network strip and click");
        scrollUpTillVisibilityOfElement(driver,8," is playing");
        clickOnElement(stripNetwork);
    }
    //nagdhoni2197

    public void closeNetworkStrip() {
        logger.info("Close the pop ups of network strip");
        clickOnElement(closeBtn);
    }

    public void validateNetworkStrip(String expected1) {
        logger.info("verifying the network strip pop up");
        awaitForElement(driver,afterOpeningNetworkStrip);
        String actualTextAfterOpeningNetworkStrip = afterOpeningNetworkStrip.getText();
        softAssert(actualTextAfterOpeningNetworkStrip,expected1,"Actual Network strip mismatches");
        //String expectedTextAfterOpeningNetworkStrip = "Your network in this match (1)";
        //softAssert(actualTextAfterOpeningNetworkStrip,expectedTextAfterOpeningNetworkStrip,"Network strip did not match");

    }
}
