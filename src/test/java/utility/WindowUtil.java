package utility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WindowUtil {
	public static String switchToNewWindow(WebDriver driver) {
		String parent = driver.getWindowHandle();
		
		new WebDriverWait(driver, Duration.ofSeconds(5))
		     .until(d -> d.getWindowHandles().size()>1);
		
		for(String window:driver.getWindowHandles()) {
			if(!window.equals(parent)) {
				driver.switchTo().window(window);
				break;
			}
		}
		return parent;
	}
	
	public static void switchBacktoParent(WebDriver driver, String parent) {
		driver.switchTo().window(parent);
	}

}
