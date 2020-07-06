package com.automation.practice.stepdefinitions;


import com.automation.practice.utils.DataReader;
import com.automation.practice.utils.PersistentObject;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class BaseStepDefinitions {

	// variable declarations
	protected WebDriver driver;
	String browser = "";
	String osName = System.getProperty("os.name");
	String scenarioName;

	@Before
	public void setUp(Scenario scenario) {

		browser = DataReader.getConfigData("browser");
		System.out.println("Test Environment details \nOS:" + osName + "\nBrowser: " + browser);

		String baseURL = DataReader.getConfigData("baseURL");
		scenarioName = scenario.getName();
		System.out.println("Scenario Name: " + scenarioName);

		if (browser.equalsIgnoreCase("IE") || browser.equalsIgnoreCase("Internet Explorer")) {
			InternetExplorerOptions options = new InternetExplorerOptions();
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "\\SupportFiles\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver(options.ignoreZoomSettings());

		} else if (browser.equalsIgnoreCase("Firefox") || browser.equalsIgnoreCase("FF")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\SupportFiles\\Drivers\\geckodriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver();
			driver.manage().window().maximize();

		} else if (browser.equalsIgnoreCase("Chrome")) {
			if (osName.contains("Windows") || osName.contains("win"))
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\SupportFiles\\Drivers\\chromedriver.exe");
			else if (osName.contains("mac") || osName.contains("Mac"))
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/SupportFiles/Drivers/chromedriver");
			else
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/SupportFiles/Drivers/chromedriver");

			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars --start-maximized --incognito");
			driver = new ChromeDriver(options);

		} else if (browser.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + "\\SupportFiles\\Drivers\\MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
		} else {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/SupportFiles/Drivers/chromedriver.exe");

			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars --start-maximized --incognito");
			driver = new ChromeDriver(options);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(DataReader.getConfigData("implicitWait")),
				TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(DataReader.getConfigData("implicitWait")),
				TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(Integer.parseInt(DataReader.getConfigData("implicitWait")),
				TimeUnit.SECONDS);
		PersistentObject.setDriver(driver);
		driver.get(baseURL);
	}
}