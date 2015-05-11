package com.fnp.materialpreferences;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

public class CheckBoxPreference extends android.preference.CheckBoxPreference {
    public CheckBoxPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(21)
    public CheckBoxPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public CheckBoxPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CheckBoxPreference(Context context) {
        super(context);
        init();
    }

    private void init(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            setLayoutResource(R.layout.preference_material_widget);
        }
    }
}
