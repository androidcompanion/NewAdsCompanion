package com.androcompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.newadscompanion.BaseUtils.BaseClass;

public class SecondActivity extends BaseClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

    }

    public void changeActivity(View view) {
//        startActivity(new Intent(this,MainActivity.class));
        showMixedInterAds();
    }
}