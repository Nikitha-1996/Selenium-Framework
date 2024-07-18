package pageobjectmodelforproject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import commonmethods.AbstractReuseMethods;

public class PaymentOfOrderPage extends AbstractReuseMethods {
	 WebDriver driver;
	public PaymentOfOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css ="[placeholder='Select Country']")
	WebElement country;
	@FindBy(xpath="//section/button[2]")
	WebElement value;
	@FindBy(xpath="//div/a[@class='btnn action__submit ng-star-inserted']")
	WebElement placeOrderEle;
	By autosuggestivebox =By.cssSelector(".form-group");
	By autosuggestionsvisibleEle =By.cssSelector(".ta-results");
	
	
	public void autosuggestiveDropdown(String countryname){
		waitUntilvisibilityOfElement(autosuggestivebox);
		Actions a = new Actions(driver);
		a.sendKeys(country,countryname).build().perform();
		waitUntilvisibilityOfElement(autosuggestionsvisibleEle);
		value.click();	
	}
	
	public OrderConfirmationPage placeOrder() {
		placeOrderEle.click();
		OrderConfirmationPage OrderConfirmationPage = new OrderConfirmationPage(driver);
		return OrderConfirmationPage;
	}

}
