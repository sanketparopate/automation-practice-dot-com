# automation-practice-dot-com
Created by: Sanket Paropate

Framework Components:
Cucumber JVM
Selenium WebDriver
Java

Framework Architechture:
Framework follows a Page Object Model approach with page elements and methods inside page class (Package -> pages)
Test are written with cucumber feature files and implemented by StepDefinitions (Package -> stepdefinitions)
Each page class or group of page classes(forming a module) will have stepdefinition (named accordingly)
e.g. UserRegistration.page has stepdefinitions in UserRegistrationStepDefinitions.class file

BasePage.java - All common methods required to perform actions on pages are definied in BasePage and BasePage is extended by page classes
BaseStepDefinition.java - Contains initialization methods for WebDriver in @Before cucumber hook
@After hook is written in any one of the stepdefinition.java files
@After will capture screenshots on failure and also embed then in Cucumber reports

Scenarios:
Create a new user with all fields - Pass
Create a new user with mandatory fields only - Pass
Unable to create a new user due to missing mandatory fields and validate error messages - Pass
Unable to create a new user - deliberate failure to capture screenshot - Fail (should create screenshot)
Create new user - negative - failure expected - Fail

The failed scenarios will generate screenshots in folder 'Screenshots-FailedScenarios/<currendate>'

Execution methods:
1. Run Feature file
2. Run CucumberRunner/TestRunner.java in test->java->com.automatiom.practice package
3. In terminal, navigate to root folder and use command 'mvn test'

Cucumber Reports:
Cucumber reports can be found in 'target/cucumber-reports' folder. Open file index.html in browser

PS: currently framework will support only chrome browser in Mac environment
