package com.pab.intent_example;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity  extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivity_dua);

        Intent intent = getIntent();
        if(intent != null) {
            String receivedData = intent.getStringExtra("isiData");
            // Gunakan data yang diterima di sini

        }
    }
}
