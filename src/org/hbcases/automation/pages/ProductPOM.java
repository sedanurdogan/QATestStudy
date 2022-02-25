package org.hbcases.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProductPOM {

	@FindBy(xpath = "//span[@class='seller']//a")
	WebElement productSellerName;

	@FindBy(xpath = "//span[@class='addToCartButton']//button")
	WebElement addButton;

	@FindBy(id = "cartItemCount")
	WebElement itemCount;

	@FindBy(xpath = "//td[@id='merchantTabTrigger']//span[@class='campaignText']")
	// div[@class='other-sellers']//a[@class='optionsLength']
	WebElement otherSellersCount;

	@FindBy(className = "checkoutui-Modal-1k7QD")
	WebElement modalWindow;
	
	@FindBy(xpath="//span[@class='checkoutui-ProductOnBasketHeader-22Wrk']")
	WebElement modalWindowAddText;
	
	

	WebDriver driver;
	WebDriverWait wait;
	Actions action;
	ChromeOptions options = new ChromeOptions();

	public ProductPOM(WebDriver driver, int waitTime) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, waitTime);
		action = new Actions(this.driver);
		options.addArguments("–disable-notifications");
	}

	public String getProductSellerName() {

		return wait.until(ExpectedConditions.elementToBeClickable(productSellerName)).getText();
	}

	public void addProductToCart() {

		System.out.println(addButton.getText());
		wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
		;
		Assert.assertNotEquals(wait.until(ExpectedConditions.elementToBeClickable(itemCount)).getText(), 0,
				"Item is not added");

	}

	public boolean checkOtherSellers() {

		return otherSellersCount.isDisplayed();

//		System.out.println(sellercount.split(" ")[0]);
//		System.out.println(sellercount.split(" ")[1]);
//		
//		System.out.println(sellercount.split(" ")[1].replace("(","").replace(")",""));}
//		
//		else {
//			System.out.println("diger satici yok");
//		}
	}

	public void closeCheckOutWindow() {

		wait.until(ExpectedConditions.elementToBeClickable(modalWindow));
		if (modalWindow.isDisplayed()) {
			
			driver.switchTo().activeElement();

			
			//*[@id="AddToCart_96fd0c0d-aff0-475f-9261-bb48fd3fdbb2"]/div/div/div/div/div/div[1]/div/div[1]/div/div[1]/div/span
			wait.until(ExpectedConditions.visibilityOf(modalWindowAddText));
			Assert.assertEquals(modalWindowAddText.getText().trim().toLowerCase(), "ürün sepetinizde", "The product does not added");

			WebElement modalWindowCloseButton = modalWindow.findElement(By.className("checkoutui-Modal-2iZXl"));
			modalWindowCloseButton.click();
		} else {
			
			System.out.println("hello cinims");

		}

	}

}
