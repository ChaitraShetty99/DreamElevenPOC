package tests;

import TestAnnotation.TestInfo;
import base.BaseTest;
import base.ExcelUtility;
import base.FileUtility;
import com.codoid.products.exception.FilloException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import java.io.IOException;
import java.util.ArrayList;

@Listeners(base.Listeners.class)
public class Dream11POC extends BaseTest {

    @TestInfo(testcaseID = "TC01", testCaseName = "Verify Notification option from top right corner of Home page.")
    @Test(description = "In this test method, verified the Notification Icon and its Content")
    public void verifyNotificationIconTest() throws FilloException, InterruptedException, IOException, FilloException {
        // Extent Report - generates a report after execution of a method
        extentTest = extentReports.createTest("verifyNotificationIconTest");

        // Fetches current executing test case id
        String testCaseId = getTestCaseId(Dream11POC.class, "verifyNotificationIconTest");

        //      Fetches the data from Excel sheet by implementing excel utility
        String expectedNotification1= ExcelUtility.getExcelData(sheetName,testCaseId,"data1");
        String expectedNotification2=ExcelUtility.getExcelData(sheetName,testCaseId,"data2");
        String expectedNotification3=ExcelUtility.getExcelData(sheetName,testCaseId,"data3");
        String expectedNotification4=ExcelUtility.getExcelData(sheetName,testCaseId,"data4");
        String backButtonXpath=ExcelUtility.getExcelData(sheetName,testCaseId,"data5");

        //      Generates the xpath at runtime and convert it to web element
        String notificationBellIconXpath=generateXpath(FileUtility.content_desc,expectedNotification4);
        WebElement notificationBellIcon=generateWebElement(driver,notificationBellIconXpath);

        //      By the runtime xpath notification bell icon tapped      */
        NotificationPage notificationPage = new NotificationPage(driver);
        clickOnElement(notificationBellIcon);

        String notificationTitleXpath=generateXpath(FileUtility.text,expectedNotification4);
        WebElement notificationTitle= generateWebElement(driver,notificationTitleXpath);

        //      Verified notification page through assertion
        softAssert(notificationTitle.getText(), expectedNotification4,"Validated Notification Page Successfully");
        logger.info("Presence of notification page verified");

        //      Fetch the notification list and verified few list from it
        ArrayList<String> actualNotification= notificationPage.fetchNotificationData(1);
        notificationPage.validateTheNotification(actualNotification,expectedNotification1,expectedNotification2,expectedNotification3);

        String notificationBackButtonXpath=generateXpath(FileUtility.text+backButtonXpath,expectedNotification4);
        WebElement notificationBackButton= generateWebElement(driver,notificationBackButtonXpath);

        //      Tap on notification back button and verified presence of landing page
        clickOnElement(notificationBackButton);


    }


