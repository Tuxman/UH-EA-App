package com.example.nick.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class AskMember extends AppCompatActivity {






    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_member);
    }

    public void yesResponse(View view) {                        //If they want to be a member
        Intent intent = new Intent(this, Information.class);
        intent.putExtra("memberChoice", true);
        startActivity(intent);
    }

    public void noResponse(View view) {
        Intent intent = new Intent(this, Information.class);
        intent.putExtra("memberChoice", false);
        startActivity(intent);
    }
}
