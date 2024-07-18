package Test.Framework;
import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
public class SeleniumcodeStandalone {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String email= "qatest10@yopmail.com";
		String password ="Test@123";
	WebDriverManager.chromiumdriver().setup();
	WebDriver driver = new ChromeDriver();
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
	//registration
	//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	driver.manage().window().maximize();
	driver.get("https://rahulshettyacademy.com/client");
	driver.findElement(By.cssSelector(".text-reset")).click();
    driver.findElement(By.id("firstName")).sendKeys("Selenium");
	driver.findElement(By.id("lastName")).sendKeys("Qalearn");
	driver.findElement(By.id("userEmail")).sendKeys(email);
	driver.findElement(By.id("userMobile")).sendKeys("8517978586");
	WebElement elementiD = driver.findElement(By.cssSelector(".ng-pristine.ng-valid"));
	Select dropDownstatic = new Select(elementiD);
	dropDownstatic.selectByValue("2: Student");
	WebElement Checkbox =driver.findElement(By.xpath("//input[@value='Female']"));
	Checkbox.click();
	driver.findElement(By.id("userPassword")).sendKeys(password);
	driver.findElement(By.id("confirmPassword")).sendKeys(password);
	driver.findElement(By.xpath("//div[@class='col-md-1']/input")).click();
	driver.findElement(By.cssSelector("[type='submit']")).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Account Created Successfully']")));
	// successly created message capture then click login
	Assert.assertEquals(driver.findElement(By.xpath("//*[text()='Account Created Successfully']")).getText(), "Account Created Successfully");
	driver.findElement(By.cssSelector(".btn.btn-primary")).click();
	String productname ="IPHONE 13 PRO";
	//login
	driver.findElement(By.id("userEmail")).sendKeys(email);
	driver.findElement(By.id("userPassword")).sendKeys(password);
	driver.findElement(By.id("login")).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	//select and add to the cart
	List<WebElement> allProducts =driver.findElements(By.cssSelector(".mb-3"));
	//specific to product name go from allproducts by limiting the scope)
	WebElement prod = allProducts.stream().filter(product->
	product.findElement(By.cssSelector(".card-body b")).getText().contentEquals(productname)).findFirst().orElse(null);
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	
	//loading invincible wait and toast message visible wait and then click cart button

	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container"))); //toast message appears
    // ask developer for locator or class name here class anme is ng-animating
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); // loading invisible
	driver.findElement(By.cssSelector("[routerlink*='cart'] label")).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart h3")));
	
	// validate the cart items are what we add to cart
	List<WebElement> iD =driver.findElements(By.cssSelector(".cart h3"));
	Boolean booleanvalueReturn =iD.stream().anyMatch(cartname->cartname.getText().contentEquals(productname));
	Assert.assertTrue(booleanvalueReturn);
	driver.findElement(By.cssSelector(".totalRow button")).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".form-group")));
	// autosuggestive drop-down handle in payment
	Actions a = new Actions(driver);
	a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
    driver.findElement(By.xpath("//section/button[2]")).click();
    
    //placing the order
    driver.findElement(By.xpath("//div/a[@class='btnn action__submit ng-star-inserted']")).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
    //get confirm message
    String message =driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
	
	}

}
