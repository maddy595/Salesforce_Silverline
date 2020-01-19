package salesforceTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import salesforce.util.BaseClass;
import salesforce.util.SalesforceConstants;

public class LoginTest extends BaseClass {
	
	@BeforeClass
	public void initialize() {
		initializeExtentReports();
		openBrowser("chrome");
		navigate("Salesforcedevurl");
		
	}
	
	@Test(priority=1)
	public void SalesforceLogin() {
		test=extent.createTest("Login to Salesforce");
		test.log(Status.INFO, "Will login to the application");
		System.out.println(SalesforceConstants.LoginPagetitle);
		verifyPageTitle(SalesforceConstants.LoginPagetitle);
		type("usernameTextbox", "madhav.gaikwad@acc.com");
		type("passwordTextbox", "p@sword123");
		click("Loginbutton");
		IsLoginSuccessful(SalesforceConstants.HomePagetitle);
	}
	
	@Test(priority=2)
	public void VerifySalesOrgTest() { 
		test=extent.createTest("Home Page title Verification");
		test.log(Status.INFO, "Will verify Home Page title");
		verifyText("OrgName", SalesforceConstants.OrgName);
	}
	
	@Test(priority=3)
	public void logout() {
		test=extent.createTest("Logout Test");
		test.log(Status.INFO, "Will Logout from application");
		click("profilelink");
		click("logoutlink");
		
	}
	
	@AfterClass
	public void close(){
		driver.quit();
	}
	
	@AfterSuite
	public void tearDown(){
		extent.flush();
		
	}
	

}
