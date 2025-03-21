package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.properties.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest=new ThreadLocal();//Thread safe
    
    
    @Override
    public void onStart(ITestContext context) {
        System.out.println("🚀 Test Execution Started: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("🔹 Starting Test: " + result.getMethod().getMethodName());
        test = extent.createTest(result.getMethod().getMethodName()); // ✅ Assign test here
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ Test Passed: " + result.getMethod().getMethodName());
        extentTest.get().log(Status.PASS, "TEST PASSED");
    }	

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ Test Failed: " + result.getMethod().getMethodName());
        extentTest.get().log(Status.FAIL, "TEST FAILED");
        extentTest.get().fail(result.getThrowable());

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            System.out.println("🚨 Error retrieving WebDriver instance: " + e.getMessage());
        }

        String filePath = null;
        try {
            filePath = getScreenshot(result.getMethod().getMethodName(), driver);
            if (filePath != null) {
        	extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
            }
        } catch (IOException e) {
            System.out.println("🚨 Screenshot capture failed: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⚠️ Test Skipped: " + result.getMethod().getMethodName());
        extentTest.get().log(Status.SKIP, "TEST SKIPPED");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("📜 Test Execution Finished: " + context.getName());
        extent.flush();
    }
}
