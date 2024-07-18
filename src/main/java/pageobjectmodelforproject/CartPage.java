package pageobjectmodelforproject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonmethods.AbstractReuseMethods;

public class CartPage extends AbstractReuseMethods{
	 WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".cart h3")
	List<WebElement> allcartItems;
	@FindBy(css=".totalRow button")
	WebElement checkout;
	By cartItemsvisible =By.cssSelector(".cart h3");
	
   public List<WebElement> cartItems() {
	   waitUntilvisibilityOfElement(cartItemsvisible);
	   return allcartItems;
   }
   public Boolean compareCartitemsToAddcartitems(String productname) {
	   List<WebElement> itemsinCart =cartItems();
	   Boolean booleanvalueReturn =itemsinCart.stream().anyMatch(cartname->cartname.getText().contentEquals(productname));
	  return booleanvalueReturn;
   }
   public PaymentOfOrderPage checkOut() {
	   checkout.click();
	   PaymentOfOrderPage PaymentOfOrderPage = new PaymentOfOrderPage(driver);
	   return PaymentOfOrderPage;
   }
}
