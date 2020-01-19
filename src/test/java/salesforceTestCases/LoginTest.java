package salesforceTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import salesforce.util.BaseClass;

public class LoginTest extends BaseClass {
	
	@BeforeClass
	public void initialize() {
		init("chrome");
		driver.get("https://ap4.lightning.force.com/lightning/page/home");
	}
	
	@Test(priority=1)
	public void login1() {
		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys("madhav.gaikwad@acc.com");
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("p@sword123");	
		driver.findElement(By.xpath("//*[@id='Login']")).click();
		System.out.println("Login1");
	}
	
	@Test(priority=2)
	public void Login2() {
		
		System.out.println(driver.getTitle());
		
	}
	
	@AfterClass
	public void tearDown(){
		driver.quit();
	}
	

}
