package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class baseClass 
{
	public static WebDriver driver;
	public static Properties prop;
	public static FileInputStream file;
	Calendar calendar = Calendar.getInstance();
    SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

	public static WebDriver selectDriver() throws IOException
	{
		prop=new Properties();
		file=new FileInputStream("C:\\Users\\M1073149\\eclipse-workspace\\healthcare\\src\\test\\java\\Utilities\\Healthcare.properties");
		prop.load(file);
		String browserName=prop.getProperty("browser");

		if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","C:\\\\Users\\\\M1073149\\\\OneDrive - Mindtree Limited\\\\Chrome Webdriver\\\\chromedriver_win32\\\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			System.setProperty("","");
			driver=new EdgeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("","");
			driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("opera"))
		{
			System.setProperty("","");
			driver=new OperaDriver();
		}
		return driver;
	}
	public void getScreenshot(String result) throws IOException
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src,new File("C:\\Users\\M1073149\\eclipse-workspace\\healthcare\\Output\\screenshot\\ss_"+formater.format(calendar.getTime())+"_.png"));
	}
}
