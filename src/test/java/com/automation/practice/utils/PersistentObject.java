package com.automation.practice.utils;

import org.openqa.selenium.WebDriver;

public class PersistentObject {

	private static WebDriver driver;
	private static String userEmail;

	public static String getUserEmail() {
		return userEmail;
	}

	public static void setUserEmail(String userEmail) {
		PersistentObject.userEmail = userEmail;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		PersistentObject.driver = driver;
	}
}
