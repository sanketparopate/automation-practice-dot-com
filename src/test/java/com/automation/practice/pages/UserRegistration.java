package com.automation.practice.pages;

import com.automation.practice.utils.DataReader;
import com.automation.practice.utils.PersistentObject;
import cucumber.api.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.*;

public class UserRegistration extends BasePage {

	@FindBy(xpath = "//*[@id='create-account_form']")
	public WebElement txtCreateAccount;

	@FindBy(xpath = "//*[@id='email_create']")
	public WebElement tbNewUserEmail;

	@FindBy(xpath = "//*[@id='SubmitCreate']")
	public WebElement btnCreateAccount;

	@FindBy(xpath = "//*[@id='create_account_error']")
	public WebElement alertCreateAccountError;

	@FindBy(xpath = "//*[@id='account-creation_form']")
	public WebElement frmAccountCreation;

	@FindBy(xpath = "//*[@id='id_gender1']")
	public WebElement rbtnGenderMale;

	@FindBy(xpath = "//*[@id='id_gender2']")
	public WebElement rbtnGenderFemale;

	@FindBy(xpath = "//*[@id='customer_firstname']")
	public WebElement tbFirstName;

	@FindBy(xpath = "//*[@id='customer_lastname']")
	public WebElement tbLastName;

	@FindBy(xpath = "//*[@id='email']")
	public WebElement tbEmail;

	@FindBy(xpath = "//*[@id='passwd']")
	public WebElement tbPassword;

	@FindBy(xpath = "//label[text()='Sign up for our newsletter!']")
	public WebElement cbNewsletter;

	@FindBy(xpath = "//label[text()='Receive special offers from our partners!']")
	public WebElement cbOffers;

	@FindBy(xpath = "//*[@id='company']")
	public WebElement tbCompany;

	@FindBy(xpath = "//*[@id='firstname']")
	public WebElement tbAddFirstName;

	@FindBy(xpath = "//*[@id='lastname']")
	public WebElement tbAddLastName;

	@FindBy(xpath = "//*[@id='address1']")
	public WebElement tbAddress1;

	@FindBy(xpath = "//*[@id='address2']")
	public WebElement tbAddress2;

	@FindBy(xpath = "//*[@id='city']")
	public WebElement tbCity;

	@FindBy(xpath = "//*[@id='postcode']")
	public WebElement tbPostCode;

	@FindBy(xpath = "//*[@id='other']")
	public WebElement tbAdditionalInfo;

	@FindBy(xpath = "//*[@id='phone']")
	public WebElement tbHomePhone;

	@FindBy(xpath = "//*[@id='phone_mobile']")
	public WebElement tbMobile;

	@FindBy(xpath = "//*[@id='alias']")
	public WebElement tbAddressAlias;

	@FindBy(xpath = "//*[@id='submitAccount']")
	public WebElement btnRegister;

	@FindBy(xpath = "//h1[text()='My account']")
	public WebElement txtMyAccountHeader;

	@FindBy(xpath = "//*[@title='Log me out']")
	public WebElement btnLogout;

	@FindBy(xpath = "//*[@id='center_column']/div")
	public WebElement alertAccountFormError;

	Map<String, String> userDetails;

	//	XPath strings are used instead of Page Factory due to NoSuchElementException
	String ddDateXpath = "//*[@id='days']";
	String ddMonthXpath = "//*[@id='months']";
	String ddYearXpath = "//*[@id='years']";
	String ddStateXpath = "//*[@id='id_state']";
	String ddCountryXpath = "//*[@id='id_country']";


	public UserRegistration() {
		this.driver = PersistentObject.getDriver();
		PageFactory.initElements(driver, this);
	}

