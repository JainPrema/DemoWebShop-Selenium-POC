package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import utility.ConfigReader;
import utility.LoggingUtil;

public class BaseTest {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public WebDriver getDriver() {
		return driver.get();
	}
	
	
	//@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public void setup() {
		ChromeOptions options = new ChromeOptions();

	    options.addArguments("--headless=new");
	    options.addArguments("--no-sandbox");
	    options.addArguments("--disable-dev-shm-usage");
	    options.addArguments("--window-size=1920,1080");
	    
		String browser = ConfigReader.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
		}
		else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
		}
			
		getDriver().manage().window().maximize();
		getDriver().get(ConfigReader.getProperty("url"));
		LoggingUtil.info("Browser launched");
	}
	
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		 if (getDriver() != null) {
			 getDriver().quit();
			 LoggingUtil.info("Browser closed");
		 }
	}

}