    @TestInfo(testcaseID = "TC02", testCaseName = "Verify Switch between the Sports tabs in home page.")
    @Test(description = "In this test method, verified switching to different games and validated that corresponding match cards")
    public void verifySwitchBetweenSportsTest() throws FilloException {
        //      Extent Report - generates a report after execution of a method      
        extentTest = extentReports.createTest("verifySwitchBetweenSportsTest");

        // Fetches current executing test case id
        String testCaseId = getTestCaseId(Dream11POC.class, "verifySwitchBetweenSportsTest");

        String cricketGame=ExcelUtility.getExcelData(sheetName,testCaseId,"data1");
        String kabaddiGame=ExcelUtility.getExcelData(sheetName,testCaseId,"data2");
        String footballGame=ExcelUtility.getExcelData(sheetName,testCaseId,"data3");
        String basketballGame=ExcelUtility.getExcelData(sheetName,testCaseId,"data4");
        String baseballGame=ExcelUtility.getExcelData(sheetName,testCaseId,"data5");
        String handballGame=ExcelUtility.getExcelData(sheetName,testCaseId,"data6");
        String hockeyGame=ExcelUtility.getExcelData(sheetName,testCaseId,"data7");
        String volleyballGame=ExcelUtility.getExcelData(sheetName,testCaseId,"data8");

        //      Object creation     
        LandingPage landingPage = new LandingPage(driver);

        //      Click On Cricket and verify cricket is selected        
        scrollAndClick(driver, 5, cricketGame);

        //      Verify upcoming matches in cricket game        
        landingPage.verifyUpcomingMatchesPresent();

        //      Verifying whether the match belongs to respective game      
        landingPage.verifyMatchSeriesBelongToParticularGame(cricketGame);

        //      Click On Kabaddi and verify kabaddi is selected        
        scrollAndClick(driver, 5, kabaddiGame);

        //      Verify upcoming matches in kabaddi game        
        landingPage.verifyUpcomingMatchesPresent();

        //      Verifying whether the match belongs to respective game      
        landingPage.verifyMatchSeriesBelongToParticularGame(kabaddiGame);

        //      Click On Football and verify football is selected      
        scrollAndClick(driver, 5, footballGame);

        //      Verify upcoming matches in football game       
        landingPage.verifyUpcomingMatchesPresent();

        //      Verifying whether the match belongs to respective game     
        landingPage.verifyMatchSeriesBelongToParticularGame(footballGame);

        //      Click On Basketball and verify basketball is selected      
        scrollAndClick(driver, 5, basketballGame);

        //      Verify upcoming matches in basketball game     
        landingPage.verifyUpcomingMatchesPresent();

        //      Verifying whether the match belongs to respective game     
        landingPage.verifyMatchSeriesBelongToParticularGame(basketballGame);

        //      Click On Baseball and verify baseball is selected      
        scrollAndClick(driver, 5, baseballGame);

        //      Verify upcoming matches in baseball game       
        landingPage.verifyUpcomingMatchesPresent();

        //      Verifying whether the match belongs to respective game      
        landingPage.verifyMatchSeriesBelongToParticularGame(baseballGame);

        //      Click On Hockey and verify hockey is selected      
        scrollAndClick(driver, 5, hockeyGame);

        //      Verify upcoming matches in hockey game     
        landingPage.verifyUpcomingMatchesPresent();
        landingPage.verifyMatchSeriesBelongToParticularGame(hockeyGame);

        //      Click On Handball and verify handball is selected      
        scrollAndClick(driver, 5, handballGame);

        //      Verify upcoming matches in handball game       
        landingPage.verifyUpcomingMatchesPresent();

        //      Verifying whether the match belongs to respective game     
        landingPage.verifyMatchSeriesBelongToParticularGame(handballGame);

        //      Click On Volleyball and verify volleyball is selected      
        scrollAndClick(driver, 5, volleyballGame);

        //      Verify upcoming matches in volleyball game     
        landingPage.verifyUpcomingMatchesPresent();

        //      Verifying whether the match belongs to respective game     
        landingPage.verifyMatchSeriesBelongToParticularGame(volleyballGame);
    }

    @TestInfo(testcaseID = "TC03", testCaseName = "Verify tap on Feed banners in each Sports, landing of banner is as per the content of the banner")
    @Test(description = "In this test method, Verified the different sport and  banner feed.")
    public void verifyTapOnSportsTest() throws FilloException {
        //      Extent Report - generates a report after execution of a method     
        extentTest = extentReports.createTest("verifyTapOnSportsTest");

        // Fetches current executing test case id
        String testCaseID = getTestCaseId(Dream11POC.class, "verifyTapOnSportsTest");
        String testCaseId=ExcelUtility.getExcelData(sheetName,testCaseID,"data1");
        String cricketGame=ExcelUtility.getExcelData(sheetName,testCaseId,"data1");
        String kabaddiGame=ExcelUtility.getExcelData(sheetName,testCaseId,"data2");
        String footballGame=ExcelUtility.getExcelData(sheetName,testCaseId,"data3");
        String basketballGame=ExcelUtility.getExcelData(sheetName,testCaseId,"data4");
        String baseballGame=ExcelUtility.getExcelData(sheetName,testCaseId,"data5");
        String handballGame=ExcelUtility.getExcelData(sheetName,testCaseId,"data6");
        String hockeyGame=ExcelUtility.getExcelData(sheetName,testCaseId,"data7");
        String volleyballGame=ExcelUtility.getExcelData(sheetName,testCaseId,"data8");

        //      Object Creation        
        LandingPage landingPage = new LandingPage(driver);

        //      Scroll to cricket element and click on banner return to landing page and verify in landing page     
        scrollAndClick(driver,5,cricketGame);
        landingPage.bannerFeed();
        landingPage.homeButtonIsSelectedAndDisplayed();

        //      Scroll to kabaddi element and click on banner return to landing page and verify in landing page     
        scrollAndClick(driver,5,kabaddiGame);
        landingPage.bannerFeed();
        landingPage.homeButtonIsSelectedAndDisplayed();

        //      Scroll to football element and click on banner return to landing page and verify in landing page        
        scrollAndClick(driver,5,footballGame);
        landingPage.bannerFeed();
        landingPage.homeButtonIsSelectedAndDisplayed();

        //      Scroll to basketball element and click on banner return to landing page and verify in landing page      
        scrollAndClick(driver,5,basketballGame);
        landingPage.bannerFeed();
        landingPage.homeButtonIsSelectedAndDisplayed();

        //      Scroll to baseball element and click on banner return to landing page and verify in landing page        
        scrollAndClick(driver,5,baseballGame);
        landingPage.bannerFeed();
        landingPage.homeButtonIsSelectedAndDisplayed();

        //      Scroll to hockey element and click on banner return to landing page and verify in landing page      
        scrollAndClick(driver,5,hockeyGame);
        landingPage.bannerFeed();
        landingPage.homeButtonIsSelectedAndDisplayed();

        //      Scroll to handball element and click on banner return to landing page and verify in landing page        
        scrollAndClick(driver,5,handballGame);
        landingPage.bannerFeed();
        landingPage.homeButtonIsSelectedAndDisplayed();

        //      Scroll to volleyball element and click on banner return to landing page and verify in landing page      
        scrollAndClick(driver,5,volleyballGame);
        landingPage.bannerFeed();
        landingPage.homeButtonIsSelectedAndDisplayed();
    }

