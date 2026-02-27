package com.makemytrip.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Driver Manager Class
 * Handles WebDriver initialization and cleanup
 */
public class DriverManager {
    private static final Logger logger = LogManager.getLogger(DriverManager.class);
    private static WebDriver driver;

    /**
     * Initialize WebDriver based on browser type
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            String browserType = ConfigReader.getProperty("browser");
            if (browserType == null || browserType.isEmpty()) {
                browserType = "chrome";
            }
            driver = initializeDriver(browserType.toLowerCase());
        }
        return driver;
    }

    /**
     * Initialize driver based on browser type
     */
    private static WebDriver initializeDriver(String browserType) {
        switch (browserType) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                logger.info("Initializing Chrome WebDriver");
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                logger.info("Initializing Firefox WebDriver");
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                logger.info("Initializing Edge WebDriver");
                driver = new EdgeDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                logger.info("Initializing Chrome WebDriver (default)");
                driver = new ChromeDriver();
        }

        // Set implicit wait
        String implicitWaitTime = ConfigReader.getProperty("implicitWait");
        if (implicitWaitTime != null && !implicitWaitTime.isEmpty()) {
            int waitTime = Integer.parseInt(implicitWaitTime);
            logger.info("Setting implicit wait: " + waitTime + " seconds");
        }

        // Maximize window
        driver.manage().window().maximize();
        logger.info("Browser window maximized");

        return driver;
    }

    /**
     * Quit WebDriver
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("WebDriver closed successfully");
        }
    }

    /**
     * Close current window
     */
    public static void closeDriver() {
        if (driver != null) {
            driver.close();
            logger.info("Current window closed");
        }
    }
}
