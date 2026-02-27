package com.makemytrip.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * Test Runner for Cucumber BDD Tests
 * Runs Cucumber feature files with TestNG
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.makemytrip.stepdefs"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "junit:target/cucumber-reports/cucumber.xml"
        },
        monochrome = true,
        dryRun = false,
        tags = "@smoke"
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @DataProvider(parallel = false)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
