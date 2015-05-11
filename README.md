# material-preferences
Making straightforward to create a preference screen on pre-Lollipop devices looking exactly as if it was a Lollipop one. API 7.

## Install (Gradle dependency)

Uploading...

## Usage
### SettingsActivity
    public class SettingsActivity extends com.fnp.materialpreferences.PreferenceActivity {
        @Override
        public String getToolbarName() {
            return getString(R.string.settings);
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            loadFragment(new MyPreferenceFragment());
        }

        public static class MyPreferenceFragment extends com.fnp.materialpreferences.PreferenceFragment {

            @Override
            public int addPreferencesFromResource() {
                return R.xml.preferences;
            }
        }
    }

### XML

    <PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android">
        <com.fnp.materialpreferences.PreferenceCategory
            <!-- Any value from PreferenceCategory --> >
            <com.fnp.materialpreferences.CheckBoxPreference
                <!-- Any value from CheckBoxPreference --> />
            </com.fnp.materialpreferences.PreferenceCategory>
    </PreferenceScreen>

### Result
![Screenshots](/assets/result-1.png)

## TODO
- ~~Add toolbar to PreferenceActivity with optional title~~
- ~~Proper tint based on accent color~~
- ~~Show title and summary of the preferences in Material format~~
- ~~Proper paddings and margins~~
- ~~Use Material Preference Category style~~
- Override all kind of Preferences
- ListPreference should update its summary based on the value or a custom summary array
- Use Material Dialogs style
