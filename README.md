# material-preferences
Making straightforward to create a preference screen on pre-Lollipop devices looking exactly as if it was a Lollipop one. API 7.

## Install (Gradle dependency)

Uploading...

## Features
- Material look (title, summary, widgets...) on pre-Lollipop
- Nested PreferenceScreen with Toolbar and handling configuration change correctly (restore state)
- No API change

## Usage
### SettingsActivity
```java
public class SettingsActivity extends com.fnp.materialpreferences.PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /**
         * We load a PreferenceFragment which is the recommended way by Android 
         * see @http://developer.android.com/guide/topics/ui/settings.html#Fragment
         * @TargetApi(11)
         */
        setPreferenceFragment(new MyPreferenceFragment());
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
<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android"
    android:title="@string/settings" android:key="settings">
    <!-- title will be used in the Toolbar -->
    <PreferenceCategory
        <!-- Any value from PreferenceCategory --> >
        <CheckBoxPreference
            <!-- Any value from CheckBoxPreference --> />
        </PreferenceCategory>
</PreferenceScreen>
```

### Result
<img src=assets/result-1.png width=500 height=845 />

### Beware: Do not override library resources intentionless
Check the [resources](library/src/main/res) folder, if you use in your project a resource with the same name as this library, for example, "md_preference_material.xml", your resource will have preference and that might cause the library to fail if you are not doing this intentionally.

More info: https://developer.android.com/tools/projects/index.html#considerations @see "Resource conflicts"

## TODO
- ~~Add toolbar to all PreferenceFragment (including nested screens)~~
- ~~Proper tint based on accent color~~
- ~~Show title and summary of the preferences in Material format~~
- ~~Proper paddings and margins~~
- ~~Use Material Preference Category style~~
- Override all kind of Preferences
- ListPreference should update its summary based on the value or a custom summary array
- Use Material Dialogs style
