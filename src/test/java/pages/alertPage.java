package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.WaitUtil;

public class alertPage {
	WebDriver driver;
	
	
	By name = By.id("name");
	By alertBtn = By.id("alertbtn");
	By confirmBtn = By.id("confirmbtn");
	
	public alertPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void handleAlert() {
		driver.findElement(name).sendKeys("Prema");
		WaitUtil.waitForElement(driver, driver.findElement(alertBtn)).click();
		
		Alert alert = WaitUtil.waitForAlert(driver);
		System.out.println("Alert text is "+ alert.getText());
		alert.accept();
	}
	
	public void handleConfirm() {
		driver.findElement(name).sendKeys("Prema");
		WaitUtil.waitForElement(driver, driver.findElement(confirmBtn)).click();
		
		Alert alert = WaitUtil.waitForAlert(driver);
		System.out.println("Confirm text is "+ alert.getText());
		alert.dismiss();
	}
	

}
