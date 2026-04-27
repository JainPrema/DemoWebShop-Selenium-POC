package utility;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {
	
	public static WebDriverWait getWait(WebDriver driver) {
		return new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public static WebElement waitForElement(WebDriver driver, WebElement element) {
		return getWait(driver).until(ExpectedConditions.visibilityOf(element));
	}
	
	
	public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement element) {
		return getWait(driver).until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	public static boolean waitForElementToBeDisappear(WebDriver driver, By locator) {
		if(driver.findElements(locator).size()==0) {
			return true;
		}
		return getWait(driver).until(ExpectedConditions.invisibilityOfElementLocated(locator));
		
	}
	
	public static void switchToFrame(WebDriver driver, By locator) {
		getWait(driver).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}
	
	public static Alert waitForAlert(WebDriver driver) {
		return getWait(driver).until(ExpectedConditions.alertIsPresent());
	}
	
	
	
	

}
