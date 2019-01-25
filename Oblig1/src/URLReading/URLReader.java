package URLReading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLReader {

    private static URL worldTimeServerURL;
    private static BufferedReader in;
    private static ArrayList<String> test;
    private static RegionList regionList;

    public static void reader() throws IOException {
        createURL();
        createBufferedReader();

        regionList = new RegionList();
        String inputLine;
        String regionCode = "";
        String regionName = "";
        String regex = "";
        Pattern pattern;
        Matcher matcher;

        while((inputLine = in.readLine()) != null) {
            inputLine = inputLine.trim(); //removes any blank spaces at the beginning and enda of the string

            regex = "^<option value=\"";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(inputLine);

            if (matcher.find()) { //checks if the line of HTML-code contains area codes and names

                regex = "([A-Z0-9-]{2,6})";
                pattern = Pattern.compile(regex);
                matcher = pattern.matcher(inputLine);

                if (matcher.find()) { //finds the region codes
                    regionCode = matcher.group();
                    regionList.addRegionCode(regionCode);
                    //System.out.println(regionCode);
                }
            }
        }
        in.close();
        System.out.println(regionList.toString());
    }


    public static void createURL() {
        try {
            worldTimeServerURL = new URL("https://www.worldtimeserver.com/");
        } catch (MalformedURLException e) {
            System.err.println("URL is not valid!");
        }
    }

    public static void createBufferedReader() {
        try {
            in = new BufferedReader(new InputStreamReader(worldTimeServerURL.openStream()));
        } catch (IOException e) {
            System.err.println("IOException while creating a BufferedReader in method createBufferedReader");
        }

    }
}

/**
 * REGEX - NOTATER
 */

//AB //([A-Z0-9]{2,4})
//ABC
//AB1
//AB1C
//ABCD

//AB-CD //([A-Z]{2}-[A-Z0-9]{2,4})
//AB-CDE
//AB-CD1
//AB-CD1E
//AB-CDE1

// ^<option value="
// ^ Til å finne begynnelsen


