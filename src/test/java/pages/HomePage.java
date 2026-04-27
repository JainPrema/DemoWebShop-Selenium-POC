package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.WaitUtil;

public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath ="(//a[@href='/books'])[1]")
	WebElement booksCategory;
	
	@FindBy(xpath = "(//a[@href='/computers'])[1]")
	WebElement computersCategory;
	
	@FindBy(linkText = "Apparel & Shoes")
	WebElement apparelShoesCategory;
	
	@FindBy(linkText ="Shopping cart")
	WebElement cartBtn;
	
	@FindBy(linkText = "Register")
	WebElement registerBtn;
	
	@FindBy(linkText ="Log out")
	WebElement logoutBtn;
	
	@FindBy(css = ".information a")
	List<WebElement> footerLinks;
	
	
	public BooksPage goToBooks() {
		WaitUtil.waitForElementToBeClickable(driver, booksCategory);
		booksCategory.click();
		return new BooksPage(driver);
	}
	
	public  ComputerPage goToComputers() {
		WaitUtil.waitForElementToBeClickable(driver, computersCategory);
		computersCategory.click();
		return new ComputerPage(driver);	
		
	}
	
	public ApparelShoesPage goToApparelShoesPage() {
		WaitUtil.waitForElementToBeClickable(driver, apparelShoesCategory);
		apparelShoesCategory.click();
		return new ApparelShoesPage(driver);
	}
	
	public CartPage goToCartPage() {
		WaitUtil.waitForElementToBeClickable(driver, cartBtn);
		cartBtn.click();
		return new CartPage(driver);
	}
	
	
	public  RegisterPage goToRegister() {
		registerBtn.click();
		return new RegisterPage(driver);	
		
	}
	
	public boolean isUserLoggedIn() {
		WaitUtil.waitForElement(driver, logoutBtn);
		return logoutBtn.isDisplayed();
	}
	
	public void logout() {
		logoutBtn.click();
	}
	
	public void openFooterLinks() {
		
		String parent = driver.getWindowHandle();
		
		for(WebElement link: footerLinks) {
			String url = link.getAttribute("href");
			driver.switchTo().newWindow(WindowType.TAB);
			driver.get(url);
			
			System.out.println("Opened : "+ driver.getTitle());
			driver.close();
			driver.switchTo().window(parent);
		}
	}

	
	

}
