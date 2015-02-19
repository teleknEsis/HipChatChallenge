package com.kmiller.hipchatchallenge.model;

/**
 * This class holds information that can be used to create a Span after the token is parsed from a message
 */
public class SpannableMarker {

    private String token;
    private int start;
    private int end;

    public SpannableMarker(String token, int start, int end) {
        this.token = token;
        this.start = start;
        this.end = end;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
