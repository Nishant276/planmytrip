package com.makemytrip.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

/**
 * Page Object for Train Results Page
 */
public class TrainResultsPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(TrainResultsPage.class);

    // Locators
    @FindBy(xpath = "//div[@class='train-item']")
    private List<WebElement> trainResults;

    @FindBy(xpath = "//div[@class='no-results']")
    private WebElement noResultsMessage;

    @FindBy(xpath = "//span[@class='seat-availability']")
    private List<WebElement> seatAvailabilityElements;

    public TrainResultsPage(WebDriver driver) {
        super(driver);
        logger.info("TrainResultsPage initialized");
    }

    /**
     * Check if trains are available
     */
    public boolean areTrainsAvailable() {
        try {
            waitForPageLoad();
            boolean trainsFound = !trainResults.isEmpty();
            if (trainsFound) {
                logger.info("Trains found: " + trainResults.size());
            } else {
                logger.warn("No trains available");
            }
            return trainsFound;
        } catch (Exception e) {
            logger.error("Error checking train availability: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get number of available trains
     */
    public int getNumberOfAvailableTrains() {
        try {
            logger.info("Total trains found: " + trainResults.size());
            return trainResults.size();
        } catch (Exception e) {
            logger.error("Error getting number of trains: " + e.getMessage());
            return 0;
        }
    }

    /**
     * Get seat availability for first train
     */
    public String getFirstTrainSeatAvailability() {
        try {
            if (!trainResults.isEmpty()) {
                WebElement firstTrain = trainResults.get(0);
                WebElement seatInfo = firstTrain.findElement(By.xpath(".//span[@class='seat-availability']"));
                String availability = seatInfo.getText();
                logger.info("First train seat availability: " + availability);
                return availability;
            }
            logger.warn("No trains found to get seat availability");
            return "No trains available";
        } catch (Exception e) {
            logger.error("Error getting seat availability: " + e.getMessage());
            return "Error retrieving seat availability";
        }
    }

    /**
     * Get train details
     */
    public String getTrainDetails(int index) {
        try {
            if (index < trainResults.size()) {
                WebElement train = trainResults.get(index);
                String trainDetails = train.getText();
                logger.info("Train " + index + " details: " + trainDetails);
                return trainDetails;
            }
            logger.warn("Train at index " + index + " not found");
            return "Train not found";
        } catch (Exception e) {
            logger.error("Error getting train details: " + e.getMessage());
            return "Error retrieving train details";
        }
    }

    /**
     * Check if specific seat availability text exists
     */
    public boolean isSeatAvailable() {
        try {
            waitForPageLoad();
            for (WebElement element : seatAvailabilityElements) {
                String text = element.getText().toLowerCase();
                if (text.contains("available") || text.contains("seats")) {
                    logger.info("Seats are available");
                    return true;
                }
            }
            logger.warn("No seats available");
            return false;
        } catch (Exception e) {
            logger.error("Error checking seat availability: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get error message if no trains available
     */
    public String getErrorMessage() {
        try {
            if (noResultsMessage != null) {
                String message = noResultsMessage.getText();
                logger.info("Error message: " + message);
                return message;
            }
            return "No error message found";
        } catch (Exception e) {
            logger.error("Error retrieving error message: " + e.getMessage());
            return "Error retrieving error message";
        }
    }
}
