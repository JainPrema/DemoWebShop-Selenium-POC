package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{
    int count = 0;
    int maxTry = 2 ;
    
	@Override
	public boolean retry(ITestResult result) {
		if(count < maxTry) {
			count++;
			System.out.println("Retrying" + result.getName()+ "again. Attempt:"+ count);
			return true;
		}
		return false;
	}
	

}
