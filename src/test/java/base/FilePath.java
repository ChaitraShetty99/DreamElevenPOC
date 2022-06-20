package base;

public interface FilePath {

   String APK_PATH=System.getProperty("user.dir")+"/resources/dream11.apk";
   String LOG4J_PROPERTIES="./resources/log4j.properties";
   String MAIN_JS=System.getProperty("user.home")+"\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
   String NODE_JS="C:\\Program Files\\nodejs\\node.exe";
   String EXCEL_PATH=System.getProperty("user.dir")+"/src/test/testData/ExcelData.xlsx";
   String COMMON_DATA=System.getProperty("user.dir")+"/src/test/testData/commonData.properties";
   String SCREEN_SHOT_PATH=System.getProperty("user.dir") + "/screenshots/";
   String REPORT_PATH=System.getProperty("user.dir")+"/reports";
   String SERVERLOG="./ServerLogs/";



}
