package com.example.nick.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CashPaid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_paid);
    }

    public void continueResponse(View view) {
        Intent intent = new Intent(this, SignedIn.class);
        startActivity(intent);
    }
}
