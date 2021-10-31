package Pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.baseClass;

public class contactSubmit extends baseClass
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

	@DataProvider
	public Object[][] getData() throws IOException
	{
		//row stands for how many different data types test should run  -1
		//column stands for how many values you are sending per test	-2
		Object[][] data=new Object[3][3];
		//0th row
		data[0][0]="Yogesh";
		data[0][1]="Admin@mail.com";
		data[0][2]="Hello World";

		//1st row
		data[1][0]="Suthar";
		data[1][1]="Adminq@mail.com";
		data[1][2]="Hello Public";

		data[2][0]="Mindtree";
		data[2][1]="mindtreeWalw@mail.com";
		data[2][2]="Mindtree is Best";

		return data;
	}

	@Test(dataProvider="getData")
	public void contactDataSubmit(String name,String Email,String Message) throws Exception
	{
		driver.get(prop.getProperty("url"));
		log.info("Url Loaded");
		driver.findElement(Path.contactPage.btnContact).click();
		log.info("contact Button Clicekd");
		try {
			driver.findElement(Path.contactPage.getInTouch).isDisplayed();
			log.info("Get in Touch Displayed");
		} 
		catch (Exception e) {
			log.error("Got Error!!");
		}

		log.info("entering Details");
		driver.findElement(Path.contactPage.nameBtn).sendKeys(name);
		log.info("Name Entered");
		driver.findElement(Path.contactPage.eMailBtn).sendKeys(Email);
		log.info("Email Entered");
		driver.findElement(Path.contactPage.messageBtn).sendKeys(Message);
		log.info("Message Entered");
		driver.findElement(Path.contactPage.submitBtn).click();
		log.info("Clicked on Submit Button");

		Thread.sleep(10000);
		try {
			driver.findElement(Path.contactPage.thnkMsg).isDisplayed();	
			log.info("Thanks message Displayed");
		} 
		catch (Exception e) {
			log.error("Got Error!!");
		}
	}


	@AfterTest
	public void endTest()
	{
		log.info("Closing Browser");
		driver.close();
		log.info("Browser Closed");
		driver=null;
	}
}
