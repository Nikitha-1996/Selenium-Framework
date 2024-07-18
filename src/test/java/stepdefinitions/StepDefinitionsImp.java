package stepdefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import TestComponents.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjectmodelforproject.CartPage;
import pageobjectmodelforproject.LoginPage;
import pageobjectmodelforproject.OrderConfirmationPage;
import pageobjectmodelforproject.PaymentOfOrderPage;
import pageobjectmodelforproject.ProductCataloguePage;

public class StepDefinitionsImp extends TestBase {
	public LoginPage loginPage;
	public ProductCataloguePage productsCat;
	public CartPage CartPage;
	public PaymentOfOrderPage PaymentOfOrderPage;
	public OrderConfirmationPage OrderConfirmationPage;
	
	@Given("I Landed on the E-commerce page")
	public void I_Landed_on_the_E_commerce_page() throws IOException {
		loginPage = launchapplication();
	}
	@Given("logged in to the E-commerce application with user name {string} and password {string}")
	public void logged_with_username_password(String email, String password) {
		productsCat = loginPage.landing(email, password);
	}
	@When("Adding the product {string} to the cart")
	public void Adding_product_to_cart(String productName) {
		List<WebElement> Listproducts = productsCat.getProductsListintheCart();
		productsCat.compareProductName(productName);
		CartPage = productsCat.addCart(productName);
		productsCat.cart();
	}
	@When("Checkout the product {string} place the order")
	public void checkout_the_product_place_the_order(String productName) {
		List<WebElement> cartItems  = CartPage.cartItems(); 
		Boolean value = CartPage.compareCartitemsToAddcartitems(productName);
		Assert.assertTrue(value);
		PaymentOfOrderPage = CartPage.checkOut();
		PaymentOfOrderPage.autosuggestiveDropdown("india");
		OrderConfirmationPage = PaymentOfOrderPage.placeOrder();
	}
	@Then("confirmation message {string} visible")
	public void confirmation_message_visible(String string) {
		String message = OrderConfirmationPage.orderConfirmationMessage();
		Assert.assertTrue(message.equalsIgnoreCase(string));
		driver.quit();
	}
	@Then("error message {string} is visible")
	public void error_message_visible(String erromessage) {
		Assert.assertEquals(erromessage, loginPage.getError());
		driver.quit();
	}
	
}
