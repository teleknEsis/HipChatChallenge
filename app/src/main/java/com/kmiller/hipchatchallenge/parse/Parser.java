package com.kmiller.hipchatchallenge.parse;

import com.kmiller.hipchatchallenge.model.SpannableMarker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.Observable;
import rx.Subscriber;

/**
 * Abstract class used to parse SpannableMarkers out of a message. Uses RxJava to stream the results as
 * they are parsed.
 */
public abstract class Parser {

    protected Pattern mPattern;

    public Parser() {
        mPattern = getPattern();
    }

    protected abstract Pattern getPattern();

    protected abstract int getGroup();

    protected Observable<SpannableMarker> parse(final String message) {
        return Observable.create(new Observable.OnSubscribe<SpannableMarker>() {
            @Override
            public void call(Subscriber<? super SpannableMarker> subscriber) {
                Matcher matcher = mPattern.matcher(message);
                while (matcher.find()) {
                    String target = matcher.group(getGroup());
                    subscriber.onNext(new SpannableMarker(target, matcher.start(), matcher.end()));
                }
                subscriber.onCompleted();
            }
        });
    }
}
