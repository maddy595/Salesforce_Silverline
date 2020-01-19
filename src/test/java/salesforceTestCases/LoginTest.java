package salesforceTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import salesforce.util.BaseClass;

public class LoginTest extends BaseClass {
	
	@BeforeTest
	public void initialize() {
		initializeExtentReports();
		openBrowser("chrome");
		driver.get("https://ap4.lightning.force.com/lightning/page/home");
	}
	
	@Test(priority=1)
	public void SalesforceLogin() {
		test=extent.createTest("Login to Salesforce");
		test.log(Status.INFO, "Will login to the application");
		
		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys("madhav.gaikwad@acc.com");
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("p@sword123");	
		driver.findElement(By.xpath("//*[@id='Login']")).click();
		System.out.println("Login1");
		
	}
	
	@Test(priority=2)
	public void HomePageTest() {
		test=extent.createTest("HomePageTest");
		System.out.println(driver.getTitle());
		test.log(Status.INFO, "Got the title");
		
	}
	

	
	@AfterTest
	public void tearDown(){
		
		driver.quit();
	}
	

}
