package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports startReport() {
		//String path = System.getProperty("user.dir")+"\\reports\\Report_"+ System.currentTimeMillis()+".html";
		String path = System.getProperty("user.dir")+"\\reports\\ExtentReport.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Demo Webshop report");
		reporter.config().setDocumentTitle("Test Results");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Prema");
		
		return extent;
		
	}

}
