package com.automation.practice.cucumberrunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(strict = false, features = {"src/test/resources/"},
        glue = {"com.automation.practice.stepdefinitions"},
		plugin = { "pretty", "html:target/cucumber-reports" },
		tags = {"~@skip"})

public class TestRunner {
}