package com.kmiller.hipchatchallenge.parse;

import java.util.regex.Pattern;

/**
 * Parses emoticons, such as (woah), out of a message
 */
public class EmoticonParser extends Parser {

    @Override
    protected Pattern getPattern() {
        return Pattern.compile("\\(([\\x00-\\x7F|^\\)]{1,15})\\)");
    }

    @Override
    protected int getGroup() {
        return 1;
    }


}
