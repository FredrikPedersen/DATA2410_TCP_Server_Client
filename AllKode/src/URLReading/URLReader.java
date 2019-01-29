package URLReading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLReader {

    private static BufferedReader in;
    private static String inputLine;
    private static String time;
    private static String date;
    private static Matcher matcher;

    public static String reader() throws IOException {
        String userRegion = UserInput.getUserInput();
        userRegion = userRegion.replaceAll(" ", "+");
        URL worldTimeServerURL = createURL("https://www.worldtimeserver.com/search.aspx?searchfor="+userRegion);
        createBufferedReader(worldTimeServerURL);

        while((inputLine = in.readLine()) != null) {
            inputLine = inputLine.trim(); //removes any blank spaces at the beginning and end of the string

            findRegionTime();
            findRegionDate();
        }

        in.close();
        if (date == null || time == null) {
            return ("I can't find anything for that location. Please check your spelling. If you live in a country with several time zones, please type in" +
                    "your closest major city or capitol");

        }
        return date + " " + time;
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
            e.printStackTrace();
        }
    }

    private static void findRegionTime() throws IOException {
        Pattern regex = Pattern.compile("<span id=\"theTime\" class=\"fontTS\">", Pattern.MULTILINE | Pattern.DOTALL);
        matcher = regex.matcher(inputLine);

        if (matcher.find()) {
            time = in.readLine().trim();
        }
    }

    private static void findRegionDate(){
        Pattern regex = Pattern.compile("^([A-Za-z]{6,9}, [A-Za-z]{3,9} [0-9]{2}, [0-9]{4})");
        matcher = regex.matcher(inputLine);

        if (matcher.find()) {
            date = inputLine;
        }
    }
}


