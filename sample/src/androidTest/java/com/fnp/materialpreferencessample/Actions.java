package com.fnp.materialpreferencessample;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.test.InstrumentationRegistry;

public class Actions {
    public static void rotateScreen(Activity activity) {
        Context context = InstrumentationRegistry.getTargetContext();
        int orientation = context.getResources().getConfiguration().orientation;

        activity.setRequestedOrientation(
                (orientation == Configuration.ORIENTATION_PORTRAIT) ?
                        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE :
                        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        );
    }
}
