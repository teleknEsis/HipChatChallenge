package com.kmiller.hipchatchallenge.parse;

import com.kmiller.hipchatchallenge.model.Link;
import com.kmiller.hipchatchallenge.model.LinkSpannableMarker;
import com.kmiller.hipchatchallenge.model.SpannableMarker;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * After parsing the urls out of the message, the html is downloaded from each url and the title
 * is parsed out, returning a Link object
 */
public class LinkParser extends Parser {

    private OkHttpClient mClient;
    private Pattern mTitlePattern;

    public LinkParser(OkHttpClient client) {
        mClient = client;
        mTitlePattern = Pattern.compile("<title>([^<]+)</title>", Pattern.CASE_INSENSITIVE);
    }

    @Override
    protected Pattern getPattern() {
        return android.util.Patterns.WEB_URL;
    }

    @Override
    protected int getGroup() {
        return 0;
    }

    public Observable<LinkSpannableMarker> parseLinks(final String value) {
        return Observable.create(new Observable.OnSubscribe<LinkSpannableMarker>() {
            @Override
            public void call(final Subscriber<? super LinkSpannableMarker> subscriber) {
                parse(value).doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        subscriber.onCompleted();
                    }
                }).subscribe(new Action1<SpannableMarker>() {
                    @Override
                    public void call(SpannableMarker spannableMarker) {
                        Link link = fetchLink(spannableMarker);
                        subscriber.onNext(new LinkSpannableMarker(link, spannableMarker.getStart(), spannableMarker.getEnd()));
                    }
                });
            }
        }).subscribeOn(Schedulers.io());
    }

    /**
     * Synchronously fetches the page title of the link supplied in the marker
     * @param marker
     * @return
     */
    private Link fetchLink(SpannableMarker marker) {
        Link link = new Link();
        link.setUrl(marker.getToken());
        try {
            Request request = new Request.Builder()
                    .url(link.getUrl())
                    .build();

            Response response = mClient.newCall(request).execute();
            String responseBody = response.body().string();

            Matcher matcher = mTitlePattern.matcher(responseBody);
            if (matcher.find()) {
                link.setTitle(matcher.group(1));
            }
        } catch (IOException exception) {
            //Just eat the error so it doesn't crash
        }
        return link;
    }
}
