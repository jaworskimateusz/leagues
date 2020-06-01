package pl.jaworskimateuszm.myleagues.utils;

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
}
