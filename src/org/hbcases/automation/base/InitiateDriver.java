package org.hbcases.automation.base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class InitiateDriver {

	public WebDriver driver;

	@BeforeMethod
	public void StartBrowser() throws IOException {

		if (configurationReader.appConfigurationReader("BrowserName").equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
			ChromeOptions o = new ChromeOptions();
			o.addArguments("--disable-notifications");
		} else if (configurationReader.appConfigurationReader("BrowserName").equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", ".\\driver\\geckodriver.exe");
			driver = new FirefoxDriver();

		} else {
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();
		driver.get(configurationReader.appConfigurationReader("UrlName"));
	}

	@AfterMethod
	public void closeBrowser() {

		driver.quit();

	}

}
