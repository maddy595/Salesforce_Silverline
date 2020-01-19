package salesforce.util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BaseClass {

public WebDriver driver;
	
	public void init(String bType) {
		if (driver==null) {
		   System.out.println("browser name is " +bType);
			if(bType.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", SalesforceConstants.Chrome_Driver_Exe);
				driver = new ChromeDriver();
			}else if(bType.equals("firefox")){
				System.setProperty("webdriver.gecko.driver", SalesforceConstants.Firefox_Driver_Exe);
				driver = new FirefoxDriver();
			}else if (bType.equals("ie")){
				System.setProperty("webdriver.ie.driver", SalesforceConstants.IE_Driver_Exe);
				driver = new InternetExplorerDriver();				
			}	
		driver.manage().window().maximize();
		//driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		}
	}
	
	

}
