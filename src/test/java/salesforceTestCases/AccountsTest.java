package salesforceTestCases;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import salesforce.util.BaseClass;


public class AccountsTest extends BaseClass{
	
	
	@BeforeClass
	public void initialize() {
		init("chrome");
		driver.get("https://ap4.lightning.force.com/lightning/page/home");
	}
	
	@Test(priority=1)
	public void goToAccountPage() {
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys("madhav.gaikwad@acc.com");
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("p@sword123");	
		driver.findElement(By.xpath("//*[@id='Login']")).click();
		WebElement accountlink = driver.findElement(By.xpath("//*[@title='Accounts']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", accountlink);
	}
	
	@Test(priority=2)
	public void createanAccount() {
		driver.findElement(By.xpath("//*[@id='brandBand_1']/div/div[1]/div[2]/div/div/div[1]/div[1]/div[2]/ul/li[1]/a")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 40);
		//Active dropdown
		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//span[contains(text(),'Active')]/..//following::div[1]/div/div/div/a"));
		act.moveToElement(ele).click().perform();
		driver.findElement(By.xpath("//li[@class='uiMenuItem uiRadioMenuItem']//following::a[2]")).click();
		//List<WebElement> elem = driver.findElements(By.xpath("//li[@class='uiMenuItem uiRadioMenuItem']/a"));
		//for(int i=0; i<elem.size();i++) {
		//if(elem.get(i).getText().contains("Yes")) {
				//.getAttribute("title").contains("Yes")) {
			//elem.get(i).click();
			//System.out.println("Element is clicked");
			//break;
		//}
		//Parent Account lookup
		//Account Name 

		driver.findElement(By.xpath("//*[@class='label inputLabel uiLabel-left form-element__label uiLabel']/span[contains(text(),'Account Name')]/..//following-sibling::input")).sendKeys("TestAccount1");
		driver.findElement(By.xpath("//input[@class=' default input uiInput uiInputTextForAutocomplete uiInput--default uiInput--input uiInput uiAutocomplete uiInput--default uiInput--lookup']")).sendKeys("Bank of America");
		//driver.findElement(By.xpath("//*[@class='label inputLabel uiLabel-left form-element__label uiLabel']/span[contains(text(),'Parent Account')]/..//following::div[1]")).sendKeys("ame");
		
		//TO select account from dropdown
		String accountlookup = "//ul/li[@class='lookup__item  default uiAutocompleteOption forceSearchInputLookupDesktopOption']";
		wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//ul/li[@class='lookup__item  default uiAutocompleteOption forceSearchInputLookupDesktopOption']"), 1));
		/*	
		String AccountLookup="//*[@class='test-id__section-content slds-section__content section__content']/div/div[3]/div[1]/div/div/div/div/div/div[1]/div/div/div[2]/ul/li[";
		List<WebElement> Accounts = driver.findElements(By.xpath("//ul/li[@class='lookup__item  default uiAutocompleteOption forceSearchInputLookupDesktopOption']"));
		System.out.println(Accounts.size());
		for(int j=1; j<=Accounts.size();j++) {
			String xpath=AccountLookup+j+"]/a/div[2]";
			System.out.println(xpath);
			WebElement Account=driver.findElement(By.xpath(AccountLookup+j+"]/a/div[2]"));
			System.out.println(Account.getText());
		if(Account.getText().contains("Bank of America"))
			Account.click();
		} */
	
		driver.findElement(By.xpath("//ul/li[@class='lookup__item  default uiAutocompleteOption forceSearchInputLookupDesktopOption']")).click();
		
		//Select SLA from dropdown
		act.moveToElement(driver.findElement(By.xpath("//*[text()='SLA Serial Number']"))).perform();
		driver.findElement(By.xpath("//*[text()='SLA']//following::div[1]")).click();
		driver.findElement(By.xpath("//*[@class='select-options']/ul/li[4]/a")).click();
		
		driver.findElement(By.xpath("//*[@class='form-element']")).click();
		driver.findElement(By.xpath("//div/div[2]/table/tbody/tr[3]/td[5]/span")).click();
		driver.findElement(By.xpath("//*[text()='SLA Serial Number']/..//following::input[1]")).sendKeys("Aewfbr5431");
		driver.findElement(By.xpath("//div/div/div[2]/div/div/button[3]/span")).click();
		
		
		
	}
	
	@AfterClass
	public void tearDown(){
		driver.quit();
	}
	
}
