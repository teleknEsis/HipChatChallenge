package com.kmiller.hipchatchallenge.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.kmiller.hipchatchallenge.R;
import com.kmiller.hipchatchallenge.holdr.Holdr_ActivityMain;

/**
 * This activity demos a possible application of the code written for the challenge.
 */
public class MainActivity extends ActionBarActivity implements Holdr_ActivityMain.Listener {

    private Holdr_ActivityMain mHoldr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHoldr = new Holdr_ActivityMain(findViewById(android.R.id.content));
        mHoldr.setListener(this);

        mHoldr.freeText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                onTransformButtonClick(null);
                return true;
            }
        });
    }

    @Override
    public void onTransformButtonClick(ImageView transformButton) {
        hideKeyboard();
        mHoldr.results.parseText(mHoldr.freeText.getText().toString());
        mHoldr.freeText.setText("");
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mHoldr.freeText.getWindowToken(), 0);
    }
}
