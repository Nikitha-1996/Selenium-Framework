package commonmethods;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjectmodelforproject.YourOrdersPage;

public class AbstractReuseMethods {
  WebDriver driver;
	public AbstractReuseMethods(WebDriver driver) {
		this.driver=driver;
	}
	@FindBy(css="[routerlink*='cart'] label")
	WebElement cartheader;
	@FindBy(css=".cart h3")
	WebElement cartItems;
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderbutton;
	By checkbuttonele = By.cssSelector(".cart h3");
	
	public void waitUntilvisibilityOfElement(By waituntilProductVisible ) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
		wait.until(ExpectedConditions.visibilityOfElementLocated(waituntilProductVisible));
	}
	
	public void waitUntilinvisiblityof(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	public void waitUntilvisibilityOfElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	public void cart() {
		cartheader.click();
		waitUntilvisibilityOfElement(cartItems);
	}
	public YourOrdersPage order() {
		orderbutton.click();
		YourOrdersPage yourOrderPage = new YourOrdersPage(driver);
		return yourOrderPage;
	}
		
	}
