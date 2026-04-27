package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import listeners.RetryAnalyzer;
import pages.BooksPage;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;

public class FlakyTest extends BaseTest{
	@Test(groups = "smoke", retryAnalyzer = RetryAnalyzer.class)
	public void flakyCartCountTest() {

		LoginPage login = new LoginPage(getDriver());
		HomePage home = login.LoginCheck("anu_b@gmail.com", "anub@123");

	    BooksPage books = home.goToBooks();
	    books.addBooksToCartByIndex(0);

	    CartPage cart = home.goToCartPage();

	    Assert.assertEquals(cart.getCartItemsCount(), 1);
	}

}
