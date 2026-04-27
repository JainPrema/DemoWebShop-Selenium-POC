package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utility.WaitUtil;

public class BooksPage {
    WebDriver driver;
    
	public BooksPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "page-title")
	WebElement booksTitle;
	
	@FindBy(css = ".product-item")
	List<WebElement> booksList;
	
	@FindBy(id ="products-orderby")
	WebElement sortBy;
	
	@FindBy(css = ".bar-notification")
	WebElement message;
	
	public void booksPageVisible() {
		WaitUtil.waitForElement(driver, booksTitle);
	}
	
	public List<String> sortBooks() {
		Select select = new Select(sortBy);
		select.selectByVisibleText("Name: A to Z");
		List<WebElement> books =  driver.findElements(By.cssSelector(".product-title"));
		List<String> bookNames =new ArrayList<>();
		
		for(WebElement book :books) {
			bookNames.add(book.getText());
		}
		return bookNames;
	}
	
	public  HomePage addBooksToCartByIndex(int index) {
		List<WebElement> addToCartButtons = driver.findElements(By.xpath("//input[@value='Add to cart']"));
		WebElement button = addToCartButtons.get(index);
		WaitUtil.waitForElementToBeClickable(driver, button);
		try {
			button.click();
		}
		catch(Exception e) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
		}
		
		WaitUtil.waitForElement(driver,message );
		WaitUtil.waitForElementToBeDisappear(driver, By.cssSelector(".bar-notification"));
		return new HomePage(driver);
	}
	
	
	public void addBookToCartByName(String bookName) {
		for(WebElement book: booksList) {
			String name = book.findElement(By.cssSelector("h2")).getText();
			
			if(name.equalsIgnoreCase(bookName)) {
				book.findElement(By.cssSelector("input[value='Add to cart']")).click();
				break;
			}
		}
	}
	
	
	
	

}
