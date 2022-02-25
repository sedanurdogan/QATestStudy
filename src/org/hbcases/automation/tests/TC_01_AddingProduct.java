package org.hbcases.automation.tests;

import java.io.IOException;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hbcases.automation.base.InitiateDriver;
import org.hbcases.automation.base.configurationReader;
import org.hbcases.automation.pages.HomepagePOM;
import org.hbcases.automation.pages.LoginPOM;
import org.hbcases.automation.pages.ProductPOM;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_01_AddingProduct extends InitiateDriver {

//	/@Test
	public void Login() throws IOException {
	
		
		HomepagePOM homepagepom = new HomepagePOM(driver, 30);
		homepagepom.directingLoginPage();
		
		LoginPOM loginpom = new LoginPOM(driver, 30);
		loginpom.enterUserInfo();
		

	}

	@Test
	public void searchProduct() throws IOException, InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		HomepagePOM homepagepom = new HomepagePOM(driver, 30);
		ProductPOM productpom = new ProductPOM(driver, 30);
		LoginPOM loginpom = new LoginPOM(driver, 30);
		
		 
		String firstSeller=null, secondSeller= null; 
		

		homepagepom.directingLoginPage();
		loginpom.enterUserInfo();
		
		homepagepom.searchProduct();
		String navigationalUrl = homepagepom.selectProduct();
		
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
	   
		
		firstSeller=productpom.getProductSellerName();
		productpom.addProductToCart();
		productpom.closeCheckOutWindow();
		Assert.assertEquals(productpom.checkOtherSellers(),true,"there is no other seller");

		driver.quit();

	}
	

	public void deneme() {
		
		ProductPOM productpom = new ProductPOM(driver, 30);
		productpom.checkOtherSellers();
		Assert.assertEquals(productpom.checkOtherSellers(),true,"there is no other seller");
		
	}

}
