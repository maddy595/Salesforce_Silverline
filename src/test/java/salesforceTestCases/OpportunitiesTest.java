package salesforceTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import salesforce.util.BaseClass;
import salesforce.util.SalesforceConstants;


public class OpportunitiesTest extends BaseClass{
	
	@BeforeTest
	public void initialize() {
		//initializeExtentReports();
		if(extent==null)
			getInstance();
		openBrowser("chrome");
		navigate("Salesforcedevurl");
	}
	
	@Test(priority=1)
	public void goToOpportunitiesPage() {
		test=extent.createTest("OpportunityListTest");
		test.log(Status.INFO, "Navigation to Opportunities Page");
		verifyPageTitle(SalesforceConstants.LoginPagetitle);
		type("usernameTextbox", "madhav.gaikwad@acc.com");
		type("passwordTextbox", "p@sword123");
		click("Loginbutton");
		IsLoginSuccessful(SalesforceConstants.HomePagetitle);
		WebElement Opptylink = driver.findElement(By.xpath("//*[@title='Opportunities']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", Opptylink);
		test.log(Status.INFO, "Opportunity Link is clicked");	
	}
	
	@Test(priority=2)
	public void createNewOpportunity() {
		test=extent.createTest("OpportunityCreationTest");
		test.log(Status.INFO, "New Opportunity will be created");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		Actions act = new Actions(driver);
		//Click on New Opportunity
		click("NewOpptyLink");
		//TYpe Oppty Name
		type("OpptyName",prop.getProperty("OpportunityName"));
		//Select a Close Date
		click("CloseDatelink");
		click("SelectDate");
		//Select a Stage
		click("StageDropdown");
		click("Selectstage");
		//Save Opportunity
		click("SaveOppty");
		waitfor(2000);
		if(isElementPresent("OpportunitiesDetailsTitle")) {
			test.log(Status.INFO, "Opportunity is created successfully");
			test.pass("Opportunity is verified successfully");
		}else {
			test.fail("Opportunity creation failed");
			test.log(Status.INFO, "Opportunity is not created");
		}
	}
	
	
	@Test(priority=3)
	public void DeleteOpportunity() {
		test=extent.createTest("OpportunityDeletionTest");
		test.log(Status.INFO, "Newly Created Opportunity will be Deleted");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Actions act = new Actions(driver);
		WebElement Opptylink = driver.findElement(By.xpath("//*[@title='Opportunities']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", Opptylink);
		waitfor(2000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div/div/table/tbody/tr[1]/th/span/a"))));
		String OpptyTExt=driver.findElement(By.xpath("//div/div/table/tbody/tr[1]/th/span/a")).getText();
		if(OpptyTExt.equalsIgnoreCase(prop.getProperty("OpportunityName"))) {
			driver.findElement(By.xpath("//div/div/table/tbody/tr[1]/th/span/a/../..//following::td[6]/span/div")).click();
			driver.findElement(By.xpath("//ul[@class='scrollable']/li[2]/a[@title='Delete']")).click();
			JavascriptExecutor js = (JavascriptExecutor)driver;
			waitfor(3000);
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[text()='Delete']")))).click();	
			waitfor(3000);
			if(isElementPresent("OpptyDeletionTest")) {
				test.log(Status.INFO, "Tried Deleting Opportunity successfully");
				test.pass("Tried Deleting Opportunity successfully");
			}else {
				test.fail("Opportunity Deletion failed");
				test.log(Status.INFO, "Could not try deleting opportunity");
				Assert.fail();
			}
		}else {
			test.fail("Opportunity Deletion Failed");
			Assert.fail();
		}	
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
