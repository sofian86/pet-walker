package pl.petwalker.web.standalone;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestUrl {

    private static final Logger LOG = LoggerFactory.getLogger(TestUrl.class);

    private static final String DEFAULT_BASE_URL = "https://glacial-wave-7183.herokuapp.com";

    private static final String BASE_URL;

    static {
        BASE_URL = StringUtils.defaultIfBlank(System.getProperty("baseUrl"), DEFAULT_BASE_URL);
        LOG.info("Base URL initialized to " + BASE_URL);
    }

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
