package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import utility.DataReader;

public class RegisterAndRememberMeTest extends BaseTest {
	
	static String email;
	static String passwd;
	
	@Test(dataProvider = "registerData")
	public void registerUser(String gender, String fname, String lname, String mail, String pass) {
		email = mail;
		passwd = pass;
		
		HomePage home = new HomePage(getDriver());
		RegisterPage register = home.goToRegister();
		
		register.registerUser(gender, fname, lname, mail, pass);
		
		Assert.assertTrue(home.isUserLoggedIn());
		home.logout();
		
	}
	
	@Test(dependsOnMethods = "registerUser")
	public void rememeberMe() {
		 
		 HomePage home = new LoginPage(getDriver()).loginWithRememberMe(email, passwd);
		 Assert.assertTrue(home.isUserLoggedIn());
		 getDriver().quit();
		// setDriver(new ChromeDriver());
		 getDriver().manage().window().maximize();
		 getDriver().get("https://demowebshop.tricentis.com/");
		 Assert.assertTrue(new HomePage(getDriver()).isUserLoggedIn());
	}
	
	
	@DataProvider(name ="registerData")
	public Object[][] getRegiterData() throws IOException{
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\NewRegisterData.xlsx";
		return DataReader.getExceldata(path, "Sheet1");
	}

}
