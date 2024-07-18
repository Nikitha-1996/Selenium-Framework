package pageobjectmodelforproject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonmethods.AbstractReuseMethods;

public class ProductCataloguePage extends AbstractReuseMethods{
 WebDriver driver;
	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css =".mb-3")
			List<WebElement> allProducts;
	By products = By.cssSelector(".mb-3");
	@FindBy(css=".ng-animating")
	WebElement spinner;
	By addtoCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.id("toast-container");
	By productele =By.cssSelector(".card-body b");

	
	public List<WebElement> getProductsListintheCart() {
		waitUntilvisibilityOfElement(products);
		return allProducts;
	}
	public WebElement compareProductName(String expectedProductName) {
		WebElement prod =allProducts.stream().filter(product->
		product.findElement(productele).getText().contentEquals(expectedProductName)).findFirst().orElse(null);
		return prod;
	}
	public CartPage addCart(String expectedProductName) {
		WebElement prod=compareProductName(expectedProductName);
		prod.findElement(addtoCart).click();
		waitUntilvisibilityOfElement(toastMessage);
		waitUntilinvisiblityof(spinner);
		CartPage CartPage = new CartPage(driver);
		return CartPage;
	}

}
