package com.fnp.materialpreferencessample;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.fnp.materialpreferencessample.Actions.rotateScreen;
import static com.fnp.materialpreferencessample.Matchers.matchToolbarTitle;

@RunWith(AndroidJUnit4.class)
public class SettingsActivityTest {
    @Rule
    public IntentsTestRule<SettingsActivity> rule = new IntentsTestRule<>(SettingsActivity.class);

    @Test
    public void testTitleRotations() {
        matchToolbarTitle("Settings");
        rotateScreen(rule.getActivity());
        matchToolbarTitle("Settings");
    }

    @Test
    public void testSecondLevelTitleRotations() {
        String label = "Second level";
        onView(withText(label)).perform(click());

        matchToolbarTitle(label);
        rotateScreen(rule.getActivity());
        matchToolbarTitle(label);
    }

    @Test
    public void testThirdLevelTitleRotations() {
        onView(withText("Second level")).perform(click());
        String label = "Third level";
        onView(withText(label)).perform(click());

        matchToolbarTitle(label);
        rotateScreen(rule.getActivity());
        matchToolbarTitle(label);
    }

    @Test
    public void testFourthLevelTitleRotations() {
        onView(withText("Second level")).perform(click());
        onView(withText("Third level")).perform(click());
        String label = "Fourth level";
        onView(withText(label)).perform(click());

        matchToolbarTitle(label);
        rotateScreen(rule.getActivity());
        matchToolbarTitle(label);
    }

    @Test
    public void testAboutSendsIntent() {
        onView(withText("About")).perform(click());
        intended(hasAction("android.intent.action.ABOUT_ACTIVITY"));
    }
}