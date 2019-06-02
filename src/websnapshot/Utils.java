/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websnapshot;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mista
 */
public class Utils {

    private static final Logger logger = LoggerFactory.getLogger(Utils.class);

    public static Model validateUrl(Set<String> URLs) {

        int count = 0;
        Set<String> invalid = new HashSet<>();
        Set<String> valid = new HashSet<>();

        Pattern pattern = Pattern.compile("(?i)\\b(?:(?:https?|ftp)://)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,}))\\.?)(?::\\d{2,5})?(?:[/?#]\\S*)?\\b");
        for (String url : URLs) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.find()) {
                logger.info("Verified url: {}", matcher.group());
                valid.add(matcher.group());
            } else {
                count++;
                invalid.add(url);
            }
        }
        return new Model(count, invalid, valid);
    }

    public static String getFileName(String url){
        return url.split("://")[1];
    }
    public static String getHostName(String url) {
        URI uri;
        String hostname = "";
        try {
            uri = new URI(url);
            hostname = uri.getHost();
            if (hostname != null) {
                return hostname.startsWith("www.") ? hostname.substring(4) : hostname;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return hostname;
    }

    public static Boolean StoreFile(File file, String filename, String location) {
        try {
            FileUtils.copyFile(file, new File(location + filename + ".jpg"));
            logger.info("File {}{}.jpg stored successfully", location, filename);
        } catch (IOException e) {
            logger.info("Error while Storing files: {}", e.getMessage());
        }
        return true;
    }
}
