/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websnapshot;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mista 
 * Screen shot Capture Service
 */

public class Screenshot {

    private static final Logger logger = LoggerFactory.getLogger(Screenshot.class);
    private static String OS = System.getProperty("os.name").toLowerCase();
    private WebDriver driver;

    public Screenshot(String driverpath, WebDriver drv) {
	
        // Configure driver location// identify the OS type
        logger.info("Detected Operating System: " + OS);
        System.setProperty("webdriver.chrome.driver", "/SW/Chrome/chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        this.driver = drv;
        try {
            driver = new ChromeDriver(chromeOptions);
        } catch (Exception w) {
            driver.close();
        }
    }

    public File snap(String url) {
        File src = null;
        try {
            // Navigate to the specified directory
            driver.navigate().to(url);
            driver.navigate().refresh();

			// WebDriverWait wait = new WebDriverWait(driver, 10);
            // Sleep for 5 seconds in case the website has not fully loaded
            // Thread.sleep(5000);
            // driver.wait(5000);
            // Take the screenshot and copy to the specified directory
            src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            logger.info("url [{}] screenshot taken", url);
            return src;

        } catch (Exception e) {
            logger.info(" ERROR while taking screenshot: {}", e.getMessage());
        }
        return src;
    }

    public void close() {
        // Close after completion
        driver.close();
    }
}
