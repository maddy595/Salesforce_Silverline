package salesforceTestCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class AccountsTest extends BaseClass{

	@BeforeClass
	public void initialize() {
		if(extent==null)
			getInstance();
		openBrowser("chrome");
		navigate("Salesforcedevurl");	
	}
	
	@Test(priority=1)
	public void goToAccountPage() {
		test=extent.createTest("NavigatetoAccountListPage");
		test.log(Status.INFO, "Navigate to Accounts List Page");
		verifyPageTitle(SalesforceConstants.LoginPagetitle);
		type("usernameTextbox", "madhav.gaikwad@acc.com");
		type("passwordTextbox", "p@sword123");
		click("Loginbutton");
		IsLoginSuccessful(SalesforceConstants.HomePagetitle);
		WebElement accountlink = getElement("AccounttopHatLink");
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", accountlink);
		
	}
	
	@Test(priority=2)
	public void createanAccount() {
		test=extent.createTest("NewAccountCreationTest");
		test.log(Status.INFO, "New Account will be created");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		Actions act = new Actions(driver);
		//New Account Link
		click("NewAccountLink");
		//Active dropdown
		WebElement ActiveDropdown = getElement("Active_dropdownlink");
		act.moveToElement(ActiveDropdown).click().perform();
		//GetElementfrom dropdown
		List<WebElement> elem = driver.findElements(By.xpath("//li[@class='uiMenuItem uiRadioMenuItem']//following::a"));
		System.out.println(elem.size());
		for(int i=1; i<=elem.size();i++) {
		WebElement dropdowntextElement= driver.findElement(By.xpath("//li[@class='uiMenuItem uiRadioMenuItem']//following::a["+i+"]"));
		String dropdowntext= dropdowntextElement.getAttribute("title");
		System.out.println(dropdowntext);
			if(dropdowntext.contains("No")) {
				act.moveToElement(dropdowntextElement).click().perform();
				test.log(Status.INFO, "Value from Active dropdown is selected");
				break;
			}
		}
		//Parent Account lookup
		//Account Name 
		type("AccountNameText", prop.getProperty("AccountName"));
		type("ParentAccountlookup", "Bank of America");
		//TO select account from dropdown
		String accountlookup = "//ul/li[@class='lookup__item  default uiAutocompleteOption forceSearchInputLookupDesktopOption']";
		wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//ul/li[@class='lookup__item  default uiAutocompleteOption forceSearchInputLookupDesktopOption']"), 1));
		click("lookupoption");
		//Select SLA from dropdown
		WebElement SLASerial = getElement("SLASerialNumber");
		act.moveToElement(SLASerial).perform();
		click("SLADropdown");
		click("SLAValue");
		click("DateTextBox");
		click("DateValue");
		type("SLANumber","Aewfbr5431");
		//Click Save Button
		click("SaveAccountRecord");
		if(verify(getElement("AccountdetailsTitle"), prop.getProperty("AccountName"))){
			test.log(Status.INFO, "Account is created successfully");
			test.pass("Account is verified successfullu");
		}else {
			test.fail("Account creation failed");
			test.log(Status.INFO, "Account is not created");
		}
		
	}
	
	@Test(priority=3)
	public void DeleteanAccount() {
		test=extent.createTest("Account Deletion Test");
		test.log(Status.INFO, "Account will be deleted");
		WebDriverWait wai = new WebDriverWait(driver, 30);
		WebElement Opptylink = driver.findElement(By.xpath("//*[@title='Opportunities']"));
		WebElement accountlink = getElement("AccounttopHatLink");
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", Opptylink);
		executor.executeScript("arguments[0].click();", accountlink);
		waitfor(2000);
		WebElement AccountName = getElement("AccountlistTitle");
		wai.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div/table/tbody/tr[1]/th/span/a"))));
		String AccountText=AccountName.getText();
		if(AccountText.equalsIgnoreCase(prop.getProperty("AccountName"))) {
			driver.findElement(By.xpath("//div/div/table/tbody/tr[1]/th/span/a/../..//following::td[4]/span/div")).click();
			driver.findElement(By.xpath("//ul[@class='scrollable']/li[2]/a[@title='Delete']")).click();
			waitfor(3000);
			wai.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[text()='Delete']")))).click();
			waitfor(2000);
			if(!AccountText.equalsIgnoreCase(prop.getProperty("AccountName"))) {
				test.pass("Account is Deleted Successfully");
				test.log(Status.INFO, "Account is deleted successfully");
			}else {
				test.fail("Account Deletion Failed");
			}
		}else {
			test.fail("Account Deletion Failed");
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
