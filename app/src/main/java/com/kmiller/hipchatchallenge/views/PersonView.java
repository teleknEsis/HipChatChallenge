package com.kmiller.hipchatchallenge.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

import com.kmiller.hipchatchallenge.R;
import com.kmiller.hipchatchallenge.holdr.Holdr_ViewIntroPerson;
import com.kmiller.hipchatchallenge.model.EmoticonToken;
import com.kmiller.hipchatchallenge.parse.EmoticonFactory;

import java.util.List;

/**
 * Custom view for each person on the Splash screen. Handles animating the person image in, as
 * well as animating an emoticon for each person.
 */
public class PersonView extends FrameLayout {

    private static final int ANIMATION_DURATION = 750;

    private Holdr_ViewIntroPerson mHoldr;
    private List<EmoticonToken> mTokens;
    private boolean mInitialized;
    private Handler mHandler;
    private boolean mAnimating = true;

    public PersonView(Context context) {
        super(context);
        init(null);
    }

    public PersonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PersonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        mHoldr = new Holdr_ViewIntroPerson(LayoutInflater.from(getContext()).inflate(R.layout.view_intro_person, this, true));

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.PersonView);
            int resource = a.getResourceId(R.styleable.PersonView_person_drawable, R.drawable.person1);
            mHoldr.person.setImageResource(resource);
        }

        mTokens = new EmoticonFactory().getEmoticonTokens();
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //Hide the view when it's first laid out
        if (!mInitialized) {
            setTranslationY(getMeasuredHeight());
            mInitialized = true;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mAnimating = false;
    }

    public void animateUp(final int delay) {
        this.animate()
                .translationY(0)
                .setStartDelay(delay)
                .setDuration(ANIMATION_DURATION)
                .setInterpolator(new DecelerateInterpolator())
                .start();

        mHandler.postDelayed(new EmoticonRunnable(), delay + (int)(Math.random() * 1000));
    }

    private EmoticonToken getRandomEmoticon() {
        int index = (int)Math.floor(Math.random() * mTokens.size());
        return mTokens.get(index);
    }

    private class EmoticonRunnable implements Runnable {
        @Override
        public void run() {
            if (mAnimating) {
                mHoldr.emoticon.setImageResource(getRandomEmoticon().getResource());
                mHoldr.emoticon.setTranslationY(0);
                ((View) mHoldr.emoticon).setAlpha(1);

                mHoldr.emoticon.animate()
                        .setStartDelay(0)
                        .translationY(Math.round(-mHoldr.person.getMeasuredHeight() * 1.5))
                        .setDuration(1500)
                        .setInterpolator(new LinearInterpolator())
                        .start();

                mHoldr.emoticon.animate()
                        .setStartDelay(1000)
                        .alpha(0)
                        .setInterpolator(new LinearInterpolator())
                        .setDuration(500)
                        .start();

                mHandler.postDelayed(this, 1500 + Math.round(Math.random() * 1500));
            }
        }
    }
}
