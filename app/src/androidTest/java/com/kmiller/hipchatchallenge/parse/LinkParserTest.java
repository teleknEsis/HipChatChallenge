package com.kmiller.hipchatchallenge.parse;

import com.kmiller.hipchatchallenge.model.LinkSpannableMarker;
import com.kmiller.hipchatchallenge.model.SpannableMarker;
import com.kmiller.hipchatchallenge.robolectric.HipchatTestRunner;
import com.squareup.okhttp.OkHttpClient;

import org.junit.Test;
import org.junit.runner.RunWith;

import rx.Observable;

import static junit.framework.Assert.assertEquals;

/**
 * Basic test for the Mention Parser
 */
@RunWith(HipchatTestRunner.class)
public class LinkParserTest {

    @Test
    public void testParse() {
        LinkParser parser = new LinkParser(new OkHttpClient());
        Observable<SpannableMarker> links = parser.parse("http://there.com should be http://www.three.com links in te https://results.net");
        assertEquals(3, links.toList().toBlocking().first().size());
    }
}
