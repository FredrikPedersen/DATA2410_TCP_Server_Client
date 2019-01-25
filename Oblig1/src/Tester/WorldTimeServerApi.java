package Tester;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.Set;

public class WorldTimeServerApi {
    private static String QUERY_URL = "https://www.worldtimeserver.com/search.aspx?searchfor=%s";
    private static String DEFAULT_USERAGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:64.0) Gecko/20100101 Firefox/64.0";

    public Optional<LocalDateTime> query(String location) {
        try {

            location = URLEncoder.encode(location, "UTF-8");

            Document htmlDocument = Jsoup.connect(String.format(QUERY_URL, location))
                    .userAgent(DEFAULT_USERAGENT)
                    .get();

            Elements time = htmlDocument.select("#time > div > input");

            if(time.size() == 0) {
                System.out.println("Not found!");
                return Optional.empty();
            }

            String timeStamp = time.attr("value");

            LocalDateTime dateTime = fromEpochMilli(timeStamp);

            return Optional.of(dateTime);
        } catch (IOException ex) {
            System.out.println("Jsoup: Unable to fetch document, exception: " + ex.getMessage());
            return Optional.empty();
        }
    }

    private LocalDateTime fromEpochMilli(String source) {
        // Just truncate any .11 values, our api needs a long
        long timeStamp = (long)Double.parseDouble(source);
        Instant instant = Instant.ofEpochMilli(timeStamp);

        return instant.atZone(ZoneId.of("GMT")).toLocalDateTime();
    }

}