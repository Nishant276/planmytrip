package com.makemytrip.stepdefs;

import com.makemytrip.pages.HomePage;
import com.makemytrip.pages.TrainResultsPage;
import com.makemytrip.utils.DriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Step Definitions for Train Booking Feature
 */
public class TrainBookingStepDefinitions {
    private static final Logger logger = LogManager.getLogger(TrainBookingStepDefinitions.class);
    private WebDriver driver;
    private HomePage homePage;
    private TrainResultsPage trainResultsPage;
    private boolean trainsAvailable;
    private String testStatus;

    @Before
    public void setUp() {
        logger.info("Setting up WebDriver");
        driver = DriverManager.getDriver();
    }

    @After
    public void tearDown() {
        logger.info("Closing WebDriver");
        DriverManager.quitDriver();
    }

    @Given("I navigate to MakeMyTrip website")
    public void navigateToMakeMyTripWebsite() {
        logger.info("Step: Navigate to MakeMyTrip website");
        try {
            String url = "https://www.makemytrip.com/";
            homePage = new HomePage(driver);
            homePage.navigateToMakeMyTrip(url);
            logger.info("Successfully navigated to MakeMyTrip website");
        } catch (Exception e) {
            logger.error("Error navigating to website: " + e.getMessage());
            Assert.fail("Failed to navigate to website: " + e.getMessage());
        }
    }

    @When("I click on the Trains tab")
    public void clickOnTrainsTab() {
        logger.info("Step: Click on Trains tab");
        try {
            homePage.clickOnTrainsTab();
            logger.info("Successfully clicked on Trains tab");
        } catch (Exception e) {
            logger.error("Error clicking on Trains tab: " + e.getMessage());
            Assert.fail("Failed to click on Trains tab: " + e.getMessage());
        }
    }

    @And("I enter {string} as the source station")
    public void enterSourceStation(String station) {
        logger.info("Step: Enter source station: " + station);
        try {
            homePage.enterSourceStation(station);
            logger.info("Successfully entered source station: " + station);
        } catch (Exception e) {
            logger.error("Error entering source station: " + e.getMessage());
            Assert.fail("Failed to enter source station: " + e.getMessage());
        }
    }

    @And("I enter {string} as the destination station")
    public void enterDestinationStation(String station) {
        logger.info("Step: Enter destination station: " + station);
        try {
            homePage.enterDestinationStation(station);
            logger.info("Successfully entered destination station: " + station);
        } catch (Exception e) {
            logger.error("Error entering destination station: " + e.getMessage());
            Assert.fail("Failed to enter destination station: " + e.getMessage());
        }
    }

    @And("I select the journey date as tomorrow")
    public void selectJourneyDate() {
        logger.info("Step: Select journey date as tomorrow");
        try {
            LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date = tomorrow.format(formatter);
            homePage.enterDepartureDate(date);
            logger.info("Successfully selected journey date: " + date);
        } catch (Exception e) {
            logger.error("Error selecting journey date: " + e.getMessage());
            Assert.fail("Failed to select journey date: " + e.getMessage());
        }
    }

    @And("I click on the Search button")
    public void clickSearchButton() {
        logger.info("Step: Click on Search button");
        try {
            trainResultsPage = homePage.clickSearchButton();
            logger.info("Successfully clicked Search button");
        } catch (Exception e) {
            logger.error("Error clicking Search button: " + e.getMessage());
            Assert.fail("Failed to click Search button: " + e.getMessage());
        }
    }

    @Then("I should see a list of available trains")
    public void verifyTrainsAvailable() {
        logger.info("Step: Verify trains are available");
        try {
            trainsAvailable = trainResultsPage.areTrainsAvailable();
            if (trainsAvailable) {
                int numberOfTrains = trainResultsPage.getNumberOfAvailableTrains();
                logger.info("Trains are available. Count: " + numberOfTrains);
                testStatus = "PASS - Trains available";
            } else {
                logger.warn("No trains available");
                testStatus = "FAIL - No trains available";
            }
        } catch (Exception e) {
            logger.error("Error verifying trains availability: " + e.getMessage());
            Assert.fail("Failed to verify trains availability: " + e.getMessage());
        }
    }

    @And("If trains are available then I should mark the test as {string}")
    public void markTestAsGood(String status) {
        logger.info("Step: Mark test as " + status);
        try {
            if (trainsAvailable) {
                logger.info("Marking test as: " + status);
                testStatus = status.toUpperCase();
                Assert.assertTrue(trainsAvailable, "Trains should be available");
                logger.info("Test marked as: " + testStatus);
            }
        } catch (Exception e) {
            logger.error("Error marking test: " + e.getMessage());
            Assert.fail("Failed to mark test: " + e.getMessage());
        }
    }

    @And("If no trains are available then I should throw an error message")
    public void throwErrorIfNoTrains() {
        logger.info("Step: Throw error if no trains available");
        try {
            if (!trainsAvailable) {
                String errorMessage = trainResultsPage.getErrorMessage();
                logger.error("No trains available. Error: " + errorMessage);
                Assert.fail("ERROR: No trains available from Ballia to Sealdah. " + errorMessage);
            }
        } catch (AssertionError e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error throwing exception: " + e.getMessage());
            Assert.fail("Failed to handle error scenario: " + e.getMessage());
        }
    }

    @Then("the trains list should display with passenger details")
    public void verifyTrainsListWithDetails() {
        logger.info("Step: Verify trains list with passenger details");
        try {
            Assert.assertTrue(trainResultsPage.areTrainsAvailable(), "Trains should be available");
            int numberOfTrains = trainResultsPage.getNumberOfAvailableTrains();
            logger.info("Trains displayed with details. Total: " + numberOfTrains);
        } catch (Exception e) {
            logger.error("Error verifying trains list: " + e.getMessage());
            Assert.fail("Failed to verify trains list: " + e.getMessage());
        }
    }

    @And("each train should show seat availability information")
    public void verifySeatAvailability() {
        logger.info("Step: Verify seat availability information");
        try {
            boolean seatsAvailable = trainResultsPage.isSeatAvailable();
            if (seatsAvailable) {
                String seatInfo = trainResultsPage.getFirstTrainSeatAvailability();
                logger.info("Seat availability info: " + seatInfo);
                Assert.assertTrue(true, "Seat availability information displayed");
            } else {
                logger.warn("Seats are not available");
                Assert.fail("No seat availability information found");
            }
        } catch (Exception e) {
            logger.error("Error verifying seat availability: " + e.getMessage());
            Assert.fail("Failed to verify seat availability: " + e.getMessage());
        }
    }
}
