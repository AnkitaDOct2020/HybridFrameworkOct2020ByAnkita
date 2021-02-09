package testScripts;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.PredefinedActions;
import pages.AuthenticationPage;
import pages.CreateAccountPage;
import pages.HomePage;
import pages.MyProfilePage;
import pojo.CreateAccountDetailsPojo;

public class CreateAccountTest extends TestBase{
	
	@Test
	public void createAccountTest() {

		HomePage homePage = new HomePage();
		AuthenticationPage authenticationPage = homePage.ClickOnSignIn();
		authenticationPage.isAuthenticationVisible();
		authenticationPage.enterEmailAdress("ankitp0p3@gmail.com");
		CreateAccountPage createAccountPage = authenticationPage.clickOnCreateAccount();

		boolean isHeadingTextDisplayed = createAccountPage.isPageHeadingTextDisplayed();
		Assert.assertTrue(isHeadingTextDisplayed, "Header text wasnot displayed");

		CreateAccountDetailsPojo createAccountDetailsPojo = new CreateAccountDetailsPojo();
		createAccountDetailsPojo.setMale(false);
		createAccountDetailsPojo.setFirstName("Automation");
		createAccountDetailsPojo.setLastName("Technocredits");
		createAccountDetailsPojo.setPassword("automation123");
		createAccountDetailsPojo.setDays("12");
		createAccountDetailsPojo.setMonth("January");
		createAccountDetailsPojo.setYear("1997");
		createAccountDetailsPojo.setCompany("ABC");
		createAccountDetailsPojo.setAddress1("650 Grassmere park");
		createAccountDetailsPojo.setCity("nashville");
		createAccountDetailsPojo.setState("Tennessee");
		createAccountDetailsPojo.setPostCode("37211");
		createAccountDetailsPojo.setAdditionalInfo("NA");
		createAccountDetailsPojo.sethPhone("9856321478");
		createAccountDetailsPojo.setmNumber("4563217896");
		createAccountDetailsPojo.setAliasAddress("");

		createAccountPage.enterCreateAccountDetails(createAccountDetailsPojo);
		MyProfilePage myProfilePage = createAccountPage.clickOnRegistration();
		String actual = myProfilePage.getHeaderText();
		String expected = "Automation Technocredits";
		Assert.assertEquals(actual, expected, "Verification not happend");
	}

	@Test
	public void createAccountUIValidationTest() {
		PredefinedActions.start();

		HomePage homePage = new HomePage();
		AuthenticationPage authenticationPage = homePage.ClickOnSignIn();

		authenticationPage.isAuthenticationVisible();

		authenticationPage.enterEmailAdress("ankitp0p4@gmail.com");
		CreateAccountPage createAccountPage = authenticationPage.clickOnCreateAccount();

		boolean isHeadingTextDisplayed = createAccountPage.isPageHeadingTextDisplayed();
		Assert.assertTrue(isHeadingTextDisplayed, "Header text wasnot displayed");

		CreateAccountDetailsPojo createAccountDetailsPojo = new CreateAccountDetailsPojo();
		createAccountPage.enterCreateAccountDetails(createAccountDetailsPojo);

		createAccountPage.clickOnRegistration();

		List<String> expectedMessages = new ArrayList<String>();
		expectedMessages.add("There are 8 errors");
		expectedMessages.add("You must register at least one phone number.");
		expectedMessages.add("lastname is required.");
		expectedMessages.add("firstname is required.");
		expectedMessages.add("passwd is required.");
		expectedMessages.add("address1 is required");
		expectedMessages.add("city is required.");
		expectedMessages.add("The Zip/Postal code you've entered is invalid. It must follow this format: 00000\r\n");
		expectedMessages.add("This country requires you to choose a State.");

		List<String> actualErrorMessages = createAccountPage.getErrorMessage();

		Assert.assertEquals(actualErrorMessages, expectedMessages);
	}
}