package per.itachi.java.features.net;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class UrlTest {

    @Test
    public void test() {
        String strUrl = "https://www.example.com/";
        try {
            URL url = new URL(strUrl);
            log.info("url.path={}, url.path={}, [{}]",
                    url.getPath(), Arrays.asList(url.getPath().split("/")), "/".substring(0).length());
        }
        catch (MalformedURLException e) {
            log.error("Error occurred. ", e);
        }
    }

}
