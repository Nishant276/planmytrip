package com.makemytrip.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for common functions
 */
public class TestUtils {
    private static final Logger logger = LogManager.getLogger(TestUtils.class);

    /**
     * Get current timestamp
     */
    public static String getCurrentTimestamp() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = formatter.format(new Date());
        logger.info("Current timestamp: " + timestamp);
        return timestamp;
    }

    /**
     * Get current date
     */
    public static String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String date = formatter.format(new Date());
        logger.info("Current date: " + date);
        return date;
    }

    /**
     * Sleep for specified seconds
     */
    public static void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
            logger.info("Slept for " + seconds + " seconds");
        } catch (InterruptedException e) {
            logger.error("Interrupted while sleeping: " + e.getMessage());
        }
    }

    /**
     * Print test result
     */
    public static void printTestResult(String testName, boolean passed, String message) {
        String result = passed ? "PASSED" : "FAILED";
        String outputMessage = String.format("[%s] Test: %s - %s", result, testName, message);
        logger.info(outputMessage);
        System.out.println(outputMessage);
    }
}
