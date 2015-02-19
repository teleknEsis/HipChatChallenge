package com.kmiller.hipchatchallenge.model;

/**
 * Object that ties together a url and title from the html response of the url
 */
public class Link {

    private String url;
    private String title;

    public Link() {}

    public Link(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
