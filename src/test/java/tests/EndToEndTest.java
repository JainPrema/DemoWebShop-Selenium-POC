package tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import listeners.RetryAnalyzer;
import pages.BooksPage;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import pages.OrderConfirmationPage;
import pages.RegisterPage;
import utility.ConfigReader;
import utility.DataReader;
import utility.LoggingUtil;

public class EndToEndTest extends BaseTest {

	@Test(dataProvider = "loginData", dataProviderClass = DataReader.class, groups = "regression")
	public void completeOrder(String user, String passwd, boolean expectedSuccess) throws IOException {

		LoggingUtil.info("Executing End to End test with data provider");
		LoginPage login = new LoginPage(getDriver());
		HomePage home = login.LoginCheck(user, passwd);
		
		if (!expectedSuccess) {
			LoggingUtil.info("Validating invalid login scenario");
			Assert.assertTrue(login.isLoginErrorDisplayed());
			return;
		}
      
		
		CartPage cart = home. goToCartPage();
		cart.cartIfNotEmpty();
		
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\testData.csv";
		List<String[]> data = DataReader.getCSVDataList(path);
		for(String[] row : data) {
			String category =row[0];
			String product = row[1];
			int qty = Integer.parseInt(row[2]);
			
			if(category.equalsIgnoreCase("Books")) {
				BooksPage book = home.goToBooks();
				
				book.booksPageVisible();
				List<String> actual = book.sortBooks();
				List<String> sorted = new ArrayList<>(actual);
				Collections.sort(sorted);
				Assert.assertEquals(actual, sorted);

				book.addBookToCartByName(product);
			}
			
			else if(category.equalsIgnoreCase("Computers")) {
				home.goToComputers().addComputersToCart(product);
			}
			
			else if(category.equalsIgnoreCase("ApparelShoes")) {
				home.goToApparelShoesPage().addBlueJeansToCart(qty);
			}
		}
		
		
		CartPage cart1 = home.goToCartPage();
		Assert.assertEquals(cart1.getCartItemsCount(), 3);
		
		 Assert.assertEquals(cart1.getTotalQuantity(), 12);

		CheckoutPage checkout = cart1.checkoutProcess();
		
		OrderConfirmationPage confirm = checkout.ShippingAndPaymentSteps();
		Assert.assertTrue(confirm.isOrderSuccessful());
		confirm.logOut();
		LoggingUtil.info("End to End test executed successfully");
	}
	
	
	@Test(groups = "sanity")
	public void registerAndLoginTest() {
		String gender = ConfigReader.getProperty("regGender");
		String Fname = ConfigReader.getProperty("regFirstName");
		String Lname = ConfigReader.getProperty("regLastName");
		String password = ConfigReader.getProperty("defaultRegisterPass");
		
		String email = "user" + System.currentTimeMillis() + "@gmail.com";
		
		HomePage home = new HomePage(getDriver());
		RegisterPage register = home.goToRegister();
		HomePage registeredUser = register.registerUser(gender, Fname, Lname, email, password);
		Assert.assertTrue(registeredUser.isUserLoggedIn(), "Registration Failed");
		registeredUser.logout();
		
		LoginPage login = new LoginPage(getDriver());
		HomePage loggedInHome = login.LoginCheck(email, password);
		
		 Assert.assertTrue(loggedInHome.isUserLoggedIn(), "login Failed");
		 loggedInHome.logout();
		
	}
	
	@Test(dependsOnMethods = "completeOrder", retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
	public void openMultipleWindows() {
		HomePage home = new HomePage(getDriver());
		home.openFooterLinks();
	}
	

}
