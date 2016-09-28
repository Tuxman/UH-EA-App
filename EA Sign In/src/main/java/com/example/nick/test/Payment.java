package com.example.nick.test;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.squareup.sdk.register.ChargeRequest;
import com.squareup.sdk.register.CurrencyCode;
import com.squareup.sdk.register.RegisterClient;
import com.squareup.sdk.register.RegisterSdk;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Payment extends AppCompatActivity {

    private RegisterClient registerclient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTransaction();
            }
        });

        registerclient = RegisterSdk.createClient(this, "sq0idp-r2B8oyjUGHjnZLBFDPdmtg");
    }

    public void startTransaction() {
        int amount = 100;
        Set<ChargeRequest.TenderType> tenderTypes = new LinkedHashSet<>();
        tenderTypes.add(ChargeRequest.TenderType.CARD);
        int timeout = 3500;

        ChargeRequest request = new ChargeRequest.Builder(amount, CurrencyCode.USD)
                .autoReturn(timeout, TimeUnit.MILLISECONDS)
                .restrictTendersTo(tenderTypes)
                .build();

        try {
            Intent chargeIntent = registerclient.createChargeIntent(request);
            startActivityForResult(chargeIntent, 80085);
        }catch (ActivityNotFoundException e) {
            registerclient.openRegisterPlayStoreListing();
        }
    }





    public void cashResponse(View view) {
        Intent intent = new Intent(this, CashPaid.class);
        startActivity(intent);
    }

    public void creditResponse(View view) {
        //TODO add in Square code
    }

}
