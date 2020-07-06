package com.automation.practice.stepdefinitions;

import com.automation.practice.pages.UserRegistration;
import com.automation.practice.utils.PersistentObject;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class UserRegistrationStepDefinitions {

	UserRegistration userRegistration = new UserRegistration();

	@Given("^Create a new user with following details$")
	public void create_a_new_user_with_following_details(DataTable userDetailsDT) throws Throwable {
		userRegistration.createUserAccount(userDetailsDT);
	}

	@Then("^User validates account creation is successful$")
	public void user_validates_User_registration_is_successful() throws Throwable {
		Assert.assertTrue("Unable to validate user account creation", userRegistration.validateUserRegistrationSuccessful());
	}

	@Then("^User validates error is displayed$")
	public void user_validates_error_is_displayed() throws Throwable {
		Assert.assertTrue("Unable to validate error message component", userRegistration.validateUserRegistrationUnSuccessful());
	}

	@Given("^User navigates to Create Account page$")
	public void user_navigates_to_Create_Account_page() throws Throwable {
		Assert.assertTrue("Unable to navigate to Create Account page", userRegistration.navigateToCreateAccount());
	}

	@Given("^User enters Email \"([^\"]*)\" and navigates to Account details page$")
	public void user_enters_Email_and_navigates_to_Account_details_page(String userEmail) throws Throwable {
		Assert.assertTrue("Error in email provided", userRegistration.naviagteToAccountDetails(userEmail));
	}

	@Given("^User validates landing page$")
	public void user_validates_landing_page() throws Throwable {
		Assert.assertTrue("Unable to validate landing page", userRegistration.validateLandingPage());
	}

	@Then("^Error message \"([^\"]*)\" is displayed$")
	public void error_message_is_displayed(String errorMessage) throws Throwable {
		Assert.assertTrue("Unable to validate error message: " + errorMessage, userRegistration.valiadteErrorMessage(errorMessage));
	}

	@After
	public void setDefaultDBParameterValues(Scenario scenario) throws IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date date = new Date();
		String strDate = formatter.format(date);
		if (scenario.isFailed()) {
			File screenshotFile = ((TakesScreenshot) PersistentObject.getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File(System.getProperty("user.dir")
					+ "\\Screenshots-FailedScenarios\\" + strDate + "\\" + scenario.getName() + ".png"));
			scenario.embed(Files.readAllBytes(screenshotFile.toPath()), "image/png");
		}
		PersistentObject.getDriver().quit();
	}
}
