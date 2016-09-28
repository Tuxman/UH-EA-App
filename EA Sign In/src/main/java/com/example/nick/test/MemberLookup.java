package com.example.nick.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MemberLookup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_lookup);
    }

    public void submitButton(View view){
        Intent intent = new Intent(this, SignedIn.class);
        startActivity(intent);
    }
}

//TODO Make name field searchable in our database

//TODO Create a database