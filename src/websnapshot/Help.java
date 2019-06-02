/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websnapshot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mista
 */
public class Help {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(Help.class);

    /**
     * This will be used to provide adhoc tutorials for the application commands
     */  
    public static void getAllCommands() {
        System.out.println("   Help Class");
        System.out.println("   USAGE: [COMMAND] [<urls>]");
        System.out.println("    ");
        System.out.println("   Example \"--path=/SW/Chrome/chromedriver.exe, --output=./test-folder/, https://www.google.com\"");
        System.out.println("    ");
        System.out.println("   This will take a screen shot and store in a folder created in the current path called [test-folder]");
        System.out.println("    ");
        System.out.println("   Explanation:");
        System.out.println("     -- path       sets the chrome driver location on the file system.");
        System.out.println("                       Example:");
        System.out.println("                           \"--path=/SW/Chrome/chromedriver.exe\"");
        System.out.println("    ");
        System.out.println("    ");
        System.out.println("     -- output     storage location for the processed files");
        System.out.println("                       Example:");
        System.out.println("                           \"--output=./temp/\"");
        System.out.println("    ");
        System.out.println("    ");
        System.out.println("     [<urls>]      space separated url data, the urls will hhave to conform to url standards for screenshot");
        System.out.println("                       Example:");
        System.out.println("                           https://www.google.com, https://www.facebook.com");
        System.out.println("    ");
    }
}
