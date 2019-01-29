package URLReading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLReader {

    private static BufferedReader in;
    private static String inputLine;
    private static String time;
    private static String date;
    private static Matcher matcher;

    public static String reader() {

        String userRegion = UserInput.getUserInput();
        userRegion = userRegion.replaceAll(" ", "+");

        try {
            URL worldTimeServerURL = new URL("https://www.worldtimeserver.com/search.aspx?searchfor=" + userRegion);
            in = new BufferedReader(new InputStreamReader(worldTimeServerURL.openStream()));

            while ((inputLine = in.readLine()) != null) {
                inputLine = inputLine.trim(); //removes any blank spaces at the beginning and end of the string

                findRegionTime();
                findRegionDate();
            }

            in.close();

            if (date == null || time == null) {
                return ("I can't find anything for that location. Please check your spelling. If you live in a country with several time zones, please type in" +
                        "your closest major city or capitol");

            }

        }catch (IOException e) {
            e.printStackTrace();
        }

        return date + " " + time;
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


