package org.hbcases.automation.pages;

import java.io.IOException;

import org.hbcases.automation.base.configurationReader;
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

public class LoginPOM {

	@FindBy(id = "myAccount")
	WebElement myAccount;

	@FindBy(xpath = "//*[@id=\"txtUserName\"]")
	WebElement userName;

	@FindBy(id = "btnLogin")
	WebElement loginButon;

	@FindBy(xpath = "//*[@id=\"txtPassword\"]")
	WebElement password;

	@FindBy(xpath = "//*[@id=\"btnEmailSelect\"]")
	WebElement signInButon;

	@FindBy(xpath = "//div[@id='myAccount']//span[@class='sf-OldMyAccount-PhY-T']")
	WebElement myAccountInfo;

	WebDriver driver;
	WebDriverWait wait;
	Actions action;
	ChromeOptions options = new ChromeOptions();

	public LoginPOM(WebDriver driver, int waitTime) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, waitTime);
		action = new Actions(this.driver);
		options.addArguments("–disable-notifications");
	}

	public void enterUserInfo() throws IOException {

		wait.until(ExpectedConditions.elementToBeClickable(userName))
				.sendKeys(configurationReader.appConfigurationReader("UserEmail"));
		wait.until(ExpectedConditions.elementToBeClickable(loginButon)).click();

		boolean checkemail = driver
				.findElements(By.xpath(
						"//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div/div[2]/div[2]/div/div/form/div/div[1]/div[2]"))
				.isEmpty();
		Assert.assertTrue(checkemail, "Wrong User email");

		wait.until(ExpectedConditions.elementToBeClickable(password))
				.sendKeys(configurationReader.appConfigurationReader("UserPassword"));
		wait.until(ExpectedConditions.elementToBeClickable(signInButon)).click();

		try {
			if (new WebDriverWait(driver, 5)
					.until(ExpectedConditions.elementToBeClickable(
							By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div/div[4]/div/div/div[1]/div[2]")))
					.isDisplayed()) {
				Assert.fail("Wrong password");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		wait.until(ExpectedConditions.elementToBeClickable(myAccountInfo));
		Assert.assertEquals(myAccountInfo.getText().trim().toLowerCase(), "hesabım", "Giriş Başarısız");

	}

}
