package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.WaitUtil;

public class OrderConfirmationPage {
	WebDriver driver;
	
	public OrderConfirmationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//strong[contains(text(), 'Your order has been successfully processed!')]")
	WebElement succeesMessage;
	
	@FindBy(linkText = "Log out")
	WebElement logoutBtn;
	
	
	public boolean isOrderSuccessful() {
		WaitUtil.waitForElement(driver, succeesMessage);
		return succeesMessage.isDisplayed();
	}
	
	public void logOut() {
		logoutBtn.click();
	}

}
