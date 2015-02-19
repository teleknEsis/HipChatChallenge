package com.kmiller.hipchatchallenge.parse;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.UnderlineSpan;

import com.google.gson.Gson;
import com.kmiller.hipchatchallenge.R;
import com.kmiller.hipchatchallenge.model.HipchatParseResult;
import com.kmiller.hipchatchallenge.model.Link;
import com.kmiller.hipchatchallenge.model.LinkSpannableMarker;
import com.kmiller.hipchatchallenge.model.SpannableMarker;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func3;
import rx.schedulers.Schedulers;

/**
 * This class is for the Coding Challenge issued by HipChat. It takes a message (String) and parses out
 * emoticons, mentions, and links. For the links, the html content is downloaded and the title of the page
 * is also parsed out.
 *
 * This class uses RxJava to handle the concurrent operations by zipping the 3 main operations into a single
 * observable.
 *
 * Additional functionality was added to produce a Spannable out of the parsed data
 */
public class HipchatParser {

    private Gson mGson;

    private EmoticonParser mEmoticonParser;
    private MentionParser mMentionParser;
    private LinkParser mLinkParser;

    public HipchatParser(OkHttpClient client) {
        mGson = new Gson();
        mEmoticonParser = new EmoticonParser();
        mMentionParser = new MentionParser();
        mLinkParser = new LinkParser(client);
    }

    public Observable<String> parseMessage(final String message) {
        return Observable.zip(getSpannableMarkers(mMentionParser, message),
                getSpannableMarkers(mEmoticonParser, message),
                getLinkSpannableMarkers(message),
                new Func3<List<SpannableMarker>, List<SpannableMarker>, List<LinkSpannableMarker>, String>() {
            @Override
            public String call(List<SpannableMarker> mentions, List<SpannableMarker> emoticons, List<LinkSpannableMarker> links) {
                HipchatParseResult result = new HipchatParseResult();
                result.setMentions(getResultsFromSpannableMarkers(mentions));
                result.setEmoticons(getResultsFromSpannableMarkers(emoticons));
                result.setLinks(getResultsFromLinkSpannableMarkers(links));
                return mGson.toJson(result);
            }
        });
    }

    public Observable<Spannable> parseSpannable(final Context context, final String message) {
        return Observable.zip(getSpannableMarkers(mMentionParser, message),
                getSpannableMarkers(mEmoticonParser, message),
                getLinkSpannableMarkers(message),
                new Func3<List<SpannableMarker>, List<SpannableMarker>, List<LinkSpannableMarker>, Spannable>() {
                    @Override
                    public Spannable call(List<SpannableMarker> mentions, List<SpannableMarker> emoticons, List<LinkSpannableMarker> links) {
                        SpannableStringBuilder spannable = new SpannableStringBuilder(message);
                        setEmoticonSpans(context, spannable, emoticons);
                        setMentionSpans(context, spannable, mentions);
                        setLinkSpans(context, spannable, links);
                        return spannable;
                    }
                });
    }

    private List<String> getResultsFromSpannableMarkers(List<SpannableMarker> markers) {
        List<String> results = new ArrayList<>();
        for (SpannableMarker marker : markers) {
            results.add(marker.getToken());
        }
        return results;
    }

    private List<Link> getResultsFromLinkSpannableMarkers(List<LinkSpannableMarker> markers) {
        List<Link> results = new ArrayList<>();
        for (LinkSpannableMarker marker : markers) {
            results.add(marker.getLink());
        }
        return results;
    }

    private Observable<List<SpannableMarker>> getSpannableMarkers(final Parser parser, final String message) {
         return Observable.create(new Observable.OnSubscribe<List<SpannableMarker>>() {
             @Override
             public void call(Subscriber<? super List<SpannableMarker>> subscriber) {
                 subscriber.onNext(parser.parse(message).toList().toBlocking().single());
             }
         }).subscribeOn(Schedulers.computation());
    }

    private Observable<List<LinkSpannableMarker>> getLinkSpannableMarkers(final String message) {
        return Observable.create(new Observable.OnSubscribe<List<LinkSpannableMarker>>() {
            @Override
            public void call(Subscriber<? super List<LinkSpannableMarker>> subscriber) {
                subscriber.onNext(mLinkParser.parseLinks(message).toList().toBlocking().single());
            }
        }).subscribeOn(Schedulers.computation());
    }

    private void setEmoticonSpans(Context context, SpannableStringBuilder spannable, List<SpannableMarker> markers) {
        EmoticonFactory factory = new EmoticonFactory();
        for (SpannableMarker marker : markers) {
            int resourceId = factory.getEmoticonResource(marker.getToken().toLowerCase());
            if (resourceId != EmoticonFactory.NOT_FOUND) {
                spannable.setSpan(new ImageSpan(context, resourceId),
                        marker.getStart(),
                        marker.getEnd(),
                        Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            }
        }
    }

    private void setMentionSpans(Context context, SpannableStringBuilder spannable, List<SpannableMarker> markers) {
        for (SpannableMarker marker : markers) {
            spannable.setSpan(new TextAppearanceSpan(context, R.style.Mention),
                    marker.getStart(),
                    marker.getEnd(),
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE);

            spannable.setSpan(new BackgroundColorSpan(context.getResources().getColor(R.color.hc_blue)),
                    marker.getStart(),
                    marker.getEnd(),
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
    }

    private void setLinkSpans(Context context, SpannableStringBuilder spannable, List<LinkSpannableMarker> markers) {
        for (SpannableMarker marker : markers) {
            spannable.setSpan(new UnderlineSpan(),
                    marker.getStart(),
                    marker.getEnd(),
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE);

            spannable.setSpan(new TextAppearanceSpan(context, R.style.Link),
                    marker.getStart(),
                    marker.getEnd(),
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
    }
}
