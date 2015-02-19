package com.kmiller.hipchatchallenge.parse;

import java.util.regex.Pattern;

/**
 * Parses Mentions, such as "@chris" from a message
 */
public class MentionParser extends Parser {

    @Override
    protected Pattern getPattern() {
        return Pattern.compile("@([^\\W]+)");
    }

    @Override
    protected int getGroup() {
        return 1;
    }
}
