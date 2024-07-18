package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobjectmodelforproject.LoginPage;

public class TestBase {
	public WebDriver driver;
	public LoginPage loginPage;

	public WebDriver intializeWebdriver() throws IOException {
		Properties properties = new Properties();
		FileInputStream inputstream = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\TestResources\\GlobalData.properties");
		properties.load(inputstream);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: properties.getProperty("browser");
		// String browserName = properties.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")){
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\Nikitha\\Downloads\\Automation\\driver\\geckodriver.exe");
		}
		/*
		 * if (browserName.equalsIgnoreCase("edge")) { driver = new EdgeDriver();
		 * System.setProperty("webdriver.edge.driver",
		 * "C:\\Users\\Nikitha\\Downloads\\Automation\\driver\\Edgedriver.exe"); }
		 */
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchapplication() throws IOException {
		driver = intializeWebdriver();
		loginPage = new LoginPage(driver);
		loginPage.goToURL();
		return loginPage;
	}

	@AfterMethod(alwaysRun = true) 
	public void close() {
		driver.quit();
	}

	public List<HashMap<String, String>> dataReadfromJSON(String filepath) throws IOException {

		// string converted from json
		String file = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		// string to Hashmap
		ObjectMapper objectMapper = new ObjectMapper(); // jackson Datbid depencies from maven we got this
		List<HashMap<String, String>> hashMapinputs = objectMapper.readValue(file,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return hashMapinputs;

	}

	public String screenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File src = screenshot.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + "//reports" + testCaseName + ".png");
		FileUtils.copyFile(src, dest);
		return System.getProperty("user.dir") + "//Testreports//" + testCaseName + ".png";
	}

	/*
	 * public ExtentReports extentReports() { String path =
	 * System.getProperty("user.dir")+"//Testreports//index.html"; File File = new
	 * File(path); ExtentSparkReporter sparkReport = new ExtentSparkReporter(File);
	 * sparkReport.config().setDocumentTitle("E-commerce Reports");
	 * sparkReport.config().setReportName("Automation Reports");
	 * 
	 * ExtentReports extentreports = new ExtentReports();
	 * extentreports.attachReporter(sparkReport);
	 * extentreports.setSystemInfo("Automating Test Reports", "Application");
	 * 
	 * return extentreports; }
	 */
}
