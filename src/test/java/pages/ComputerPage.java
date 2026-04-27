package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.WaitUtil;

public class ComputerPage {
	WebDriver driver;
	
	public ComputerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//a[@href='/desktops'])[5]")
	WebElement selectDesktops;
	
	@FindBy(linkText = "Simple Computer")
	WebElement simpleComputer;
	
	@FindBy(id ="product_attribute_75_5_31_96")
	WebElement processorType;
	
	@FindBy(id = "add-to-cart-button-75")
	WebElement addComputerToCart;
	
	@FindBy(css=".product-item")
	List<WebElement> computerList;
	
	public HomePage addComputersToCart(String computerName) {
		WaitUtil.waitForElementToBeClickable(driver, selectDesktops);
		selectDesktops.click();
		
		for(WebElement item : computerList) {
			String compName = item.findElement(By.cssSelector("h2.product-title")).getText();
			
			if(compName.equalsIgnoreCase(computerName)) {
				item.findElement(By.cssSelector("h2.product-title a")).click();
				break;
			
			}
		}
				
		WaitUtil.waitForElementToBeClickable(driver, processorType);
		processorType.click();
		
		WaitUtil.waitForElementToBeClickable(driver, addComputerToCart);
		addComputerToCart.click();
		return new HomePage(driver);
		
	}
	

}
