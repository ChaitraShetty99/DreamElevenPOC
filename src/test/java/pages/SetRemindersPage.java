package pages;

import actions.CommonAction;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import base.FileUtility;
import java.io.IOException;

public class SetRemindersPage extends CommonAction {


    AppiumDriver driver;
    public SetRemindersPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(xpath = "//*[@text='Set Reminders']") //to validate
    public WebElement actualSetReminder;

    @FindBy(xpath="//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]")
    public WebElement tapOnMatch;

    @FindBy(xpath="//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]")
    public WebElement tapOnSeries;

    @FindBy(xpath="//*[@text='Set Reminders']/preceding-sibling::android.widget.ImageView")
    public WebElement closeSetReminderPage;

    @FindBy(xpath ="//*[contains(@text,'successfully changed')]")
    public WebElement toggleOn;

    @FindBy(xpath ="//*[contains(@text,'successfully changed')]")
    public WebElement toggleOff;

    public void toggleOnMatch(String expectedToastMsg) {
        clickOnElement(tapOnMatch);
        awaitForElement(driver,toggleOn);
        String actualToastMsg=toggleOn.getText();
        logger.info(actualToastMsg);
        softAssert(actualToastMsg,expectedToastMsg,"Toggle on is not displayed");
        logger.info("validated toggleOnMatch msg Successfully");
        awaitForElement(driver,tapOnMatch);
    }

    public void toggleOffMatch(String expectedToastMsg) {
        clickOnElement(tapOnMatch);
        awaitForElement(driver,toggleOff);
        String actualToastMsg=toggleOff.getText();
        logger.info(actualToastMsg);
        softAssert(actualToastMsg,expectedToastMsg,"Toggle off is not displayed");
        logger.info("validated toggleOffMatch msg Successfully");
        awaitForElement(driver,tapOnSeries);
    }

    public void toggleOnSeries(String expectedToastMsg) {
        clickOnElement(tapOnSeries);
        awaitForElement(driver,toggleOn);
        String actualToastMsg=toggleOn.getText();
        logger.info(actualToastMsg);
        softAssert(actualToastMsg,expectedToastMsg,"Toggle on is not displayed");
        logger.info("validated toggleOnSeries msg Successfully");
        awaitForElement(driver,tapOnSeries);
    }

    public void toggleOffSeries(String expectedToastMsg) {
        clickOnElement(tapOnSeries);
        awaitForElement(driver,toggleOff);
        String actualToastMsg=toggleOff.getText();
        logger.info(actualToastMsg);
        softAssert(actualToastMsg,expectedToastMsg,"Toggle off is not displayed");
        logger.info("validated toggleOffSeries msg Successfully");
        awaitForElement(driver,closeSetReminderPage);
    }

    public void closeSetRemindersPage() throws IOException {
        clickOnElement(closeSetReminderPage);
        logger.info("SetRemindersPage is closed Successfully");
        takeScreenshotAtEndOfTest(driver, "closeSetRemindersPage");

    }

    public void validateSetReminderPage(String expectedSetRemindersTitleText){
        awaitForElement(driver,actualSetReminder);
        String actualSetReminderTitleText=actualSetReminder.getText();
        softAssert(actualSetReminderTitleText, expectedSetRemindersTitleText,"Set reminder page is not validated");
        logger.info("validated SetReminders Page Successfully");
    }

}
