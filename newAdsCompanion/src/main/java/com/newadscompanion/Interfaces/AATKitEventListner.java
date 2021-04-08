package com.newadscompanion.Interfaces;

import com.intentsoftware.addapptr.ad.VASTAdData;

public interface AATKitEventListner {

    void onNoAd(int placementId);

    void onHaveAd(int placementId);

    void onUserEarnedIncentive(int placementId);

    public void onResumeAfterAd(int placementId);

    void onHaveVASTAd(int placementId, VASTAdData data);
}
