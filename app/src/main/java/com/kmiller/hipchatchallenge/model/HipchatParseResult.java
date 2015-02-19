package com.kmiller.hipchatchallenge.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The resulting object that will get transformed into a Json string as a result of the HipchatParser
 */
public class HipchatParseResult {

    private List<String> mentions;
    private List<String> emoticons;
    private List<Link> links;

    public HipchatParseResult() {
        mentions = new ArrayList<>();
        emoticons = new ArrayList<>();
        links = new ArrayList<>();
    }

    public List<String> getMentions() {
        return mentions;
    }

    public void setMentions(List<String> mentions) {
        this.mentions = mentions;
    }

    public List<String> getEmoticons() {
        return emoticons;
    }

    public void setEmoticons(List<String> emoticons) {
        this.emoticons = emoticons;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addMention(String mention) {
        mentions.add(mention);
    }

    public void addEmoticon(String emoticon) {
        emoticons.add(emoticon);
    }

    public void addLink(Link link) {
        links.add(link);
    }
}
