package com.androcompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.newadscompanion.AdsConfig.DefaultIds;
import com.newadscompanion.BaseUtils.BaseClass;
import com.newadscompanion.Interfaces.OnPlayVerificationFailed;

import java.util.concurrent.Callable;

public class MainActivity extends BaseClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DefaultIds defaultIds = new DefaultIds(this);

        defaultIds.setDefaults("TEST","1","1","1","0","0","ca-app-pub-3940256099942544~3347511713","ca-app-pub-3940256099942544/6300978111",
                "ca-app-pub-3940256099942544/1033173712","ca-app-pub-3940256099942544/1033173712","ca-app-pub-3940256099942544/2247696110",
                "ca-app-pub-3940256099942544/2247696110","ca-app-pub-3940256099942544/5224354917","1018728721882370_1018729048549004","1018728721882370_1018730038548905",
                "1018728721882370_1018730038548905","YOUR_PLACEMENT_ID","YOUR_PLACEMENT_ID","YOUR_PLACEMENT_ID","YOUR_PLACEMENT_ID",
                "d8943712-2cb6-43fc-bc57-e7d4d8387e9f",
                "b195f8dd8ded45fe847ad89ed1d016da","24534e1901884e398f1253216226017e","24534e1901884e398f1253216226017e","YOUR_PLACEMENT_ID","YOUR_PLACEMENT_ID",
                "920b6145fb1546cf8b5cf2ac34638bb7","na","na","na","na","na",
                "1","1","1","1","0000000","2",true,
                false,true,true ,true,true,getResources().getColor(R.color._tint));

        proceedWithDelay(4000, "loading", new Callable<Void>() {
            @Override
            public Void call() throws Exception {
//                loadInterstitial1();
                showBannerAd(7,0);
                return null;
            }
        });
    }


//    @Override
//    public void onVerificationFailed() {
//        toast("GoingWell");
//    }

    public void showInter(View view) {
        showMixedInterAds();
//        showInterstitial1(true, new Callable<Void>() {
//            @Override
//            public Void call() throws Exception {
//                return null;
//            }
//        });
    }

    public void showInter2(View view) {
        showInterstitial2(true, new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                return null;
            }
        });
    }

    public void showInter3(View view) {
        startActivity(new Intent(this,SecondActivity.class));
//        showMixedInterAds();

    }
}