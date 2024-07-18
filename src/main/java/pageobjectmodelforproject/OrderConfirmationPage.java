package pageobjectmodelforproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonmethods.AbstractReuseMethods;

public class OrderConfirmationPage extends AbstractReuseMethods{
	 WebDriver driver;
	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMessageEle;
	By confirmationMessage =By.cssSelector(".hero-primary");
	
	public String orderConfirmationMessage() {
		waitUntilvisibilityOfElement(confirmationMessage);
		String message =confirmationMessageEle.getText();
		return message;
	}
}
