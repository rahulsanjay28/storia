package com.asu.storia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.asu.storia.R;
import com.asu.storia.network.GetLatestStoriesRequest;
import com.asu.storia.service.UpdateLockScreenStorySerivce;

public class StoriaMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storia_main);

        Intent i = new Intent(this, UpdateLockScreenStorySerivce.class);
        startForegroundService(i);
    }
}
