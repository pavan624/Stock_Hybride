package Driverfactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import CommonFunLibrary.FunctionLibrary;
import Utilities.ExcelFileUtil;

public class Driverscript {

	WebDriver driver;
	@Test
	public void start() throws Exception {
		System.out.println("print");
		
ExcelFileUtil excel=new ExcelFileUtil();

	for(int i=1;i<=excel.rowCount("MasterTestCases");i++)
	{
	String ModuleStatus="";
	if(excel.getData("MasterTestCases", i, 2).equalsIgnoreCase("y"))
	{
		
	String TCModule=excel.getData("MasterTestCases", i, 1);

	
	for(int j=1;j<=excel.rowCount(TCModule);j++)
	{
		
	
String Description=excel.getData(TCModule, j, 0);
String Object_Type=excel.getData(TCModule, j, 1);
String Locator_Type=excel.getData(TCModule, j, 2);
String Locator_Value=excel.getData(TCModule, j, 3);
String Test_Data=excel.getData(TCModule, j, 4);
try{
	if(Object_Type.equalsIgnoreCase("startBrowser"))
	{
	driver=FunctionLibrary.startBrowser();
	System.out.println("browser start");

	}
	else if(Object_Type.equalsIgnoreCase("openApplication"))
	{
		FunctionLibrary.openApplication(driver);
		System.out.println("openApplication");
		
	}
	else if(Object_Type.equalsIgnoreCase("typeAction"))
	{
	FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
	System.out.println("browser start");
	
	}
	else if(Object_Type.equalsIgnoreCase("clickAction"))
	{
		FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
		
	}
	else if(Object_Type.equalsIgnoreCase("waitForElement"))
	{
	FunctionLibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);	
	
	}
		
	else if(Object_Type.equalsIgnoreCase("closeBrowser"))
	{
		FunctionLibrary.closeBrowser(driver);
		
	}
	
	//write as pass into status column
	excel.setData(TCModule, j, 5, "PASS");
	
	ModuleStatus="true";
	}catch(Exception e)
{
	System.out.println("exception handled");
File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
FileUtils.copyFile(srcFile, new File("D:\\ypavanprograms\\Stock_Accounting_MVN\\Screens\\"
+Description+FunctionLibrary.generateDate()+".png"));		
excel.setData(TCModule, j, 5, "FAIL");

ModuleStatus="false";
break;
}
if(ModuleStatus.equalsIgnoreCase("true"))
{
	excel.setData("MasterTestCases", i, 3, "PASS");
}
else
{
	if(ModuleStatus.equalsIgnoreCase("False"))
	{
	excel.setData("MasterTestCases", i, 3, "FAIL");	
	}
}

}
}
else{

excel.setData("MasterTestCases", i, 3, "Not Executed");
		
	}
	}
	}
	
	
}
