package Utilities;

import java.io.IOException;



//import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import Base.baseClass;

public class listeners implements ITestListener
{

	baseClass b=new baseClass();
	
	public void onTestFailure(ITestResult result) 
	{
		
		try {
			b.getScreenshot(result.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
