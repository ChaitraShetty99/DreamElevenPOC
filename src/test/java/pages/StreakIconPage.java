package pages;

import actions.CommonAction;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StreakIconPage extends CommonAction {

    AppiumDriver driver;
    public StreakIconPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(xpath = "//*[@text='GOT IT']")
    public WebElement tapOnGotItButton;

    public void tapOnGotItButton(){
        logger.info("Tap on got it button");
        awaitForElement(driver,tapOnGotItButton);
        clickOnElement(tapOnGotItButton);
    }

}
