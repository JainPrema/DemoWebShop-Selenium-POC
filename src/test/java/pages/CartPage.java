package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.WaitUtil;

public class CartPage {
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "cart-item-row")
	List<WebElement> cartItemsCount;
	
	@FindBy(className = "order-summary-content")
	WebElement cartEmptyMessage;
	
	@FindBy(css = "input[name='removefromcart']")
	List<WebElement> removeBtn;
	
	@FindBy(name = "updatecart")
	WebElement updateCartBtn;
	
	@FindBy(css = ".qty-input")
	List<WebElement> quantity;
	
	@FindBy(id ="termsofservice")
	WebElement clickCheckBox;
	
	@FindBy(id = "terms-of-service-warning-box")
	WebElement warningMesage;
	
	@FindBy(css = ".ui-dialog-titlebar-close")
	WebElement popupClaseBtn;
	
	@FindBy(id = "checkout")
	WebElement checkoutBtn;
	
	public int getCartItemsCount() {
		return cartItemsCount.size();
	}
	
	public HomePage cartIfNotEmpty() {
		List<WebElement> removeCheckbox = driver.findElements(By.name("removefromcart"));
		System.out.println("Items in cart: " + removeCheckbox.size());
		
		if(removeCheckbox.size() > 0) {
		    for(int i=0; i<removeCheckbox.size();i++) {
		    	WebElement checkbox = removeCheckbox.get(i);
		    	WaitUtil.waitForElementToBeClickable(driver, checkbox);
		    	checkbox.click();
		    }
			updateCartBtn.click();
			WaitUtil.waitForElementToBeDisappear(driver, By.name("removefromcart"));
		}
		return new HomePage(driver);
	}
	
	public int getTotalQuantity() {
		int total = 0;
		for(WebElement q:quantity) {
			String qty = q.getAttribute("value");
			total += Integer.parseInt(qty);
		}
		return total;
	}
	
	public void clickCheckoutWithoutAgree() {
		checkoutBtn.click();
	}
	
	public boolean isMessageDisplayed() {
		return warningMesage.isDisplayed();
	}
	
	public CheckoutPage checkoutProcess() {
		WaitUtil.waitForElementToBeClickable(driver, checkoutBtn);
		checkoutBtn.click();
		
		WaitUtil.waitForElement(driver, warningMesage);
		System.out.println("Warning message is: "+ warningMesage.getText());
		
		popupClaseBtn.click();
		
		WaitUtil.waitForElementToBeClickable(driver, clickCheckBox);
		clickCheckBox.click();
		
		checkoutBtn.click();
		
		return new CheckoutPage(driver);
	}

}
