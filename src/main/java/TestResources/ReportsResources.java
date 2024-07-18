package TestResources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportsResources{
	public static ExtentReports extentReports() {
		String path = System.getProperty("user.dir")+"//Testreports//index.html";
		File File = new File(path);
		ExtentSparkReporter sparkReport = new ExtentSparkReporter(File);
		sparkReport.config().setDocumentTitle("E-commerce Reports");
		sparkReport.config().setReportName("Automation Reports");

		ExtentReports extentreports = new ExtentReports();
		extentreports.attachReporter(sparkReport);
		extentreports.setSystemInfo("Automating Test Reports", "Application");
		
		return extentreports;
	}

}
