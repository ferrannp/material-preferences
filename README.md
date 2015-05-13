# material-preferences
Making straightforward to create a preference screen on pre-Lollipop devices looking exactly as if it was a Lollipop one. API 7.

## Install (Gradle dependency)

Uploading...

## Usage
### SettingsActivity
```java
public class SettingsActivity extends com.fnp.materialpreferences.PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /**
         * Optional, we load a PreferenceFragment which is 
         * the recommended way by Android 
         * see @http://developer.android.com/guide/topics/ui/settings.html#Fragment
         * @TargetApi(11)
         */
        loadFragment(new MyPreferenceFragment());
    }

    public static class MyPreferenceFragment extends com.fnp.materialpreferences.PreferenceFragment {
        @Override
        public int addPreferencesFromResource() {
            return R.xml.preferences;
        }
    }
}
```

### XML
```xml
<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android">
    <com.fnp.materialpreferences.PreferenceCategory
        <!-- Any value from PreferenceCategory --> >
        <com.fnp.materialpreferences.CheckBoxPreference
            <!-- Any value from CheckBoxPreference --> />
        </com.fnp.materialpreferences.PreferenceCategory>
</PreferenceScreen>
```

### Result
<img src=assets/result-1.png width=500 height=845 />

## TODO
- ~~Add toolbar to PreferenceActivity with optional title~~
- ~~Proper tint based on accent color~~
- ~~Show title and summary of the preferences in Material format~~
- ~~Proper paddings and margins~~
- ~~Use Material Preference Category style~~
- Override all kind of Preferences
- ListPreference should update its summary based on the value or a custom summary array
- Use Material Dialogs style
