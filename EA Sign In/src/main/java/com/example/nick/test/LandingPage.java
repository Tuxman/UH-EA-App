package com.example.nick.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;


public class LandingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity);
    }

    public void yesResponse(View view) {                        //They want to be a member
        Intent intent = new Intent(this, MemberLookup.class);
        startActivity(intent);
    }

    public void noResponse(View view) {                         //They
        Intent intent = new Intent(this, AskMember.class);
        startActivity(intent);
    }
}
