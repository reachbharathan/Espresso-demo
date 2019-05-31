package com.example.kanthivelg.espresso;

import android.app.Activity;
import android.content.Context;
import android.support.test.espresso.FailureHandler;
import android.support.test.espresso.base.DefaultFailureHandler;
import android.view.View;

import com.jraska.falcon.Falcon;
import com.squareup.spoon.Spoon;

import org.hamcrest.Matcher;

import java.io.File;


public class CustomFailureHandler implements FailureHandler {
    private final FailureHandler mDelegate;
    private Activity activity;

    public CustomFailureHandler(Context targetContext, Activity activity) {
        mDelegate = new DefaultFailureHandler(targetContext);
        this.activity = activity;
    }

    @Override
    public void handle(Throwable error, Matcher<View> viewMatcher) {
        try {
            mDelegate.handle(error, viewMatcher);
        } catch (Exception e) {
            File file = Spoon.screenshot(activity, "Espresso_Assertion_Failed");
            Falcon.takeScreenshot(activity,file);
            throw e;
        }
    }

}
