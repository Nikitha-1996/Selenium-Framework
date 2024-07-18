package Test.Framework;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.TestBase;
import pageobjectmodelforproject.CartPage;
import pageobjectmodelforproject.OrderConfirmationPage;
import pageobjectmodelforproject.PaymentOfOrderPage;
import pageobjectmodelforproject.ProductCataloguePage;
import pageobjectmodelforproject.YourOrdersPage;

public class ProductPurchasedTest extends TestBase {
	String productname = "IPHONE 13 PRO";

	@Test(dataProvider = "dataSetsforTestcases", groups="purchasing")
	public void main(HashMap<String,String> inputData) throws IOException {

		// String countryname = "india";
		ProductCataloguePage productsCat = loginPage.landing(inputData.get("email"),inputData.get("password")); //1
		List<WebElement> Listproducts = productsCat.getProductsListintheCart(); //2
		productsCat.compareProductName(inputData.get("product"));
		CartPage CartPage = productsCat.addCart(productname);
		productsCat.cart(); //2
		List<WebElement> cartItems = CartPage.cartItems(); //3
		Boolean value = CartPage.compareCartitemsToAddcartitems(productname);
		Assert.assertTrue(value);
		PaymentOfOrderPage PaymentOfOrderPage = CartPage.checkOut();
		PaymentOfOrderPage.autosuggestiveDropdown("india");
		OrderConfirmationPage OrderConfirmationPage = PaymentOfOrderPage.placeOrder();//3
		String message = OrderConfirmationPage.orderConfirmationMessage();
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
	}

	@Test(dependsOnMethods = { "main" })
	public void orderpageTest() {
		ProductCataloguePage productsCat = loginPage.landing("qatest1@yopmail.com", "Test@123");
		YourOrdersPage yourOrderPage = productsCat.order();
		Boolean ordervalue = yourOrderPage.orderVerify(productname);
		Assert.assertTrue(ordervalue);
	}

	@DataProvider
	public Object[][] dataSetsforTestcases() throws IOException {
		List<HashMap<String,String>> hashmapinputs=dataReadfromJSON(System.getProperty("user.dir") + "\\src\\test\\java\\data\\data.json");
		return new Object[][] { {hashmapinputs.get(0)},
			{hashmapinputs.get(1)} };
	}

}
