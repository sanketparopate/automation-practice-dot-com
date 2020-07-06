package com.automation.practice.pages;

import com.automation.practice.enums.ExpectedCondition;
import com.automation.practice.utils.DataReader;
import com.automation.practice.utils.PersistentObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.bind.DatatypeConverter;

public class BasePage {

	@FindBy(xpath = "//img[@class='logo img-responsive']")
	public WebElement logoHomePage;

	@FindBy(xpath = "//a[@class='login']")
	public WebElement btnSignIn;

	protected WebDriver driver;
	protected static final int WAIT_FOR_ELEMENT_SECS = Integer
			.parseInt(DataReader.getConfigData("implicitWait"));
	protected WebDriverWait wait = null;
	public int hours;
	public int minutes;

	public BasePage() {
		this.driver = PersistentObject.getDriver();
		wait = new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECS);
	}

	public String decodedValue(String strEncoded) {
		String strDecoded = new String(DatatypeConverter.parseBase64Binary(strEncoded));
		return strDecoded;
	}

	protected void click(WebElement element) {
		waitForElement(element, ExpectedCondition.VISIBLE);
		waitForElement(element, ExpectedCondition.CLICKABLE);
		element.click();
	}

	protected void enterText(WebElement element, String text) {

		waitForElement(element, ExpectedCondition.VISIBLE);
		waitForElement(element, ExpectedCondition.CLICKABLE);
		click(element);
		clearText(element);
		element.sendKeys(text);
		element.sendKeys(Keys.TAB);
		pause(300);
	}

	public void pause(int duration) {
		try {
			Thread.sleep(duration);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void clearText(WebElement element) {
		waitForElement(element);
		element.clear();
	}

	protected String getElementAttributeValue(WebElement element, String attributeName) {
		waitForElement(element);
		boolean isFound = false;
		String attrValue = "";
		try {
			attrValue = element.getAttribute(attributeName);
			if (attrValue != null)
				isFound = true;
		} catch (Exception e) {
			isFound = false;
		}

		if (isFound)
			return attrValue;
		else
			return "NA";
	}

	protected void waitForElement(WebElement element) { // Will wait for element to be visible
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	protected void waitForElement(WebElement element, ExpectedCondition condition) {
		switch (condition) {
			case VISIBLE:
				wait.until(ExpectedConditions.visibilityOf(element));
				break;
			case INVISIBLE:
				wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
				break;
			case CLICKABLE:
				wait.until(ExpectedConditions.elementToBeClickable(element));
				break;
			case SELECTED:
				wait.until(ExpectedConditions.elementSelectionStateToBe(element, true));
				break;
			case DESELECTED:
				wait.until(ExpectedConditions.elementSelectionStateToBe(element, false));
				break;
			case PRESENCE:
				break;
		}
	}

	protected boolean selectDropDownByVisibleText(String elementXpath, String visibleText)
			throws StaleElementReferenceException {

		try {
			Select drpDown = new Select(driver.findElement(By.xpath(elementXpath)));
			if (visibleText != null)
				drpDown.selectByVisibleText(visibleText);
			else
				drpDown.selectByValue("");
			return true;

		} catch (Exception e) {
			System.out.println("Required option '" + visibleText + "' is not present in the dropdown.\n Exception" + e);
			return false;
		}
	}

	protected boolean selectDropDownByValue(String elementXpath, String value) throws StaleElementReferenceException {

		try {

			Select drpDown = new Select(driver.findElement(By.xpath(elementXpath)));
			drpDown.selectByValue(value);
			return true;

		} catch (Exception e) {
			System.out.println("Required option '" + value + "' is not present in the dropdown.\n Exception" + e);
			return false;
		}
	}


	protected boolean isElementDisplayed(WebElement element) {
		try {
			waitForElement(element);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}