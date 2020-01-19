package salesforce.util;

public class SalesforceConstants {
	
	public static final String Chrome_Driver_Exe= System.getProperty("user.dir")+"\\chromedriver.exe";
	public static final String Firefox_Driver_Exe= "C:\\Selenium Files\\geckodriver.exe";
	public static final String IE_Driver_Exe= "C:\\Selenium Files\\IEDriverServer.exe";
	
	public static String ScreenshotFilePath= System.getProperty("user.dir")+"\\ReportsandScreenshots\\LoginReport.html";
	public static String PropertiesFile= System.getProperty("user.dir")+"\\src\\main\\java\\salesforce\\util\\common.properties";

	public static String LoginPagetitle="Login | Salesforce";
	public static String HomePagetitle="Lightning Experience";
	public static String OrgName="Sales";
	
}
