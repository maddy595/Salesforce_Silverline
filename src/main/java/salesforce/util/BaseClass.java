package salesforce.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseClass {

public WebDriver driver;
public Properties prop;
public ExtentReports extent;
public ExtentTest test;
public ExtentHtmlReporter reporter;
FileInputStream fis ;

public void initializeExtentReports() {
    reporter=new ExtentHtmlReporter(SalesforceConstants.ScreenshotFilePath);
    extent = new ExtentReports();
    extent.attachReporter(reporter);
  
    
}

	public void openBrowser(String bType) {
		if (prop==null) {
			try {
		File file = new File(SalesforceConstants.PropertiesFile);
		prop = new Properties();
		fis = new FileInputStream(file);
		prop.load(fis);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			}
		  }
		
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
	
	public void navigate(String url) {
		driver.get(prop.getProperty(url));
	}
	
	public void type(String xpathEleKey , String email) {
		getElement(xpathEleKey).sendKeys(email);
		
	}
	
	public void click(String xpathEleKey) {
			getElement(xpathEleKey).click();
		}
	
	public WebElement getElement(String locatorKey) {
		WebElement e = null;
		try {
		if(locatorKey.endsWith("_id"))
		e= driver.findElement(By.id(prop.getProperty(locatorKey)));
		else if (locatorKey.endsWith("_name"))
			e= driver.findElement(By.name(prop.getProperty(locatorKey)));
		else if (locatorKey.endsWith("xpath"))
			e= driver.findElement(By.xpath(prop.getProperty(locatorKey)));
		else 
			reportfailure("Locator not correct - "+locatorKey);
		}catch(Exception ex) {
			reportfailure(ex.getMessage());
			ex.printStackTrace();
			
		}
		return e;
		
	}
	
	
	
	/************************Validations***********************/
	
	public boolean verifyTitle() {
		return false;
	}
	
	public boolean isElementPresent(String locatorKey) {
		List<WebElement> elementlist = null;
		try {
		if(locatorKey.endsWith("_id"))
			elementlist= driver.findElements(By.id(prop.getProperty(locatorKey)));
		else if (locatorKey.endsWith("_name"))
			elementlist= driver.findElements(By.name(prop.getProperty(locatorKey)));
		else if (locatorKey.endsWith("xpath"))
			elementlist= driver.findElements(By.xpath(prop.getProperty(locatorKey)));
		else {
		//	reportfailure("Locator not correct - "+locatorKey);
		}
		if(elementlist.size()==0)
			return false;
		else
			return true;
		}catch(Exception ex) {
		//	reportfailure(ex.getMessage());
			ex.printStackTrace();
			Assert.fail();
		}
		return false;
		
	}
	
	public boolean verifyText(String locatorKey,String ExpectedTextKey) {
		String actualText=getElement(locatorKey).getText().trim();
		String ExpectedText=prop.getProperty("ExpectedTextKey");
		if (actualText.equals(ExpectedText))
		return true;
		else
			return false;
	}

    /****************************reporting************************/
	
	public void reportpass(String msg) {
	
		test.log(Status.PASS, msg);
	}
	
	public void reportfailure(String msg) {
		test.log(Status.FAIL, msg);
		takeScreenshot();
		//This line will stop the test case from executing
		Assert.fail();
	}
	
	public void takeScreenshot() {
		
		TakesScreenshot src = (TakesScreenshot)driver;
		File srcfile = src.getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		String filename= d.toString().replaceAll(":", "_").replaceAll(" ", "_")+".jpg";
		try {
			
		FileHandler.copy(srcfile, new File(System.getProperty("user.dir")+"//Screenshot//"+filename));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		//put screenshot file in reports
	}
	
	
	public void screenshotwithName() { //Challange - File in destination is replaced EveryTime
		File Dest;
		Date d = new Date();
		System.out.println(d);
		SimpleDateFormat sdf = new SimpleDateFormat("MM_DD_YYYY_HH_mm");
		String s = sdf.format(d);
		TakesScreenshot tks = (TakesScreenshot)driver;
		File source = tks.getScreenshotAs(OutputType.FILE);
		String methodName= Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println(methodName);
		String destFile = SalesforceConstants.ScreenshotFilePath+"\\"+methodName+s+".JPG";
		System.out.println(destFile);
		Dest = new File(destFile);
		try {
			FileHandler.copy(source, Dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	

}
