package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;
import pages.ApparelShoesPage;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ComputerPage;
import pages.HomePage;
import pages.LoginPage;
import pages.OrderConfirmationPage;
import utility.LoggingUtil;

public class AddItemsToCartWithoutLogin extends BaseTest{
	
	@Test(groups = "regression")
	public void withoutLoginCheckout() {
		LoggingUtil.info("Executing AddItemsToCartWithoutLogin");
		SoftAssert softAssert = new SoftAssert();
		
		HomePage home = new HomePage(getDriver());
		
		CartPage cart = home.goToCartPage();
		cart.cartIfNotEmpty();
		
		ComputerPage computer = home.goToComputers();
		computer.addComputersToCart("Simple Computer");
		ApparelShoesPage shoes = home.goToApparelShoesPage();
		shoes.addBlueJeansToCart(25);
		home.goToCartPage();
		softAssert.assertEquals(cart.getCartItemsCount(), 2, "count mismatch");
		softAssert.assertEquals(cart.getTotalQuantity(), 26, "Total quantity mismatch");
		
		cart.checkoutProcess();
		HomePage home1 = new LoginPage(getDriver()).LoginCheck("anu_b@gmail.com", "anub@123");
		CartPage cart1 = home1.goToCartPage();
		CheckoutPage checkout = cart1.checkoutProcess();
		OrderConfirmationPage confirm = checkout.ShippingAndPaymentSteps();
		Assert.assertTrue(confirm.isOrderSuccessful());
		confirm.logOut();
		
		softAssert.assertAll();
		
		
		
	}
	

}
