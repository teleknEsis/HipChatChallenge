package com.kmiller.hipchatchallenge.parse;

import com.kmiller.hipchatchallenge.model.SpannableMarker;
import com.kmiller.hipchatchallenge.robolectric.HipchatTestRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import rx.Observable;

import static junit.framework.Assert.assertEquals;

/**
 * Basic test for the Emoticon Parser
 */
@RunWith(HipchatTestRunner.class)
public class EmoticonParserTest {

    @Test
    public void testParse() {
        EmoticonParser parser = new EmoticonParser();
        Observable<SpannableMarker> emoticons = parser.parse("(hello) there should be (three) emoticons in this (result)");
        assertEquals(3, emoticons.toList().toBlocking().first().size());
    }
}
