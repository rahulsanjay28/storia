package com.asu.storia.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.asu.storia.R;

public class StoriaMainActivity extends AppCompatActivity {

    private static final String TAG = StoriaMainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storia_main);

//        new SignUpRequest().signup("temp@gmail.com", "temp235711", "Temp", new Response() {
//            @Override
//            public void onResponse(String response) {
//                Log.i(TAG, "onResponse " + response);
//            }
//        });

    }

}
