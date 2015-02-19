package com.kmiller.hipchatchallenge.model;

/**
 * Ties a Link object to a SpannableMarker
 */
public class LinkSpannableMarker extends SpannableMarker {

    private Link link;

    public LinkSpannableMarker(Link link, int start, int end) {
        super(link.getUrl(), start, end);
        this.link = link;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
