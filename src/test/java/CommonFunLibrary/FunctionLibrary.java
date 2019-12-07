package CommonFunLibrary;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.PropertyFileUtil;

public class FunctionLibrary {

	static WebDriver driver;
	public static WebDriver startBrowser() throws Exception{
		
		if(PropertyFileUtil.getValueForKey("browser").equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
			driver=new ChromeDriver();
		}else if(PropertyFileUtil.getValueForKey("browser").equalsIgnoreCase("firefox")){
			
		}
		else if(PropertyFileUtil.getValueForKey("browser").equalsIgnoreCase("ie"))
		{
			
			
		}
		else{
			System.out.println("browser value not matching");
		}
	
		return driver;
				
	}
	
	
	public static void openApplication(WebDriver driver) throws Exception{
		driver.get(PropertyFileUtil.getValueForKey("url"));
		driver.manage().window().maximize();	
	}
	
	public static void waitForElement(WebDriver driver,String locatortype,String locatorvalue,String waittitme){
		
		
		WebDriverWait mywait=new WebDriverWait(driver, Integer.parseInt(waittitme));
		
		if(locatortype.equalsIgnoreCase("id")){
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorvalue)));
		}
		else if(locatortype.equalsIgnoreCase("name")){
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorvalue)));
		}else if(locatortype.equalsIgnoreCase("xpath"))
		{
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));
		}
		else
		{
				System.out.println("unable to locate for waitForElement method");
		}	
		
	}
	
	public static void typeAction(WebDriver driver,String locatortype,String locatorvalue,String testdata){
		if(locatortype.equalsIgnoreCase("id")){
			driver.findElement(By.id(locatorvalue)).clear();
			driver.findElement(By.id(locatorvalue)).sendKeys(testdata);
		}else if(locatortype.equalsIgnoreCase("xpath")){
			driver.findElement(By.xpath(locatorvalue)).clear();
			driver.findElement(By.xpath(locatorvalue)).sendKeys(testdata);
		}else if(locatortype.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorvalue)).clear();
			driver.findElement(By.name(locatorvalue)).sendKeys(testdata);
		}else{
			System.out.println("Locator not matching for typeAction method");
		}
		
	}
	
	public static void clickAction(WebDriver driver,String locatortype,String locatorvalue){
		if(locatortype.equalsIgnoreCase("id")){
			driver.findElement(By.id(locatorvalue)).click();
		}else if(locatortype.equalsIgnoreCase("xpath")){
			driver.findElement(By.xpath(locatorvalue)).click();
		}
		else if(locatortype.equalsIgnoreCase("name")){
			driver.findElement(By.name(locatorvalue)).click();
		}
	
	
	}
	
	public static void closeBrowser(WebDriver driver)
	{
	driver.close();
	}
	

	public static String generateDate(){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy_mm_ss_dd");
		return sdf.format(date);
		
	
	
	
	}
	
	


}
