package pages;

import actions.CommonAction;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends CommonAction {

    AppiumDriver driver;

    public RegistrationPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(xpath = "//*[@text='REGISTER']")
    public WebElement registerButton;

    @FindBy(xpath = "//*[@text='Mobile no.']")
    public WebElement mobileNoTextField;

    @FindBy(xpath = "//*[@text='English']")
    public WebElement selectEnglishLanguage;
    @FindBy(xpath = "//*[@text='CONTINUE']")
    public WebElement continueButton;

    public void clickOnRegisterButton(){
        logger.info("Tap on register button");
        clickOnElement(registerButton);
    }

    public void enterMobileNumber(String text){
        logger.info("Enter the mobile number");
        type(mobileNoTextField,text);
    }




}
