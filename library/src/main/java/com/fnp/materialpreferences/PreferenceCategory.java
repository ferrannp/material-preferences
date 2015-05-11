package com.fnp.materialpreferences;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

public class PreferenceCategory extends android.preference.PreferenceCategory {

    public PreferenceCategory(Context context) {
        super(context);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            setLayoutResource(R.layout.preference_category);
        }
    }

    public PreferenceCategory(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            setLayoutResource(R.layout.preference_category);
        }
    }
}
