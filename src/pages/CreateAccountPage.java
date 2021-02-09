package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;
import pojo.CreateAccountDetailsPojo;

public class CreateAccountPage extends PredefinedActions {
	WebDriverWait wait;

	public boolean isPageHeadingTextDisplayed() {
		wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.textToBe(By.cssSelector("#noSlide h1"), "CREATE AN ACCOUNT"));
	}

	private void selectGender(boolean isMale) {
		wait = new WebDriverWait(driver, 30);
		WebElement titleElement;

		if (isMale) {
			titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender1")));
		} else {
			titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender2")));
		}
		wait.until(ExpectedConditions.elementToBeClickable(titleElement));
		titleElement.click();

	}

	private void enterFirstName(String firstName) {
		System.out.println("Enter First Name");
		if (firstName != null) {
			driver.findElement(By.id("customer_firstname")).sendKeys(firstName);
		} else
			System.out.println("first Name is blank");
	}

	private void enterLastName(String lastName) {
		System.out.println("Enter Last Name");
		if (lastName != null) {
			driver.findElement(By.id("customer_lastname")).sendKeys(lastName);
		} else
			System.out.println("Last name is blank");
	}

	private void enterPassword(String password) {
		System.out.println("Enter Password");
		if (password != null) {
			driver.findElement(By.id("passwd")).sendKeys(password);
		} else
			System.out.println("Password is blank");

	}

	private void selectDays(String day) {
		System.out.println("Birthdate selected from drop down");
		if (day != null) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-days")));
			Select s = new Select(driver.findElement(By.id("days")));
			s.selectByValue(day);
		} else
			System.out.println("Day is not given");
	}

	private void selectMonth(String month) {
		System.out.println("Birth month selected");
		if (month != null) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-months")));
			Select s = new Select(driver.findElement(By.id("months")));
			s.selectByVisibleText(month + " ");
		} else
			System.out.println("month is not given");

	}

	private void selectYear(String year) {
		System.out.println("Birth year selected");
		if (year != null) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-years")));
			Select s = new Select(driver.findElement(By.id("years")));
			s.selectByValue(year);
		} else
			System.out.println("year is not given");
	}

	private void selectCompany(String company) {
		System.out.println("Company Name is entered");
		if (company != null) {
			driver.findElement(By.id("company")).sendKeys(company);
		} else
			System.out.println("Comapny is blank");
	}

	private void selectAddress(String adrs) {
		System.out.println("Address Name is entered");
		if (adrs != null) {
			driver.findElement(By.id("address1")).sendKeys(adrs);
		} else
			System.out.println("Adress is blank");
	}

	private void selectCity(String city) {
		System.out.println("City Name is entered");
		if (city != null) {
			driver.findElement(By.id("city")).sendKeys(city);
		} else
			System.out.println("city is blank");
	}

	private void selectState(String state) {
		System.out.println("State is selected");
		if (state != null) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-id_state")));
			Select s = new Select(driver.findElement(By.id("id_state")));
			s.selectByVisibleText(state);
		} else
			System.out.println("State is given blank");
	}

	private void selectPostcode(String postcode) {
		System.out.println("Postcode is entered");
		if (postcode != null) {
			driver.findElement(By.id("postcode")).sendKeys(postcode);
		} else
			System.out.println("postcode is blank");
	}

	private void selectOtherInfo(String otherInfo) {
		System.out.println("Other information is entered");
		if (otherInfo != null) {
			driver.findElement(By.id("other")).sendKeys(otherInfo);
		} else
			System.out.println("OtherInfo is blank");
	}

	private void selectHomeNumber(String homeNumber) {
		System.out.println("Home mobile number is entered");
		if (homeNumber != null) {
			driver.findElement(By.id("phone")).sendKeys(homeNumber);
		} else
			System.out.println("homenumber  is blank");
	}

	private void selectMobileNumber(String mobileNumber) {
		System.out.println("Mobile number is entered");
		if (mobileNumber != null) {
			driver.findElement(By.id("phone_mobile")).sendKeys(mobileNumber);
		} else
			System.out.println("mobile number is blank");
	}

	public void enterCreateAccountDetails(CreateAccountDetailsPojo createAccountDetailsPojo) {
		selectGender(createAccountDetailsPojo.isMale());
		enterFirstName(createAccountDetailsPojo.getFirstName());
		enterLastName(createAccountDetailsPojo.getLastName());
		enterPassword(createAccountDetailsPojo.getPassword());
		selectDays(createAccountDetailsPojo.getDays());
		selectMonth(createAccountDetailsPojo.getMonth());
		selectYear(createAccountDetailsPojo.getYear());
		selectCompany(createAccountDetailsPojo.getCompany());
		selectAddress(createAccountDetailsPojo.getAddress1());
		selectCity(createAccountDetailsPojo.getCity());
		selectState(createAccountDetailsPojo.getState());
		selectPostcode(createAccountDetailsPojo.getPostCode());
		selectOtherInfo(createAccountDetailsPojo.getAdditionalInfo());
		selectHomeNumber(createAccountDetailsPojo.gethPhone());
		selectMobileNumber(createAccountDetailsPojo.getmNumber());
	}

	public MyProfilePage clickOnRegistration() {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("submitAccount"))).click();
		System.out.println("Details Registered in Application");
		return new MyProfilePage();
	}

	public List<String> getErrorMessage() {
		List<WebElement> listofErrorElements = driver.findElements(By.cssSelector("ol>li"));
		List<String> listOfErrorText = new ArrayList<String>();
		String totalErrorText = driver.findElement(By.cssSelector(".alert.alert-danger>p")).getText();
		listOfErrorText.add(totalErrorText);

		for (WebElement element : listofErrorElements) {
			listOfErrorText.add(element.getText());
		}
		return listOfErrorText;
	}
}