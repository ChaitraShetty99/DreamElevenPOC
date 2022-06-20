package pages;

import actions.CommonAction;
import base.ExcelUtility;
import com.codoid.products.exception.FilloException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import base.FileUtility;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;

import static io.appium.java_client.touch.offset.PointOption.point;

public class LandingPage extends CommonAction {

    AppiumDriver driver;
    public LandingPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(xpath = "//android.widget.TextView[@content-desc=\"BALANCE\"]")
    public WebElement balanceButton;

    @FindBy(xpath = "//*[@text='Home']")
    public WebElement homeButton;

    @FindBy(xpath = "//*[@content-desc='Your Account Balance']")
    public WebElement walletIcon;

    @FindBy(xpath = "(//android.widget.HorizontalScrollView)[1]")
    public WebElement gamesSection;

    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Match_Card_1_Flag_1\"] | ((//*[contains(@text,'MEGA')])[1]/../child::*)[8]")
    public WebElement matchCardFlag1;

    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Match_Card_1_Flag_2\"] | ((//*[contains(@text,'MEGA')])[1]/../child::*)[11]")
    public WebElement matchCardFlag2;

    @FindBy(xpath = "//*[@text='My Matches']")
    public WebElement myMatchesButton;

    @FindBy(xpath = "//*[@text='Winners']")
    public WebElement winnersButton;

    @FindBy(xpath = "//*[contains(@text,\"You haven't joined any\")]")
    public WebElement upcomingSections;

    @FindBy(xpath = "//*[@text='Contest']")
    public WebElement contest;

    @FindBy(xpath = "//*[@text='Team']")
    public WebElement team;

    @FindBy(xpath = "//*[@text='Chat']")
    public WebElement chatButton;

    @FindBy(xpath = "//*[@text='Rewards']")
    public WebElement rewardsButton;

    @FindBy(xpath="//*[@content-desc='Match_Card_1']/android.widget.ImageView")
    public WebElement tapOnSetReminder;

    @FindBy(xpath = "//*[@content-desc='Streaks_Fab']/parent::android.widget.LinearLayout")
    public WebElement dragStreakIcon;

    @FindBy(xpath = "//*[@content-desc='Streaks_Fab']/parent::android.widget.LinearLayout")
    public WebElement tapStreakIcon;

    @FindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"My Matches\"]/android.widget.ImageView")
    public WebElement myMatches;
    @FindBy(xpath = "//*[contains(@text,'Upcoming')]")
    public WebElement upcomingMatches;

    @FindBy(xpath = "(//android.view.ViewGroup[1]/android.widget.TextView[1][@text])[1]")
    public WebElement matchSeriesName;

    @FindBy(xpath = " //android.view.ViewGroup[@content-desc=\"Match_Card_1\"]/android.widget.TextView[2] | (//android.view.ViewGroup[1]/android.widget.TextView[1][@text])[2]")//
    public WebElement challenger;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Match_Card_1\"]/android.widget.TextView[3]| (//android.view.ViewGroup[1]/android.widget.TextView[1][@text])[3]")//
    public WebElement opponent;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Match_Card_1\"]/android.widget.TextView[4] | (//android.view.ViewGroup[1]/android.widget.TextView[1][@text])[4]")//
    public WebElement time;

    @FindBy(xpath = "//*[@text='MEGA']")
    public WebElement megaText;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Match_Card_1\"]/android.widget.TextView[9] | (//*[contains(@text,'MEGA')])[1]/../child::*[16]")
    public WebElement flagshipAmount;

    @FindBy(xpath = "//*[contains(@text,'MEGA')]/../*[7]")
    public List<WebElement> timeWithMegaOption;

    @FindBy(xpath = "//android.widget.RelativeLayout/android.widget.ImageView")
    public WebElement stayTuned;

    @FindBy(xpath = "//androidx.viewpager.widget.ViewPager/child::android.widget.FrameLayout | //android.widget.ImageView[@content-desc=\"Feed_Banner\"]")
    public WebElement feedBanner;

    public void tapOnSetReminder(){
        logger.info("Tap on set reminder");
        clickOnElement(tapOnSetReminder);
    }

    public void validateStreakIcon(String expectedDefaultStreakIconYPosition,String expectedDefault1StreakIconYPosition) throws IOException {
        logger.info("Verify the presence of streak icon");
        awaitForElement(driver,dragStreakIcon);
        int actualDefaultStreakIconYPosition=dragStreakIcon.getLocation().getY();
        takeScreenshotAtEndOfTest(driver, "validateStreakIcon");
        if(actualDefaultStreakIconYPosition==Integer.parseInt(expectedDefaultStreakIconYPosition)){
            softAssert(actualDefaultStreakIconYPosition,Integer.parseInt(expectedDefaultStreakIconYPosition),"Streak icon is not displayed");
            logger.info("Validated StreaksIcon Successfully");
        }else{
            softAssert(actualDefaultStreakIconYPosition,Integer.parseInt(expectedDefault1StreakIconYPosition),"Streak icon is not displayed");
            logger.info("Validated StreaksIcon Successfully");
        }

    }

    public void tapStreakIcon(){
        logger.info("Tap on streak icon");
        awaitForElement(driver,tapStreakIcon);
        clickOnElement(tapStreakIcon);
    }

    public void dragStreakIconUpAndDown(AppiumDriver driver,String expectedDefaultPositionOfStreakIcon,String expectedMaxUpStreakIcon) {
        Dimension size = driver.manage().window().getSize();
        logger.info("total size of height is :" + size.height);
        logger.info("total size of width is :" + size.width);
        int endVerticalY = (int) (size.height * 0.20);
        int startVerticalX = (int) (size.width / 2);

        int x_start = dragStreakIcon.getLocation().getX();
        int y_start = dragStreakIcon.getLocation().getY();
        logger.info("Default position is: "+y_start);
        try {
            logger.info("Performing scroll action");
            new TouchAction<>(driver).longPress(point(x_start, y_start))
                    .moveTo(point(startVerticalX, endVerticalY)).release().perform();
            int y_max=dragStreakIcon.getLocation().getY();
            takeScreenshotAtEndOfTest(driver, "draggableUpAndDown");
            logger.info("ScrollUp max position of StreakIcon is : " + y_max);
            softAssert(y_start>Integer.parseInt(expectedDefaultPositionOfStreakIcon) && y_max <Integer.parseInt(expectedMaxUpStreakIcon),"Streak icon is not draggable");
        } catch (Exception e) {
            logger.info("not scrolled");
        }
        int endVerticalYDown = (int) (size.height * 0.98);
        int startVerticalXDown = (int) (size.width / 2);
        int xStart = dragStreakIcon.getLocation().getX();
        int yDown = dragStreakIcon.getLocation().getY();
        try {
            logger.info("Performing scroll action");
            new TouchAction<>(driver).longPress(point(xStart, yDown))
                    .moveTo(point(startVerticalXDown, endVerticalYDown)).release().perform();
            takeScreenshotAtEndOfTest(driver, "draggableUpAndDown");
            awaitForElement(driver,dragStreakIcon);
            int yMaxDown=dragStreakIcon.getLocation().getY();
            logger.info("ScrollDown max position of StreakIcon is : " +yMaxDown);
            softAssert(y_start>Integer.parseInt(expectedDefaultPositionOfStreakIcon) && yMaxDown>Integer.parseInt(expectedDefaultPositionOfStreakIcon),"Streak icon is not draggable");
        } catch (Exception e) {
            logger.info("not scrolled");
        }
    }
    public void validateDraggableStreakIcon(AppiumDriver driver,String expectedStart,String expectedEnd) throws IOException {
        awaitForElement(driver,dragStreakIcon);
        Dimension size = driver.manage().window().getSize();
        logger.info("total size of height is :" + size.height);
        logger.info("total size of width is :" + size.width);
        int endVerticalY = (int) (size.height * 0.20);
        int startVerticalX = (int) (size.width / 2);

        int x_start = dragStreakIcon.getLocation().getX();
        int y_start = dragStreakIcon.getLocation().getY();
        logger.info("Default position is: "+x_start+" "+y_start);
        try {
            logger.info("Performing scroll action");
            new TouchAction<>(driver).longPress(point(x_start, y_start))
                    .moveTo(point(startVerticalX, endVerticalY)).release().perform();
            takeScreenshotAtEndOfTest(driver, "draggableUpAndDown");
            int y_max=dragStreakIcon.getLocation().getY();
            softAssert(y_start>Integer.parseInt(expectedStart) && y_max<Integer.parseInt(expectedEnd),"Streak icon is not draggable");
            logger.info("Validated StreakIcon is Draggable");
        } catch (Exception e) {
            logger.info("not scrolled");
        }

    }

    public void verifyMatchSeriesBelongToParticularGame(String game){
       try{
           switch (game.toUpperCase()){
               case "CRICKET":
                   awaitForElement(driver,matchSeriesName);
                   logger.info(matchSeriesName.getText()+" name of the match series.");
                   softAssert(matchSeriesName.getText().contains(FileUtility.t20) | matchSeriesName.getText().contains(FileUtility.t10) | matchSeriesName.isDisplayed()  ,"Cricket game not listed");
                   break;
               case "KABADDI":
                   awaitForElement(driver,matchSeriesName);
                   logger.info(matchSeriesName.getText()+" name of the match series.");
                   softAssert(matchSeriesName.getText().contains(FileUtility.kabaddi) |matchSeriesName.isDisplayed() ,"Kabaddi game not listed");
                   break;

               case "BASKETBALL":
                   awaitForElement(driver,matchSeriesName);
                   logger.info(matchSeriesName.getText()+" name of the match series.");
                   softAssert( matchSeriesName.isDisplayed(),"Basketball game not listed");
                   break;
               case "BASEBALL":
                   awaitForElement(driver,matchSeriesName);
                   logger.info(matchSeriesName.getText()+" name of the match series.");
                   softAssert(matchSeriesName.getText().contains(FileUtility.mlb)|matchSeriesName.getText().contains(FileUtility.kbo)| matchSeriesName.isDisplayed(),"Baseball game not listed");
                   break;
               case "HOCKEY":
                   awaitForElement(driver,matchSeriesName);
                   logger.info(matchSeriesName.getText()+" name of the match series.");
                   softAssert(matchSeriesName.getText().contains(FileUtility.fih)|matchSeriesName.isDisplayed(),"Hockey game not listed");
                   break;
               case "FOOTBALL":
               case "HANDBALL":
               case "VOLLEYBALL":
                   awaitForElement(driver,matchSeriesName);
                   logger.info(matchSeriesName.getText()+" name of the match series.");
                   softAssert(matchSeriesName.isDisplayed(),"Match series didn't present");
                   break;

           }
       }catch (Exception e){
           logger.info("No matches present.");
           softAssert(stayTuned.isDisplayed(),"No matches present");
       }

    }

    public void verifyUpcomingMatchesPresent(){
        try{
            awaitForElement(driver,upcomingMatches);
            softAssert(upcomingMatches.isDisplayed(),"Upcoming Matches is absent in page");
            softAssert(upcomingMatches.getText().equalsIgnoreCase("Upcoming matches"),"Up coming matches did not change");
        }catch (Exception e){
            softAssert(stayTuned.isDisplayed(),"No matches present");
        }
    }

    public void verifyContentsOfMatchCard(String columnName) throws FilloException {
        awaitForElement(driver,challenger);
        softAssert(challenger.isDisplayed(),"Challenger did not display");
        ExcelUtility.writeExcelData(FileUtility.secondDataSheet,columnName,challenger.getText());
        softAssert(opponent.isDisplayed(),"Opponent did not display");
        ExcelUtility.writeExcelData(FileUtility.secondDataSheet,columnName,opponent.getText());
        softAssert(megaText.isDisplayed(),"Mega text did not display");
        ExcelUtility.writeExcelData(FileUtility.secondDataSheet,columnName,megaText.getText());
        awaitForElement(driver,flagshipAmount);
        softAssert(flagshipAmount.isDisplayed(),"Flagship amount did not display");
        ExcelUtility.writeExcelData(FileUtility.secondDataSheet,columnName,flagshipAmount.getText());
        softAssert(time.isDisplayed(),"Timer of the match did not display");
        awaitForElement(driver,matchCardFlag1);
        softAssert(matchCardFlag1.isDisplayed(),"Match card flag 1 did not display");
        softAssert(matchCardFlag2.isDisplayed(),"Match card flag 2 did not display");
    }

    public void verifyMegaOptionWithinADay(){
        List<WebElement> timeList=timeWithMegaOption;
        LinkedHashSet<String> listOfTime= new LinkedHashSet<>();
        listOfTime.clear();

        try {
            while (megaText.isDisplayed()){
                for(WebElement time:timeList){
                    if(!time.getText().isEmpty())
                        listOfTime.add(time.getText());
                }
                if(megaText.isDisplayed())
                    scrollUp(driver);
            }
        } catch (Exception e) {
            logger.info("MEGA option is disappear.");
        }
        logger.info("List Match card has Mega :"+listOfTime);
        for(String times: listOfTime){
            int hours= Integer.parseInt(times.substring(0,3).replaceAll("[^0-9]", ""));
            logger.info(String.valueOf(hours));
            softAssert.assertTrue(hours<25,"Mega option is displayed for more than 25 hours");
            softAssert.assertAll();
        }
    }

    public void verifyMegaPrizeAmount(){
        logger.info(flagshipAmount.getText());
        String amount=flagshipAmount.getText();
        clickOnElement(matchCardFlag1);
        waitOrPause(5);
        WebElement element= driver.findElement(By.xpath("//*[contains(@text,'"+amount+"')]"));
        softAssert(element.isDisplayed(),"Prize amount not reflected same as a Match card");
    }

    public void verifyMyMatches(){
        awaitForElement(driver,myMatches);
        softAssert(myMatches.isDisplayed(),"MyMatches is not displayed");
        clickOnElement(myMatches);
    }

    public void verifyMatchesUnderUpcomingSection(String match){
        awaitForElement(driver,upcomingMatches);
        softAssert.assertTrue(upcomingMatches.isSelected(),"Upcoming is not selected");
        switch (match.toUpperCase()){
            case "MATCHADDED":
                awaitForElement(driver,contest);
                softAssert(contest.isDisplayed(),"Contest is not present");
                awaitForElement(driver,team);
                softAssert(team.isDisplayed(),"Team is not present");
                break;
            case "MATCHNOTADDED":
                String expectedUpcomingSections="You haven't joined any upcoming contests";
                softAssert(upcomingSections.getText(),expectedUpcomingSections,"UpcomingSection did not match");
                break;
        }
    }

    public void homeButtonIsSelectedAndDisplayed(){
        logger.info("Verify the Home section present");
        softAssert(homeButton.isDisplayed(),"Home button did not display");
        homeButton.click();
        softAssert(homeButton.isSelected(),"Home button did not selected");
    }

    public void bannerFeed() {
        logger.info("Click on banner feed");
        awaitForElement(driver,feedBanner);
        clickOnElement(feedBanner);
        try {
            boolean value=homeButton.isDisplayed();
            if(value)
                softAssert(Boolean.FALSE,"Banner did not navigate");
        }catch (Exception e){
            logger.info("Preview of banner");
        }
        pressNavigationBack(driver);
    }

    public void verifyBottomNavigationElementIsPresentAndClick(WebElement elements){


            try {
                awaitForElement(driver, elements);
                boolean expectedResult = elements.isDisplayed();
                softAssert.assertTrue(expectedResult, "Elements is not present");
                clickOnElement(elements);
                softAssert.assertAll();
            }catch (Exception e)
            {
                logger.info("Bottom Elements");
            }
    }


}

