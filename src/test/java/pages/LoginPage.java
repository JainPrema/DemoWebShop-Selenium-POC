package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Log in")
	WebElement loginMenu;
	
	@FindBy(id = "Email")
	WebElement email;
	
	@FindBy(id = "Password")
	WebElement password;
	
	@FindBy(id ="RememberMe")
	WebElement rememberMeBtn;
	
	@FindBy(css = "input[value='Log in']")
	WebElement loginBtn;
	
	@FindBy(css = ".validation-summary-errors")
	WebElement loginErrorMessage;
	
	
	
	public HomePage LoginCheck(String usr, String passwd) {
		loginMenu.click();
		email.sendKeys(usr);
		password.sendKeys(passwd);
		loginBtn.click();
		return new HomePage(driver);
	}
	
	public HomePage loginWithRememberMe(String usr, String passwd) {
		loginMenu.click();
		email.sendKeys(usr);
		password.sendKeys(passwd);
		rememberMeBtn.click();
		loginBtn.click();
		return new HomePage(driver);
	}
	
	public boolean isLoginErrorDisplayed() {
	    return loginErrorMessage.isDisplayed();
	}
	
	

}