	/**
	 * This is a reusuable method which is used to enter user details based on inputs from feature file data table. Datatable can vary as per scenario, but same method will deal with enetering details
	 *
	 * @param userDetailsDT
	 */
	public void createUserAccount(DataTable userDetailsDT) {
		userDetails = userDetailsDT.asMap(String.class, String.class);
		List<String> userDetailsKeySet = new LinkedList<>(userDetails.keySet());

		for (String parameter : userDetailsKeySet)
			switch (parameter) {

				case "Title":
					if (userDetails.get(parameter).equalsIgnoreCase("Mr."))
						click(rbtnGenderMale);
					else if (userDetails.get(parameter).equalsIgnoreCase("Mrs."))
						click(rbtnGenderFemale);
					else
						System.out.println("Unknown value for " + parameter);
					break;

				case "FirstName":
					enterText(tbFirstName, userDetails.get(parameter));
					break;

				case "LastName":
					enterText(tbLastName, userDetails.get(parameter));
					break;

				case "Password":
					if (userDetails.get(parameter).equalsIgnoreCase("GetPassword"))
						enterText(tbPassword, decodedValue(DataReader.getConfigData("password")));
					else
						enterText(tbPassword, userDetails.get(parameter));
					break;

				case "DOBDate":
					selectDropDownByValue(ddDateXpath, userDetails.get(parameter));
					break;

				case "DOBMonth":
					DateTimeFormatter parser = DateTimeFormatter.ofPattern("MMM")
							.withLocale(Locale.ENGLISH);
					TemporalAccessor accessor = parser.parse(userDetails.get(parameter));
					selectDropDownByValue(ddMonthXpath, String.valueOf(accessor.get(ChronoField.MONTH_OF_YEAR)));
					break;

				case "DOBYear":
					selectDropDownByValue(ddYearXpath, userDetails.get(parameter));
					break;

				case "Newsletter":
					if (userDetails.get(parameter).equalsIgnoreCase("yes"))
						click(cbNewsletter);
					break;

				case "Offers":
					if (userDetails.get(parameter).equalsIgnoreCase("yes"))
						click(cbOffers);
					break;

				case "Company":
					enterText(tbCompany, userDetails.get(parameter));
					break;

				case "Address1":
					enterText(tbAddress1, userDetails.get(parameter));
					break;

				case "Address2":
					enterText(tbAddress2, userDetails.get(parameter));
					break;

				case "City":
					enterText(tbCity, userDetails.get(parameter));
					break;

				case "State":
					selectDropDownByVisibleText(ddStateXpath, userDetails.get(parameter));
					break;

				case "ZipCode":
					enterText(tbPostCode, userDetails.get(parameter));
					break;

				case "Country":
					selectDropDownByVisibleText(ddCountryXpath, userDetails.get(parameter));
					break;

				case "AdditoinalInfo":
					enterText(tbAdditionalInfo, userDetails.get(parameter));
					break;
				case "HomePhone":
					enterText(tbHomePhone, userDetails.get(parameter));
					break;

				case "Mobile":
					enterText(tbMobile, userDetails.get(parameter));
					break;

				case "AddressAlias":
					enterText(tbAddressAlias, userDetails.get(parameter));
					break;

				default:
					System.out.println("Unknown parameter: " + parameter);

			}

//		Assertions for prepopulated fileds
		Assert.assertTrue(validateEmail());
		Assert.assertTrue(validateAddressFirstName());
		Assert.assertTrue(validateAddresLastName());
		click(btnRegister);
	}


	/**
	 * This method validated is registration is successful by checking presence of 'My Account' after register
	 *
	 * @return
	 */
	public boolean validateUserRegistrationSuccessful() {
		return isElementDisplayed(txtMyAccountHeader);
	}

	/**
	 * This method validates presence of error on screen
	 *
	 * @return
	 */
	public boolean validateUserRegistrationUnSuccessful() {
		return isElementDisplayed(alertAccountFormError);
	}

	/**
	 * This method validates error message for mandatory fields for user registration
	 *
	 * @param errorMessage
	 * @return
	 */
	public boolean valiadteErrorMessage(String errorMessage) {
		Boolean validatErrror = false;
		try {
			if (isElementDisplayed(alertAccountFormError)) {
				List<WebElement> listErrorMessages = driver.findElements(By.xpath("//*[@id='center_column']/div/ol/li"));
				for (WebElement error : listErrorMessages) {
					System.out.println("error text for " + error.getText());
					if (error.getText().equalsIgnoreCase(errorMessage))
						validatErrror = true;
				}
			}
			return validatErrror;
		} catch (Exception e) {
			System.out.println("Exception in validating error message" + e);
		}
		return validatErrror;
	}

	/**
	 * Validate prepoulated email in registration form
	 *
	 * @return
	 */
	public boolean validateEmail() {
		return getElementAttributeValue(tbEmail, "value").equalsIgnoreCase(PersistentObject.getUserEmail());
	}

	/**
	 * Validate prepoulated First Name in Address section in registration form
	 *
	 * @return
	 */
	public boolean validateAddressFirstName() {
		return getElementAttributeValue(tbAddFirstName, "value").equalsIgnoreCase(userDetails.get("FirstName"));
	}

	/**
	 * Validate prepoulated Last Name in Address section in registration form
	 *
	 * @return
	 */
	public boolean validateAddresLastName() {
		return getElementAttributeValue(tbAddLastName, "value").equalsIgnoreCase(userDetails.get("LastName"));
	}

	/**
	 * Navigate to Create account
	 *
	 * @return
	 */
	public boolean navigateToCreateAccount() {
		click(btnSignIn);
		return isElementDisplayed(txtCreateAccount);
	}

	/**
	 * Validate presence of HomePage or Sign In button on landing page
	 *
	 * @return
	 */
	public boolean validateLandingPage() {
		return isElementDisplayed(logoHomePage) || isElementDisplayed(btnSignIn);
	}

	/**
	 * Enter email from feature file. If error is displayed for existing user then create a new email by appending date and time
	 *
	 * @param userEmail
	 * @return
	 */
	public boolean naviagteToAccountDetails(String userEmail) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String strDate = formatter.format(date);
//**	Enter User Email from Feature File
		enterText(tbNewUserEmail, userEmail);
		click(btnCreateAccount);

//**	If error 'User already exists is displayed then create a new email id by appendig date+time
		if (isElementDisplayed(alertCreateAccountError)) {
			System.out.println("Email " + userEmail + " already exists. Creating a new email with date and time");
			userEmail = "TestUser" + strDate + "@test.com";
			enterText(tbNewUserEmail, userEmail);
			click(btnCreateAccount);
			System.out.println("New user registration email: " + userEmail);
		}

		PersistentObject.setUserEmail(userEmail);
		return isElementDisplayed(frmAccountCreation);
	}
}