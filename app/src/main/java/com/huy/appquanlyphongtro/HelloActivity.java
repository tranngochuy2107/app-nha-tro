package com.huy.appquanlyphongtro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class HelloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                //do something
                startActivity(new Intent(HelloActivity.this, Login.class));
            }
        }, 3000 );//time in milisecond
    }
}