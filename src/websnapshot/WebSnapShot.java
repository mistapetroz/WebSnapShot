/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websnapshot;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mista
 */
public class WebSnapShot {

    private static final Set<String> commands = new HashSet<>(Arrays.asList("LOCAL"));
    private static String driverpath = "/SW/Chrome/chromedriver.exe";
    private static String output = "../temp/";
    private static final Logger logger = (Logger) LoggerFactory.getLogger(WebSnapShot.class);
    private static WebDriver driver;

    public WebSnapShot() {
        super();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //args = new String[]{"--path=/SW/Chrome/chromedriver.exe", "--output=/X/CodeZone/Java/WebSnapShot/temp/", "https://mathiasbynens.be/demo/url-regex"};

        if (args.length < 1) {
            Help.getAllCommands();
            System.exit(1);
            return;
        }
        //System.out.println("------------------------------------------------------------------STARTING-----------------------------------------------------------------");
        logger.info("Application started with command-line arguments: {}", Arrays.asList(args));

        /**
         * Configurations
         */
        boolean setup = false;
        for (String arg : args) {
            if (arg.contains("--path=")) {
                driverpath = arg.replace("--path=", "");
                setup = true;
            } else if (arg.contains("--output=")) {
                output = arg.replace("--output=", "");
                setup = true;
            }
        }
        if (setup) {
            logger.info("No Settings captred. Switching to DEFAULT configs");
        }

        logger.info("Chrome driver path : {}", driverpath);
        logger.info("Output folder      : {}", output);

        /**
         * Remove duplicates
         */
        Set<String> urls = new HashSet<>(Arrays.asList(args));

        /**
         * Remove settings from the arguments list and retain potential url list
         */
        urls.removeIf(n -> (n.contains("--output=") || n.contains("--path=")));

        // Executions
        Model processed = Utils.validateUrl(urls);
        Screenshot screenshot = new Screenshot(driverpath, driver);

        for (String url : processed.getValidUrlList()) {
            File src = screenshot.snap(url);
            Utils.StoreFile(src, Utils.getHostName(url)+"/"+Utils.getFileName(url), output);
        }
        screenshot.close();
        logger.info("The processing is complete.");
        logger.info("Invalids data count: {}", (processed.getInvalidCount() == 0) ? "NONE" : processed.getInvalidCount());
        logger.info("Invalid Urls List  : {}", (processed.getInvalidUrlList().isEmpty()) ? "NONE" : processed.getInvalidUrlList());
        //System.out.println("-----------------------------------------------------------------------ENDING-----------------------------------------------------------------");
        System.exit(0);
    }

}