    @TestInfo(testcaseID = "TC06", testCaseName = "Test to verify mega contest text")
    @Test(description = "In this test script its verified that the MEGA label present within 24 hours time.")
    public void verifyMegaContestTextTest() throws FilloException {
        //      Extent Report - generates a report after execution of a method      
        extentTest = extentReports.createTest("verifyMegaContestTextTest");

        //      Fetches current executing test case id
        String testCaseId=getTestCaseId(Dream11POC.class,"verifyMegaContestTextTest");
        String testCaseID=ExcelUtility.getExcelData(sheetName,testCaseId,"data1");
        String kabaddiGame=ExcelUtility.getExcelData(sheetName,testCaseID,"data2");

        //      Object Creation     
        LandingPage landingPage = new LandingPage(driver);

        //      scroll and click on kabaddi game section and verify kabaddi game selected       
        scrollAndClick(driver, 7, kabaddiGame);

        //      Verifies the showed amount is reflected inside match card       
        landingPage.verifyMegaPrizeAmount();
        pressNavigationBack(driver);

        //      Verifies the MEGA text present within match card which occurs in 24 hours       
        landingPage.verifyMegaOptionWithinADay();
    }

    @TestInfo(testcaseID = "TC08", testCaseName = "Verify the Upcoming Section and My Matches Carousel are displayed")
    @Test(description = "In this test,Verifying the Upcoming Section and My Matches Carousel")
    public void verifyUpcomingSectionAndMyMatchesTest() throws FilloException {
        //      Extent Report - generates a report after execution of a method
        extentTest = extentReports.createTest("verifyUpcomingSectionAndMyMatchesTest");

        //      Fetches current executing test case id
        String testCaseId= getTestCaseId(Dream11POC.class,"verifyUpcomingSectionAndMyMatchesTest");
        String testCaseID= ExcelUtility.getExcelData(sheetName,testCaseId,"data3");
        String matchAdded=ExcelUtility.getExcelData(sheetName,testCaseId,"data1");
        String matchNotAdded=ExcelUtility.getExcelData(sheetName,testCaseId,"data2");
        String cricketGame=ExcelUtility.getExcelData(sheetName,testCaseID,"data1");
        String kabaddiGame=ExcelUtility.getExcelData(sheetName,testCaseID,"data2");
        String footballGame=ExcelUtility.getExcelData(sheetName,testCaseID,"data3");
        String basketballGame=ExcelUtility.getExcelData(sheetName,testCaseID,"data4");
        String baseballGame=ExcelUtility.getExcelData(sheetName,testCaseID,"data5");
        String handballGame=ExcelUtility.getExcelData(sheetName,testCaseID,"data6");
        String hockeyGame=ExcelUtility.getExcelData(sheetName,testCaseID,"data7");
        String volleyballGame=ExcelUtility.getExcelData(sheetName,testCaseID,"data8");

        //      Object Creation
        LandingPage landingPage = new LandingPage(driver);

        //      Verifying the MyMatches
        landingPage.verifyMyMatches();

        //      Verifying the MatchesUnderUpcomingSection
        scrollAndClick(driver, 5, cricketGame);
        landingPage.verifyMatchesUnderUpcomingSection(matchAdded);

        scrollAndClick(driver, 5, kabaddiGame);
        landingPage.verifyMatchesUnderUpcomingSection(matchNotAdded);

        scrollAndClick(driver, 5, footballGame);
        landingPage.verifyMatchesUnderUpcomingSection(matchNotAdded);

        scrollAndClick(driver, 5, basketballGame);
        landingPage.verifyMatchesUnderUpcomingSection(matchNotAdded);

        scrollAndClick(driver, 5, baseballGame);
        landingPage.verifyMatchesUnderUpcomingSection(matchNotAdded);

        scrollAndClick(driver, 5, handballGame);
        landingPage.verifyMatchesUnderUpcomingSection(matchNotAdded);

        scrollAndClick(driver, 5, volleyballGame);
        landingPage.verifyMatchesUnderUpcomingSection(matchNotAdded);

        scrollAndClick(driver, 5, hockeyGame);
        landingPage.verifyMatchesUnderUpcomingSection(matchNotAdded);
    }

