package org.hbcases.automation.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class shoppingCartPOM {

	@FindBy(xpath = "//div[@class='merchantName_36DkG']//a")
	List<WebElement> sellerNameList;

	WebDriver driver;
	WebDriverWait wait;

	public shoppingCartPOM(WebDriver driver, int waitTime) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, waitTime);
	}

	public void CheckProductList(String firstProductSeller, String secondProductSeller) {

		boolean isaddedFirst = false;
		boolean isaddedSecond = false;

		wait.until(ExpectedConditions.visibilityOfAllElements(sellerNameList));
		for (WebElement webElement : sellerNameList) {

			if (webElement.getText().equalsIgnoreCase(firstProductSeller))
				isaddedFirst = true;

			else if (webElement.getText().equalsIgnoreCase(secondProductSeller))
				isaddedSecond = true;

		}

		if (!(isaddedFirst == true && isaddedSecond == true)) {
			Assert.fail("Products are does not added to shopping cart");
		}

	}

}
