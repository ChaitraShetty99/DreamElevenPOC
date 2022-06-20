package cleanRepository;

import base.FilePath;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Calendar;

public class CleanDirectory {

    public static void deleteOlderFilesInDirectory(String directoryPath, long day){
        File directory= new File(directoryPath);
        File[] listOfFiles=directory.listFiles();
        if(listOfFiles!=null){
            for(File file: listOfFiles){
                if(file.isDirectory())
                    deleteOlderFilesInDirectory(file.getAbsolutePath(),day);
                else{
                    if(file.lastModified()<day){
                        file.delete();
                        System.out.println("All older files deleted successfully");
                    }
                }
            }
        }
    }

    @Test
    public static void deleteFile(){
        int numDays=-1;
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,numDays);
        long time=calendar.getTimeInMillis();
        String screenshotPath= FilePath.SCREEN_SHOT_PATH;
        String reportPath=FilePath.REPORT_PATH;
        String serverLogPath=FilePath.SERVERLOG;
        deleteOlderFilesInDirectory(screenshotPath,time);
        deleteOlderFilesInDirectory(reportPath,time);
        deleteOlderFilesInDirectory(serverLogPath,time);
    }

}
