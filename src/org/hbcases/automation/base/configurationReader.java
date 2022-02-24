package org.hbcases.automation.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class configurationReader {
	
	public static String appConfigurationReader(String keyword) throws IOException {
		// TODO Auto-generated method stub
		
		File file = new File(".\\config\\config.properties");
		FileReader fileReader = new FileReader(file);
		Properties propFile = new Properties();
		
		propFile.load(fileReader);
		
		return propFile.getProperty(keyword).toString();

	}

}
