package pages;

import actions.CommonAction;
import base.BaseTest;
import base.FileUtility;
import com.google.j2objc.annotations.Weak;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import javax.swing.*;


public class ProfilePage extends CommonAction
{
    AppiumDriver driver;
    public ProfilePage(AppiumDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @FindBy(xpath = "//android.view.ViewGroup/child::android.view.ViewGroup/child::android.widget.ImageView")
    public MobileElement profileIcon;
    @FindBy(xpath = "//android.widget.CheckedTextView[@text='My Balance']")
    public WebElement myBalance;
    @FindBy(xpath = "//android.widget.TextView[@text='Feed']")
    public WebElement feed;
    @FindBy(xpath = "//android.widget.TextView[@text='My Level']")
    public WebElement myLevel;
    @FindBy(xpath = "//android.widget.CheckedTextView[@text='Find People']")
    public WebElement findPeople;
    @FindBy(xpath = "//android.widget.CheckedTextView[@text='How to Play']")
    public WebElement howToPlay;
    @FindBy(xpath = "//android.widget.TextView[@text='Help & Support']")
    public WebElement helpAndSupport;

    public void tapOnProfileImage() {
        logger.info("Tap on profile icon");
        clickOnElement(profileIcon);
    }
        public void validateTheMenuBar(String expected1,String expected2,String expected3,String expected4,String expected5,String expected6) {
        String actualBalanceText = myBalance.getText();
        softAssert(actualBalanceText, expected1,"Actual balance mismatches");

        String actualMyLevelText = myLevel.getText();
        softAssert(actualMyLevelText, expected2,"My level text mismatches");

        String actualFindPeopleText = findPeople.getText();
        softAssert(actualFindPeopleText, expected3,"Find people text mismatches");

        String actualFeedText = feed.getText();
        softAssert(actualFeedText, expected4,"Feed text mismatches");

        String actualHowToPlayText = howToPlay.getText();
        softAssert(actualHowToPlayText, expected5,"How to play text mismatches");

        String ActualHelpSupportText = helpAndSupport.getText();
        softAssert(ActualHelpSupportText, expected6,"Help and support mismatches");
    }
}
