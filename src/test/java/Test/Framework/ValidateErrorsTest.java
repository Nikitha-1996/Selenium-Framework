package Test.Framework;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.RetryFailedTestcases;
import TestComponents.TestBase;
import pageobjectmodelforproject.CartPage;
import pageobjectmodelforproject.ProductCataloguePage;


public class ValidateErrorsTest extends TestBase{
	@Test(groups= {"ErrorValdation"}, retryAnalyzer=RetryFailedTestcases.class)
	public void loginValidtionMessage() {
		ProductCataloguePage productsCat = loginPage.landing("qatest@yopmail.com", "Test@123");
		Assert.assertEquals("Incorrect email or password.", loginPage.getError());
	}
	@Test
	public void productnotfoundinCart() {
		String productname = "IPHONE 13 PRO";
		//String countryname = "india";
		ProductCataloguePage productsCat = loginPage.landing("qatest18@yopmail.com", "Test@123");
		List<WebElement> Listproducts = productsCat.getProductsListintheCart();
		productsCat.compareProductName(productname);
		CartPage CartPage = productsCat.addCart(productname);
		productsCat.cart();
		List<WebElement> cartItems = CartPage.cartItems();
		Boolean value = CartPage.compareCartitemsToAddcartitems("IPHONE 13 PRO data");
		Assert.assertFalse(value);
	}
}
