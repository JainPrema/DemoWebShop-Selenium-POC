package utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
	
	public static String Capture(WebDriver driver, String fileName) throws IOException {
		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String dest = System.getProperty("user.dir")+"//screenshots//"+fileName+".png";
			FileUtils.copyFile(src, new File(dest));
			
			return dest;
		}
		catch(Exception e) {
			throw new RuntimeException("Screenshot failed", e);
		}
		
		
	}

}
