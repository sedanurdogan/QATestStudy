package org.hbcases.automation.tests;

import java.io.IOException;

import java.util.ArrayList;

import org.hbcases.automation.base.InitiateDriver;
import org.hbcases.automation.pages.HomepagePOM;
import org.hbcases.automation.pages.LoginPOM;
import org.hbcases.automation.pages.ProductPOM;
import org.hbcases.automation.pages.shoppingCartPOM;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_01_AddingProduct extends InitiateDriver {

	@Test
	public void addingSameProductwithLoginCase() throws IOException, InterruptedException {

		HomepagePOM homepagepom = new HomepagePOM(driver, 30);
		ProductPOM productpom = new ProductPOM(driver, 30);
		LoginPOM loginpom = new LoginPOM(driver, 30);
		shoppingCartPOM shoppingcartpom = new shoppingCartPOM(driver, 30);

		String firstSeller = null, secondSeller = null;

		homepagepom.directingLoginPage();
		loginpom.enterUserInfo();

		homepagepom.searchProduct();
		String navigationalUrl = homepagepom.selectProduct();

		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));

		firstSeller = productpom.getProductSellerName();
		productpom.addProductToCart();
		productpom.closeCheckOutWindow();
		Assert.assertEquals(productpom.checkOtherSellers(), true, "there is no other seller");
		productpom.addSecondProductToChart();
		secondSeller = productpom.getSecondProductSellerName();
		productpom.closeCheckOutWindow();
		homepagepom.gotoShoppingCart();

		Assert.assertEquals(driver.getTitle().toLowerCase(), "sepetim", "Page does not reach shopping cart URL");
		shoppingcartpom.CheckProductList(firstSeller, secondSeller);

	}

	@Test
	public void addingSameProductwithoutLoginCase() throws IOException, InterruptedException {

		HomepagePOM homepagepom = new HomepagePOM(driver, 30);
		ProductPOM productpom = new ProductPOM(driver, 30);
		shoppingCartPOM shoppingcartpom = new shoppingCartPOM(driver, 30);

		String firstSeller = null, secondSeller = null;

		homepagepom.searchProduct();
		String navigationalUrl = homepagepom.selectProduct();

		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));

		firstSeller = productpom.getProductSellerName();
		productpom.addProductToCart();
		productpom.closeCheckOutWindow();
		Assert.assertEquals(productpom.checkOtherSellers(), true, "there is no other seller");
		productpom.addSecondProductToChart();
		secondSeller = productpom.getSecondProductSellerName();
		productpom.closeCheckOutWindow();
		homepagepom.gotoShoppingCart();

		Assert.assertEquals(driver.getTitle().toLowerCase(), "sepetim", "Page does not reach shopping cart URL");
		shoppingcartpom.CheckProductList(firstSeller, secondSeller);

	}

}
