package com.asu.storia.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.asu.storia.utils.StoriaSharedPreferenceUtils;

public class StoriaBaseActivity extends AppCompatActivity {

    private static final String TAG = StoriaBaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String userId = StoriaSharedPreferenceUtils.getInstance().getUserId(this);
        if(!TextUtils.isEmpty(userId)) {
            Intent intent = new Intent(this, StoriaHomeActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this, StoriaSignUpActivity.class);
            startActivity(intent);
        }
    }
}
