package pages;

import actions.CommonAction;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonAction {
    AppiumDriver driver;

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(xpath = "//*[@text='Log in']")
    public WebElement loginButton;

    @FindBy(xpath = "//*[@resource-id='android:id/autofill_dataset_list']/*")
    public WebElement autofillSuggestion;

    @FindBy(xpath = "//*[@text='Email or mobile no']")
    public WebElement mobileOrEmailTextField;

    @FindBy(xpath = "//*[@text='NEXT']")
    public WebElement nextButton;

    @FindBy(xpath = "//*[@text='CONTINUE IN ENGLISH']")
    public WebElement continueInEnglish;

    public void clickOnLoginButton(){
        logger.info("Tap on login button");
        clickOnElement(loginButton);
    }

    public void clickOnNextButton(){
        logger.info("Tap on next button");
        clickOnElement(nextButton);
    }

    public void enterMobileOrEmailTextField(String text){
        logger.info("enter the mobile and email.");
        try {
            if(autofillSuggestion.isDisplayed()){
                clickOnElement(autofillSuggestion);
                awaitForElement(driver,mobileOrEmailTextField);
                type(mobileOrEmailTextField,text);
            }
        } catch (Exception e) {
            type(mobileOrEmailTextField,text);
        }
    }
    public void clickOnContinueInEnglish(){
        logger.info("Tap on continue english button");
        clickOnElement(continueInEnglish);
    }


}
