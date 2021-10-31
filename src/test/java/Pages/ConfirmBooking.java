package Pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Base.baseClass;

public class ConfirmBooking extends baseClass
{
	WebDriver driver;
	WebElement wb;
	
	public static ExtentReports extent;
	static ExtentSparkReporter exreport;
	static Calendar calendar = Calendar.getInstance();
    static SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
    
	public static Logger log = LogManager.getLogger(ConfirmBooking.class.getName());

	@BeforeTest
	public void getDriver() throws Exception
	{
		log.info("Initializing Driver");
		this.driver=selectDriver();
		log.info("Driver Initialized");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void confirmBooking() throws InterruptedException
	{
		extent = new ExtentReports();
		exreport = new ExtentSparkReporter("./Output/ExtentReport/ExtentReportsTestNG_"+formater.format(calendar.getTime())+"_.html");
		exreport.config().setTheme(Theme.DARK);
		exreport.config().setDocumentTitle("Extent Report");		
		extent.attachReporter(exreport);
		
		ExtentTest first=extent.createTest("Booking Test");
		
		log.info("loading Url");
		driver.get(prop.getProperty("url"));
		first.pass("Url Loaded");
		log.info("Url Loaded");
		driver.findElement(Path.HomePage.btnAppointment).click();
		log.info("Button Clicked");
		first.info("Button Clicked");
		try{
			driver.findElement(Path.HomePage.bookServiceText).isDisplayed();
			log.info("Text is Displayed");
			first.info("text Displayed");
			String actualTitle=driver.getTitle();
			String expectedTitle=prop.getProperty("expectedTitle");
			Assert.assertEquals(actualTitle, expectedTitle);
			log.info("Title are Same");
		} 
		catch (Exception e) {
			log.info("Got Error");
		}

		driver.findElement(Path.bookService.btnSelect).click();
		log.info("clicked on select button");
		driver.findElement(Path.bookService.btnCalenderNext).click();
		log.info("clicked next month button");
		
		Thread.sleep(10000);
		driver.findElement(Path.bookService.selectDate).click();
		driver.findElement(Path.bookService.fname).sendKeys("Yogeshkumar");
		driver.findElement(Path.bookService.email).sendKeys("Yogeshkumar@mail.com");
		driver.findElement(Path.bookService.phone).sendKeys("12345678");
		driver.findElement(Path.bookService.confmBtn).click();
		
	}

	@AfterTest
	public void endTest()
	{
		log.info("closing browser");
		driver.close();
		log.info("closed browser");
		driver=null;
		extent.flush();
	}
}
