package testScripts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import base.PredefinedActions;

public class TestBase {
	@BeforeMethod
	public void setUp() {
		System.out.println("Step : Open Browser");	
		PredefinedActions.start();
	}
	@AfterMethod
	public void tearDown() {
		System.out.println("Step : Close browser");
		PredefinedActions.close();	
	}

}
