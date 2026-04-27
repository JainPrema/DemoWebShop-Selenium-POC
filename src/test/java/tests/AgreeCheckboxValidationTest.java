package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;
import pages.ApparelShoesPage;
import pages.CartPage;
import pages.ComputerPage;
import pages.HomePage;
import pages.LoginPage;
import utility.LoggingUtil;

public class AgreeCheckboxValidationTest extends BaseTest{
	
	@Test(groups = "sanity")
	public void checkBoxValidation() {
		LoggingUtil.info("Executing AgreeCheckboxValidationTest");
		SoftAssert softAssert = new SoftAssert();
		LoginPage login = new LoginPage(getDriver());
		HomePage home = login.LoginCheck("anu_a@gmail.com", "anua@123");
		
		CartPage cart = home.goToCartPage();
		cart.cartIfNotEmpty();
		
		ComputerPage computer = home.goToComputers();
		computer.addComputersToCart("Simple Computer");
		ApparelShoesPage shoes = home.goToApparelShoesPage();
		shoes.addBlueJeansToCart(25);
		
		CartPage cart1 = home.goToCartPage();
		softAssert.assertEquals(cart1.getCartItemsCount(), 2, "count mismatch");
		softAssert.assertEquals(cart1.getTotalQuantity(), 26, "Total quantity mismatch");
		
		cart1.clickCheckoutWithoutAgree();
		Assert.assertTrue(cart1.isMessageDisplayed(), "Warning not displayed");
		
		
		softAssert.assertAll();
		
		
		
		
		
		
		
	}
	

}
