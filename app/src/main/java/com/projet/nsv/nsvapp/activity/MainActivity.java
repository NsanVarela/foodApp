package com.projet.nsv.nsvapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.projet.nsv.nsvapp.R;

public class MainActivity extends AppCompatActivity {


    public static final String EXTRA_MESSAGE = "com.example.NSVApp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
    }
}
