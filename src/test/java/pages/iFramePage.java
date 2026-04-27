package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import utility.ScreenshotUtil;
import utility.WaitUtil;

public class iFramePage {
	
	WebDriver driver;
	
	By frame = By.cssSelector(".demo-frame");
	By source = By.id("draggable");
	By target = By.id("droppable");
	
	public iFramePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void performDragandDrop() throws IOException {
		WaitUtil.switchToFrame(driver, frame);
		
		WebElement src = driver.findElement(source);
		WebElement tgt = driver.findElement(target);
		
		Actions actions = new Actions(driver);
		actions.dragAndDrop(src, tgt).perform();
		
		String path = ScreenshotUtil.Capture(driver, "drag_drop_success");
		System.out.println("Screenshot saved at "+ path);
		
		
		driver.switchTo().defaultContent();
		
	}

}
