package pl.petwalker.web.standalone;

public class TestUrl {
    private static final String BASE_URL = "https://glacial-wave-7183.herokuapp.com";

    public static String url(final String context) {
        return BASE_URL + "/" + removeLeadingSlashes(context);
    }

    private static String removeLeadingSlashes(String context) {
        String result = context;
        while (result.length() > 0 && result.charAt(0) == '/') {
            result = result.substring(1);
        }
        return result;
    }
}
