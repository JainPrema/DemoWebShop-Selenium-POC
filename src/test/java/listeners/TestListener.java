package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;
import utility.ConfigReader;
import utility.EmailUtil;
import utility.ExtentManager;
import utility.ScreenshotUtil;

public class TestListener implements ITestListener{
    private static ExtentReports extent = ExtentManager.startReport();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    
    
	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getName());
		test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().pass("Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.get().fail(result.getThrowable());
		try {
			BaseTest baseTest =(BaseTest) result.getInstance();
			WebDriver driver = baseTest.getDriver();
			String path = ScreenshotUtil.Capture(driver, result.getName());
			test.get().addScreenCaptureFromPath(path);
			
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().skip("Test Skipped");
	}


	@Override
	public void onFinish(ITestContext context) {
		extent.flush();

		String reportPath = System.getProperty("user.dir") + "\\reports\\ExtentReport.html";

		String subject = "Automation Execution Report";
		// String body = "Hi team, \n\n Please find attached execution
		// report.\n\nThanks";
		String body = "Hi Team,\n\nExecution completed.Please find attached execution report.\n\nView report:\nhttps://seleniumpoc.netlify.app/extentreport";

		try {
			EmailUtil.sendMail(ConfigReader.getProperty("email.to"), subject, body, reportPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
