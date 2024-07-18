package TestComponents;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import TestResources.ReportsResources;

public class TestListeners extends TestBase implements ITestListener{
	ExtentTest test;
	ExtentReports extentReports= ReportsResources.extentReports();
	ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
	
		test =extentReports.createTest(result.getMethod().getMethodName());
		extentTestThread.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTestThread.get().log(Status.PASS, result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTestThread.get().log(Status.FAIL,result.getThrowable() );
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filePath=null;
	//screenshot
		try {
			filePath = screenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		//System.out.println(filePath); -C:\Users\Nikitha\eclipse-workspace\Frame//Testreports//loginValidtionMessage.png
		//System.out.println(result.getMethod().getMethodName()); -loginValidtionMessage
	}
	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
	}

}
