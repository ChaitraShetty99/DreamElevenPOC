package base;

import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static actions.CommonAction.logger;
import static actions.CommonAction.takeScreenshotAtEndOfTest;
import static base.BaseTest.driver;
import static base.BaseTest.extentTest;

public class Listeners implements ITestListener {


    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, result.getName() + " test case is passed.");
            logger.info(result.getName() + " test case is Passed.");
        }
    }
    @Override
    public void onTestFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(Status.FAIL, "Test case is failed " + result.getName());
            logger.info("Test case is failed " + result.getName());
            extentTest.log(Status.FAIL, "Test case failed because of " + result.getThrowable());
            logger.info("Test case failed because of " + result.getThrowable());
            String screenshotFilePath = null;
            try {
                screenshotFilePath = takeScreenshotAtEndOfTest(driver, result.getName());
                extentTest.log(Status.FAIL,"Path of the screen shot -"+screenshotFilePath);
                extentTest.log(Status.FAIL, "Test case is failed at " + extentTest.addScreenCaptureFromPath(screenshotFilePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (result.getStatus() == ITestResult.SKIP) {
            extentTest.log(Status.SKIP,result.getName()+" is skipped.");
            logger.info(result.getName()+" is skipped.");
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
