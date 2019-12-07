package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtil {

	public static String getValueForKey(String key) throws Exception
    {
    	
    	Properties configProperties=new Properties();
    	FileInputStream fi=new FileInputStream("D:\\ypavanprograms\\Stock_Accounting\\PropertiesFile\\Repositry.Properties");
    	configProperties.load(fi);
	     return (String) configProperties.get(key);
    }
	



}
