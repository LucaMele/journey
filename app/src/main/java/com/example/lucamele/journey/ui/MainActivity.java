package com.example.lucamele.journey.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lucamele.journey.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            EntryScreenFragment fragment = new EntryScreenFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, EntryScreenFragment.TAG).commit();
        }
    }
}
