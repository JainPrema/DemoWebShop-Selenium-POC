package tests;

import java.io.IOException;

import org.openqa.selenium.WindowType;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.alertPage;
import pages.iFramePage;
import utility.LoggingUtil;

public class WindowAlertTest extends BaseTest{
	
	@Test(groups = "smoke")
	public void windowActions() throws IOException {
		LoggingUtil.info("Executing iframe test and alert test");
		String parentWindow = getDriver().getWindowHandle();
		
		getDriver().switchTo().newWindow(WindowType.TAB);
		getDriver().get("https://jqueryui.com/droppable/");
		
		iFramePage frame = new iFramePage(getDriver());
		frame.performDragandDrop();
		getDriver().close();
		getDriver().switchTo().window(parentWindow);
		
		getDriver().switchTo().newWindow(WindowType.TAB);
		getDriver().get("https://rahulshettyacademy.com/AutomationPractice/");
		
		alertPage alert = new alertPage(getDriver());
		alert.handleAlert();
		alert.handleConfirm();
		getDriver().close();
		getDriver().switchTo().window(parentWindow);
		LoggingUtil.info("iframe and alert action execution completed successfully");
		
		
		
		
	}

}
