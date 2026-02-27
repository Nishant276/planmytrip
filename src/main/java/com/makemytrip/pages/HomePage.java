package com.makemytrip.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Page Object for MakeMyTrip Home Page
 */
public class HomePage extends BasePage {
    private static final Logger logger = LogManager.getLogger(HomePage.class);

    // Locators
    @FindBy(xpath = "//span[text()='Trains']")
    private WebElement trainsTab;

    @FindBy(xpath = "//input[@placeholder='From']")
    private WebElement sourceStationInput;

    @FindBy(xpath = "//input[@placeholder='To']")
    private WebElement destinationStationInput;

    @FindBy(xpath = "//input[@placeholder='Departure Date']")
    private WebElement departureDateInput;

    @FindBy(xpath = "//button[contains(text(), 'Search')]")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        super(driver);
        logger.info("HomePage initialized");
    }

    /**
     * Navigate to MakeMyTrip website
     */
    public void navigateToMakeMyTrip(String url) {
        try {
            driver.navigate().to(url);
            waitForPageLoad();
            logger.info("Navigated to: " + url);
        } catch (Exception e) {
            logger.error("Error navigating to website: " + e.getMessage());
        }
    }

    /**
     * Click on Trains tab
     */
    public void clickOnTrainsTab() {
        try {
            trainsTab.click();
            waitForPageLoad();
            logger.info("Clicked on Trains tab");
        } catch (Exception e) {
            logger.error("Error clicking on Trains tab: " + e.getMessage());
            throw new RuntimeException("Unable to click on Trains tab: " + e.getMessage());
        }
    }

    /**
     * Enter source station
     */
    public void enterSourceStation(String station) {
        try {
            sourceStationInput.clear();
            sourceStationInput.sendKeys(station);
            waitForPageLoad();
            logger.info("Entered source station: " + station);
        } catch (Exception e) {
            logger.error("Error entering source station: " + e.getMessage());
            throw new RuntimeException("Unable to enter source station: " + e.getMessage());
        }
    }

    /**
     * Enter destination station
     */
    public void enterDestinationStation(String station) {
        try {
            destinationStationInput.clear();
            destinationStationInput.sendKeys(station);
            waitForPageLoad();
            logger.info("Entered destination station: " + station);
        } catch (Exception e) {
            logger.error("Error entering destination station: " + e.getMessage());
            throw new RuntimeException("Unable to enter destination station: " + e.getMessage());
        }
    }

    /**
     * Enter departure date
     */
    public void enterDepartureDate(String date) {
        try {
            departureDateInput.clear();
            departureDateInput.sendKeys(date);
            waitForPageLoad();
            logger.info("Entered departure date: " + date);
        } catch (Exception e) {
            logger.error("Error entering departure date: " + e.getMessage());
            throw new RuntimeException("Unable to enter departure date: " + e.getMessage());
        }
    }

    /**
     * Click search button
     */
    public TrainResultsPage clickSearchButton() {
        try {
            searchButton.click();
            waitForPageLoad();
            logger.info("Clicked Search button");
            return new TrainResultsPage(driver);
        } catch (Exception e) {
            logger.error("Error clicking search button: " + e.getMessage());
            throw new RuntimeException("Unable to click search button: " + e.getMessage());
        }
    }
}
