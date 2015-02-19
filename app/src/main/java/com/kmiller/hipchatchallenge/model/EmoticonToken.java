package com.kmiller.hipchatchallenge.model;

import android.support.annotation.DrawableRes;

/**
 * Links a know emoticon token to an embedded drawable
 */
public class EmoticonToken {

    private String token;
    private @DrawableRes int resource;

    public EmoticonToken(String token, @DrawableRes int resourceId) {
        this.token = token;
        this.resource = resourceId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }
}
