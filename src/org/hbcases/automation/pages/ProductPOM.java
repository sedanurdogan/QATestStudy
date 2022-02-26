package org.hbcases.automation.pages;

import org.openqa.selenium.By;
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

	@FindBy(xpath = "//span[@class='checkoutui-ProductOnBasketHeader-22Wrk']")
	WebElement modalWindowAddText;

	@FindBy(className = "hb-toast-text")
	WebElement checoutText;

	@FindBy(xpath = "(//table//tr//td[@class='form-area']//button[@class='add-to-basket button small'])[1]")
	WebElement secondproductAddButton;

	@FindBy(xpath = "(//table//tr/td[@class='shipping-and-campaigns']//a)[1]")
	WebElement secondProductSellerName;

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

		wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();

	}

	public boolean checkOtherSellers() {

		return otherSellersCount.isDisplayed();

	}

	public void closeCheckOutWindow() throws InterruptedException {

		boolean status = false;

		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(checoutText));

			status = true;

			Assert.assertEquals(checoutText.getText().trim().toLowerCase(), "ürün sepete eklendi",
					"The product does not added");

		} catch (Exception e) {

		}

		if (status == false)

		{
			wait.until(ExpectedConditions.elementToBeClickable(modalWindow));

			if (modalWindow.isDisplayed()) {

				driver.switchTo().activeElement();

				wait.until(ExpectedConditions.visibilityOf(modalWindowAddText));
				Assert.assertEquals(modalWindowAddText.getText().trim().toLowerCase(), "ürün sepetinizde",
						"The product does not added");

				WebElement modalWindowCloseButton = modalWindow.findElement(By.className("checkoutui-Modal-2iZXl"));
				modalWindowCloseButton.click();
			}
		}

	}

	public void addSecondProductToChart() {

		wait.until(ExpectedConditions.elementToBeClickable(secondproductAddButton)).click();
	}

	public String getSecondProductSellerName() {

		return wait.until(ExpectedConditions.elementToBeClickable(secondProductSellerName)).getText();
	}

}
