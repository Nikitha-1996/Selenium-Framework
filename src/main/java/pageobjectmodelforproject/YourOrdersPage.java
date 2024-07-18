package pageobjectmodelforproject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonmethods.AbstractReuseMethods;



public class YourOrdersPage extends AbstractReuseMethods{
	 WebDriver driver;
	 
		public YourOrdersPage(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(xpath="//tr/td[2]")
		List<WebElement> allcartItems;
		
		public Boolean orderVerify(String productName) {
		Boolean ordervalue =	allcartItems.stream().anyMatch(o->o.getText().equalsIgnoreCase(productName));
		return ordervalue;
		}
		
}
