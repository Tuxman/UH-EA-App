package com.example.nick.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SignedIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_in);
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, LandingPage.class);
        startActivity(intent);
    }
}
