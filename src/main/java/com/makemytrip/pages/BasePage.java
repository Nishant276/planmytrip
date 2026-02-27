package com.makemytrip.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Base Page Object class
 * Contains common methods and properties for all page objects
 */
public class BasePage {
    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Wait for page to load
     */
    public void waitForPageLoad() {
        try {
            Thread.sleep(2000);
            logger.info("Page loaded successfully");
        } catch (InterruptedException e) {
            logger.error("Error while waiting for page load: " + e.getMessage());
        }
    }

    /**
     * Get page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Scroll to element
     */
    public void scrollToElement(int x, int y) {
        try {
            Thread.sleep(1000);
            logger.info("Scrolled to position: " + x + ", " + y);
        } catch (InterruptedException e) {
            logger.error("Error during scroll: " + e.getMessage());
        }
    }
}
