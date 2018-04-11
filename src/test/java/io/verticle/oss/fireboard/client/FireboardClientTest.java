package io.verticle.oss.fireboard.client;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * @author Jens Saade
 */
public class FireboardClientTest {

    //@Test
    public void post() {

        Status sample = null;

        try {
            sample = new FireboardMessageBuilder()
                    .event("event")
                    .category("cat")
                    .severity(3)
                    .ident("ident")
                    .link(new URL("https://google.com"))
                    .message("message")
                    .status(StatusEnum.warn)
                    .uuid("123")
                    .properties(
                            new MessagePropertyHelper()
                                    .section("test1")
                                    .property("a", "b")
                                    .section("test2")
                                    .property("b", "c")
                                    .property("c", "d")
                                    .build()
                    ).build();

            StatusJSONConverter converter = new StatusJSONConverter();
            String json = converter.convert(sample);
            assertNotNull(json);
            System.out.println(json);

        } catch (MalformedURLException e) {
            fail();
        }

        assertNotNull(sample);

        FireboardClient.post(sample);


        try {
            Thread.sleep(4000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}