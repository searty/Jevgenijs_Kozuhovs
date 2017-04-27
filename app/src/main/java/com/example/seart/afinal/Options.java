package com.example.seart.afinal;


import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Options extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        addPreferencesFromResource(R.xml.options);
    }

}
