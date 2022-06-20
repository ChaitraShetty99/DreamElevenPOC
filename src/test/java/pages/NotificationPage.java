package pages;

import actions.CommonAction;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class NotificationPage extends CommonAction {

    AppiumDriver driver;

    public NotificationPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(xpath = "//android.view.ViewGroup/android.widget.TextView")
    public List<WebElement> fetchNotification;

    public ArrayList<String> fetchNotificationData(int scrollUpCount){
        ArrayList<String> notificationList = new ArrayList<>();
        notificationList.clear();
        for (int scrollUp = 0; scrollUp <= scrollUpCount; scrollUp++) {
            List<WebElement> list = fetchNotification;
            for (WebElement e : list) {
                String notificationTexts = e.getText();
                logger.info(notificationTexts);
                notificationList.add(notificationTexts);
            }
            if (scrollUp == (scrollUpCount - 1)) {
                scrollUp(driver);
            }
        }
        logger.info(notificationList.size()+"++++");
        logger.info(notificationList);
        return notificationList;
    }

    public void validateTheNotification(ArrayList<String> notificationList,String expectedNotification1,String expectedNotification2,String expectedNotification3 ){
        for (int i=0;i<notificationList.size();i++){
            softAssert(notificationList.contains(expectedNotification1)&& notificationList.contains(expectedNotification2)&& notificationList.contains(expectedNotification3) ,"Notification data did not present in the list");
        }
    }
    }


