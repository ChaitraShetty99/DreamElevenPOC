package pages;

import actions.CommonAction;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import base.FileUtility;
import java.io.IOException;

public class DreamStreaksPage extends CommonAction {

    AppiumDriver driver;
    public DreamStreaksPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(xpath = "//*[@text='Dream Streaks']")
    public WebElement fetchDreamStreaksText;

    @FindBy(xpath = "//*[@content-desc='get-back-header-icon']")
    public WebElement tapOnBackIcon;

    public void validateDreamStreaksPage (String expectedDreamStreaksTitleText) throws IOException {
        String actualDreamStreakTitleText=fetchDreamStreaksText.getText();
        takeScreenshotAtEndOfTest(driver, "validateDreamStreaksPage");
        softAssert(actualDreamStreakTitleText,expectedDreamStreaksTitleText,"Dream streak did not match");
        logger.info("Validated DreamStreaks page Successfully");
        awaitForElement(driver,tapOnBackIcon);
        clickOnElement(tapOnBackIcon);
    }


}