    @TestInfo(testcaseID = "TC10", testCaseName = "Verify the content of match card")
    @Test(description = "In this test method, the contents of match card are validated.")
    public void verifyContentOfTheMatchCardTest() throws FilloException {
        //      Extent Report - generates a report after execution of a method
        extentTest = extentReports.createTest("verifyContentOfTheMatchCardTest");

        //      Fetches current executing test case id
        String testCaseId=getTestCaseId(Dream11POC.class,"verifyContentOfTheMatchCardTest");
        String testCaseID=ExcelUtility.getExcelData(sheetName,testCaseId,"data1");
        String kabaddiGame=ExcelUtility.getExcelData(sheetName,testCaseID,"data2");

        //      Object Creation
        LandingPage landingPage = new LandingPage(driver);
        scrollAndClick(driver, 5, kabaddiGame);
        landingPage.verifyUpcomingMatchesPresent();
        landingPage.verifyMatchSeriesBelongToParticularGame(kabaddiGame);

        //      Verifying the contents of match card like icons of 2 teams and their respective names
        landingPage.verifyContentsOfMatchCard(testCaseId);
    }

    @TestInfo(testcaseID = "TC11", testCaseName = "Verify on the tap of profile image, The side drawer should be opened")
    @Test(description = "In this test method, we are able to tap on the profileIcon and side drawer is opened & verified")
    public void verifyProfileTest() throws FilloException {
        //      Extent Report - generates a report after execution of a method      
        extentTest=extentReports.createTest("verifyProfileTest");

        String testCaseId = getTestCaseId(Dream11POC.class, "verifyProfileTest");
        String expectedMyBalanceText = ExcelUtility.getExcelData(sheetName,testCaseId,"data1");
        String expectedMyLevelText = ExcelUtility.getExcelData(sheetName,testCaseId,"data2");
        String expectedFindPeopleText = ExcelUtility.getExcelData(sheetName,testCaseId,"data3");
        String expectedFeedText = ExcelUtility.getExcelData(sheetName,testCaseId,"data4");
        String expeHowToPlayText = ExcelUtility.getExcelData(sheetName,testCaseId,"data5");
        String expectedHelpAndSupportText = ExcelUtility.getExcelData(sheetName,testCaseId,"data6");

        LandingPage landingPage = new LandingPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        /*
        tapping on the ProfileIcon
         */
        profilePage.tapOnProfileImage();
        /*
        validating the side drawer Menu through different menu list
         */
        profilePage.validateTheMenuBar(expectedMyBalanceText,expectedMyLevelText,expectedFindPeopleText,expectedFeedText,expeHowToPlayText,expectedHelpAndSupportText);
        swipeAction(driver);
        landingPage.homeButtonIsSelectedAndDisplayed();
    }


