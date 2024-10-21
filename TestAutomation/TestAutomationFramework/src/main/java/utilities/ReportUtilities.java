package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

//Reports utilities file to start test and track and log testcase execution through reports.
public class ReportUtilities {

	//Constructor
	public ReportUtilities(WebDriver driver) {
		this.driver = driver;
	}
	
	WebDriver driver;
	public static ExtentTest extentTest;
	
	ExtentReports extent = new ExtentReports();
	String reportPath = new File("Reports"+ File.separator + "report.html").getAbsolutePath();
	ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
	
	public void StartTest(String testName) {
		spark.config().setDocumentTitle("Sanity Test Report");
		extent.attachReporter(spark);
		extentTest = extent.createTest(testName).assignAuthor("Sakshi").assignCategory("Sanity Test");
	}
	
	public void CompleteTest() {
		extent.flush();
	}
	
	public static String CaptureScreenshot(WebDriver driver) throws IOException {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destinationFilePath = new File("Reports/Images/screenshot"+System.currentTimeMillis()+".png");
		
		String absoluteFilePath = destinationFilePath.getAbsolutePath();
		FileUtils.copyFile(srcFile, destinationFilePath);
		
		return absoluteFilePath;
	}
	
	public void AddScreenshotForPassOrFailStatus(String status, String message) throws IOException {
		extentTest.addScreenCaptureFromPath(CaptureScreenshot(driver));
		if(status.equals("PASS")) {
			extentTest.log(Status.INFO, message);
			extentTest.pass("Customer's appointment data verified successfully.");
		}
		else {
			extentTest.log(Status.INFO, message);
			extentTest.fail("Complete appointment Data is not available on the customer dashboard.");
		}		
	}
}
