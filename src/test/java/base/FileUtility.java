package base;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class FileUtility {

    public static String app;
    public static String appPackage;
    public static String appActivity;
    public static String UDID;
    public static String expectedHelpAndSupport;
    public static String expectedFeedText;
    public static String expectedMyBalanceText;
    public static String expectedMyLevelText;
    public static String expectedFindPeopleText;
    public static String expectedHowToPlayText;
    public static String mobileNumber;
    public static String content_desc;
    public static String text;
    public static String implicitWait;

    public static String kabaddi;
    public static String secondDataSheet;
    public static String t20;
    public static String t10;
    public static String mlb;
    public static String kbo;
    public static String fih;

    @Inject
    public FileUtility(
            @Named("app") String app,
            @Named("appPackage") String appPackage,
            @Named("appActivity") String appActivity,
            @Named("UDID") String UDID,
            @Named("expectedHelpAndSupport") String expectedHelpAndSupport,
            @Named("expectedFeedText") String expectedFeedText,
            @Named("expectedMyBalanceText") String expectedMyBalanceText,
            @Named("expectedMyLevelText") String expectedMyLevelText,
            @Named("expectedFindPeopleText") String expectedFindPeopleText,
            @Named("expectedHowToPlayText") String expectedHowToPlayText,
            @Named("mobileNumber") String mobileNumber,
            @Named("content_desc") String content_desc,
            @Named("text") String text,
            @Named("implicitWait") String implicitWait,
            @Named("secondDataSheet") String secondDataSheet,
            @Named("t20") String t20,
            @Named("t10") String t10,
            @Named("mlb") String mlb,
            @Named("kbo") String kbo,
            @Named("fih") String fih,
            @Named("kabaddi") String kabaddi

    ){
        FileUtility.app=app;
        FileUtility.appPackage=appPackage;
        FileUtility.appActivity=appActivity;
        FileUtility.UDID=UDID;
        FileUtility.expectedHelpAndSupport=expectedHelpAndSupport;
        FileUtility.expectedFeedText=expectedFeedText;
        FileUtility.expectedMyBalanceText=expectedMyBalanceText;
        FileUtility.expectedMyLevelText=expectedMyLevelText;
        FileUtility.expectedFindPeopleText=expectedFindPeopleText;
        FileUtility.expectedHowToPlayText=expectedHowToPlayText;
        FileUtility.mobileNumber=mobileNumber;
        FileUtility.content_desc=content_desc;
        FileUtility.text=text;
        FileUtility.implicitWait=implicitWait;
        FileUtility.secondDataSheet=secondDataSheet;
        FileUtility.t20=t20;
        FileUtility.t10=t10;
        FileUtility.mlb=mlb;
        FileUtility.kbo=kbo;
        FileUtility.fih=fih;
        FileUtility.kabaddi=kabaddi;

    }
}
