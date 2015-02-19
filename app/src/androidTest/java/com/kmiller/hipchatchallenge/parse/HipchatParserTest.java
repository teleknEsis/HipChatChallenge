package com.kmiller.hipchatchallenge.parse;

import com.google.gson.Gson;
import com.kmiller.hipchatchallenge.model.HipchatParseResult;
import com.kmiller.hipchatchallenge.model.Link;
import com.kmiller.hipchatchallenge.robolectric.HipchatTestRunner;
import com.squareup.okhttp.OkHttpClient;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import rx.functions.Action1;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Tests for the HipchatParser. Tests all of the "acceptance criteria" from the issued challenge
 */
@RunWith(HipchatTestRunner.class)
public class HipchatParserTest {

    private Gson mGson;
    private HipchatParser mParser;

    @Before
    public void setup() {
        mGson = new Gson();
        //The http client *really* ought to be mocked out so that these tests won't ever fail if
        //the remote content changes. I started doing that with Mockito, but the Response class
        //is final so I figured it wasn't crucial for the sake of this demo
        mParser = new HipchatParser(new OkHttpClient());
    }

    @Test
    public void singleMentionTest() {
        String message = "@chris you around?";

        HipchatParseResult result = new HipchatParseResult();
        result.setMentions(Arrays.asList("chris"));

        verifyParseResult(message, mGson.toJson(result));
    }

    @Test
    public void emoticonsTest() {
        String message = "Good morning! (megusta) (coffee)";

        HipchatParseResult result = new HipchatParseResult();
        result.setEmoticons(Arrays.asList("megusta", "coffee"));

        verifyParseResult(message, mGson.toJson(result));
    }

    @Test
    public void linkTest() {
        String message = "Olympics are starting soon; http://www.nbcolympics.com";

        HipchatParseResult result = new HipchatParseResult();
        result.setLinks(Arrays.asList(new Link("http://www.nbcolympics.com", "NBC Olympics | Home of the 2016 Olympic Games in Rio")));

        verifyParseResult(message, mGson.toJson(result));
    }

    @Test
    public void allTheThingsTest() {
        String message = "@bob @john (success) such a cool feature; https://twitter.com/jdorfman/status/430511497475670016";

        HipchatParseResult result = new HipchatParseResult();
        result.setLinks(Arrays.asList(new Link("https://twitter.com/jdorfman/status/430511497475670016", "Justin Dorfman on Twitter: &quot;nice @littlebigdetail from @HipChat (shows hex colors when pasted in chat). http://t.co/7cI6Gjy5pq&quot;")));
        result.setEmoticons(Arrays.asList("success"));
        result.setMentions(Arrays.asList("bob", "john"));

        verifyParseResult(message, mGson.toJson(result));
    }

    private void verifyParseResult(String message, final String expected) {
        final CountDownLatch latch = new CountDownLatch(1);

        mParser.parseMessage(message)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        assertEquals(expected, s);
                        latch.countDown();
                    }
                });

        try {
            latch.await();
        } catch (InterruptedException exception) {
            assertTrue(false);
        }
    }
}
