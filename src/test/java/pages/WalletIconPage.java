package pages;

import actions.CommonAction;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.DecimalFormat;

public class WalletIconPage extends CommonAction
{
   public AppiumDriver driver;
        public WalletIconPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(xpath = "//*[@content-desc='Your Account Balance']")
    public WebElement walletIcon;

    @FindBy(xpath = "//*[@text='ADD CASH']")
    public WebElement addCashButton;

    @FindBy(xpath = "//*[@text='WINNINGS']")
    public MobileElement winningsText;

    @FindBy(xpath = "//*[@text='CASH BONUS']")
    public MobileElement cashBonusText;

    public void clickOnWalletIcon() {
        logger.info("Tap on wallet icon");
        walletIcon.click();
    }



}
