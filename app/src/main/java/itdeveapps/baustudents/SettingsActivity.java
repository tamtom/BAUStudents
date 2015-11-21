package itdeveapps.baustudents;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.parse.ParsePush;

public class SettingsActivity extends PreferenceActivity {
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preflay);
        LinearLayout root = (LinearLayout) findViewById(android.R.id.list).getParent().getParent().getParent();
        Toolbar bar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.tool_bar, root, false);
        root.addView(bar, 0); // insert at top
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final CheckBoxPreference it = (CheckBoxPreference) findPreference("it");
        final CheckBoxPreference science = (CheckBoxPreference) findPreference("science");
        final CheckBoxPreference eng = (CheckBoxPreference) findPreference("eng");
        final CheckBoxPreference business = (CheckBoxPreference) findPreference("business");
        final CheckBoxPreference farm = (CheckBoxPreference) findPreference("farm");
        final CheckBoxPreference salt = (CheckBoxPreference) findPreference("salt");
        final CheckBoxPreference acdemy = (CheckBoxPreference) findPreference("acdemy");
        final CheckBoxPreference dean = (CheckBoxPreference) findPreference("dean");
        dean.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if (dean.isChecked()) {
                    ParsePush.subscribeInBackground("dean");
                    Toast.makeText(getBaseContext(), "Subscirbed!", Toast.LENGTH_SHORT).show();
                } else {
                    ParsePush.unsubscribeInBackground("dean");
                    Toast.makeText(getBaseContext(), "UNSubscirbed!", Toast.LENGTH_SHORT).show();


                }
                return true;
            }
        });
        acdemy.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if (acdemy.isChecked()) {
                    ParsePush.subscribeInBackground("acdemy");
                    Toast.makeText(getBaseContext(), "Subscirbed!", Toast.LENGTH_SHORT).show();
                } else {
                    ParsePush.unsubscribeInBackground("acdemy");
                    Toast.makeText(getBaseContext(), "UNSubscirbed!", Toast.LENGTH_SHORT).show();


                }
                return true;
            }
        });


        salt.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if (salt.isChecked()) {
                    ParsePush.subscribeInBackground("salt");
                    Toast.makeText(getBaseContext(), "Subscirbed!", Toast.LENGTH_SHORT).show();
                } else {
                    ParsePush.unsubscribeInBackground("salt");
                    Toast.makeText(getBaseContext(), "UNSubscirbed!", Toast.LENGTH_SHORT).show();


                }
                return true;
            }
        });

        farm.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if (farm.isChecked()) {
                    ParsePush.subscribeInBackground("farm");
                    Toast.makeText(getBaseContext(), "Subscirbed!", Toast.LENGTH_SHORT).show();
                } else {
                    ParsePush.unsubscribeInBackground("farm");
                    Toast.makeText(getBaseContext(), "UNSubscirbed!", Toast.LENGTH_SHORT).show();


                }
                return true;
            }
        });

        business.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if (business.isChecked()) {
                    ParsePush.subscribeInBackground("business");
                    Toast.makeText(getBaseContext(), "Subscirbed!", Toast.LENGTH_SHORT).show();
                } else {
                    ParsePush.unsubscribeInBackground("business");
                    Toast.makeText(getBaseContext(), "UNSubscirbed!", Toast.LENGTH_SHORT).show();


                }
                return true;
            }
        });

        eng.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if (eng.isChecked()) {
                    ParsePush.subscribeInBackground("eng");
                    Toast.makeText(getBaseContext(), "Subscirbed!", Toast.LENGTH_SHORT).show();
                } else {
                    ParsePush.unsubscribeInBackground("eng");
                    Toast.makeText(getBaseContext(), "UNSubscirbed!", Toast.LENGTH_SHORT).show();


                }
                return true;
            }
        });


        it.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if (it.isChecked()) {
                    ParsePush.subscribeInBackground("IT");
                    Toast.makeText(getBaseContext(), "Subscirbed!", Toast.LENGTH_SHORT).show();
                } else {
                    ParsePush.unsubscribeInBackground("IT");
                    Toast.makeText(getBaseContext(), "UNSubscirbed!", Toast.LENGTH_SHORT).show();


                }
                return true;
            }
        });

        science.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if (science.isChecked()) {
                    ParsePush.subscribeInBackground("science");
                    Toast.makeText(getBaseContext(), "Subscirbed!", Toast.LENGTH_SHORT).show();
                } else {
                    ParsePush.unsubscribeInBackground("science");
                    Toast.makeText(getBaseContext(), "UNSubscirbed!", Toast.LENGTH_SHORT).show();


                }
                return true;

            }
        });

    }

}