    @TestInfo(testcaseID = "TC12", testCaseName = "Verify the Set Reminder on the match card")
    @Test(description = "In this test method, verified the SetReminder Icon is clickable on MatchCard & fetched toggle ON & OFF text and validated.")//retryAnalyzer = base.Retry.class
    public void VerifySetReminderOnMatchCardTest() throws IOException, FilloException {
        //      Extent Report - generates a report after execution of a method      
        extentTest = extentReports.createTest("VerifySetReminderOnMatchCardTest");

        //      Fetches current executing test case id
        String testCaseId = getTestCaseId(Dream11POC.class, "VerifySetReminderOnMatchCardTest");

        String expectedSetRemindersTitleText= ExcelUtility.getExcelData(sheetName,testCaseId,"data1");
        String expectedToastMsgText=ExcelUtility.getExcelData(sheetName,testCaseId,"data2");

        //      Object Creation
        LandingPage landingPage = new LandingPage(driver);
        SetRemindersPage setRemindersPage = new SetRemindersPage(driver);

        //      Tapping SetReminder Icon inside match card
        landingPage.tapOnSetReminder();

        //      Checking whether SetReminder popup is displayed and validated
        setRemindersPage.validateSetReminderPage(expectedSetRemindersTitleText);

        //      Tapping on Match toggle ON and fetching displayed message
        setRemindersPage.toggleOnMatch(expectedToastMsgText);

        //      Tapping on Match toggle OFF and fetching displayed message
        setRemindersPage.toggleOffMatch(expectedToastMsgText);

        //      Tapping on Series toggle ON and fetching displayed message
        setRemindersPage.toggleOnSeries(expectedToastMsgText);

        //      Tapping on Series toggle OFF and fetching displayed message
        setRemindersPage.toggleOffSeries(expectedToastMsgText);

        //      Validating SetReminder Popup is closed
        setRemindersPage.closeSetRemindersPage();
    }

    @TestInfo(testcaseID = "TC15", testCaseName = "Verify the max draggable position of the streaks icon")
    @Test(description = "In this test method, verified the Maximum position of up & down of Draggable Streak/Icon.")
    public void verifyDraggablePositionOfStreaksIconTest() throws FilloException {
        //      Extent Report - generates a report after execution of a method      
        extentTest = extentReports.createTest("VerifyDraggablePositionOfStreaksIconTest");

        //      Fetches current executing test case id
        String testCaseId = getTestCaseId(Dream11POC.class, "verifyDraggablePositionOfStreaksIconTest");

        //      Fetches the data from Excel sheet by implementing excel utility
        String expectedDefaultPositionOfStreakIcon= ExcelUtility.getExcelData(sheetName,testCaseId,"data1");
        String expectedMaxUpStreakIcon=ExcelUtility.getExcelData(sheetName,testCaseId,"data2");

        //      Object Creation
        LandingPage landingPage = new LandingPage(driver);

        //      Checking Streak/Icon draggable or movable position of Maximum Up and Down
        landingPage.dragStreakIconUpAndDown(driver,expectedDefaultPositionOfStreakIcon,expectedMaxUpStreakIcon);
    }

    @TestInfo(testcaseID = "TC14", testCaseName = "Verify Gamification icon/Streaks")
    @Test(description = "In this test method, verified the Gamification Streak/Icon is clickable & Draggable.")
    public void verifyGamificationIconOrStreaksTest() throws IOException, FilloException {
        //      Extent Report - generates a report after execution of a method      
        extentTest = extentReports.createTest("verifyGamificationIconOrStreaksTest");

        //      Fetches current executing test case id
        String testCaseId = getTestCaseId(Dream11POC.class, "verifyGamificationIconOrStreaksTest");

        //      Fetches the data from Excel sheet by implementing excel utility
        String expectedDreamStreaksTitleText= ExcelUtility.getExcelData(sheetName,testCaseId,"data1");
        String expectedDefaultStreakIconYPosition=ExcelUtility.getExcelData(sheetName,testCaseId,"data2");
        String expectedDefault1StreakIconYPosition=ExcelUtility.getExcelData(sheetName,testCaseId,"data3");
        String expectedStart=ExcelUtility.getExcelData(sheetName,testCaseId,"data4");
        String expectedEnd=ExcelUtility.getExcelData(sheetName,testCaseId,"data5");
        //      Page Objects
        LandingPage landingPage = new LandingPage(driver);
        StreakIconPage streakIconPage = new StreakIconPage(driver);
        DreamStreaksPage dreamStreaksPage = new DreamStreaksPage(driver);

        //      Validating StreakIcon is displayed in landing page
        landingPage. validateStreakIcon(expectedDefaultStreakIconYPosition,expectedDefault1StreakIconYPosition);

        //      Tapping on StreakIcon and verifying whether it is clickable or not
        landingPage.tapStreakIcon();

        //      Tapping on Got-it button to verify DreamStreak page
        streakIconPage.tapOnGotItButton();

        //      Validating DreamStreakPage is Displayed
        dreamStreaksPage.validateDreamStreaksPage(expectedDreamStreaksTitleText);

        //      Verifying & validating StreakIcon is Draggable/movable
        landingPage.validateDraggableStreakIcon(driver,expectedStart,expectedEnd);

    }
}
