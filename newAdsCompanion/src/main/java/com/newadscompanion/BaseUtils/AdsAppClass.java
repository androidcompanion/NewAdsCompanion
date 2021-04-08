package com.newadscompanion.BaseUtils;

import android.app.Application;

import com.intentsoftware.addapptr.AATKit;
import com.intentsoftware.addapptr.AATKitConfiguration;
import com.intentsoftware.addapptr.BannerPlacementLayout;
import com.intentsoftware.addapptr.ad.VASTAdData;
import com.newadscompanion.Interfaces.AATKitEventListner;
import com.newadscompanion.ModelsCompanion.AdsPrefernce;

public class AdsAppClass extends Application implements AATKit.Delegate{
    private AATKitEventListner listener;

    @Override
    public void onCreate() {
        super.onCreate();
        AATKitConfiguration configuration = new AATKitConfiguration(this);
        configuration.setUseDebugShake(false);
//        configuration.setDelegate(this);
        AATKit.init(configuration);
        AdsPrefernce adsPrefernce = new AdsPrefernce(this);
        if (adsPrefernce.extraPara4().equals("TEST")){
            AATKit.enableTestMode(2525);
        }

    }

    public void setListener(AATKitEventListner listener) {
        this.listener = listener;
    }

    public void removeListener() {
        listener = null;
    }

    @Override
    public void aatkitHaveAd(int placementId) {

    }

    @Override
    public void aatkitNoAd(int placementId) {

    }

    @Override
    public void aatkitPauseForAd(int placementId) {

    }

    @Override
    public void aatkitResumeAfterAd(int placementId) {
        if (listener != null){
            listener.onResumeAfterAd(placementId);
        }
    }

    @Override
    public void aatkitShowingEmpty(int placementId) {

    }

    @Override
    public void aatkitUserEarnedIncentive(int placementId) {

    }

    @Override
    public void aatkitObtainedAdRules(boolean fromTheServer) {

    }

    @Override
    public void aatkitUnknownBundleId() {

    }

    @Override
    public void aatkitHaveAdForPlacementWithBannerView(int placementId, BannerPlacementLayout bannerView) {

    }

    @Override
    public void aatkitHaveVASTAd(int placementId, VASTAdData data) {

    }
}
