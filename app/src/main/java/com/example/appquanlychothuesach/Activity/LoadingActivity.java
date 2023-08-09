package com.example.appquanlychothuesach.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appquanlychothuesach.Activity.LoginActivity;
import com.example.appquanlychothuesach.R;

public class LoadingActivity extends AppCompatActivity {

    private TextView appname;
    private ImageView logoapp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        logoapp1 = (ImageView) findViewById(R.id.logoapp_1);
        appname = (TextView) findViewById(R.id.appname);
        logoapp1.animate().translationY(-2400).setDuration(2700).setStartDelay(500);
        logoapp1.animate().translationX(2000).setDuration(2000).setStartDelay(1000);
        appname.animate().translationY(-2400).setDuration(2700).setStartDelay(500);
        appname.animate().translationX(2000).setDuration(2000).setStartDelay(1000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        },2000);
    }
}