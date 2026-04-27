package listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import utility.ConfigReader;
import utility.EmailUtil;

public class SuiteListener implements ISuiteListener{
	 @Override
	    public void onFinish(ISuite suite) {

	        String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
	        String subject = "Automation Execution Report";
	        String body =
	                "Hi Team,\n\nExecution completed. Please find attached report.\n\n"
	              + "View online report:\n"
	              + "https://jainprema.github.io/DemoWebShop-Selenium-POC/";

	        try {
	            EmailUtil.sendMail(
	                    ConfigReader.getProperty("email.to"),
	                    subject,
	                    body,
	                    reportPath
	            );

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}
