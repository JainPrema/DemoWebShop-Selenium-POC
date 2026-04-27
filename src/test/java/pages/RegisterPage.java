package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage  {
	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By maleRadio = By.id("gender-male");
	By femaleRdio = By.id("gender-female");
	By fname = By.id("FirstName");
	By lname = By.id("LastName");
	By email = By.id("Email");
	By passwd = By.id("Password");
	By confirmPasswd = By.id("ConfirmPassword");
	By registerBtn = By.id("register-button");
	
	
	public void selectGender(String gender) {
		if(gender.equalsIgnoreCase("male")) {
			driver.findElement(maleRadio).click();
		}
		else {
			driver.findElement(femaleRdio).click();
		}
	}
	
	public HomePage registerUser(String gender, String f_name, String l_name, String emailID, String password) {
		selectGender(gender);
		driver.findElement(fname).sendKeys(f_name);
		driver.findElement(lname).sendKeys(l_name);
		driver.findElement(email).sendKeys(emailID);
		driver.findElement(passwd).sendKeys(password);
		driver.findElement(confirmPasswd).sendKeys(password);
		driver.findElement(registerBtn).click();
		return new HomePage(driver);
	}
	

}
