import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLReader {

    private static BufferedReader in;
    private static Matcher matcher;
    private static String inputLine;
    private static String time;
    private static String date;

    /**
     * <h2>Reader</h2>
     * This is the method to be called to read from a URL. It crates a URL and BufferedReader-object, then parses
     * through the URL while calling methods to find where the time and date is stored on worldtimeserver.com.
     *
     * @return the date and time. If no time or date is found in the HTML, returns null.
     */
    public static String reader() {

        String userRegion = UserInput.getUserInput(); //Where do the user want to see what time and date it is?
        userRegion = userRegion.replaceAll(" ", "+"); //Replaces any blank spaces in the user input with a +. Eg New York = New+York

        try {
            URL worldTimeServerURL = new URL("https://www.worldtimeserver.com/search.aspx?searchfor=" + userRegion); //URL for the worldtimerserver's region page
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

    /**
     * <h2>findRegionTime</h2>
     * Method for finding the time in the user's desired region. Uses a regular expression to look for the tag used on the line
     * before the line where the time is placed in the HTML-document. If found, the time variable is set to equal the next line
     * without any blank spaces.
     *
     * @throws IOException if the BufferedReader fails to read the next line
     */
    private static void findRegionTime() throws IOException {
        Pattern regex = Pattern.compile("<span id=\"theTime\" class=\"fontTS\">");
        matcher = regex.matcher(inputLine);

        if (matcher.find()) {
            time = in.readLine().trim();
        }
    }

    /**
     * <h2>findRegionDate</h2>
     * Method for finding the date in the user's desired region. Uses a regular expression that matches the date-signature
     * in the HTML-document. If found, the date variable is set to equal the line where the date is found.
     *
     */
    private static void findRegionDate(){
        //Regular expression mathcing "any sentence that starts with a 6-9-letter word containing any letters followed by a dash and space followed by any 3-9-letter
        //word followed by a space and any two-digit number followed by a dash and a space followed by any 4-digit number.
        Pattern regex = Pattern.compile("^([A-Za-z]{6,9}, [A-Za-z]{3,9} [0-9]{2}, [0-9]{4})");
        matcher = regex.matcher(inputLine);

        if (matcher.find()) {
            date = inputLine;
        }
    }
}


