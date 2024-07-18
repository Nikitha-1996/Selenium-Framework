package pageobjectmodelforproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonmethods.AbstractReuseMethods;

public class LoginPage extends AbstractReuseMethods {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="#userEmail")
	WebElement useremail;
	@FindBy(id="userPassword")
	WebElement passwordEle;
	@FindBy(id="login")
	WebElement loginEle;
	@FindBy(css="[class*=toast-message]")
	WebElement loginerrormessage;

	public void goToURL() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	public ProductCataloguePage landing(String email, String password) {
		useremail.sendKeys(email);
		passwordEle.sendKeys(password);
		loginEle.click();
		ProductCataloguePage productsCat = new ProductCataloguePage(driver);
        return productsCat;
	}
	public String getError() {
		waitUntilvisibilityOfElement(loginerrormessage);
		return loginerrormessage.getText();
	}
}