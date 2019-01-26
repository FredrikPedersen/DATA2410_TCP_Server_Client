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
    private static String regex;
    private static String regionCode;
    private static String regionName;
    private static String inputLine;
    private static String userRegion;
    private static Pattern pattern;
    private static Matcher matcher;
    private static String time;

    public static void findTime() throws IOException {
        reader();

        if (!regionName.equals(userRegion)) {
                System.err.println("Couldn't find your specified area. Did you write it correctly? " +
                        "Remember that in countries with several time zones you will have to specify your state or county. E.g. United States - South Dakota (western)");
        }

        URL areaTimeURL = createURL("https://www.worldtimeserver.com/current_time_in_" +regionCode+".aspx");
        createBufferedReader(areaTimeURL);

        while ((inputLine = in.readLine()) != null) {
            inputLine = inputLine.trim();
            findRegionTime();
        }
    }

    private static void reader() throws IOException {
        worldTimeServerURL = createURL("https://www.worldtimeserver.com/");
        createBufferedReader(worldTimeServerURL);
        userRegion = UserInput.getUserInput();

        while((inputLine = in.readLine()) != null) {
            inputLine = inputLine.trim(); //removes any blank spaces at the beginning and enda of the string

            regex = "^<option value=\"";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(inputLine);

            if (matcher.find()) { //checks if the line of HTML-code contains area codes and names
                findRegionCode();
                findRegionName();

                if (regionName.toLowerCase().trim().equals(userRegion.toLowerCase().trim())) {
                    break;
                }
            }
        }
        in.close();
    }

    private static URL createURL(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            System.err.println("URL is not valid!");
            return null;
        }
    }

    private static void createBufferedReader(URL url) {
        try {
            in = new BufferedReader(new InputStreamReader(url.openStream()));
        } catch (IOException e) {
            System.err.println("IOException while creating a BufferedReader in method createBufferedReader");
        }
    }

    private static void findRegionCode() {
        regex = "([A-Z0-9-]{2,6})";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(inputLine);

        if (matcher.find()) { //finds the region codes
            regionCode = matcher.group();
        }
    }

    private static void findRegionName() {
        regex = "(<[^>]+>).*?(<[^>]+>)";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(inputLine);

        if (matcher.find()) {
            regionName = inputLine.replace(matcher.group(1), "");
            regionName = regionName.replace(matcher.group(2), "");
        }
    }

    private static void findRegionTime() throws IOException {
        if (inputLine.equals("<span id=\"theTime\" class=\"fontTS\">\n")) {
            time = in.readLine();
            System.out.println(time);
        }
    }
}


