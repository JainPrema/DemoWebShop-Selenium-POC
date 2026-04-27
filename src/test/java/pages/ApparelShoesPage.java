package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ApparelShoesPage {
	WebDriver driver;
	
	public ApparelShoesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Blue Jeans")
	WebElement blueJeans;
	
	@FindBy(className = "qty-input")
	WebElement qtyBox;
	
	@FindBy(id = "add-to-cart-button-36")
	WebElement addToCartBtn;
	
	public HomePage addBlueJeansToCart(int qty) {
		blueJeans.click();
		qtyBox.clear();
		qtyBox.sendKeys(String.valueOf(qty));
		addToCartBtn.click();
		return new HomePage(driver);
	}
	
	
	
	

}
