package org.hbcases.automation.pages;

import java.io.IOException;

import org.hbcases.automation.base.configurationReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomepagePOM {
	
	@FindBy(id="myAccount")
	WebElement myAccount; 
	
	@FindBy(id="login")
	WebElement loginLink; 
	
	@FindBy(xpath="//input[@placeholder='Ürün, kategori veya marka ara']")
	WebElement searchBox;
	
	@FindBy(className = "SearchBoxOld-buttonContainer")
	WebElement searchButton;
	
	@FindBy(xpath="//li[@class='productListContent-item' and @id='i0']")
	WebElement product;
	
	@FindBy(xpath="//li[@class='productListContent-item' and @id='i0']//a")
	WebElement productUrl;
	
	WebDriver driver;
	WebDriverWait wait;
	Actions action;
	ChromeOptions options = new ChromeOptions();
	
	
	public HomepagePOM(WebDriver driver, int waitTime) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, waitTime);
		action = new Actions(this.driver);
		options.addArguments("–disable-notifications");
	}
	
	public void directingLoginPage() {
		
		action.moveToElement(myAccount).perform();
		wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
		Assert.assertEquals(driver.getTitle(), "Üye Giriş Sayfası & Üye Ol - Hepsiburada",
				"Yönlendirilen Sayfa Yanlıs");
		
	}
	
	
	
	public void searchProduct() throws IOException {
		
		wait.until(ExpectedConditions.elementToBeClickable(searchBox)).sendKeys(configurationReader.appConfigurationReader("SearchedProduct"));
		wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
		String expectedTitle= configurationReader.appConfigurationReader("SearchedProduct")+" - Hepsiburada";
		wait.until(ExpectedConditions.elementToBeClickable(product));
		Assert.assertEquals(driver.getTitle(), expectedTitle, "Search product failed");		
	}
	
	public String selectProduct() {
		
		//String productUrl;
		action.moveToElement(product).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(product)).click();
		return productUrl.getAttribute("href");
		
	}
	
	

}
