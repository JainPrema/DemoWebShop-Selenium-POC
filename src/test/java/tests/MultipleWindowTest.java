package tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Test;

import base.BaseTest;
import listeners.RetryAnalyzer;

public class MultipleWindowTest extends BaseTest {
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void windowHandle() {
		
		String parent = getDriver().getWindowHandle();
		List<WebElement> footerLinks = getDriver().findElements(By.cssSelector(".information a"));
		
		for(WebElement link: footerLinks) {
			String url = link.getAttribute("href");
			getDriver().switchTo().newWindow(WindowType.TAB);
			getDriver().get(url);
			
			System.out.println("Opened : "+ getDriver().getTitle());
			getDriver().close();
			getDriver().switchTo().window(parent);
		}
		
		
		
		
	}

}
