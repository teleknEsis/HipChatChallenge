package com.kmiller.hipchatchallenge.views;

import android.app.Activity;
import android.content.Context;
import android.text.Spannable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.kmiller.hipchatchallenge.parse.HipchatParser;
import com.squareup.okhttp.OkHttpClient;

import rx.functions.Action1;

/**
 * Uses the HipchatParser to parse a Spannable containing emoticon, mention, and link spans, and then
 * sets that spannable on itself
 */
public class HipchatTextView extends TextView {

    private HipchatParser mParser;

    public HipchatTextView(Context context) {
        super(context);
        init();
    }

    public HipchatTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HipchatTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mParser = new HipchatParser(new OkHttpClient());
        if (!TextUtils.isEmpty(getText())) {
            parseText(getText().toString());
        }
    }

    public void parseText(String text) {
        mParser.parseSpannable(getContext(), text).subscribe(new Action1<Spannable>() {
            @Override
            public void call(final Spannable spannable) {
                if (getContext() instanceof Activity) {
                    //ensure we're on the UI thread when we try to do this
                    ((Activity)getContext()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setText(spannable);
                        }
                    });
                }
            }
        });
    }
}
