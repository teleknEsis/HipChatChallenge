package com.kmiller.hipchatchallenge.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import com.kmiller.hipchatchallenge.R;
import com.kmiller.hipchatchallenge.holdr.Holdr_ActivitySplash;

/**
 * Intro activity for the demo. It uses images and colors from the web site, with a little personal touch
 */
public class SplashActivity extends ActionBarActivity implements Holdr_ActivitySplash.Listener {

    private static final int ANIMATION_DURATION = 750;
    private static final int LOGO_START_DELAY = 500;
    private static final int REVEAL_DURATION = 1500;
    private static final int REVEAL_START_DELAY = 1000;
    private static final int PEOPLE_DELAY = 2000;
    private static final int CONTINUE_DELAY = 4000;

    private Holdr_ActivitySplash mHoldr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mHoldr = new Holdr_ActivitySplash(findViewById(android.R.id.content));
        mHoldr.setListener(this);

        ((View)mHoldr.logo).setAlpha(0);

        mHoldr.logo.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mHoldr.logo.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                fadeLogoIn();
                revealText();
                animatePeopleIn();
                showContinueButton();
            }
        });
    }

    private void fadeLogoIn() {
        mHoldr.logo.animate()
                .alpha(1)
                .setStartDelay(LOGO_START_DELAY)
                .setDuration(ANIMATION_DURATION)
                .setInterpolator(new LinearInterpolator())
                .start();
    }

    private void revealText() {
        mHoldr.centerText.setAlpha(0);
        mHoldr.revealView.animate()
                .translationX(mHoldr.revealView.getMeasuredWidth())
                .setDuration(REVEAL_DURATION)
                .setStartDelay(REVEAL_START_DELAY)
                .setInterpolator(new DecelerateInterpolator())
                .start();
        mHoldr.centerText.animate()
                .alpha(1)
                .setDuration(REVEAL_DURATION)
                .setStartDelay(REVEAL_START_DELAY)
                .setInterpolator(new LinearInterpolator())
                .start();
    }

    private void animatePeopleIn() {
        int initialStart = PEOPLE_DELAY;
        mHoldr.person1.animateUp(initialStart);
        mHoldr.person2.animateUp(initialStart + 200);
        mHoldr.person3.animateUp(initialStart + 400);
        mHoldr.person4.animateUp(initialStart + 600);
        mHoldr.person5.animateUp(initialStart + 800);
        mHoldr.person6.animateUp(initialStart + 1000);
    }

    private void showContinueButton() {
        mHoldr.viewDemoButton.animate()
                .alpha(1)
                .setDuration(ANIMATION_DURATION)
                .setStartDelay(CONTINUE_DELAY)
                .setInterpolator(new DecelerateInterpolator())
                .start();
    }

    @Override
    public void onViewDemoButtonClick(Button viewDemoButton) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
