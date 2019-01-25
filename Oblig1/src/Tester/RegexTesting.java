package Tester;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTesting {


    public static void main(String[] args) throws Exception {

        // String to be scanned to find the pattern.
        String line = "<option value=\"US-ND1\">United States - North Dakota (western)</option>";
        String regex = "^<option value=\"";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);

        if (matcher.find()) {
            String areaCode = "";
            String areaName = "";

            regex = "([A-Z0-9-]{2,6})";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(line);

            if (matcher.find()) {
                areaCode = matcher.group();
                //System.out.println("Code = " + areaCode);
            }

            //FÅR SELEKTERT HTML-tagen, men får ikke tak i det mellom (som er det vi vil ha. FAEN ELLER BALFLGUHFHF
            regex = "(<[^>]+>).*?(<[^>]+>)";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(line);

            if (matcher.find()) {
                System.out.println(matcher.group(1) +" "+ matcher.group(2));
                areaName = "";
                System.out.println("Name = " + areaName);
            }



        }

    }
}

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

