package com.androcompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.newadscompanion.BaseUtils.BaseClass;
import com.newadscompanion.Interfaces.OnNetworkChangeListner;

import java.util.concurrent.Callable;

public class SecondActivity extends BaseClass implements OnNetworkChangeListner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

    }

    public void changeActivity(View view) {
//        startActivity(new Intent(this,MainActivity.class));
//        showMixedInterAds(new Callable<Void>() {
//            @Override
//            public Void call() throws Exception {
//                return null;
//            }
//        });
    }

    @Override
    public void onInternetConnected() {

    }

    @Override
    public void onInternetDisconnected() {

    }

    @Override
    public void onAdDataDownloaded() {

    }
}