package pl.jaworskimateuszm.myleagues.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {

     public static Integer stringToInt(String text) {
        try {
            if (text.isEmpty())
                return  -1;
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static Date stringToDate(String text) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd h:mm").parse(text);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
