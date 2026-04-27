package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.WaitUtil;

public class CheckoutPage {
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//@FindBy(id = "billing-address-select")
	//WebElement newAddress;
	
	//@FindBy(id = "BillingNewAddress_CountryId")
	//WebElement country;
	
	//@FindBy(id = "BillingNewAddress_City")
	//WebElement city;
	
	//@FindBy(id ="BillingNewAddress_Address1")
	//WebElement address1;
	
	//@FindBy(id = "BillingNewAddress_ZipPostalCode")
	//WebElement zip;
	
	//@FindBy(id = "BillingNewAddress_PhoneNumber")
	//WebElement phone;
	
	
	
	@FindBy(css = "input[onclick='Billing.save()']")
	WebElement billingContinueBtn;
	
	@FindBy(css = "input[onclick='Shipping.save()']")
	WebElement shippingContinueBtn;
	
	@FindBy(xpath ="//input[@id='shippingoption_1']")
	WebElement selectShippingMethod;
	@FindBy(xpath = "//input[@onclick='ShippingMethod.save()']")
	WebElement shippingMethodContinueBtn;
	
	@FindBy(xpath = "//input[@onclick='PaymentMethod.save()']")
	WebElement paymentMethodContinueBtn;
	
	@FindBy(xpath ="//input[@onclick='PaymentInfo.save()']")
	WebElement paymentInfo;
	
	@FindBy(css = "input[value='Confirm']")
	WebElement confirmOrderBtn;
	
	public OrderConfirmationPage ShippingAndPaymentSteps() {
		WaitUtil.waitForElementToBeClickable(driver, billingContinueBtn);
		billingContinueBtn.click();
		
		WaitUtil.waitForElementToBeClickable(driver, shippingContinueBtn);
		shippingContinueBtn.click();
		
		WaitUtil.waitForElementToBeClickable(driver, selectShippingMethod);
		selectShippingMethod.click();
		shippingMethodContinueBtn.click();
		
		WaitUtil.waitForElementToBeClickable(driver, paymentMethodContinueBtn);
		paymentMethodContinueBtn.click();
		
		WaitUtil.waitForElementToBeClickable(driver, paymentInfo);
		paymentInfo.click();
		
		WaitUtil.waitForElementToBeClickable(driver, confirmOrderBtn);
		confirmOrderBtn.click();
		
		return new OrderConfirmationPage(driver);
	}
	
	

}
