package TestComponents;

import Resources.ExtentReporterNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extentReports = ExtentReporterNG.GetReportObject();
    ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {
        // Here we can keep the extend for each Test as it start first
        test = extentReports.createTest(result.getMethod().getMethodName());
        threadLocal.set(test); //Unique Thread id
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        threadLocal.get().log(Status.PASS, "Test is passed");
    }


    @Override
    public void onTestFailure(ITestResult result) {

        //Step 1 : Get the method result error
        threadLocal.get().fail(result.getThrowable());

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
                    .get(result.getInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Step 2: take the Screenshot
        String filepath = null;
        try {
            filepath = GetScreenshot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Step 3: Attach the Screenshot
        threadLocal.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        // not implemented
    }


    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // not implemented
    }


    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }


    @Override
    public void onStart(ITestContext context) {
        // not implemented
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
        ;
    }

}
