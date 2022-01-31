package Utilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static Base.TestBase.driver;

public class Listeners implements ITestListener {

    ExtentReports extent = ExtentReporterNG.extentReportGenerator();
    ExtentTest test;

    public void onTestStart(ITestResult result) {

        test = extent.startTest(result.getName());

    }

    public void onTestSuccess(ITestResult result) {

        test.log(LogStatus.PASS, result.getName() + " Scenario is passed.");

    }

    public void onTestFailure(ITestResult result) {

        test.log(LogStatus.FAIL, result.getName() + " Scenario is failed.");
        test.log(LogStatus.FAIL, "Fail Details: "+result.getThrowable());
        String screenshotPath = null;
        try {
            screenshotPath = TestUtilities.getScreenshot(driver, result.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));

    }

    public void onFinish(ITestContext context) {

        extent.flush();

    }

}