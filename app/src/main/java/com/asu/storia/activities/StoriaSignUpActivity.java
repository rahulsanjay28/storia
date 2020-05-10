package com.asu.storia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.asu.storia.R;

public class StoriaSignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storia_sign_up);

        TextView signInLink = findViewById(R.id.link_login);
        signInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(StoriaSignUpActivity.this, StoriaSignInActivity.class);
                startActivity(intent);
            }
        });
    }
}
