package org.hbcases.automation.tests;

import java.io.IOException;
import java.sql.Driver;
import java.util.List;
import java.util.Set;

import org.hbcases.automation.base.InitiateDriver;
import org.hbcases.automation.base.configurationReader;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_01_AddingProduct extends InitiateDriver  {
	
	//@Test
	public void Login() throws IOException {
		// TODO Auto-generated method stub

		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement myAccountButton = driver.findElement(By.id("myAccount"));
		myAccountButton.click();
		
		
		WebElement loginLink =  driver.findElement(By.id("login"));
		wait.until(ExpectedConditions.elementToBeClickable(loginLink));
		loginLink.click();
		
		Assert.assertEquals(driver.getTitle(), "Üye Giriş Sayfası & Üye Ol - Hepsiburada", "Yönlendirilen Sayfa Yanlıs");
	
		

		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"txtUserName\"]")));
		WebElement userName =  driver.findElement(By.xpath("//*[@id=\"txtUserName\"]"));
		userName.sendKeys(configurationReader.appConfigurationReader("UserEmail"));
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnLogin")));
		WebElement loginButon = driver.findElement(By.id("btnLogin"));
		loginButon.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"txtPassword\"]")));
		WebElement password = driver.findElement(By.xpath("//*[@id=\"txtPassword\"]"));
		password.sendKeys(configurationReader.appConfigurationReader("UserPassword"));
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"btnEmailSelect\"]")));
		WebElement signInButon = driver.findElement(By.xpath("//*[@id=\"btnEmailSelect\"]"));
		signInButon.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"myAccount\"]/span/a/span[2]")));
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"myAccount\"]/span/a/span[2]")).getText(),"Sedanur Doğan","Giriş Başarısız" );
		
		
	}

	@Test
	public void searchProduct() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		WebElement searchBoxText = driver.findElement(By.xpath("//*[@id=\"SearchBoxOld\"]/div/div/div[1]/div[2]/input"));
		
		searchBoxText.sendKeys(configurationReader.appConfigurationReader("SearchedProduct"));
		
		driver.findElement(By.xpath("//*[@id=\"SearchBoxOld\"]/div/div/div[2]")).click();
	
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String productUrl;
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"i0\"]")));
		WebElement product = driver.findElement(By.xpath("//*[@id=\"i0\"]"));
		
		productUrl = driver.findElement(By.xpath("//*[@id=\"i0\"]/div/a")).getAttribute("href");
				//product.getAttribute("href");
		
		System.out.println(productUrl);
	
		product.click();
		
		driver.navigate().to(productUrl);
	
		String productSellerName  =driver.findElement(By.xpath("/html/body/div[2]/main/div[3]/section[1]/div[4]/div/div[4]/div[1]/div[2]/div/div[1]/div[4]/div[1]/span/span[2]")).getText();
		
		System.out.println(productSellerName);
	
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"addToCart\"]")));
		driver.findElement(By.xpath("//*[@id=\"addToCart\"]")).click();
		System.out.println("sepete eklendi.");
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("checkoutui-Modal-1k7QD")));
		WebElement modalWindow = driver.findElement(By.className("checkoutui-Modal-1k7QD"));	
		
		driver.switchTo().activeElement();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"AddToCart_9202e5b8-6163-4537-3404-52c23d98a7d9\"]/div/div/div/div/div/div[1]/div/div[1]/div/div[1]/div/span")));
		Assert.assertEquals(modalWindow.findElement(By.xpath("//*[@id=\"AddToCart_9202e5b8-6163-4537-3404-52c23d98a7d9\"]/div/div/div/div/div/div[1]/div/div[1]/div/div[1]/div/span")).getText(), 
				" Ürün sepetinizde", "The product does not added");
	
		WebElement modalWindowCloseButton = modalWindow.findElement(By.className("checkoutui-Modal-2iZXl"));
		modalWindowCloseButton.click();
		
		driver.quit();
		
		 

	}
	
	


}
