# material-preferences
Making straightforward to create a preference screen on pre-Lollipop devices looking exactly as if it was a Lollipop one. API 7.

## Install (Gradle dependency)

    compile 'com.fnp:material-preferences:0.1.4'

## Features
- Material look (title, summary, widgets...) on pre-Lollipop devices
- Nested PreferenceScreen with Toolbar and handling configuration change correctly (restore state)
- No API change (only trick for [SwitchPreference](README.md#switchpreference))

## Usage

Please check the [sample project](sample).

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
*IMPORTANT*: Use always an ```android:key``` on your ```PreferenceScreen(s)```, if not, the library won't be able to restore the state when a configuration change happens (like device rotation).

```xml
<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android"
    android:key="settings"
    android:title="@string/settings">
    <!-- title will be used in the Toolbar -->
    <PreferenceCategory
        <!-- Any value from PreferenceCategory --> >
        <CheckBoxPreference
            <!-- Any value from CheckBoxPreference --> />
        </PreferenceCategory>
</PreferenceScreen>
```

### SwitchPreference
There is a trick for ```SwitchPreference```, instead, use a ```CheckBoxPreference``` and use the ```mp_preference_layout```as a widget layout. For now, this is the only way to style it.

```xml
<CheckBoxPreference
    android:defaultValue="true"
    android:key="some_key"
    android:title="Hooray!"
    android:widgetLayout="@layout/mp_preference_switch"/>
```

### Result
<img src=assets/result-1.png width=500 height=845 />

### Beware: Do not override library resources intentionless
Check the [resources](library/src/main/res) folder, if you use in your project a resource with the same name as this library, for example, "md_preference_material.xml", your resource will have preference and that might cause the library to fail if you are not doing this intentionally.

More info: https://developer.android.com/tools/projects/index.html#considerations @see "Resource conflicts"
