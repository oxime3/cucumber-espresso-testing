package com.bignerdranch.android.bqtabs;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import androidx.core.app.NavUtils;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.bignerdranch.android.bqtabs.database.BurgerQueenDBHelper;

import java.util.List;

public class Settings extends PreferenceActivity {
    static UserBase userbase;
    //int id = 1;
    static User u1 ;
    BurgerQueenDBHelper BurgerQueenDB;



    private static Preference.OnPreferenceChangeListener sUserUpdateListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {

            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            int id = pref.getInt( "ID", 0);
            u1 = userbase.getUserData(id);
            String stringValue = newValue.toString();
            String key = preference.getKey();


            if (preference instanceof ListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                // Set the summary to reflect the new value.
                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);
                String rest = (String) listPreference.getEntries()[index];




                userbase.updateUser(u1.getId(), u1.getUsername(), u1.getPassword(), u1.getEmail(),rest);
                u1 = userbase.getUserData(u1.getId());






            }else{
                if (key.equalsIgnoreCase("user")) {

                    preference.setSummary(stringValue);
                    userbase.updateUser(u1.getId(), stringValue, u1.getPassword(), u1.getEmail(), u1.getHome_restaurant());
                    u1 = userbase.getUserData(u1.getId());
                    Log.i("user", u1.getUsername());
                }else if (key.equalsIgnoreCase("email")) {
                    userbase.updateUser(u1.getId(), u1.getUsername(), u1.getPassword(), stringValue, u1.getHome_restaurant());
                    preference.setSummary(stringValue);
                    u1 = userbase.getUserData(u1.getId());
                    Log.i("email", u1.getEmail());
                }else{
                    userbase.updateUser(u1.getId(), u1.getUsername(), stringValue, u1.getEmail(), u1.getHome_restaurant());
                    Log.i("pswd", u1.getPassword());
                    u1 = userbase.getUserData(u1.getId());

                }

            }


            //mSettings.getString(preference.getKey(),"");





            return true;
        }
    };

    private static void userUpdate(Preference preference) {
        preference.setOnPreferenceChangeListener(sUserUpdateListener);
        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
        sUserUpdateListener.onPreferenceChange(preference, mSettings.getString(preference.getKey(),""));

    }

   /* private static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }*/






    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getLayoutInflater().inflate(R.layout.toolbar, (ViewGroup)findViewById(android.R.id.content));
        //Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        //setActionBar();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

//        (toolbar);




        /*getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new AccountPreference())
                .commit();*/
        //int id = 1;
        //BurgerQueenDBHelper BurgerQueenDB;
        BurgerQueenDB = new BurgerQueenDBHelper(this);
        /*userbase = UserBase.get(this);
        int id = mSettings.getInt( "ID", 0);


        u1 = userbase.getUserData(id);
        Log.i("Just", u1.getUsername());

        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString("default_name", u1.getUsername() );
        editor.putString("default_password", u1.getPassword() );
        editor.putString("default_email", u1.getEmail());
        editor.apply();
*/



        //editor.apply();

    }



    @Override
    public boolean onMenuItemSelected(int featureId, android.view.MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            if (!super.onMenuItemSelected(featureId, item)) {
                NavUtils.navigateUpFromSameTask(this);
            }
            return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }

   /* @Override
    public boolean onIsMultiPane() {
        return isXLargeTablet(this);
    }*/



    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_headers, target);
    }


    protected boolean isValidFragment(String fragmentName) {
        return PreferenceFragment.class.getName().equals(fragmentName)
                || GeneralPreference.class.getName().equals(fragmentName)
                || AccountPreference.class.getName().equals(fragmentName);
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class GeneralPreference extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_general);
            setHasOptionsMenu(true);

        }

        @Override
        public boolean onOptionsItemSelected(android.view.MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), Settings.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class AccountPreference extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_account);
            setHasOptionsMenu(true);

            SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(getActivity());
            int id = mSettings.getInt( "ID", 0);
            userbase = UserBase.get(getActivity());
            u1 = userbase.getUserData(id);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.

            EditTextPreference idpreference = (EditTextPreference) findPreference("id");
            ListPreference rest= (ListPreference)findPreference("home_list");
            EditTextPreference user = (EditTextPreference)findPreference("user");
            EditTextPreference passwd = (EditTextPreference)findPreference("pswd");
            EditTextPreference email = (EditTextPreference)findPreference("email");

            idpreference.setText(String.valueOf(id));

            user.setText( u1.getUsername() );
            //user.setSummary( u1.getUsername());
            passwd.setText( u1.getPassword() );
            email.setText( u1.getEmail() );

            int index = mSettings.getInt("Index", 0);
            rest.setValueIndex(index);






            /*user.setText(u1.getUsername());
            pswd.setText(u1.getPassword());
            email.setText(u1.getEmail());*/

            PreferenceScreen screen = getPreferenceScreen();
            screen.removePreference(idpreference);

            /*SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString("username",u1.getUsername());
            editor.putString("pswd",u1.getPassword());
            editor.putString("email",u1.getEmail());*/

            //int index = rest.findIndexOfValue(u1.getHome_restaurant());
            //rest.setValueIndex(index);
            //editor.putString("home_list", u1.getHome_restaurant());
            //editor.apply();






            userUpdate(findPreference("user"));
            userUpdate(findPreference("pswd"));
            userUpdate(findPreference("email"));
            userUpdate(findPreference("home_list"));






        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), Settings.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }





    }




}
