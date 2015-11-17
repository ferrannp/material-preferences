package com.fnp.materialpreferencessample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.fnp.materialpreferencessample.Actions.rotateScreen;
import static com.fnp.materialpreferencessample.Matchers.matchToolbarTitle;

@RunWith(AndroidJUnit4.class)
public class AboutActivityTest {

    @Rule public ActivityTestRule<AboutActivity> rule = new ActivityTestRule<>(AboutActivity.class);

    @Test
    public void testTitleIsKeptBetweenRotations() {
        matchToolbarTitle("About");
        rotateScreen(rule.getActivity());
        matchToolbarTitle("About");
    }
}