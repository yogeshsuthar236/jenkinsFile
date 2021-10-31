package Pages;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Base.baseClass;

public class UrlValidate extends baseClass
{
	WebDriver driver;
	WebElement wb;
	public static Logger log = LogManager.getLogger(ConfirmBooking.class.getName());

	@BeforeTest
	public void getDriver() throws Exception
	{
		this.driver=selectDriver();
		log.info("Driver Initialized");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void validatingUrl()
	{
		driver.get(prop.getProperty("url"));
		log.info("Url Loaded");
		driver.findElement(Path.HomePage.btnBooking).click();
		log.info("clicked on Booking Button");
		driver.findElement(Path.HomePage.bookServiceText).isDisplayed();
		log.info("Text is Displayed");
		String actualTitle=driver.getTitle();
		String expectedTitle=prop.getProperty("expectedTitle");
		Assert.assertEquals(actualTitle, expectedTitle);
		log.info("Titles Are same");
		log.info("Test Verified");
	}

	@AfterTest
	public void endTest()
	{
		log.info("Closing Browser");
		driver.close();
		log.info("Closed");
		driver=null;
	}



}
