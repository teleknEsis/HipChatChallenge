package com.kmiller.hipchatchallenge.parse;

import com.kmiller.hipchatchallenge.model.SpannableMarker;
import com.kmiller.hipchatchallenge.robolectric.HipchatTestRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import rx.Observable;

import static junit.framework.Assert.assertEquals;

/**
 * Basic test for the Mention Parser
 */
@RunWith(HipchatTestRunner.class)
public class MentionParserTest {

    @Test
    public void testParse() {
        MentionParser parser = new MentionParser();
        Observable<SpannableMarker> mentions = parser.parse("@there should be @three mentions in this @result");
        assertEquals(3, mentions.toList().toBlocking().first().size());
    }
}
