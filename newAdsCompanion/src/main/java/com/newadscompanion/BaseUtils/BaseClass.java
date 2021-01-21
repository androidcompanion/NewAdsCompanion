package com.newadscompanion.BaseUtils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.appnext.ads.fullscreen.RewardedVideo;
import com.appnext.ads.interstitial.Interstitial;
import com.appnext.banners.BannerAdRequest;
import com.appnext.banners.BannerSize;
import com.appnext.banners.BannerView;
import com.appnext.base.Appnext;
import com.appnext.core.AppnextAdCreativeType;
import com.appnext.core.AppnextError;
import com.appnext.core.callbacks.OnAdClosed;
import com.appnext.core.callbacks.OnAdError;
import com.appnext.core.callbacks.OnAdLoaded;
import com.appnext.core.callbacks.OnAdOpened;
import com.appnext.core.callbacks.OnVideoEnded;
import com.bumptech.glide.Glide;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mopub.common.MoPub;
import com.mopub.common.MoPubReward;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.common.privacy.PersonalInfoManager;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubRewardedVideoListener;
import com.mopub.mobileads.MoPubRewardedVideos;
import com.mopub.mobileads.MoPubView;
import com.newadscompanion.AdsConfig.DefaultIds;
import com.newadscompanion.BroadcastUtils.NetworkStateReceiver;
import com.newadscompanion.Interfaces.InhouseBannerListener;
import com.newadscompanion.Interfaces.InhouseInterstitialListener;
import com.newadscompanion.Interfaces.InhouseNativeListener;
import com.newadscompanion.Interfaces.OnCheckServiceListner;
import com.newadscompanion.Interfaces.OnNetworkChangeListner;
import com.newadscompanion.Interfaces.OnPlayVerificationFailed;
import com.newadscompanion.Interfaces.OnRewardAdClosedListener;
import com.newadscompanion.ModelsCompanion.AdsData;
import com.newadscompanion.ModelsCompanion.AdsIdsList;
import com.newadscompanion.ModelsCompanion.AdsPrefernce;
import com.newadscompanion.ModelsCompanion.BannerAdIH;
import com.newadscompanion.ModelsCompanion.BannerDetail;
import com.newadscompanion.ModelsCompanion.GsonUtils;
import com.newadscompanion.ModelsCompanion.InterDetail;
import com.newadscompanion.ModelsCompanion.InterstitialAdIH;
import com.newadscompanion.ModelsCompanion.NativeAdIH;
import com.newadscompanion.ModelsCompanion.NativeDetail;
import com.newadscompanion.R;
import com.startapp.sdk.ads.banner.Banner;
import com.startapp.sdk.ads.banner.BannerListener;
import com.startapp.sdk.adsbase.AutoInterstitialPreferences;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;

import cz.msebera.android.httpclient.Header;

public class BaseClass extends AppCompatActivity implements NetworkStateReceiver.NetworkStateReceiverListener {

    //inHouse
    public static boolean isInterAdLoadedIH = false;
    public static boolean isBannerAdLoadedIH = false;
    public static boolean isNativeAdLoadedIH = false;
    public static int currentInter = 0;
    public static int currentBanner = 0;
    public static int currentNative = 0;
    public static boolean isFirstIHInter = true;
    public static boolean isFirstIHBanner = true;
    public static boolean isFirstIHNative = true;
    static ArrayList<InterDetail> interDetails;
    static ArrayList<InterDetail> finalInter;
    ArrayList<BannerDetail> bannerDetails;
    static ArrayList<BannerDetail> finalBanner;
    ArrayList<NativeDetail> nativeDetails;
    static ArrayList<NativeDetail> finalNative;

    ///////////////


    public RelativeLayout lay_notification;
    public TextView tv_not_text;
    public ImageView iv_not_close;

    public static boolean checkAppService = true;
    public Dialog serviceDialog;

    private long exitTime = 0;

    public DefaultIds defaultIds;

    public static boolean isAdsAvailable = false;
    public AdsPrefernce adsPrefernce;
    ArrayList<AdsIdsList> adsList;
    public GsonUtils gsonUtils;

    public InterstitialAd fbSplashInter;
    public com.google.android.gms.ads.InterstitialAd gSplashInter;
    public static InterstitialAd fbInterstitial1;
    public InterstitialAd fbInterstitial11;
    public static com.google.android.gms.ads.InterstitialAd gInterstitial1;
    public com.google.android.gms.ads.InterstitialAd gInterstitial11;
    public static InterstitialAd fbInterstitial2;
    public InterstitialAd fbInterstitial22;
    public static com.google.android.gms.ads.InterstitialAd gInterstitial2;
    public com.google.android.gms.ads.InterstitialAd gInterstitial22;
    public com.google.android.gms.ads.InterstitialAd gInterstitial3;
    com.facebook.ads.InterstitialAd fbDialogInterstitial;
    com.google.android.gms.ads.InterstitialAd gDialogInterstitial;

    // an inter
    public static Interstitial an_interstitial_Ad1;
    public Interstitial an_interstitial_Ad11;
//    public static Interstitial an_interstitial_Ad2;

    //mp inter
    public static MoPubInterstitial mpInterstitial1;
    public MoPubInterstitial mpInterstitial11;
    private static MoPubInterstitial mpInterstitial2;
    private MoPubInterstitial mpInterstitial22;
    public static boolean mpInter1Initilized = false;
    private static boolean mpInter2Initilized = false;

    //mp banner
    private static boolean mpBannerInitilized = false;


    public ProgressDialog progressDialog;
    OnPlayVerificationFailed onPlayVerificationFailed;

    public static boolean isGInter1Ready = false;
    public static boolean isFbInter1Ready = false;
    public static boolean isAnInter1Ready = false;
    public static boolean isMpInter1Ready = false;
    public static boolean isUInter1Ready = false;
    public static boolean isGInter2Ready = false;
    public static boolean isFbInter2Ready = false;
    //    public static boolean isAnInter2Ready = false;
    public static boolean isMpInter2Ready = false;
    public static boolean isUInter2Ready = false;
    public static boolean isGInter1Shown = false;
    public static boolean isFbInter1Shown = false;
    public static boolean isAnInter1Shown = false;
    public static boolean isMpInter1Shown = false;
    public static boolean isUInter1Shown = false;
    public static boolean isGInter2Shown = false;
    public static boolean isFbInter2Shown = false;
    public static boolean isAnInter2Shown = false;
    public static boolean isMpInter2Shown = false;
    public static boolean isUInter2Shown = false;

    public static boolean isGN1Shown = false;
    public static boolean isFbN1Shown = false;
    public static boolean isAnN1Shown = false;
    public static boolean isMpN1Shown = false;
    public static boolean isGN2Shown = false;
    public static boolean isFbN2Shown = false;
    public static boolean isAnN2Shown = false;
    public static boolean isMpN2Shown = false;

    public static boolean isvalidInstall = false;

    private NetworkStateReceiver networkStateReceiver;

    OnNetworkChangeListner onNetworkChangeListner;


    //Rewarded Ads
    public static RewardedAd gRewardedAd;
    public static RewardedAdLoadCallback adLoadCallback;
    public static RewardedVideoAd fbRewardedVideoAd;
    public static RewardedVideoAdListener rewardedVideoAdListener;
    public static RewardedVideo anRewardedAd;
    private MoPubRewardedVideoListener mpRewardedVideoListener;

    public static boolean isGRewardedShown = false;
    public static boolean isFbRewardedShown = false;
    public static boolean isAnRewardedShown = false;
    public static boolean isMpRewardedShown = false;

    public static boolean isGRewardedReady = false;
    public static boolean isFbRewardedReady = false;
    public static boolean isAnRewardedReady = false;
    public static boolean isMpRewardedReady = false;

    public static boolean isGUserRewarded = false;
    public static boolean isfbUserRewarded = false;
    public static boolean isAnUserRewarded = false;
    public static boolean isMpUserRewarded = false;


    public void loadRewardAd() {
        adsPrefernce = new AdsPrefernce(this);
        if (isNetworkAvailable(this)) {
            if (isAdsAvailable) {
                if (adsPrefernce.showgRewarded()) {
                    if (!isGRewardedReady) {
                        MobileAds.initialize(getApplicationContext(), adsPrefernce.gAppId());
                        gRewardedAd = new RewardedAd(this,
                                adsPrefernce.gRewardedId());
                        adLoadCallback = new RewardedAdLoadCallback() {
                            @Override
                            public void onRewardedAdLoaded() {
                                isGRewardedReady = true;
                                Log.e("RewardAds...", "google RewardAd ready");
                            }

                            @Override
                            public void onRewardedAdFailedToLoad(LoadAdError adError) {
                                isGRewardedReady = false;
                            }
                        };
                        gRewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
                    }
                }
                if (adsPrefernce.showfbRewarded()) {
                    if (!isFbRewardedReady) {
                        AudienceNetworkAds.initialize(this);
                        fbRewardedVideoAd = new RewardedVideoAd(this, adsPrefernce.fbRewardedId());
                        rewardedVideoAdListener = new RewardedVideoAdListener() {
                            @Override
                            public void onError(Ad ad, AdError error) {
                                // Rewarded video ad failed to load
                                Log.e("RewardAds...", "Rewarded video ad failed to load: " + error.getErrorMessage());
                                isFbRewardedReady = false;
                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                // Rewarded video ad is loaded and ready to be displayed
                                Log.d("RewardAds...", "Rewarded video ad is loaded and ready to be displayed!");
                                isFbRewardedReady = true;
                            }

                            @Override
                            public void onAdClicked(Ad ad) {
                                // Rewarded video ad clicked
                                Log.d("RewardAds...", "Rewarded video ad clicked!");
                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {
                                // Rewarded Video ad impression - the event will fire when the
                                // video starts playing
                                Log.d("RewardAds...", "Rewarded video ad impression logged!");
                            }

                            @Override
                            public void onRewardedVideoCompleted() {
                                // Rewarded Video View Complete - the video has been played to the end.
                                // You can use this event to initialize your reward
                                Log.d("RewardAds...", "Rewarded video completed!");

                                // Call method to give reward
                                // giveReward();
                            }

                            @Override
                            public void onRewardedVideoClosed() {
                                // The Rewarded Video ad was closed - this can occur during the video
                                // by closing the app, or closing the end card.
                                Log.d("RewardAds...", "Rewarded video ad closed!");
                            }
                        };
                        fbRewardedVideoAd.loadAd(
                                fbRewardedVideoAd.buildLoadAdConfig()
                                        .withAdListener(rewardedVideoAdListener)
                                        .build());
                    }
                }
                if (adsPrefernce.showanRewarded()) {
                    if (!isAnRewardedReady) {
                        anRewardedAd = new RewardedVideo(this, adsPrefernce.anAdId());
                        anRewardedAd.loadAd();
                        anRewardedAd.setOnAdLoadedCallback(new OnAdLoaded() {
                            @Override
                            public void adLoaded(String s, AppnextAdCreativeType appnextAdCreativeType) {
                                isAnRewardedReady = true;
                            }
                        });
                        anRewardedAd.setOnAdErrorCallback(new OnAdError() {
                            @Override
                            public void adError(String s) {
                                isAnRewardedReady = false;
                            }
                        });


                    }

                }
                if (adsPrefernce.showmpRewarded()) {
                    MoPub.onCreate(this);
                    if (!isMpRewardedReady) {
                        MoPubRewardedVideos.loadRewardedVideo(adsPrefernce.mpRewardedId());
                        mpRewardedVideoListener = new MoPubRewardedVideoListener() {
                            @Override
                            public void onRewardedVideoLoadSuccess(String adUnitId) {
                                isMpRewardedReady = true;
                            }

                            @Override
                            public void onRewardedVideoLoadFailure(String adUnitId, MoPubErrorCode errorCode) {
                                isMpRewardedReady = false;
                            }

                            @Override
                            public void onRewardedVideoStarted(String adUnitId) {
                            }

                            @Override
                            public void onRewardedVideoPlaybackError(String adUnitId, MoPubErrorCode errorCode) {
                            }

                            @Override
                            public void onRewardedVideoClicked(@NonNull String adUnitId) {
                            }

                            @Override
                            public void onRewardedVideoClosed(String adUnitId) {
                            }

                            @Override
                            public void onRewardedVideoCompleted(Set<String> adUnitIds, MoPubReward reward) {
                            }
                        };

                        MoPubRewardedVideos.setRewardedVideoListener(mpRewardedVideoListener);
                    }
                }
            }
        }

    }

    public void showRewardAds(OnRewardAdClosedListener onRewardAdClosedListener) {
        if (isNetworkAvailable(this)) {
            if (isAdsAvailable) {
                if (adsPrefernce.showgRewarded()) {
                    if (!isGRewardedShown) {
                        if (isGRewardedReady) {
                            if (gRewardedAd.isLoaded()) {
                                RewardedAdCallback adCallback = new RewardedAdCallback() {
                                    @Override
                                    public void onRewardedAdOpened() {
                                        // Ad opened.
                                    }

                                    @Override
                                    public void onRewardedAdClosed() {
                                        // Ad closed.
                                        if (isGUserRewarded) {
                                            try {
                                                onRewardAdClosedListener.onRewardSuccess();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            isGRewardedReady = false;
                                            isGRewardedShown = true;
                                            isGUserRewarded = false;
                                            gRewardedAd = new RewardedAd(BaseClass.this,
                                                    adsPrefernce.gRewardedId());
                                            gRewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
                                        } else {
                                            try {
                                                onRewardAdClosedListener.onRewardFailed();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            isGRewardedReady = false;
                                            isGRewardedShown = true;
                                            isGUserRewarded = false;
                                            gRewardedAd = new RewardedAd(BaseClass.this,
                                                    adsPrefernce.gRewardedId());
                                            gRewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
                                        }
                                    }

                                    @Override
                                    public void onUserEarnedReward(@NonNull RewardItem reward) {
                                        // User earned reward.
                                        isGUserRewarded = true;
                                        Log.d("RewardAds...", "onUserEarnedReward Google");

                                    }

                                    @Override
                                    public void onRewardedAdFailedToShow(com.google.android.gms.ads.AdError adError) {
                                        Log.d("RewardAds...", "onRewardedAdFailedToShow Google");
                                        // Ad failed to display.
                                        isGRewardedReady = false;
                                        isGRewardedShown = true;
                                        isGUserRewarded = false;
                                        gRewardedAd = new RewardedAd(BaseClass.this,
                                                adsPrefernce.gRewardedId());
                                        gRewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
                                        try {
                                            onRewardAdClosedListener.onRewardAdNotShown();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                gRewardedAd.show(this, adCallback);
                            }
                        } else {
                            goToPlanA2Rewarded(onRewardAdClosedListener);
                        }
                    } else {
                        goToPlanA2Rewarded(onRewardAdClosedListener);
                    }
                } else {
                    goToPlanA2Rewarded(onRewardAdClosedListener);
                }
            }
        }

    }

    public void goToPlanA2Rewarded(OnRewardAdClosedListener onRewardAdClosedListener) {
        if (adsPrefernce.showfbRewarded()) {
            if (!isFbRewardedShown) {
                if (isFbRewardedReady) {
                    if (fbRewardedVideoAd == null || !fbRewardedVideoAd.isAdLoaded()) {
                        goToPlanBRewarded(onRewardAdClosedListener);
                        return;
                    }
                    // Check if ad is already expired or invalidated, and do not show ad if that is the case. You will not get paid to show an invalidated ad.
                    if (fbRewardedVideoAd.isAdInvalidated()) {
                        goToPlanBRewarded(onRewardAdClosedListener);
                        return;
                    }
                    fbRewardedVideoAd.show();
                    RewardedVideoAdListener rewardedVideoAdListenerShow = new RewardedVideoAdListener() {
                        @Override
                        public void onError(Ad ad, AdError error) {
                            // Rewarded video ad failed to load
                            Log.e("RewardAds...", "Rewarded video ad failed to load: " + error.getErrorMessage());
                            isFbRewardedReady = false;
                            isFbBannerShown = true;
                            isfbUserRewarded = false;
                            fbRewardedVideoAd = new RewardedVideoAd(BaseClass.this, adsPrefernce.fbRewardedId());
                            fbRewardedVideoAd.loadAd(
                                    fbRewardedVideoAd.buildLoadAdConfig()
                                            .withAdListener(rewardedVideoAdListener)
                                            .build());
                            try {
                                onRewardAdClosedListener.onRewardAdNotShown();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                            // Rewarded video ad is loaded and ready to be displayed
                            Log.d("RewardAds...", "Rewarded video ad is loaded and ready to be displayed!");
                        }

                        @Override
                        public void onAdClicked(Ad ad) {
                            // Rewarded video ad clicked
                            Log.d("RewardAds...", "Rewarded video ad clicked!");
                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {
                            // Rewarded Video ad impression - the event will fire when the
                            // video starts playing
                            Log.d("RewardAds...", "Rewarded video ad impression logged!");
                        }

                        @Override
                        public void onRewardedVideoCompleted() {
                            // Rewarded Video View Complete - the video has been played to the end.
                            // You can use this event to initialize your reward
                            Log.d("RewardAds...", "Rewarded video completed!");
                            isfbUserRewarded = true;

                            // Call method to give reward
                            // giveReward();
                        }

                        @Override
                        public void onRewardedVideoClosed() {
                            // The Rewarded Video ad was closed - this can occur during the video
                            // by closing the app, or closing the end card.
                            Log.d("RewardAds...", "Rewarded video ad closed!");
                            if (isfbUserRewarded) {
                                try {
                                    onRewardAdClosedListener.onRewardSuccess();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                isFbRewardedReady = false;
                                isFbRewardedShown = true;
                                isfbUserRewarded = false;
                                fbRewardedVideoAd = new RewardedVideoAd(BaseClass.this, adsPrefernce.fbRewardedId());
                                fbRewardedVideoAd.loadAd(
                                        fbRewardedVideoAd.buildLoadAdConfig()
                                                .withAdListener(rewardedVideoAdListener)
                                                .build());
                            } else {
                                try {
                                    onRewardAdClosedListener.onRewardFailed();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                isFbRewardedReady = false;
                                isFbRewardedShown = true;
                                isfbUserRewarded = false;
                                fbRewardedVideoAd = new RewardedVideoAd(BaseClass.this, adsPrefernce.fbRewardedId());
                                fbRewardedVideoAd.loadAd(
                                        fbRewardedVideoAd.buildLoadAdConfig()
                                                .withAdListener(rewardedVideoAdListener)
                                                .build());
                            }

                        }
                    };
                    fbRewardedVideoAd.buildLoadAdConfig().withAdListener(rewardedVideoAdListener).build();
//                    fbRewardedVideoAd.setAdListener(rewardedVideoAdListenerShow);

                } else {
                    goToPlanBRewarded(onRewardAdClosedListener);
                }
            } else {
                goToPlanBRewarded(onRewardAdClosedListener);
            }
        } else {
            goToPlanBRewarded(onRewardAdClosedListener);
        }
    }

    public void goToPlanBRewarded(OnRewardAdClosedListener onRewardAdClosedListener) {

        if (adsPrefernce.showanRewarded()) {
            if (!isAnRewardedShown) {
                if (isAnRewardedReady) {
                    if (anRewardedAd.isAdLoaded()) {
                        anRewardedAd.showAd();
                        // Get callback for ad closed
                        anRewardedAd.setOnAdClosedCallback(new OnAdClosed() {
                            @Override
                            public void onAdClosed() {
                                if (isAnUserRewarded) {
                                    try {
                                        onRewardAdClosedListener.onRewardSuccess();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    isAnRewardedReady = false;
                                    isAnRewardedShown = true;
                                    isAnUserRewarded = false;
                                    loadRewardAd();
                                } else {
                                    try {
                                        onRewardAdClosedListener.onRewardFailed();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    isAnRewardedReady = false;
                                    isAnRewardedShown = true;
                                    isAnUserRewarded = false;
                                    loadRewardAd();
                                }
                            }
                        });

                        anRewardedAd.setOnAdErrorCallback(new OnAdError() {
                            @Override
                            public void adError(String error) {
                                isAnRewardedReady = false;
                                isAnRewardedShown = true;
                                isAnUserRewarded = false;
                                loadRewardAd();
                                try {
                                    onRewardAdClosedListener.onRewardAdNotShown();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        anRewardedAd.setOnVideoEndedCallback(new OnVideoEnded() {
                            @Override
                            public void videoEnded() {
                                isAnUserRewarded = true;
                            }
                        });
                    } else {
                        goToPlanCRewarded(onRewardAdClosedListener);
                    }
                } else {
                    goToPlanCRewarded(onRewardAdClosedListener);
                }
            } else {
                goToPlanCRewarded(onRewardAdClosedListener);
            }
        } else {
            goToPlanCRewarded(onRewardAdClosedListener);
        }

    }

    public void goToPlanCRewarded(OnRewardAdClosedListener onRewardAdClosedListener) {
        if (adsPrefernce.showmpRewarded()) {
            if (!isMpRewardedShown) {
                if (isMpRewardedReady) {
                    if (MoPubRewardedVideos.hasRewardedVideo(adsPrefernce.mpRewardedId())) {
                        MoPub.onCreate(this);
                        MoPubRewardedVideos.showRewardedVideo(adsPrefernce.mpRewardedId());

                        MoPub.onPause(this);
                        MoPubRewardedVideoListener rewardedVideoListener = new MoPubRewardedVideoListener() {
                            @Override
                            public void onRewardedVideoLoadSuccess(String adUnitId) {
                                // Called when the video for the given adUnitId has loaded. At this point you should be able to call MoPubRewardedVideos.showRewardedVideo(String) to show the video.
                            }

                            @Override
                            public void onRewardedVideoLoadFailure(String adUnitId, MoPubErrorCode errorCode) {
                                // Called when a video fails to load for the given adUnitId. The provided error code will provide more insight into the reason for the failure to load.
                            }

                            @Override
                            public void onRewardedVideoStarted(String adUnitId) {
                                // Called when a rewarded video starts playing.
                            }

                            @Override
                            public void onRewardedVideoPlaybackError(String adUnitId, MoPubErrorCode errorCode) {
                                isMpRewardedReady = false;
                                isMpRewardedShown = true;
                                isMpUserRewarded = false;
                                loadRewardAd();
                                resetAllRewardedShownBoolean(false, onRewardAdClosedListener);
                                try {
                                    onRewardAdClosedListener.onRewardAdNotShown();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onRewardedVideoClicked(@NonNull String adUnitId) {
                                //  Called when a rewarded video is clicked.
                            }

                            @Override
                            public void onRewardedVideoClosed(String adUnitId) {
                                if (isMpUserRewarded) {
                                    try {
                                        onRewardAdClosedListener.onRewardSuccess();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    isMpRewardedReady = false;
                                    isMpRewardedShown = true;
                                    isMpUserRewarded = false;
                                    loadRewardAd();
                                    resetAllRewardedShownBoolean(false, onRewardAdClosedListener);
                                } else {
                                    try {
                                        onRewardAdClosedListener.onRewardFailed();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    isMpRewardedReady = false;
                                    isMpRewardedShown = true;
                                    isMpUserRewarded = false;
                                    loadRewardAd();
                                    resetAllRewardedShownBoolean(false, onRewardAdClosedListener);
                                }

                                MoPub.onResume(BaseClass.this);
                            }

                            @Override
                            public void onRewardedVideoCompleted(Set<String> adUnitIds, MoPubReward reward) {
                                isMpUserRewarded = true;
                                Log.e("mpprewarded","isMpUserRewarded = true");
                            }
                        };
                        MoPubRewardedVideos.setRewardedVideoListener(rewardedVideoListener);
                    } else {
                        resetAllRewardedShownBoolean(true, onRewardAdClosedListener);
                    }
                } else {
                    resetAllRewardedShownBoolean(true, onRewardAdClosedListener);
                }
            } else {
                resetAllRewardedShownBoolean(true, onRewardAdClosedListener);
            }
        } else {
            resetAllRewardedShownBoolean(true, onRewardAdClosedListener);
        }

    }

    public void resetAllRewardedShownBoolean(Boolean withListner, OnRewardAdClosedListener onRewardAdClosedListener) {

        isGRewardedShown = false;
        isFbRewardedShown = false;
        isAnRewardedShown = false;
        isMpRewardedShown = false;
        if (withListner) {
            try {
                Log.e("RewardAds...", "onRewardAdNotShown TRY ");
                onRewardAdClosedListener.onRewardAdNotShown();
            } catch (Exception e) {
                Log.e("RewardAds...", "onRewardAdNotShown CATCH ");
                e.printStackTrace();
            }
        }

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onNetworkChangeListner = (OnNetworkChangeListner)this;

        networkStateReceiver = new NetworkStateReceiver();
        networkStateReceiver.addListener(this);
        this.registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));

        isvalidInstall = verifyInstallerId(this);
        Log.e("validation", String.valueOf(isvalidInstall));

        // initialize appnext sdk
        Appnext.init(this);
        progressDialog = new ProgressDialog(this);
        defaultIds = new DefaultIds(this);
        adsPrefernce = new AdsPrefernce(this);

        // initialize startapp sdk
        StartAppSDK.init(BaseClass.this, defaultIds.SA_APP_ID(), false);
        StartAppAd.enableConsent(this, false);
        //disable startapp splash
        if (defaultIds.DISABLE_SA_SPLASH()) {
            StartAppAd.disableSplash();
        }

        if (!isInterAdLoadedIH) {
            getInterstitalAdsInHouse(defaultIds.APP_KEY());
        }
        if (!isBannerAdLoadedIH) {
            getBannerAdsInHouse(defaultIds.APP_KEY());
        }
        if (!isNativeAdLoadedIH) {
            getNativeAdsInHouse(defaultIds.APP_KEY());
        }


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isNetworkAvailable(BaseClass.this)) {
                    if (!isAdsAvailable) {
                        Log.e("Ads...", "call get Ads");
                        getAds(defaultIds.APP_KEY());
                    }
                }
            }
        }, 500);
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void getAds(final String appKey) {

        adsList = new ArrayList<AdsIdsList>();
        AsyncHttpClient client = new AsyncHttpClient();
        gsonUtils = GsonUtils.getInstance();
        RequestParams params1 = new RequestParams();
        params1.put("app_key", appKey);
        try {
            client.setConnectTimeout(50000);

            client.post("http://developercompanion.get-fans-for-musically.com/iapi/ads_service.php", params1, new BaseJsonHttpResponseHandler<AdsData>() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, AdsData response) {

                    Log.e("Ads...", "Success");

                    adsList.clear();
                    adsList = new ArrayList<>();

                    if (response.getSuccess() == 0) {
                        isAdsAvailable = true;
                        if (adsPrefernce.isMediationActive()) {
                            if (!adsPrefernce.planD()) {
                                loadMixedInterAds();
//                                initializeMoPubSDKforInter1(adsPrefernce.mpInterId1(), false);
//                                initializeMoPubSDKforInter2(adsPrefernce.mpInterId2(), false);
                                initializeMoPubSDK();
                            }
                        }
                        return;
                    }

                    adsList.addAll(response.getAdsIdsList());


                    AdsIdsList ads = adsList.get(0);

                    //add data to shared_preference
                    adsPrefernce = new AdsPrefernce(BaseClass.this);
                    adsPrefernce.setAdsDefaults(ads.getShowAds(), ads.getShowLoading(), ads.getAllowAccess(), ads.getMediation(), ads.getAd1(), ads.getAd2(), ads.getAd3(), ads.getAd4(), ads.getAd5(),
                            ads.getGAppId(), ads.getGBanner(), ads.getShowGbanner(), ads.getGInter1(), ads.getShowGinter1(), ads.getGInter2(), ads.getShowGinter2(),
                            ads.getGRewarded(), ads.getShowGrewarded(), ads.getGNative1(), ads.getShowGnative1(), ads.getGNative2(), ads.getShowGnative2(),

                            ads.getFbBanner(), ads.getShowFbbanner(), ads.getFbInter1(), ads.getShowFbinter1(), ads.getFbInter2(), ads.getShowFbinter2(),
                            ads.getFbRewarded(), ads.getShowFbRewarded(), ads.getFbNative1(), ads.getShowFbnative1(), ads.getFbNative2(), ads.getShowFbnative2(),
                            ads.getFbNativebanner(), ads.getFbNativebanner(),

                            ads.getAnAdId(), ads.getShowAnbanner(), ads.getShowAninter1(), ads.getShowAninter2(),
                            ads.getShowAnnative1(), ads.getShowAnnative2(), ads.getShowAnrewarded(),

                            ads.getMpBanner(), ads.getShowMpbanner(), ads.getMpInter1(), ads.getShowMpinter1(), ads.getMpInter2(), ads.getShowMpinter2(),
                            ads.getMpNative1(), ads.getShowMpnative1(), ads.getMpNative2(), ads.getShowMpnative2(), ads.getMpRewarded(), ads.getShowMprewarded(),

                            ads.getuAppId(), ads.getUBanner(), ads.getShowUbanner(), ads.getUInter1(), ads.getShowUinter1(), ads.getUInter2(), ads.getShowUinter2(),
                            ads.getURewarded(), ads.getShowUrewarded(),
                            ads.getExtraPara1(), ads.getExtraPara2(), ads.getExtraPara3(), ads.getExtraPara4(),

                            ads.getSaAdCount()
                    );
//                    initializeMoPubSDKforInter1(adsPrefernce.mpInterId1(), false);
//                    initializeMoPubSDKforInter2(adsPrefernce.mpInterId2(), false);
                    initializeMoPubSDK();

                    if (ads.getAd4().equals("1")) {

                        adsPrefernce = new AdsPrefernce(BaseClass.this);
                        adsPrefernce.setAdsDefaults("0", ads.getShowLoading(), ads.getAllowAccess(), ads.getMediation(), "0", "0", "0", ads.getAd4(), "0", "na", "na", "0",
                                "na", "0", "na", "0", "na", "0", "na", "0", "na", "0",
                                "na", "0", "na", "0", "na", "0", "na", "0", "na", "0",
                                "na", "0", "na", "0", "0", "0", "0",
                                "0", "0", "na", "0", "na", "0",
                                "na", "0", "na", "0", "na", "0", "na", "0",
                                "na", "0", "na", "na", "0", "na", "0",
                                "na", "0", "na", "0", ads.getExtraPara1(), ads.getExtraPara2(), ads.getExtraPara3(), ads.getExtraPara4(),
                                ads.getSaAdCount());
                        // initialize startapp sdk
                        StartAppSDK.init(BaseClass.this, defaultIds.SA_APP_ID(), false);
                        StartAppAd.enableAutoInterstitial();

                        //disable startapp splash
                        if (defaultIds.DISABLE_SA_SPLASH()) {
                            StartAppAd.disableSplash();
                        }
                        StartAppAd.setAutoInterstitialPreferences(
                                new AutoInterstitialPreferences()
                                        .setActivitiesBetweenAds(Integer.parseInt(adsPrefernce.adCountSA()))
                        );
                    }

                    if (adsPrefernce.isMediationActive()) {
                        if (!adsPrefernce.planD()) {
                            Log.e("Ads...", "loading Mixed Inter");
                            isAdsAvailable = true;
                            loadMixedInterAds();
                        }
                    }

                    isAdsAvailable = true;

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, AdsData errorResponse) {
                    isAdsAvailable = false;

                }

                @Override
                protected AdsData parseResponse(String rawJsonData, boolean isFailure) throws Throwable {

                    try {
                        if (!isFailure && !rawJsonData.isEmpty()) {
                            return gsonUtils.getGson().fromJson(rawJsonData, AdsData.class);
                        }
                    } catch (Exception ignored) {

                    }
                    return null;
                }
            });

        } catch (Exception ignored) {

        }

    }


    public void showNativeBannerAd(Integer top, Integer bottom) {
        if (isNetworkAvailable(this)) {
            if (isAdsAvailable) {
                if (!adsPrefernce.planD()) {
                    if (adsPrefernce.showgBanner()) {
                        AdView gadView;
                        MobileAds.initialize(this, adsPrefernce.gAppId());
                        final FrameLayout adContainerView = this.findViewById(R.id.native_banner_container);
                        adContainerView.setVisibility(View.VISIBLE);
                        gadView = new AdView(this);
                        adContainerView.setPadding(0, top, 0, bottom);
                        gadView.setAdUnitId(adsPrefernce.gBannerId());
                        adContainerView.addView(gadView);
                        AdRequest adRequest = new AdRequest.Builder().build();
                        gadView.setAdSize(AdSize.LARGE_BANNER);
                        gadView.loadAd(adRequest);
                        gadView.setAdListener(new com.google.android.gms.ads.AdListener() {
                            @Override
                            public void onAdLoaded() {
                                super.onAdLoaded();
                                adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                                }
                            }

                            @Override
                            public void onAdFailedToLoad(LoadAdError loadAdError) {
                                super.onAdFailedToLoad(loadAdError);
                                final FrameLayout adContainerView = findViewById(R.id.native_banner_container);
                                adContainerView.setVisibility(View.VISIBLE);
                                adContainerView.setPadding(0, top, 0, bottom);
                                showInhouseBannerAd(new InhouseBannerListener() {
                                    @Override
                                    public void onAdLoaded() {
                                        adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                            getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                                        }
                                    }

                                    @Override
                                    public void onAdShowFailed() {

                                    }
                                });
                            }
                        });
                    } else {
                        if (adsPrefernce.showfbNativeBanner()) {
                            final NativeBannerAd nativeBannerAd;
                            AudienceNetworkAds.initialize(this);
                            nativeBannerAd = new NativeBannerAd(this, adsPrefernce.fbNativeBannerId());
                            final FrameLayout adContainerView = findViewById(R.id.native_banner_container);
                            adContainerView.setVisibility(View.VISIBLE);
                            adContainerView.setPadding(0, top, 0, bottom);
                            NativeAdListener nativeAdListener = new NativeAdListener() {
                                @Override
                                public void onMediaDownloaded(Ad ad) {
                                    // Native ad finished downloading all assets
                                }

                                @Override
                                public void onError(Ad ad, AdError adError) {
                                    // Native ad failed to load
                                    final FrameLayout adContainerView = findViewById(R.id.native_banner_container);
                                    adContainerView.setVisibility(View.VISIBLE);
                                    adContainerView.setPadding(0, top, 0, bottom);
                                    showInhouseBannerAd(new InhouseBannerListener() {
                                        @Override
                                        public void onAdLoaded() {
                                            adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                                getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                                            }
                                        }

                                        @Override
                                        public void onAdShowFailed() {

                                        }
                                    });
                                }

                                @Override
                                public void onAdLoaded(Ad ad) {
                                    // Native ad is loaded and ready to be displayed
                                    inflateNativeBannerAdFacebook(nativeBannerAd);
                                    adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                                    }
                                }

                                @Override
                                public void onAdClicked(Ad ad) {
                                    // Native ad clicked
                                }

                                @Override
                                public void onLoggingImpression(Ad ad) {
                                    // Native ad impression
                                }
                            };
                            nativeBannerAd.loadAd(nativeBannerAd.buildLoadAdConfig().withAdListener(nativeAdListener).build());
                        }
                    }
                } else {
                    final FrameLayout adContainerView = (FrameLayout) findViewById(R.id.native_banner_container);
                    Banner startAppBanner = new Banner(this, new BannerListener() {
                        @Override
                        public void onReceiveAd(View view) {
                            adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                            }
                        }

                        @Override
                        public void onFailedToReceiveAd(View view) {
                            final FrameLayout adContainerView = findViewById(R.id.native_banner_container);
                            adContainerView.setVisibility(View.VISIBLE);
                            adContainerView.setPadding(0, top, 0, bottom);
                            showInhouseBannerAd(new InhouseBannerListener() {
                                @Override
                                public void onAdLoaded() {
                                    adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                                    }
                                }

                                @Override
                                public void onAdShowFailed() {

                                }
                            });
                        }

                        @Override
                        public void onImpression(View view) {

                        }

                        @Override
                        public void onClick(View view) {

                        }
                    });
                    FrameLayout.LayoutParams bannerParameters =
                            new FrameLayout.LayoutParams(
                                    FrameLayout.LayoutParams.MATCH_PARENT,
                                    FrameLayout.LayoutParams.WRAP_CONTENT);
                    adContainerView.setVisibility(View.VISIBLE);
                    adContainerView.setForegroundGravity(Gravity.CENTER);
                    adContainerView.setPadding(0, top, 0, bottom);
                    adContainerView.addView(startAppBanner, bannerParameters);

                }
            } else {
                final FrameLayout adContainerView = findViewById(R.id.native_banner_container);
                adContainerView.setVisibility(View.VISIBLE);
                adContainerView.setPadding(0, top, 0, bottom);
                showInhouseBannerAd(new InhouseBannerListener() {
                    @Override
                    public void onAdLoaded() {
                        adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                        }
                    }

                    @Override
                    public void onAdShowFailed() {

                    }
                });

            }
        }
    }

    private void inflateNativeBannerAdFacebook(NativeBannerAd nativeBannerAd) {
        // Unregister last ad
        nativeBannerAd.unregisterView();

        // Add the Ad view into the ad container.
        NativeAdLayout nativeAdLayout = new NativeAdLayout(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        // Inflate the Ad view.  The layout referenced is the one you created in the last step.
        FrameLayout adContainer = (FrameLayout) findViewById(R.id.native_banner_container);
        LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.native_banner_ad_layout_facebook, adContainer, false);
        adContainer.addView(adView);

        // Add the AdChoices icon
        RelativeLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(this, nativeBannerAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        MediaView nativeAdIconView = adView.findViewById(R.id.native_icon_view);
        Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdCallToAction.setText(nativeBannerAd.getAdCallToAction());
        nativeAdCallToAction.setVisibility(
                nativeBannerAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdTitle.setText(nativeBannerAd.getAdvertiserName());
        nativeAdSocialContext.setText(nativeBannerAd.getAdSocialContext());
        sponsoredLabel.setText(nativeBannerAd.getSponsoredTranslation());

        // Register the Title and CTA button to listen for clicks.
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);
        nativeBannerAd.registerViewForInteraction(adView, nativeAdIconView, clickableViews);
    }

    public static boolean isGBannerShown = false;
    public static boolean isFbBannerShown = false;
    public static boolean isAnBannerShown = false;
    public static boolean isMpBannerShown = false;

    public void showBannerAd(Integer top, Integer bottom) {
        if (isNetworkAvailable(this)) {
            if (isAdsAvailable) {
                if (!adsPrefernce.planD()) {
                    if (!adsPrefernce.isMediationActive()) {
                        if (adsPrefernce.showgBanner()) {
                            AdView gadView;
                            MobileAds.initialize(this, adsPrefernce.gAppId());
                            final FrameLayout adContainerView = this.findViewById(R.id.banner_container);
                            adContainerView.setVisibility(View.VISIBLE);
                            gadView = new AdView(this);
                            adContainerView.setPadding(0, top, 0, bottom);
                            gadView.setAdUnitId(adsPrefernce.gBannerId());
                            adContainerView.addView(gadView);
                            AdRequest adRequest = new AdRequest.Builder().build();
                            com.google.android.gms.ads.AdSize adSize = getAdSize();
                            gadView.setAdSize(adSize);
                            gadView.loadAd(adRequest);
                            gadView.setAdListener(new com.google.android.gms.ads.AdListener() {
                                @Override
                                public void onAdLoaded() {
                                    super.onAdLoaded();
                                    adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                                    }
                                }

                                @Override
                                public void onAdFailedToLoad(LoadAdError loadAdError) {
                                    super.onAdFailedToLoad(loadAdError);
                                    final FrameLayout adContainerView = findViewById(R.id.banner_container);
                                    adContainerView.setVisibility(View.VISIBLE);
                                    adContainerView.setPadding(0, top, 0, bottom);
                                    showInhouseBannerAd(new InhouseBannerListener() {
                                        @Override
                                        public void onAdLoaded() {
                                            adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                                getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                                            }
                                        }

                                        @Override
                                        public void onAdShowFailed() {

                                        }
                                    });
                                }
                            });
                        } else if (adsPrefernce.showfbBanner()) {
                            com.facebook.ads.AdView adView;
                            AudienceNetworkAds.initialize(this);
                            adView = new com.facebook.ads.AdView(this, adsPrefernce.fbBannerId(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                            final FrameLayout adContainerView = findViewById(R.id.banner_container);
                            adContainerView.setVisibility(View.VISIBLE);
                            adContainerView.addView(adView);
                            adContainerView.setPadding(0, top, 0, bottom);
                            AdListener bannerAdListner = new AdListener() {
                                @Override
                                public void onError(Ad ad, AdError adError) {
                                    final FrameLayout adContainerView = findViewById(R.id.banner_container);
                                    adContainerView.setVisibility(View.VISIBLE);
                                    adContainerView.setPadding(0, top, 0, bottom);
                                    showInhouseBannerAd(new InhouseBannerListener() {
                                        @Override
                                        public void onAdLoaded() {
                                            adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                                getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                                            }
                                        }

                                        @Override
                                        public void onAdShowFailed() {

                                        }
                                    });
                                }

                                @Override
                                public void onAdLoaded(Ad ad) {
                                    adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                                    }

                                }

                                @Override
                                public void onAdClicked(Ad ad) {

                                }

                                @Override
                                public void onLoggingImpression(Ad ad) {

                                }
                            };
                            adView.loadAd(adView.buildLoadAdConfig().withAdListener(bannerAdListner).build());

                        } else if (adsPrefernce.showanBanner()) {
                            final FrameLayout adContainerView = (FrameLayout) findViewById(R.id.banner_container);
                            adContainerView.setPadding(0, top, 0, bottom);
                            Appnext.init(this);
                            BannerView banner = new BannerView(this);
                            banner.setPlacementId(adsPrefernce.anAdId());
                            banner.setBannerSize(BannerSize.BANNER);
                            banner.loadAd(new BannerAdRequest());
                            adContainerView.addView(banner);
                            banner.setBannerListener(new com.appnext.banners.BannerListener() {
                                @Override
                                public void onAdLoaded(String s, AppnextAdCreativeType appnextAdCreativeType) {
                                    super.onAdLoaded(s, appnextAdCreativeType);
                                    adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                                    }
                                }

                                @Override
                                public void onError(AppnextError appnextError) {
                                    super.onError(appnextError);
                                    final FrameLayout adContainerView = findViewById(R.id.banner_container);
                                    adContainerView.setVisibility(View.VISIBLE);
                                    adContainerView.setPadding(0, top, 0, bottom);
                                    showInhouseBannerAd(new InhouseBannerListener() {
                                        @Override
                                        public void onAdLoaded() {
                                            adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                                getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                                            }
                                        }

                                        @Override
                                        public void onAdShowFailed() {

                                        }
                                    });
                                }
                            });
                        } else if (adsPrefernce.showmpBanner()) {
                            MoPubView moPubView;
                            moPubView = new MoPubView(this);
                            if (mpBannerInitilized) {
                                moPubView.setAdUnitId(adsPrefernce.mpBannerId()); // Enter your Ad Unit ID from www.mopub.com
                                moPubView.setAdSize(MoPubView.MoPubAdSize.MATCH_VIEW); // Call this if you are not setting the ad size in XML or wish to use an ad size other than what has been set in the XML. Note that multiple calls to `setAdSize()` will override one another, and the MoPub SDK only considers the most recent one.
                                moPubView.loadAd();
                                final FrameLayout adContainerView = findViewById(R.id.banner_container);
                                adContainerView.setVisibility(View.VISIBLE);
                                adContainerView.setPadding(0, top, 0, bottom);
                                adContainerView.addView(moPubView);
                                moPubView.setBannerAdListener(new MoPubView.BannerAdListener() {
                                    @Override
                                    public void onBannerLoaded(@NonNull MoPubView banner) {
                                        adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                            getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                                        }
                                    }

                                    @Override
                                    public void onBannerFailed(MoPubView banner, MoPubErrorCode errorCode) {
                                        final FrameLayout adContainerView = findViewById(R.id.banner_container);
                                        adContainerView.setVisibility(View.VISIBLE);
                                        adContainerView.setPadding(0, top, 0, bottom);
                                        showInhouseBannerAd(new InhouseBannerListener() {
                                            @Override
                                            public void onAdLoaded() {
                                                adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                                    getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                                                }
                                            }

                                            @Override
                                            public void onAdShowFailed() {

                                            }
                                        });

                                    }

                                    @Override
                                    public void onBannerClicked(MoPubView banner) {

                                    }

                                    @Override
                                    public void onBannerExpanded(MoPubView banner) {

                                    }

                                    @Override
                                    public void onBannerCollapsed(MoPubView banner) {

                                    }
                                });
                            } else {
                                initializeMoPubSDK();
                            }
                        }
                    } else {
                        if (adsPrefernce.showgBanner()) {
                            if (!isGBannerShown) {
                                showGBanner(top, bottom);
                            } else {
                                showFbBanner(top, bottom);
                            }
                        } else {
                            showFbBanner(top, bottom);
                        }
                    }
                } else {
                    final FrameLayout adContainerView = (FrameLayout) findViewById(R.id.banner_container);
                    Banner startAppBanner = new Banner(this, new BannerListener() {
                        @Override
                        public void onReceiveAd(View view) {
                            adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                            }
                        }

                        @Override
                        public void onFailedToReceiveAd(View view) {
                            final FrameLayout adContainerView = findViewById(R.id.banner_container);
                            adContainerView.setVisibility(View.VISIBLE);
                            adContainerView.setPadding(0, top, 0, bottom);
                            showInhouseBannerAd(new InhouseBannerListener() {
                                @Override
                                public void onAdLoaded() {
                                    adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                                    }
                                }

                                @Override
                                public void onAdShowFailed() {

                                }
                            });
                        }

                        @Override
                        public void onImpression(View view) {

                        }

                        @Override
                        public void onClick(View view) {

                        }
                    });
                    FrameLayout.LayoutParams bannerParameters =
                            new FrameLayout.LayoutParams(
                                    FrameLayout.LayoutParams.MATCH_PARENT,
                                    FrameLayout.LayoutParams.WRAP_CONTENT);
                    adContainerView.setVisibility(View.VISIBLE);
                    adContainerView.setForegroundGravity(Gravity.CENTER);
                    adContainerView.setPadding(0, top, 0, bottom);
                    adContainerView.addView(startAppBanner, bannerParameters);
                }
            } else {
                final FrameLayout adContainerView = findViewById(R.id.banner_container);
                adContainerView.setVisibility(View.VISIBLE);
                adContainerView.setPadding(0, top, 0, bottom);
                showInhouseBannerAd(new InhouseBannerListener() {
                    @Override
                    public void onAdLoaded() {
                        adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                        }
                    }

                    @Override
                    public void onAdShowFailed() {

                    }
                });
            }
        } else {
            final FrameLayout adContainerView = findViewById(R.id.banner_container);
            adContainerView.setVisibility(View.VISIBLE);
            adContainerView.setPadding(0, top, 0, bottom);
            showInhouseBannerAd(new InhouseBannerListener() {
                @Override
                public void onAdLoaded() {
                    adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                    }
                }

                @Override
                public void onAdShowFailed() {

                }
            });
        }
    }

    public void resetAllBannerBoolean() {
        isGBannerShown = false;
        isFbBannerShown = false;
        isAnBannerShown = false;
        isMpBannerShown = false;
    }

    public void showMpBanner(int top, int bottom) {
        if (adsPrefernce.showmpBanner()) {
            if (!isMpBannerShown) {
                MoPubView moPubView;
                moPubView = new MoPubView(this);
                if (mpBannerInitilized) {
                    moPubView.setAdUnitId(adsPrefernce.mpBannerId()); // Enter your Ad Unit ID from www.mopub.com
                    moPubView.setAdSize(MoPubView.MoPubAdSize.MATCH_VIEW); // Call this if you are not setting the ad size in XML or wish to use an ad size other than what has been set in the XML. Note that multiple calls to `setAdSize()` will override one another, and the MoPub SDK only considers the most recent one.
                    moPubView.loadAd();
                    final FrameLayout adContainerView = findViewById(R.id.banner_container);
                    adContainerView.setVisibility(View.VISIBLE);
                    adContainerView.setPadding(0, top, 0, bottom);
                    adContainerView.addView(moPubView);
                    moPubView.setBannerAdListener(new MoPubView.BannerAdListener() {
                        @Override
                        public void onBannerLoaded(@NonNull MoPubView banner) {
                            isMpBannerShown = true;
                            resetAllShownBoolean();
                            adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                            }
                        }

                        @Override
                        public void onBannerFailed(MoPubView banner, MoPubErrorCode errorCode) {
                            isMpBannerShown = true;
                            resetAllBannerBoolean();
                            final FrameLayout adContainerView = findViewById(R.id.banner_container);
                            adContainerView.setVisibility(View.VISIBLE);
                            adContainerView.setPadding(0, top, 0, bottom);
                            showInhouseBannerAd(new InhouseBannerListener() {
                                @Override
                                public void onAdLoaded() {
                                    adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                                    }
                                }

                                @Override
                                public void onAdShowFailed() {

                                }
                            });
                        }

                        @Override
                        public void onBannerClicked(MoPubView banner) {

                        }

                        @Override
                        public void onBannerExpanded(MoPubView banner) {

                        }

                        @Override
                        public void onBannerCollapsed(MoPubView banner) {

                        }
                    });
                } else {
                    initializeMoPubSDK();
                    final FrameLayout adContainerView = findViewById(R.id.banner_container);
                    adContainerView.setVisibility(View.VISIBLE);
                    adContainerView.setPadding(0, top, 0, bottom);
                    showInhouseBannerAd(new InhouseBannerListener() {
                        @Override
                        public void onAdLoaded() {
                            adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                            }
                        }

                        @Override
                        public void onAdShowFailed() {

                        }
                    });
                }
            } else {
                resetAllBannerBoolean();
                final FrameLayout adContainerView = findViewById(R.id.banner_container);
                adContainerView.setVisibility(View.VISIBLE);
                adContainerView.setPadding(0, top, 0, bottom);
                showInhouseBannerAd(new InhouseBannerListener() {
                    @Override
                    public void onAdLoaded() {
                        adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                        }
                    }

                    @Override
                    public void onAdShowFailed() {

                    }
                });
            }
        } else {
            resetAllBannerBoolean();
            final FrameLayout adContainerView = findViewById(R.id.banner_container);
            adContainerView.setVisibility(View.VISIBLE);
            adContainerView.setPadding(0, top, 0, bottom);
            showInhouseBannerAd(new InhouseBannerListener() {
                @Override
                public void onAdLoaded() {
                    adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                    }
                }

                @Override
                public void onAdShowFailed() {

                }
            });
        }

    }

    public void showAnBanner(int top, int bottom) {
        if (adsPrefernce.showanBanner()) {
            if (!isAnBannerShown) {
                final FrameLayout adContainerView = (FrameLayout) findViewById(R.id.banner_container);
                adContainerView.setPadding(0, top, 0, bottom);
                Appnext.init(this);
                BannerView banner = new BannerView(this);
                banner.setPlacementId(adsPrefernce.anAdId());
                banner.setBannerSize(BannerSize.BANNER);
                banner.loadAd(new BannerAdRequest());
                adContainerView.addView(banner);
                banner.setBannerListener(new com.appnext.banners.BannerListener() {
                    @Override
                    public void onAdLoaded(String s, AppnextAdCreativeType appnextAdCreativeType) {
                        super.onAdLoaded(s, appnextAdCreativeType);
                        isAnBannerShown = true;
                        adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                        }
                    }

                    @Override
                    public void onError(AppnextError appnextError) {
                        super.onError(appnextError);
                        isAnBannerShown = true;
                        showMpBanner(top, bottom);
                        Log.e("an banner error: ", String.valueOf(appnextError));
                    }
                });
            } else {
                showMpBanner(top, bottom);
            }
        } else {
            showMpBanner(top, bottom);
        }

    }

    public void showFbBanner(int top, int bottom) {
        if (adsPrefernce.showfbBanner()) {
            if (!isFbBannerShown) {
                com.facebook.ads.AdView adView;
                AudienceNetworkAds.initialize(this);
                adView = new com.facebook.ads.AdView(this, adsPrefernce.fbBannerId(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                final FrameLayout adContainerView = findViewById(R.id.banner_container);
                adContainerView.setVisibility(View.VISIBLE);
                adContainerView.addView(adView);
                adContainerView.setPadding(0, top, 0, bottom);
                AdListener bannerAdListner = new AdListener() {
                    @Override
                    public void onError(Ad ad, AdError adError) {
                        isFbBannerShown = true;
                        showAnBanner(top,bottom);
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                        }
                        resetAllBannerBoolean();


                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                };
                adView.loadAd(adView.buildLoadAdConfig().withAdListener(bannerAdListner).build());
            } else {
                showAnBanner(top, bottom);
            }
        } else {
            showAnBanner(top, bottom);
        }

    }

    public void showGBanner(int top, int bottom) {
        AdView gadView;
        MobileAds.initialize(this, adsPrefernce.gAppId());
        final FrameLayout adContainerView = this.findViewById(R.id.banner_container);
        adContainerView.setVisibility(View.VISIBLE);
        gadView = new AdView(this);
        adContainerView.setPadding(0, top, 0, bottom);
        gadView.setAdUnitId(adsPrefernce.gBannerId());
        adContainerView.addView(gadView);
        AdRequest adRequest = new AdRequest.Builder().build();
        com.google.android.gms.ads.AdSize adSize = getAdSize();
        gadView.setAdSize(adSize);
        gadView.loadAd(adRequest);
        gadView.setAdListener(new com.google.android.gms.ads.AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                isGBannerShown = true;
                adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                }
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                isGBannerShown = true;
                showFbBanner(top, bottom);
            }
        });
    }

    public void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void toast(String text, Boolean longToast) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    public void loadSplashInterstitial() {
        adsPrefernce = new AdsPrefernce(this);
        if (isNetworkAvailable(this)) {
            if (!adsPrefernce.planD()) {
                if (!adsPrefernce.isMediationActive()) {
                    if (adsPrefernce.planA()) {
                        if (adsPrefernce.showgInter1()) {
                            if (!isGInter1Ready) {
                                MobileAds.initialize(getApplicationContext(), adsPrefernce.gAppId());
                                gInterstitial1 = new com.google.android.gms.ads.InterstitialAd(this);
                                gInterstitial1.setAdUnitId(adsPrefernce.gInterId1());
                                gInterstitial1.loadAd(new AdRequest.Builder().build());
                                gInterstitial1.setAdListener(new com.google.android.gms.ads.AdListener() {
                                    @Override
                                    public void onAdLoaded() {
                                        super.onAdLoaded();
                                        isGInter1Ready = true;
                                    }
                                });
                            }
                        }
                        if (adsPrefernce.showfbInter1()) {
                            if (!isFbInter1Ready) {
                                AudienceNetworkAds.initialize(this);
                                fbInterstitial1 = new com.facebook.ads.InterstitialAd(this, adsPrefernce.fbInterId1());
                                InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
                                    @Override
                                    public void onInterstitialDisplayed(Ad ad) {

                                    }

                                    @Override
                                    public void onInterstitialDismissed(Ad ad) {

                                    }

                                    @Override
                                    public void onError(Ad ad, AdError adError) {

                                    }

                                    @Override
                                    public void onAdLoaded(Ad ad) {
                                        isFbInter1Ready = true;
                                    }

                                    @Override
                                    public void onAdClicked(Ad ad) {

                                    }

                                    @Override
                                    public void onLoggingImpression(Ad ad) {

                                    }
                                };
                                fbInterstitial1.loadAd(fbInterstitial1.buildLoadAdConfig().withAdListener(interstitialAdListener).build());
                            }

                        }

                    }
                    if (adsPrefernce.planB()) {
                        if (adsPrefernce.showanInter1()) {
                            an_interstitial_Ad1 = new Interstitial(this, adsPrefernce.anAdId());
                            if (!isAnInter1Ready) {
                                an_interstitial_Ad1.loadAd();
                                an_interstitial_Ad1.setOnAdLoadedCallback(new OnAdLoaded() {
                                    @Override
                                    public void adLoaded(String s, AppnextAdCreativeType appnextAdCreativeType) {
                                        isAnInter1Ready = true;
                                    }
                                });
                            }
                        }
                    }
                    if (adsPrefernce.planC()) {
                        if (adsPrefernce.showmpInter1()) {
                            if (mpInter1Initilized) {
                                mpInterstitial1 = new MoPubInterstitial((Activity) this, adsPrefernce.mpInterId1());
                                if (!isMpInter1Ready) {
                                    mpInterstitial1.load();
                                    mpInterstitial1.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                                        @Override
                                        public void onInterstitialLoaded(MoPubInterstitial interstitial) {
                                            isMpInter1Ready = true;
                                        }

                                        @Override
                                        public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {

                                        }

                                        @Override
                                        public void onInterstitialShown(MoPubInterstitial interstitial) {

                                        }

                                        @Override
                                        public void onInterstitialClicked(MoPubInterstitial interstitial) {

                                        }

                                        @Override
                                        public void onInterstitialDismissed(MoPubInterstitial interstitial) {

                                        }
                                    });
                                }
                            } else {
                                initializeMoPubSDK();
                            }
                        }
                    }
                }
            }
        }
    }

    public void showSplashInterstitial(Callable<Void> mathodToFollow) {
        if (!adsPrefernce.planD()) {
            if (adsPrefernce.allowAccess()) {
                if (!adsPrefernce.isMediationActive()) {
                    try {
                        mathodToFollow.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    showSplashAd();
                } else {
                    showMixedInterAds(mathodToFollow);
                }
            }else {
                try {
                    mathodToFollow.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                mathodToFollow.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void showSplashAd() {
        if (isNetworkAvailable(this)) {
            if (adsPrefernce.planA()) {
                if (adsPrefernce.showgInter1()) {
                    if (isGInter1Ready) {
                        if (gInterstitial1.isLoaded() && gInterstitial1 != null) {
                            gInterstitial1.show();
                            gInterstitial1.setAdListener(new com.google.android.gms.ads.AdListener() {
                                public void onAdClosed() {
                                    gInterstitial1.loadAd(new AdRequest.Builder().build());
                                }

                                @Override
                                public void onAdOpened() {
                                    super.onAdOpened();
                                    isGInter1Ready = false;
                                    isGInter1Shown = true;
                                }
                            });
                        }

                    }
                } else if (adsPrefernce.showfbInter1()) {
                    if (isFbInter1Ready) {
                        if (fbInterstitial1.isAdLoaded()) {
                            fbInterstitial1.show();
                            InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
                                @Override
                                public void onInterstitialDisplayed(Ad ad) {
                                    isFbInter1Ready = false;
                                    isFbInter1Shown = true;
                                }

                                @Override
                                public void onInterstitialDismissed(Ad ad) {
                                    fbInterstitial1.loadAd();
                                }

                                @Override
                                public void onError(Ad ad, AdError adError) {
                                }

                                @Override
                                public void onAdLoaded(Ad ad) {

                                }

                                @Override
                                public void onAdClicked(Ad ad) {

                                }

                                @Override
                                public void onLoggingImpression(Ad ad) {

                                }
                            };
                            fbInterstitial1.buildLoadAdConfig().withAdListener(interstitialAdListener).build();
                        }

                    }
                }
            } else if (adsPrefernce.planB()) {
                if (adsPrefernce.showanInter1()) {
                    if (isAnInter1Ready) {
                        if (an_interstitial_Ad1.isAdLoaded()) {
                            an_interstitial_Ad1.showAd();
                            an_interstitial_Ad1.setOnAdClosedCallback(new OnAdClosed() {
                                @Override
                                public void onAdClosed() {
                                    loadInterstitial1();
                                }
                            });
                            an_interstitial_Ad1.setOnAdOpenedCallback(new OnAdOpened() {
                                @Override
                                public void adOpened() {
                                    isAnInter1Ready = false;
                                    isAnInter1Shown = true;
                                }
                            });
                        }
                    }
                }
            } else if (adsPrefernce.planC()) {
                if (adsPrefernce.showmpInter1()) {
                    if (isMpInter1Ready) {
                        if (mpInterstitial1 != null) {
                            if (mpInterstitial1.isReady()) {
                                mpInterstitial1.show();
                                mpInterstitial1.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                                    @Override
                                    public void onInterstitialLoaded(MoPubInterstitial interstitial) {

                                    }

                                    @Override
                                    public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {

                                    }

                                    @Override
                                    public void onInterstitialShown(MoPubInterstitial interstitial) {
                                        isMpInter1Ready = false;
                                    }

                                    @Override
                                    public void onInterstitialClicked(MoPubInterstitial interstitial) {

                                    }

                                    @Override
                                    public void onInterstitialDismissed(MoPubInterstitial interstitial) {

                                        mpInterstitial1.load();
                                        mpInterstitial1.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                                            @Override
                                            public void onInterstitialLoaded(MoPubInterstitial interstitial) {
                                                Log.e("inLoadonClosed", "ismpinter1 ready = true");
                                                isMpInter1Ready = true;
                                            }

                                            @Override
                                            public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {

                                            }

                                            @Override
                                            public void onInterstitialShown(MoPubInterstitial interstitial) {

                                            }

                                            @Override
                                            public void onInterstitialClicked(MoPubInterstitial interstitial) {

                                            }

                                            @Override
                                            public void onInterstitialDismissed(MoPubInterstitial interstitial) {

                                            }
                                        });


                                    }
                                });
                            }
                        }
                    }
                }
            }


        }
    }


    public void loadMixedInterAds() {
        Log.e("Ads...", "loading ads");
        if (isNetworkAvailable(this)) {
            Log.e("Ads...", "isNetworkAvailable");
            if (isAdsAvailable) {
                Log.e("Ads...", "isAdsAvailable");

                if (adsPrefernce.isMediationActive()) {
                    Log.e("Ads...", "isMediationActive");
                    if (adsPrefernce.planA()) {
                        Log.e("Ads...", "planA");
                        if (adsPrefernce.showgInter1()) {
                            Log.e("Ads...", "showgoogle1");

                            if (!isGInter1Ready) {
                                Log.e("Ads...", "google1 not ready");
                                MobileAds.initialize(getApplicationContext(), adsPrefernce.gAppId());
                                gInterstitial1 = new com.google.android.gms.ads.InterstitialAd(this);
                                gInterstitial1.setAdUnitId(adsPrefernce.gInterId1());
                                gInterstitial1.loadAd(new AdRequest.Builder().build());
                                gInterstitial1.setAdListener(new com.google.android.gms.ads.AdListener() {
                                    @Override
                                    public void onAdLoaded() {
                                        super.onAdLoaded();
                                        Log.e("Ads...", "google1 ready");
                                        isGInter1Ready = true;
                                    }
                                });
                            }
                        }
                        if (adsPrefernce.showgInter2()) {
                            Log.e("Ads...", "showgoogle2");
                            if (!isGInter2Ready) {
                                Log.e("Ads...", "google2 not ready");
                                MobileAds.initialize(getApplicationContext(), adsPrefernce.gAppId());
                                gInterstitial2 = new com.google.android.gms.ads.InterstitialAd(this);
                                gInterstitial2.setAdUnitId(adsPrefernce.gInterId2());
                                gInterstitial2.loadAd(new AdRequest.Builder().build());
                                gInterstitial2.setAdListener(new com.google.android.gms.ads.AdListener() {
                                    @Override
                                    public void onAdLoaded() {
                                        super.onAdLoaded();
                                        Log.e("Ads...", "google2 ready");
                                        isGInter2Ready = true;
                                    }
                                });
                            }
                        }
                        if (adsPrefernce.showfbInter1()) {
                            if (!isFbInter1Ready) {
                                AudienceNetworkAds.initialize(this);
                                fbInterstitial1 = new com.facebook.ads.InterstitialAd(this, adsPrefernce.fbInterId1());
                                InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
                                    @Override
                                    public void onInterstitialDisplayed(Ad ad) {

                                    }

                                    @Override
                                    public void onInterstitialDismissed(Ad ad) {

                                    }

                                    @Override
                                    public void onError(Ad ad, AdError adError) {

                                    }

                                    @Override
                                    public void onAdLoaded(Ad ad) {
                                        isFbInter1Ready = true;
                                    }

                                    @Override
                                    public void onAdClicked(Ad ad) {

                                    }

                                    @Override
                                    public void onLoggingImpression(Ad ad) {

                                    }
                                };
                                fbInterstitial1.loadAd(fbInterstitial1.buildLoadAdConfig().withAdListener(interstitialAdListener).build());

                            }
                        }
                        if (adsPrefernce.showfbInter2()) {
                            if (!isFbInter2Ready) {
                                AudienceNetworkAds.initialize(this);
                                fbInterstitial2 = new com.facebook.ads.InterstitialAd(this, adsPrefernce.fbInterId2());
                                InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
                                    @Override
                                    public void onInterstitialDisplayed(Ad ad) {

                                    }

                                    @Override
                                    public void onInterstitialDismissed(Ad ad) {

                                    }

                                    @Override
                                    public void onError(Ad ad, AdError adError) {

                                    }

                                    @Override
                                    public void onAdLoaded(Ad ad) {
                                        isFbInter2Ready = true;
                                    }

                                    @Override
                                    public void onAdClicked(Ad ad) {

                                    }

                                    @Override
                                    public void onLoggingImpression(Ad ad) {

                                    }
                                };
                                fbInterstitial2.loadAd(fbInterstitial2.buildLoadAdConfig().withAdListener(interstitialAdListener).build());

                            }
                        }
                    }
                    if (adsPrefernce.planB()) {
                        if (adsPrefernce.showanInter1()) {
                            if (!isAnInter1Ready) {
                                an_interstitial_Ad1 = new Interstitial(this, adsPrefernce.anAdId());
                                an_interstitial_Ad1.loadAd();
                                an_interstitial_Ad1.setOnAdLoadedCallback(new OnAdLoaded() {
                                    @Override
                                    public void adLoaded(String s, AppnextAdCreativeType appnextAdCreativeType) {
                                        isAnInter1Ready = true;
                                    }
                                });
                            }
                        }
                    }
                    if (adsPrefernce.planC()) {
                        if (adsPrefernce.showmpInter1()) {
                            Log.e("Ads...", "showmpInter1");
                            if (mpInter1Initilized) {
                                Log.e("Ads...", "mpInter1Initilized");
                                if (!isMpInter1Ready) {
                                    Log.e("Ads...", "isMpInter1 Not Ready");
                                    mpInterstitial1 = new MoPubInterstitial((Activity) this, adsPrefernce.mpInterId1());
                                    mpInterstitial1.load();
                                    mpInterstitial1.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                                        @Override
                                        public void onInterstitialLoaded(MoPubInterstitial interstitial) {
                                            Log.e("Ads...", "isMpInter1Ready = true");
                                            isMpInter1Ready = true;
                                        }

                                        @Override
                                        public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {

                                        }

                                        @Override
                                        public void onInterstitialShown(MoPubInterstitial interstitial) {

                                        }

                                        @Override
                                        public void onInterstitialClicked(MoPubInterstitial interstitial) {

                                        }

                                        @Override
                                        public void onInterstitialDismissed(MoPubInterstitial interstitial) {

                                        }
                                    });
                                }
                            } else {
                                Log.e("Ads...", "Calling init for mp 1");
                                initializeMoPubSDK();
                            }
                        }
                        if (adsPrefernce.showmpInter2()) {
                            if (mpInter2Initilized) {
                                if (!isMpInter2Ready) {
                                    mpInterstitial2 = new MoPubInterstitial((Activity) this, adsPrefernce.mpInterId2());
                                    mpInterstitial2.load();
                                    mpInterstitial2.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                                        @Override
                                        public void onInterstitialLoaded(MoPubInterstitial interstitial) {
                                            isMpInter2Ready = true;
                                        }

                                        @Override
                                        public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {

                                        }

                                        @Override
                                        public void onInterstitialShown(MoPubInterstitial interstitial) {

                                        }

                                        @Override
                                        public void onInterstitialClicked(MoPubInterstitial interstitial) {

                                        }

                                        @Override
                                        public void onInterstitialDismissed(MoPubInterstitial interstitial) {

                                        }
                                    });
                                }
                            } else {
                                initializeMoPubSDK();
                            }
                        }
                    }
                }
            }
        }

    }

    public void showMixedInterAds(Callable<Void> mathodToFollow) {
        if (isNetworkAvailable(this)) {
            if (!adsPrefernce.planD()) {
                if (isAdsAvailable) {
                    if (adsPrefernce.planA()) {
                        if (adsPrefernce.showgInter1()) {
                            if (!isGInter1Shown) {
                                if (isGInter1Ready) {
                                    if (gInterstitial1.isLoaded() && gInterstitial1 != null) {
                                        try {
                                            mathodToFollow.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        gInterstitial1.show();
                                        gInterstitial1.setAdListener(new com.google.android.gms.ads.AdListener() {
                                            public void onAdClosed() {
                                                Log.e("Ads...", "g Inter 1 dismissed");
                                                MobileAds.initialize(getApplicationContext(), adsPrefernce.gAppId());
                                                gInterstitial1 = new com.google.android.gms.ads.InterstitialAd(BaseClass.this);
                                                gInterstitial1.setAdUnitId(adsPrefernce.gInterId1());
                                                gInterstitial1.loadAd(new AdRequest.Builder().build());
                                                gInterstitial1.setAdListener(new com.google.android.gms.ads.AdListener() {
                                                    @Override
                                                    public void onAdLoaded() {
                                                        super.onAdLoaded();
                                                        isGInter1Ready = true;
                                                    }
                                                });
                                            }

                                            @Override
                                            public void onAdOpened() {
                                                super.onAdOpened();
                                                Log.e("Ads...", "g Inter 1 shown");
                                                isGInter1Ready = false;
                                                isGInter1Shown = true;
                                            }

                                            @Override
                                            public void onAdFailedToLoad(LoadAdError loadAdError) {
                                                super.onAdFailedToLoad(loadAdError);
                                                showInhouseInterAd(new InhouseInterstitialListener() {
                                                    @Override
                                                    public void onAdShown() {

                                                    }

                                                    @Override
                                                    public void onAdDismissed() {
                                                        try {
                                                            mathodToFollow.call();
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }

                                                    }
                                                });

                                            }
                                        });
                                    } else {
                                        goToPlanA2(mathodToFollow);
                                    }
                                } else {
                                    goToPlanA2(mathodToFollow);
                                }
                            } else {
                                goToPlanA2(mathodToFollow);
                            }
                        } else {
                            goToPlanA2(mathodToFollow);
                        }
                    } else {
                        goToPlanB(mathodToFollow);
                    }
                } else {
                    showInhouseInterAd(new InhouseInterstitialListener() {
                        @Override
                        public void onAdShown() {

                        }

                        @Override
                        public void onAdDismissed() {
                            try {
                                mathodToFollow.call();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }else {
                try {
                    mathodToFollow.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else {
            try {
                mathodToFollow.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void goToPlanA2(Callable<Void> mathodToFollow) {

        if (adsPrefernce.showfbInter1()) {
            if (!isFbInter1Shown) {
                if (isFbInter1Ready) {
                    if (fbInterstitial1.isAdLoaded()) {
                        try {
                            mathodToFollow.call();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        fbInterstitial1.show();
                        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
                            @Override
                            public void onInterstitialDisplayed(Ad ad) {
                                isFbInter1Ready = false;
                                isFbInter1Shown = true;
                                Log.e("Ads...", "fb 1 displayed");
                            }

                            @Override
                            public void onInterstitialDismissed(Ad ad) {
                                Log.e("Ads...", "fb 1 dismissed");
                                AudienceNetworkAds.initialize(BaseClass.this);
                                fbInterstitial1 = new com.facebook.ads.InterstitialAd(BaseClass.this, adsPrefernce.fbInterId1());
                                InterstitialAdListener interstitialAdListener1 = new InterstitialAdListener() {
                                    @Override
                                    public void onInterstitialDisplayed(Ad ad) {
                                    }

                                    @Override
                                    public void onInterstitialDismissed(Ad ad) {

                                    }

                                    @Override
                                    public void onError(Ad ad, AdError adError) {

                                    }

                                    @Override
                                    public void onAdLoaded(Ad ad) {
                                        isFbInter1Ready = true;
                                    }

                                    @Override
                                    public void onAdClicked(Ad ad) {

                                    }

                                    @Override
                                    public void onLoggingImpression(Ad ad) {

                                    }
                                };
                                fbInterstitial1.loadAd(fbInterstitial1.buildLoadAdConfig().withAdListener(interstitialAdListener1).build());

                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {

                                showInhouseInterAd(new InhouseInterstitialListener() {
                                    @Override
                                    public void onAdShown() {

                                    }

                                    @Override
                                    public void onAdDismissed() {
                                        try {
                                            mathodToFollow.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {

                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        };
                        fbInterstitial1.buildLoadAdConfig().withAdListener(interstitialAdListener).build();
                    } else {
                        goToPlanB(mathodToFollow);
                    }
                } else {
                    goToPlanB(mathodToFollow);
                }
            } else {
                goToPlanB(mathodToFollow);
            }

        } else {
            goToPlanB(mathodToFollow);
        }

    }

    public void goToPlanB(Callable<Void> mathodToFollow) {
        if (adsPrefernce.planB()) {
            if (adsPrefernce.showanInter1()) {
                if (!isAnInter1Shown) {
                    if (isAnInter1Ready) {
                        if (an_interstitial_Ad1.isAdLoaded()) {
                            try {
                                mathodToFollow.call();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            an_interstitial_Ad1.showAd();
                            an_interstitial_Ad1.setOnAdClosedCallback(new OnAdClosed() {
                                @Override
                                public void onAdClosed() {
                                    Log.e("Ads...", "an Inter 1 dismissed");
                                    an_interstitial_Ad1 = new Interstitial(BaseClass.this, adsPrefernce.anAdId());
                                    an_interstitial_Ad1.loadAd();
                                    an_interstitial_Ad1.setOnAdLoadedCallback(new OnAdLoaded() {
                                        @Override
                                        public void adLoaded(String s, AppnextAdCreativeType appnextAdCreativeType) {
                                            isAnInter1Ready = true;
                                        }
                                    });
                                }
                            });
                            an_interstitial_Ad1.setOnAdOpenedCallback(new OnAdOpened() {
                                @Override
                                public void adOpened() {
                                    Log.e("Ads...", "an Inter 1 shown");
                                    isAnInter1Ready = false;
                                    isAnInter1Shown = true;
                                }
                            });
                            an_interstitial_Ad1.setOnAdErrorCallback(new OnAdError() {
                                @Override
                                public void adError(String s) {
                                    showInhouseInterAd(new InhouseInterstitialListener() {
                                        @Override
                                        public void onAdShown() {

                                        }

                                        @Override
                                        public void onAdDismissed() {
                                            try {
                                                mathodToFollow.call();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    });
                                }
                            });
                        } else {
                            goToPlanC(mathodToFollow);
                        }
                    } else {
                        goToPlanC(mathodToFollow);
                    }
                } else {
                    goToPlanC(mathodToFollow);
                }
            } else {
                goToPlanC(mathodToFollow);
            }
        } else {
            goToPlanC(mathodToFollow);
        }
    }

    public void goToPlanC(Callable<Void> mathodToFollow) {
        if (adsPrefernce.planC()) {
            if (adsPrefernce.showmpInter1()) {
                if (!isMpInter1Shown) {
                    if (mpInterstitial1 != null) {
                        if (mpInterstitial1.isReady()) {
                            try {
                                mathodToFollow.call();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            mpInterstitial1.show();
                            mpInterstitial1.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                                @Override
                                public void onInterstitialLoaded(MoPubInterstitial interstitial) {

                                }

                                @Override
                                public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
                                    showInhouseInterAd(new InhouseInterstitialListener() {
                                        @Override
                                        public void onAdShown() {

                                        }

                                        @Override
                                        public void onAdDismissed() {
                                            try {
                                                mathodToFollow.call();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    });
                                }

                                @Override
                                public void onInterstitialShown(MoPubInterstitial interstitial) {
                                    Log.e("Ads...", "Mp Inter 1 shown");
                                    isMpInter1Ready = false;
                                    isMpInter1Shown = true;
                                }

                                @Override
                                public void onInterstitialClicked(MoPubInterstitial interstitial) {

                                }

                                @Override
                                public void onInterstitialDismissed(MoPubInterstitial interstitial) {
                                    Log.e("Ads...", "Mp Inter 1 dismissed");
                                    if (mpInter1Initilized) {
                                        mpInterstitial1.load();
                                        mpInterstitial1.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                                            @Override
                                            public void onInterstitialLoaded(MoPubInterstitial interstitial) {
                                                isMpInter1Ready = true;
                                            }

                                            @Override
                                            public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {

                                            }

                                            @Override
                                            public void onInterstitialShown(MoPubInterstitial interstitial) {

                                            }

                                            @Override
                                            public void onInterstitialClicked(MoPubInterstitial interstitial) {

                                            }

                                            @Override
                                            public void onInterstitialDismissed(MoPubInterstitial interstitial) {

                                            }
                                        });
                                    } else {
                                        initializeMoPubSDK();
                                    }
                                }
                            });
                        } else {
                            goToPlanAI2(mathodToFollow);
                        }
                    } else {
                        goToPlanAI2(mathodToFollow);
                    }
                } else {
                    goToPlanAI2(mathodToFollow);
                }
            } else {
                goToPlanAI2(mathodToFollow);
            }

        } else {
            goToPlanAI2(mathodToFollow);
        }

    }

    public void goToPlanAI2(Callable<Void> mathodToFollow) {
        if (adsPrefernce.planA()) {
            if (adsPrefernce.showgInter2()) {
                if (!isGInter2Shown) {
                    if (isGInter2Ready) {
                        if (gInterstitial2.isLoaded() && gInterstitial2 != null) {
                            try {
                                mathodToFollow.call();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            gInterstitial2.show();
                            gInterstitial2.setAdListener(new com.google.android.gms.ads.AdListener() {
                                public void onAdClosed() {
                                    Log.e("Ads...", "g Inter 2 dismissed");
                                    MobileAds.initialize(getApplicationContext(), adsPrefernce.gAppId());
                                    gInterstitial2 = new com.google.android.gms.ads.InterstitialAd(BaseClass.this);
                                    gInterstitial2.setAdUnitId(adsPrefernce.gInterId2());
                                    gInterstitial2.loadAd(new AdRequest.Builder().build());
                                    gInterstitial2.setAdListener(new com.google.android.gms.ads.AdListener() {
                                        @Override
                                        public void onAdLoaded() {
                                            super.onAdLoaded();
                                            isGInter2Ready = true;
                                        }
                                    });
                                }

                                @Override
                                public void onAdOpened() {
                                    super.onAdOpened();
                                    Log.e("Ads...", "g Inter 2 shown");
                                    isGInter2Ready = false;
                                    isGInter2Shown = true;
                                }

                                @Override
                                public void onAdFailedToLoad(LoadAdError loadAdError) {
                                    super.onAdFailedToLoad(loadAdError);
                                    showInhouseInterAd(new InhouseInterstitialListener() {
                                        @Override
                                        public void onAdShown() {

                                        }

                                        @Override
                                        public void onAdDismissed() {
                                            try {
                                                mathodToFollow.call();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    });

                                }
                            });
                        } else {
                            goToPlanA2I2(mathodToFollow);
                        }
                    } else {
                        goToPlanA2I2(mathodToFollow);
                    }
                } else {
                    goToPlanA2I2(mathodToFollow);
                }
            } else {
                goToPlanA2I2(mathodToFollow);
            }
        } else {
            goToPlanBI2(mathodToFollow);
        }

    }

    public void goToPlanA2I2(Callable<Void> mathodToFollow) {

        if (adsPrefernce.showfbInter2()) {
            if (!isFbInter2Shown) {
                if (isFbInter2Ready) {
                    if (fbInterstitial2.isAdLoaded()) {
                        try {
                            mathodToFollow.call();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        fbInterstitial2.show();
                        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
                            @Override
                            public void onInterstitialDisplayed(Ad ad) {
                                Log.e("Ads...", "fb 2 displayed");
                                isFbInter2Ready = false;
                                isFbInter2Shown = true;
                                resetAllShownBoolean();
                            }

                            @Override
                            public void onInterstitialDismissed(Ad ad) {
                                Log.e("Ads...", "fb 2 dismissed");
                                AudienceNetworkAds.initialize(BaseClass.this);
                                fbInterstitial2 = new com.facebook.ads.InterstitialAd(BaseClass.this, adsPrefernce.fbInterId2());
                                InterstitialAdListener interstitialAdListener1 = new InterstitialAdListener() {
                                    @Override
                                    public void onInterstitialDisplayed(Ad ad) {

                                    }

                                    @Override
                                    public void onInterstitialDismissed(Ad ad) {

                                    }

                                    @Override
                                    public void onError(Ad ad, AdError adError) {

                                    }

                                    @Override
                                    public void onAdLoaded(Ad ad) {
                                        isFbInter2Ready = true;
                                    }

                                    @Override
                                    public void onAdClicked(Ad ad) {

                                    }

                                    @Override
                                    public void onLoggingImpression(Ad ad) {

                                    }
                                };
                                fbInterstitial2.loadAd(fbInterstitial2.buildLoadAdConfig().withAdListener(interstitialAdListener1).build());

                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {
                                showInhouseInterAd(new InhouseInterstitialListener() {
                                    @Override
                                    public void onAdShown() {

                                    }

                                    @Override
                                    public void onAdDismissed() {
                                        try {
                                            mathodToFollow.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }

                            @Override
                            public void onAdLoaded(Ad ad) {

                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        };
                        fbInterstitial2.buildLoadAdConfig().withAdListener(interstitialAdListener).build();
                    } else {
                        goToPlanBI2(mathodToFollow);
                    }
                } else {
                    goToPlanBI2(mathodToFollow);
                }
            } else {
                goToPlanBI2(mathodToFollow);
            }

        } else {
            goToPlanBI2(mathodToFollow);
        }

    }

    public void goToPlanBI2(Callable<Void> mathodToFollow) {
        if (adsPrefernce.planB()) {
            if (adsPrefernce.showanInter2()) {
                if (!isAnInter2Shown) {
                    if (isAnInter1Ready) {
                        if (an_interstitial_Ad1.isAdLoaded()) {
                            try {
                                mathodToFollow.call();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            an_interstitial_Ad1.showAd();
                            an_interstitial_Ad1.setOnAdClosedCallback(new OnAdClosed() {
                                @Override
                                public void onAdClosed() {
                                    Log.e("Ads...", "an Inter 2 dismissed");
                                    an_interstitial_Ad1 = new Interstitial(BaseClass.this, adsPrefernce.anAdId());
                                    an_interstitial_Ad1.loadAd();
                                    an_interstitial_Ad1.setOnAdLoadedCallback(new OnAdLoaded() {
                                        @Override
                                        public void adLoaded(String s, AppnextAdCreativeType appnextAdCreativeType) {
                                            isAnInter1Ready = true;
                                        }
                                    });
                                }
                            });
                            an_interstitial_Ad1.setOnAdOpenedCallback(new OnAdOpened() {
                                @Override
                                public void adOpened() {
                                    Log.e("Ads...", "an Inter 2 shown");
                                    isAnInter1Ready = false;
                                    isAnInter2Shown = true;
                                }
                            });
                            an_interstitial_Ad1.setOnAdErrorCallback(new OnAdError() {
                                @Override
                                public void adError(String s) {
                                    showInhouseInterAd(new InhouseInterstitialListener() {
                                        @Override
                                        public void onAdShown() {

                                        }

                                        @Override
                                        public void onAdDismissed() {
                                            try {
                                                mathodToFollow.call();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            });
                        } else {
                            goToPlanCI2(mathodToFollow);
                        }
                    } else {
                        goToPlanCI2(mathodToFollow);
                    }
                } else {
                    goToPlanCI2(mathodToFollow);
                }
            } else {
                goToPlanCI2(mathodToFollow);
            }
        } else {
            goToPlanCI2(mathodToFollow);
        }
    }

    public void goToPlanCI2(Callable<Void> mathodToFollow) {
        if (adsPrefernce.planC()) {
            if (adsPrefernce.showmpInter2()) {
                if (!isMpInter2Shown) {
                    if (mpInterstitial2 != null) {
                        if (mpInterstitial2.isReady()) {
                            try {
                                mathodToFollow.call();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            mpInterstitial2.show();
                            mpInterstitial2.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                                @Override
                                public void onInterstitialLoaded(MoPubInterstitial interstitial) {

                                }

                                @Override
                                public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
                                    showInhouseInterAd(new InhouseInterstitialListener() {
                                        @Override
                                        public void onAdShown() {

                                        }

                                        @Override
                                        public void onAdDismissed() {
                                            try {
                                                mathodToFollow.call();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }

                                @Override
                                public void onInterstitialShown(MoPubInterstitial interstitial) {
                                    Log.e("Ads...", "Mp Inter 2 shown");
                                    isMpInter2Ready = false;
                                    isMpInter2Shown = true;
                                    resetAllShownBoolean();
                                }

                                @Override
                                public void onInterstitialClicked(MoPubInterstitial interstitial) {

                                }

                                @Override
                                public void onInterstitialDismissed(MoPubInterstitial interstitial) {
                                    Log.e("Ads...", "mp Inter 2 dismissed");
                                    if (mpInter2Initilized) {
                                        mpInterstitial2.load();
                                        mpInterstitial2.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                                            @Override
                                            public void onInterstitialLoaded(MoPubInterstitial interstitial) {
                                                isMpInter2Ready = true;
                                            }

                                            @Override
                                            public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {

                                            }

                                            @Override
                                            public void onInterstitialShown(MoPubInterstitial interstitial) {

                                            }

                                            @Override
                                            public void onInterstitialClicked(MoPubInterstitial interstitial) {

                                            }

                                            @Override
                                            public void onInterstitialDismissed(MoPubInterstitial interstitial) {

                                            }
                                        });
                                    } else {
                                        initializeMoPubSDK();
                                    }
                                }
                            });
                        } else {
                            try {
                                mathodToFollow.call();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            resetAllShownBoolean();
                        }
                    } else {
                        try {
                            mathodToFollow.call();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        resetAllShownBoolean();
                    }
                } else {
                    try {
                        mathodToFollow.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    resetAllShownBoolean();
                }
            } else {
                try {
                    mathodToFollow.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                resetAllShownBoolean();
            }

        } else {
            try {
                mathodToFollow.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            resetAllShownBoolean();
        }

    }

    public void showMixedInterAdsOnClosed(Callable<Void> mathodToPerform) {
        if (isNetworkAvailable(this)) {
            if (!adsPrefernce.planD()) {
                if (isAdsAvailable) {
                    if (adsPrefernce.planA()) {
                        if (adsPrefernce.showgInter1()) {
                            if (!isGInter1Shown) {
                                if (isGInter1Ready) {
                                    if (gInterstitial1.isLoaded() && gInterstitial1 != null) {
                                        gInterstitial1.show();
                                        gInterstitial1.setAdListener(new com.google.android.gms.ads.AdListener() {
                                            public void onAdClosed() {
                                                try {
                                                    mathodToPerform.call();
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                                MobileAds.initialize(getApplicationContext(), adsPrefernce.gAppId());
                                                gInterstitial1 = new com.google.android.gms.ads.InterstitialAd(BaseClass.this);
                                                gInterstitial1.setAdUnitId(adsPrefernce.gInterId1());
                                                gInterstitial1.loadAd(new AdRequest.Builder().build());
                                                gInterstitial1.setAdListener(new com.google.android.gms.ads.AdListener() {
                                                    @Override
                                                    public void onAdLoaded() {
                                                        super.onAdLoaded();
                                                        isGInter1Ready = true;
                                                    }

                                                    @Override
                                                    public void onAdClosed() {
                                                        super.onAdClosed();
                                                    }
                                                });
                                            }

                                            @Override
                                            public void onAdOpened() {
                                                super.onAdOpened();
                                                isGInter1Ready = false;
                                                isGInter1Shown = true;
                                            }


                                            @Override
                                            public void onAdFailedToLoad(LoadAdError loadAdError) {
                                                super.onAdFailedToLoad(loadAdError);
                                                //Added Later
                                                isGInter1Shown = true;
                                                showInhouseInterAd(new InhouseInterstitialListener() {
                                                    @Override
                                                    public void onAdShown() {

                                                    }

                                                    @Override
                                                    public void onAdDismissed() {
                                                        try {
                                                            mathodToPerform.call();
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                });
                                            }
                                        });
                                    } else {
                                        goToPlanA2OnClosed(mathodToPerform);
                                    }
                                } else {
                                    goToPlanA2OnClosed(mathodToPerform);
                                }
                            } else {
                                goToPlanA2OnClosed(mathodToPerform);
                            }
                        } else {
                            goToPlanA2OnClosed(mathodToPerform);
                        }
                    } else {
                        goToPlanBOnClosed(mathodToPerform);
                    }
                } else {
                    showInhouseInterAd(new InhouseInterstitialListener() {
                        @Override
                        public void onAdShown() {

                        }

                        @Override
                        public void onAdDismissed() {
                            try {
                                mathodToPerform.call();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            } else {
                try {
                    mathodToPerform.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else {
            showInhouseInterAd(new InhouseInterstitialListener() {
                @Override
                public void onAdShown() {

                }

                @Override
                public void onAdDismissed() {
                    try {
                        mathodToPerform.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }

    }

    public void goToPlanA2OnClosed(Callable<Void> mathodToPerform) {
        if (adsPrefernce.showfbInter1()) {
            if (!isFbInter1Shown) {
                if (isFbInter1Ready) {
                    if (fbInterstitial1.isAdLoaded()) {
                        fbInterstitial1.show();
                        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
                            @Override
                            public void onInterstitialDisplayed(Ad ad) {
                                isFbInter1Ready = false;
                                isFbInter1Shown = true;
                            }

                            @Override
                            public void onInterstitialDismissed(Ad ad) {
                                try {
                                    mathodToPerform.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                AudienceNetworkAds.initialize(BaseClass.this);
                                fbInterstitial1 = new com.facebook.ads.InterstitialAd(BaseClass.this, adsPrefernce.fbInterId1());
                                InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
                                    @Override
                                    public void onInterstitialDisplayed(Ad ad) {

                                    }

                                    @Override
                                    public void onInterstitialDismissed(Ad ad) {

                                    }

                                    @Override
                                    public void onError(Ad ad, AdError adError) {

                                    }

                                    @Override
                                    public void onAdLoaded(Ad ad) {
                                        isFbInter1Ready = true;
                                    }

                                    @Override
                                    public void onAdClicked(Ad ad) {

                                    }

                                    @Override
                                    public void onLoggingImpression(Ad ad) {

                                    }
                                };
                                fbInterstitial1.loadAd(fbInterstitial1.buildLoadAdConfig().withAdListener(interstitialAdListener).build());

                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {
                                //Added Later
                                isFbInter1Shown = true;
                                showInhouseInterAd(new InhouseInterstitialListener() {
                                    @Override
                                    public void onAdShown() {

                                    }

                                    @Override
                                    public void onAdDismissed() {
                                        try {
                                            mathodToPerform.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {

                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        };
                        fbInterstitial1.buildLoadAdConfig().withAdListener(interstitialAdListener).build();
                    } else {
                        goToPlanBOnClosed(mathodToPerform);
                    }
                } else {
                    goToPlanBOnClosed(mathodToPerform);
                }
            } else {
                goToPlanBOnClosed(mathodToPerform);
            }

        } else {
            goToPlanBOnClosed(mathodToPerform);
        }

    }

    public void goToPlanBOnClosed(Callable<Void> mathodToPerform) {
        if (adsPrefernce.planB()) {
            if (adsPrefernce.showanInter1()) {
                if (!isAnInter1Shown) {
                    if (isAnInter1Ready) {
                        if (an_interstitial_Ad1.isAdLoaded()) {
                            an_interstitial_Ad1.showAd();
                            an_interstitial_Ad1.setOnAdClosedCallback(new OnAdClosed() {
                                @Override
                                public void onAdClosed() {
                                    try {
                                        mathodToPerform.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    an_interstitial_Ad1 = new Interstitial(BaseClass.this, adsPrefernce.anAdId());
                                    an_interstitial_Ad1.loadAd();
                                    an_interstitial_Ad1.setOnAdLoadedCallback(new OnAdLoaded() {
                                        @Override
                                        public void adLoaded(String s, AppnextAdCreativeType appnextAdCreativeType) {
                                            isAnInter1Ready = true;
                                        }
                                    });
                                }
                            });
                            an_interstitial_Ad1.setOnAdOpenedCallback(new OnAdOpened() {
                                @Override
                                public void adOpened() {
                                    isAnInter1Ready = false;
                                    isAnInter1Shown = true;
                                }
                            });
                            an_interstitial_Ad1.setOnAdErrorCallback(new OnAdError() {
                                @Override
                                public void adError(String s) {
                                    isAnInter1Shown = true;
                                    showInhouseInterAd(new InhouseInterstitialListener() {
                                        @Override
                                        public void onAdShown() {

                                        }

                                        @Override
                                        public void onAdDismissed() {
                                            try {
                                                mathodToPerform.call();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            });
                        } else {
                            goToPlanCOnClosed(mathodToPerform);
                        }
                    } else {
                        goToPlanCOnClosed(mathodToPerform);
                    }
                } else {
                    goToPlanCOnClosed(mathodToPerform);
                }
            } else {
                goToPlanCOnClosed(mathodToPerform);
            }

        } else {
            goToPlanCOnClosed(mathodToPerform);
        }
    }

    public void goToPlanCOnClosed(Callable<Void> mathodToPerform) {
        if (adsPrefernce.planC()) {
            if (adsPrefernce.showmpInter1()) {
                if (!isMpInter1Shown) {
                    if (mpInterstitial1 != null) {
                        if (mpInterstitial1.isReady()) {
                            mpInterstitial1.show();
                            mpInterstitial1.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                                @Override
                                public void onInterstitialLoaded(MoPubInterstitial interstitial) {

                                }

                                @Override
                                public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
                                    isMpInter1Shown = true;
                                    showInhouseInterAd(new InhouseInterstitialListener() {
                                        @Override
                                        public void onAdShown() {

                                        }

                                        @Override
                                        public void onAdDismissed() {
                                            try {
                                                mathodToPerform.call();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }

                                @Override
                                public void onInterstitialShown(MoPubInterstitial interstitial) {
                                    isMpInter1Ready = false;
                                    isMpInter1Shown = true;
                                    resetAllShownBoolean();
                                }

                                @Override
                                public void onInterstitialClicked(MoPubInterstitial interstitial) {

                                }

                                @Override
                                public void onInterstitialDismissed(MoPubInterstitial interstitial) {
                                    try {
                                        mathodToPerform.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    if (mpInter1Initilized) {
                                        mpInterstitial1.load();
                                        mpInterstitial1.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                                            @Override
                                            public void onInterstitialLoaded(MoPubInterstitial interstitial) {
                                                isMpInter1Ready = true;
                                            }

                                            @Override
                                            public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {

                                            }

                                            @Override
                                            public void onInterstitialShown(MoPubInterstitial interstitial) {

                                            }

                                            @Override
                                            public void onInterstitialClicked(MoPubInterstitial interstitial) {

                                            }

                                            @Override
                                            public void onInterstitialDismissed(MoPubInterstitial interstitial) {

                                            }
                                        });
                                    } else {
                                        initializeMoPubSDK();
                                    }
                                }
                            });
                        } else {
                            resetAllShownBoolean();
                            showInhouseInterAd(new InhouseInterstitialListener() {
                                @Override
                                public void onAdShown() {

                                }

                                @Override
                                public void onAdDismissed() {
                                    try {
                                        mathodToPerform.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    } else {
                        resetAllShownBoolean();
                        showInhouseInterAd(new InhouseInterstitialListener() {
                            @Override
                            public void onAdShown() {

                            }

                            @Override
                            public void onAdDismissed() {
                                try {
                                    mathodToPerform.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                } else {
                    resetAllShownBoolean();
                    showInhouseInterAd(new InhouseInterstitialListener() {
                        @Override
                        public void onAdShown() {

                        }

                        @Override
                        public void onAdDismissed() {
                            try {
                                mathodToPerform.call();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }
            } else {
                resetAllShownBoolean();
                showInhouseInterAd(new InhouseInterstitialListener() {
                    @Override
                    public void onAdShown() {

                    }

                    @Override
                    public void onAdDismissed() {
                        try {
                            mathodToPerform.call();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }

        } else {
            resetAllShownBoolean();
            showInhouseInterAd(new InhouseInterstitialListener() {
                @Override
                public void onAdShown() {

                }

                @Override
                public void onAdDismissed() {
                    try {
                        mathodToPerform.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }

    }

    public void resetAllShownBoolean() {
        isGInter1Shown = false;
        isFbInter1Shown = false;
        isAnInter1Shown = false;
        isMpInter1Shown = false;
        isUInter1Shown = false;
        isGInter2Shown = false;
        isFbInter2Shown = false;
        isAnInter2Shown = false;
        isMpInter2Shown = false;
        isUInter2Shown = false;
        Log.e("Ads...", "resetAllShownBoolean");

    }

    public void loadInterstitial1() {
        adsPrefernce = new AdsPrefernce(this);
        if (isNetworkAvailable(this)) {
            if (isAdsAvailable) {
                if (!adsPrefernce.planD()) {
                    if (!adsPrefernce.isMediationActive()) {
                        if (adsPrefernce.planA()) {
                            if (adsPrefernce.showgInter1()) {
                                MobileAds.initialize(getApplicationContext(), adsPrefernce.gAppId());
                                gInterstitial11 = new com.google.android.gms.ads.InterstitialAd(this);
                                gInterstitial11.setAdUnitId(adsPrefernce.gInterId1());

                                gInterstitial11.loadAd(new AdRequest.Builder().build());
                                gInterstitial11.setAdListener(new com.google.android.gms.ads.AdListener() {
                                    @Override
                                    public void onAdLoaded() {
                                        super.onAdLoaded();
                                    }
                                });

                            } else {
                                AudienceNetworkAds.initialize(this);
                                if (adsPrefernce.showfbInter1()) {
                                    fbInterstitial11 = new com.facebook.ads.InterstitialAd(this, adsPrefernce.fbInterId1());
                                    fbInterstitial11.loadAd();
                                }
                            }
                        } else if (adsPrefernce.planB()) {
                            Log.e("inLoad", "in plan b");
                            if (adsPrefernce.showanInter1()) {
                                Log.e("inLoad", "showaninter =  true");
                                an_interstitial_Ad11 = new Interstitial(this, adsPrefernce.anAdId());
                                Log.e("inLoad", "showaninterReady =  false");
                                an_interstitial_Ad11.loadAd();
                                an_interstitial_Ad11.setOnAdLoadedCallback(new OnAdLoaded() {
                                    @Override
                                    public void adLoaded(String s, AppnextAdCreativeType appnextAdCreativeType) {
                                        Log.e("inLoad", "showaninterReady =  true");
                                    }
                                });

                            }
                        } else if (adsPrefernce.planC()) {
                            if (adsPrefernce.showmpInter1()) {
                                Log.e("inLoad", "show mp inter true");
                                if (mpInter1Initilized) {
                                    Log.e("inLoad", "mp inter init true");
                                    mpInterstitial11 = new MoPubInterstitial((Activity) this, adsPrefernce.mpInterId1());

                                    Log.e("inLoad", "ismpinter1 ready = false");
                                    mpInterstitial11.load();
                                    mpInterstitial11.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                                        @Override
                                        public void onInterstitialLoaded(MoPubInterstitial interstitial) {
                                            Log.e("inLoad", "ismpinter1 ready = true");
                                        }

                                        @Override
                                        public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {

                                        }

                                        @Override
                                        public void onInterstitialShown(MoPubInterstitial interstitial) {

                                        }

                                        @Override
                                        public void onInterstitialClicked(MoPubInterstitial interstitial) {

                                        }

                                        @Override
                                        public void onInterstitialDismissed(MoPubInterstitial interstitial) {

                                        }
                                    });

                                } else {
                                    Log.e("inLoad", "mp inter init false");
                                    initializeMoPubSDK();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void loadInterstitial2() {
        adsPrefernce = new AdsPrefernce(this);
        if (isNetworkAvailable(this)) {
            if (isAdsAvailable) {
                if (!adsPrefernce.planD()) {
                    if (!adsPrefernce.isMediationActive()) {
                        if (adsPrefernce.planA()) {
                            if (adsPrefernce.showgInter2()) {
                                Log.e("inter2", "showgInter2");
                                MobileAds.initialize(getApplicationContext(), adsPrefernce.gAppId());
                                gInterstitial22 = new com.google.android.gms.ads.InterstitialAd(this);
                                gInterstitial22.setAdUnitId(adsPrefernce.gInterId2());

                                Log.e("inter2", "isGInter2Ready false");
                                gInterstitial22.loadAd(new AdRequest.Builder().build());
                                gInterstitial22.setAdListener(new com.google.android.gms.ads.AdListener() {
                                    @Override
                                    public void onAdLoaded() {
                                        super.onAdLoaded();
                                        Log.e("inter2", "isGInter2Ready = true");
                                    }
                                });
                            } else if (adsPrefernce.showfbInter2()) {
                                AudienceNetworkAds.initialize(this);
                                fbInterstitial22 = new com.facebook.ads.InterstitialAd(this, adsPrefernce.fbInterId2());
                                fbInterstitial22.loadAd();
                            }
                        } else if (adsPrefernce.planB()) {
                            if (adsPrefernce.showanInter1()) {
                                an_interstitial_Ad11 = new Interstitial(this, adsPrefernce.anAdId());
                                an_interstitial_Ad11.loadAd();
                                an_interstitial_Ad11.setOnAdLoadedCallback(new OnAdLoaded() {
                                    @Override
                                    public void adLoaded(String s, AppnextAdCreativeType appnextAdCreativeType) {
                                        Log.e("inLoad", "showaninterReady =  true");
                                    }
                                });

                            }
                        } else if (adsPrefernce.planC()) {
                            if (adsPrefernce.showmpInter2()) {
                                Log.e("inLoad", "show mp inter 2 true");
                                if (mpInter2Initilized) {
                                    Log.e("inLoad", "mp inter 2 init true");
                                    mpInterstitial22 = new MoPubInterstitial((Activity) this, adsPrefernce.mpInterId2());
                                    Log.e("inLoad", "ismpinter2 ready = false");
                                    mpInterstitial22.load();
                                    mpInterstitial22.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                                        @Override
                                        public void onInterstitialLoaded(MoPubInterstitial interstitial) {
                                            Log.e("inLoad", "ismpinter1 ready = true");
                                        }

                                        @Override
                                        public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {

                                        }

                                        @Override
                                        public void onInterstitialShown(MoPubInterstitial interstitial) {

                                        }

                                        @Override
                                        public void onInterstitialClicked(MoPubInterstitial interstitial) {

                                        }

                                        @Override
                                        public void onInterstitialDismissed(MoPubInterstitial interstitial) {

                                        }
                                    });

                                } else {
                                    Log.e("inLoad", "mp inter 2 init false");
                                    initializeMoPubSDK();
                                }
                            }
                        }
                    }
                }

            }
        }

    }


    public void InterstitialAd2(final boolean loadOnClosed, Callable<Void> mathodToFollow) {
        if (isNetworkAvailable(this)) {
            if (isAdsAvailable) {
                if (adsPrefernce.planA()) {
                    if (adsPrefernce.showgInter2()) {
                        if (gInterstitial22.isLoaded() && gInterstitial22 != null) {
                            try {
                                mathodToFollow.call();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            gInterstitial22.show();
                            gInterstitial22.setAdListener(new com.google.android.gms.ads.AdListener() {
                                public void onAdClosed() {
                                    if (loadOnClosed) {
//                                            loadInterstitial2();
                                        Log.e("inter2", "isGInter2Ready false");
                                        gInterstitial22.loadAd(new AdRequest.Builder().build());
                                        gInterstitial22.setAdListener(new com.google.android.gms.ads.AdListener() {
                                            @Override
                                            public void onAdLoaded() {
                                                super.onAdLoaded();
                                                Log.e("inter2", "isGInter2Ready = true");
                                            }
                                        });


                                    }
                                }

                                @Override
                                public void onAdOpened() {
                                    super.onAdOpened();
                                    Log.e("Inter2", "g2");
                                }

                                @Override
                                public void onAdFailedToLoad(LoadAdError loadAdError) {
                                    super.onAdFailedToLoad(loadAdError);
                                    showInhouseInterAd(new InhouseInterstitialListener() {
                                        @Override
                                        public void onAdShown() {

                                        }

                                        @Override
                                        public void onAdDismissed() {
                                            try {
                                                mathodToFollow.call();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    });

                                }
                            });
                        } else {
                            showInhouseInterAd(new InhouseInterstitialListener() {
                                @Override
                                public void onAdShown() {

                                }

                                @Override
                                public void onAdDismissed() {
                                    try {
                                        mathodToFollow.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }
                    } else if (adsPrefernce.showfbInter2()) {
                        if (fbInterstitial22.isAdLoaded()) {
                            if (fbInterstitial22.isAdLoaded()) {
                                try {
                                    mathodToFollow.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                fbInterstitial22.show();
                                InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
                                    @Override
                                    public void onInterstitialDisplayed(Ad ad) {

                                    }

                                    @Override
                                    public void onInterstitialDismissed(Ad ad) {
                                        if (loadOnClosed) {
                                            loadInterstitial2();
                                        }

                                    }

                                    @Override
                                    public void onError(Ad ad, AdError adError) {
                                        showInhouseInterAd(new InhouseInterstitialListener() {
                                            @Override
                                            public void onAdShown() {

                                            }

                                            @Override
                                            public void onAdDismissed() {
                                                try {
                                                    mathodToFollow.call();
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }

                                            }
                                        });
                                    }

                                    @Override
                                    public void onAdLoaded(Ad ad) {

                                    }

                                    @Override
                                    public void onAdClicked(Ad ad) {

                                    }

                                    @Override
                                    public void onLoggingImpression(Ad ad) {

                                    }
                                };
                                fbInterstitial22.buildLoadAdConfig().withAdListener(interstitialAdListener).build();
                            } else {
                                showInhouseInterAd(new InhouseInterstitialListener() {
                                    @Override
                                    public void onAdShown() {

                                    }

                                    @Override
                                    public void onAdDismissed() {
                                        try {
                                            mathodToFollow.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });
                            }
                        } else {
                            showInhouseInterAd(new InhouseInterstitialListener() {
                                @Override
                                public void onAdShown() {

                                }

                                @Override
                                public void onAdDismissed() {
                                    try {
                                        mathodToFollow.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }
                    } else {
                        showInhouseInterAd(new InhouseInterstitialListener() {
                            @Override
                            public void onAdShown() {

                            }

                            @Override
                            public void onAdDismissed() {
                                try {
                                    mathodToFollow.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }

                } else if (adsPrefernce.planB()) {
                    if (adsPrefernce.showanInter1()) {
                        if (an_interstitial_Ad11.isAdLoaded()) {
                            try {
                                mathodToFollow.call();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            an_interstitial_Ad11.showAd();
                            an_interstitial_Ad11.setOnAdClosedCallback(new OnAdClosed() {
                                @Override
                                public void onAdClosed() {
                                    if (loadOnClosed) {
                                        loadInterstitial2();

                                    }
                                }
                            });
                            an_interstitial_Ad11.setOnAdOpenedCallback(new OnAdOpened() {
                                @Override
                                public void adOpened() {
                                    Log.e("Inter2", "an2");
                                }
                            });
                        } else {
                            showInhouseInterAd(new InhouseInterstitialListener() {
                                @Override
                                public void onAdShown() {

                                }

                                @Override
                                public void onAdDismissed() {
                                    try {
                                        mathodToFollow.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }

                    } else {
                        showInhouseInterAd(new InhouseInterstitialListener() {
                            @Override
                            public void onAdShown() {

                            }

                            @Override
                            public void onAdDismissed() {
                                try {
                                    mathodToFollow.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
                } else if (adsPrefernce.planC()) {
                    if (adsPrefernce.showmpInter2()) {
                        if (mpInterstitial22 != null) {
                            if (mpInterstitial22.isReady()) {
                                try {
                                    mathodToFollow.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                mpInterstitial22.show();
                                mpInterstitial22.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                                    @Override
                                    public void onInterstitialLoaded(MoPubInterstitial interstitial) {

                                    }

                                    @Override
                                    public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
                                        showInhouseInterAd(new InhouseInterstitialListener() {
                                            @Override
                                            public void onAdShown() {

                                            }

                                            @Override
                                            public void onAdDismissed() {
                                                try {
                                                    mathodToFollow.call();
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }

                                            }
                                        });
                                    }

                                    @Override
                                    public void onInterstitialShown(MoPubInterstitial interstitial) {
                                        Log.e("Inter2", "mp2");
                                    }

                                    @Override
                                    public void onInterstitialClicked(MoPubInterstitial interstitial) {

                                    }

                                    @Override
                                    public void onInterstitialDismissed(MoPubInterstitial interstitial) {
                                        if (loadOnClosed) {
                                            mpInterstitial22.load();
                                            mpInterstitial22.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                                                @Override
                                                public void onInterstitialLoaded(MoPubInterstitial interstitial) {

                                                }

                                                @Override
                                                public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {

                                                }

                                                @Override
                                                public void onInterstitialShown(MoPubInterstitial interstitial) {

                                                }

                                                @Override
                                                public void onInterstitialClicked(MoPubInterstitial interstitial) {

                                                }

                                                @Override
                                                public void onInterstitialDismissed(MoPubInterstitial interstitial) {

                                                }
                                            });
                                        }

                                    }
                                });

                            } else {
                                showInhouseInterAd(new InhouseInterstitialListener() {
                                    @Override
                                    public void onAdShown() {

                                    }

                                    @Override
                                    public void onAdDismissed() {
                                        try {
                                            mathodToFollow.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });
                            }
                        } else {
                            showInhouseInterAd(new InhouseInterstitialListener() {
                                @Override
                                public void onAdShown() {

                                }

                                @Override
                                public void onAdDismissed() {
                                    try {
                                        mathodToFollow.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }
                    } else {
                        showInhouseInterAd(new InhouseInterstitialListener() {
                            @Override
                            public void onAdShown() {

                            }

                            @Override
                            public void onAdDismissed() {
                                try {
                                    mathodToFollow.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
                } else {
                    showInhouseInterAd(new InhouseInterstitialListener() {
                        @Override
                        public void onAdShown() {

                        }

                        @Override
                        public void onAdDismissed() {
                            try {
                                mathodToFollow.call();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            } else {
                showInhouseInterAd(new InhouseInterstitialListener() {
                    @Override
                    public void onAdShown() {

                    }

                    @Override
                    public void onAdDismissed() {
                        try {
                            mathodToFollow.call();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        } else {
            showInhouseInterAd(new InhouseInterstitialListener() {
                @Override
                public void onAdShown() {

                }

                @Override
                public void onAdDismissed() {
                    try {
                        mathodToFollow.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }


    public void InterstitialAd1(final boolean loadOnClosed, Callable<Void> mathodToFollow) {
        if (isNetworkAvailable(this)) {
            if (isAdsAvailable) {
                if (adsPrefernce.planA()) {
                    if (adsPrefernce.showgInter1()) {
                        if (gInterstitial11.isLoaded() && gInterstitial11 != null) {
                            try {
                                mathodToFollow.call();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            gInterstitial11.show();
                            gInterstitial11.setAdListener(new com.google.android.gms.ads.AdListener() {
                                public void onAdClosed() {
                                    if (loadOnClosed) {
                                        loadInterstitial1();
                                    }
                                }

                                @Override
                                public void onAdOpened() {
                                    super.onAdOpened();
                                }
                            });
                        } else {
                            showInhouseInterAd(new InhouseInterstitialListener() {
                                @Override
                                public void onAdShown() {

                                }

                                @Override
                                public void onAdDismissed() {
                                    try {
                                        mathodToFollow.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }

                    } else {
                        if (adsPrefernce.showfbInter1()) {
                            if (fbInterstitial11.isAdLoaded()) {
                                try {
                                    mathodToFollow.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                fbInterstitial11.show();
                                InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
                                    @Override
                                    public void onInterstitialDisplayed(Ad ad) {

                                    }

                                    @Override
                                    public void onInterstitialDismissed(Ad ad) {
                                        if (loadOnClosed) {
                                            loadInterstitial1();
                                        }

                                    }

                                    @Override
                                    public void onError(Ad ad, AdError adError) {
                                        showInhouseInterAd(new InhouseInterstitialListener() {
                                            @Override
                                            public void onAdShown() {

                                            }

                                            @Override
                                            public void onAdDismissed() {
                                                try {
                                                    mathodToFollow.call();
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }

                                            }
                                        });
                                    }

                                    @Override
                                    public void onAdLoaded(Ad ad) {

                                    }

                                    @Override
                                    public void onAdClicked(Ad ad) {

                                    }

                                    @Override
                                    public void onLoggingImpression(Ad ad) {

                                    }
                                };
                                fbInterstitial11.buildLoadAdConfig().withAdListener(interstitialAdListener).build();
                            } else {
                                showInhouseInterAd(new InhouseInterstitialListener() {
                                    @Override
                                    public void onAdShown() {

                                    }

                                    @Override
                                    public void onAdDismissed() {
                                        try {
                                            mathodToFollow.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });
                            }
                        } else {
                            showInhouseInterAd(new InhouseInterstitialListener() {
                                @Override
                                public void onAdShown() {

                                }

                                @Override
                                public void onAdDismissed() {
                                    try {
                                        mathodToFollow.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }
                    }
                } else if (adsPrefernce.planB()) {
                    if (adsPrefernce.showanInter1()) {

                        if (an_interstitial_Ad11.isAdLoaded()) {
                            try {
                                mathodToFollow.call();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            an_interstitial_Ad11.showAd();
                            an_interstitial_Ad11.setOnAdClosedCallback(new OnAdClosed() {
                                @Override
                                public void onAdClosed() {
                                    if (loadOnClosed) {
                                        loadInterstitial1();
                                    }
                                }
                            });
                            an_interstitial_Ad11.setOnAdOpenedCallback(new OnAdOpened() {
                                @Override
                                public void adOpened() {
                                }
                            });
                        } else {
                            showInhouseInterAd(new InhouseInterstitialListener() {
                                @Override
                                public void onAdShown() {

                                }

                                @Override
                                public void onAdDismissed() {
                                    try {
                                        mathodToFollow.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }

                    } else {
                        showInhouseInterAd(new InhouseInterstitialListener() {
                            @Override
                            public void onAdShown() {

                            }

                            @Override
                            public void onAdDismissed() {
                                try {
                                    mathodToFollow.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
                } else if (adsPrefernce.planC()) {
                    if (adsPrefernce.showmpInter1()) {
                        if (mpInterstitial11 != null) {
                            if (mpInterstitial11.isReady()) {
                                try {
                                    mathodToFollow.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                mpInterstitial11.show();
                                mpInterstitial11.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                                    @Override
                                    public void onInterstitialLoaded(MoPubInterstitial interstitial) {

                                    }

                                    @Override
                                    public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {

                                    }

                                    @Override
                                    public void onInterstitialShown(MoPubInterstitial interstitial) {
                                    }

                                    @Override
                                    public void onInterstitialClicked(MoPubInterstitial interstitial) {

                                    }

                                    @Override
                                    public void onInterstitialDismissed(MoPubInterstitial interstitial) {
                                        if (loadOnClosed) {
                                            mpInterstitial11.load();
                                        }

                                    }
                                });
                            } else {
                                showInhouseInterAd(new InhouseInterstitialListener() {
                                    @Override
                                    public void onAdShown() {

                                    }

                                    @Override
                                    public void onAdDismissed() {
                                        try {
                                            mathodToFollow.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });
                            }
                        } else {
                            showInhouseInterAd(new InhouseInterstitialListener() {
                                @Override
                                public void onAdShown() {

                                }

                                @Override
                                public void onAdDismissed() {
                                    try {
                                        mathodToFollow.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }
                    }
                    else {
                        showInhouseInterAd(new InhouseInterstitialListener() {
                            @Override
                            public void onAdShown() {

                            }

                            @Override
                            public void onAdDismissed() {
                                try {
                                    mathodToFollow.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
                } else {
                    showInhouseInterAd(new InhouseInterstitialListener() {
                        @Override
                        public void onAdShown() {

                        }

                        @Override
                        public void onAdDismissed() {
                            try {
                                mathodToFollow.call();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            } else {
                showInhouseInterAd(new InhouseInterstitialListener() {
                    @Override
                    public void onAdShown() {

                    }

                    @Override
                    public void onAdDismissed() {
                        try {
                            mathodToFollow.call();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        } else {
            showInhouseInterAd(new InhouseInterstitialListener() {
                @Override
                public void onAdShown() {

                }

                @Override
                public void onAdDismissed() {
                    try {
                        mathodToFollow.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }

    public void showInterstitial1(final boolean loadOnClosed,
                                  final Callable<Void> mathodToFollow) {
        if (adsPrefernce.showLoading()) {
            proceedWithDelay(1000, "Showing Ad...", new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    if (!adsPrefernce.planD()) {
                        if (!adsPrefernce.isMediationActive()) {
                            InterstitialAd1(loadOnClosed, mathodToFollow);
                        } else {
                            showMixedInterAds(mathodToFollow);
                        }
                    } else {
                        try {
                            mathodToFollow.call();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return null;
                }
            });
        } else {
            if (!adsPrefernce.planD()) {
                if (!adsPrefernce.isMediationActive()) {
                    InterstitialAd1(loadOnClosed, mathodToFollow);
                } else {
                    showMixedInterAds(mathodToFollow);
                }
            } else {
                try {
                    mathodToFollow.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void showInter1AdonClosed(final Callable<Void> methodParam) {
        if (isNetworkAvailable(this)) {
            adsPrefernce = new AdsPrefernce(this);
            if (!adsPrefernce.isMediationActive()) {
                if (isAdsAvailable) {
                    if (adsPrefernce.planA()) {
                        if (adsPrefernce.showgInter1()) {
                            if (gInterstitial11.isLoaded()) {
                                gInterstitial11.show();
                                gInterstitial11.setAdListener(new com.google.android.gms.ads.AdListener() {
                                    @Override
                                    public void onAdLoaded() {
                                    }

                                    @Override
                                    public void onAdClosed() {
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        gInterstitial11.loadAd(new AdRequest.Builder().build());
                                    }

                                    @Override
                                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                                        super.onAdFailedToLoad(loadAdError);
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        gInterstitial11.loadAd(new AdRequest.Builder().build());  }
                                });
                            } else {
                                showInhouseInterAd(new InhouseInterstitialListener() {
                                    @Override
                                    public void onAdShown() {

                                    }

                                    @Override
                                    public void onAdDismissed() {
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }

                        } else {
                            if (adsPrefernce.showfbInter1()) {
                                if (fbInterstitial11.isAdLoaded()) {
                                    fbInterstitial11.show();
                                    InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
                                        @Override
                                        public void onInterstitialDisplayed(Ad ad) {

                                        }

                                        @Override
                                        public void onInterstitialDismissed(Ad ad) {
                                            try {
                                                methodParam.call();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            loadInterstitial1();
                                        }

                                        @Override
                                        public void onError(Ad ad, AdError adError) {
                                            try {
                                                methodParam.call();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            loadInterstitial1();
                                        }

                                        @Override
                                        public void onAdLoaded(Ad ad) {

                                        }

                                        @Override
                                        public void onAdClicked(Ad ad) {

                                        }

                                        @Override
                                        public void onLoggingImpression(Ad ad) {

                                        }
                                    };
                                    fbInterstitial11.buildLoadAdConfig().withAdListener(interstitialAdListener).build();

                                } else {
                                    showInhouseInterAd(new InhouseInterstitialListener() {
                                        @Override
                                        public void onAdShown() {

                                        }

                                        @Override
                                        public void onAdDismissed() {
                                            try {
                                                methodParam.call();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }

                            } else {
                                showInhouseInterAd(new InhouseInterstitialListener() {
                                    @Override
                                    public void onAdShown() {

                                    }

                                    @Override
                                    public void onAdDismissed() {
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        }
                    } else if (adsPrefernce.planB()) {
                        if (adsPrefernce.showanInter1()) {
                            if (an_interstitial_Ad11.isAdLoaded()) {
                                an_interstitial_Ad11.showAd();
                                an_interstitial_Ad11.setOnAdClosedCallback(new OnAdClosed() {
                                    @Override
                                    public void onAdClosed() {
                                        loadInterstitial1();
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }

                                });
                                an_interstitial_Ad11.setOnAdErrorCallback(new OnAdError() {
                                    @Override
                                    public void adError(String s) {
                                        loadInterstitial1();
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                an_interstitial_Ad11.setOnAdOpenedCallback(new OnAdOpened() {
                                    @Override
                                    public void adOpened() {
                                    }
                                });
                            } else {
                                showInhouseInterAd(new InhouseInterstitialListener() {
                                    @Override
                                    public void onAdShown() {

                                    }

                                    @Override
                                    public void onAdDismissed() {
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        } else {
                            showInhouseInterAd(new InhouseInterstitialListener() {
                                @Override
                                public void onAdShown() {

                                }

                                @Override
                                public void onAdDismissed() {
                                    try {
                                        methodParam.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }

                    } else if (adsPrefernce.planC()) {
                        if (adsPrefernce.showmpInter1()) {
                            if (mpInterstitial11 != null) {
                                if (mpInterstitial11.isReady()) {
                                    mpInterstitial11.show();
                                    mpInterstitial11.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                                        @Override
                                        public void onInterstitialLoaded(MoPubInterstitial interstitial) {

                                        }

                                        @Override
                                        public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
                                            try {
                                                methodParam.call();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onInterstitialShown(MoPubInterstitial interstitial) {
                                        }

                                        @Override
                                        public void onInterstitialClicked(MoPubInterstitial interstitial) {

                                        }

                                        @Override
                                        public void onInterstitialDismissed(MoPubInterstitial interstitial) {
                                                mpInterstitial11.load();
                                            try {
                                                methodParam.call();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    });
                                } else {
                                    showInhouseInterAd(new InhouseInterstitialListener() {
                                        @Override
                                        public void onAdShown() {

                                        }

                                        @Override
                                        public void onAdDismissed() {
                                            try {
                                                methodParam.call();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            } else {
                                showInhouseInterAd(new InhouseInterstitialListener() {
                                    @Override
                                    public void onAdShown() {

                                    }

                                    @Override
                                    public void onAdDismissed() {
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });
                            }
                        } else {
                            showInhouseInterAd(new InhouseInterstitialListener() {
                                @Override
                                public void onAdShown() {

                                }

                                @Override
                                public void onAdDismissed() {
                                    try {
                                        methodParam.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    } else {
                        showInhouseInterAd(new InhouseInterstitialListener() {
                            @Override
                            public void onAdShown() {

                            }

                            @Override
                            public void onAdDismissed() {
                                try {
                                    methodParam.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                } else {
                    showInhouseInterAd(new InhouseInterstitialListener() {
                        @Override
                        public void onAdShown() {

                        }

                        @Override
                        public void onAdDismissed() {
                            try {
                                methodParam.call();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }

            } else {
                showMixedInterAdsOnClosed(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        try {
                            methodParam.call();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                });
            }

        } else {
            showInhouseInterAd(new InhouseInterstitialListener() {
                @Override
                public void onAdShown() {

                }

                @Override
                public void onAdDismissed() {
                    try {
                        methodParam.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void showInter2AdonClosed(final Callable<Void> methodParam) {
        if (isNetworkAvailable(this)) {
            adsPrefernce = new AdsPrefernce(this);
            if (!adsPrefernce.isMediationActive()) {
                if (isAdsAvailable) {
                    if (adsPrefernce.planA()) {
                        if (adsPrefernce.showgInter2()) {
                            if (gInterstitial22.isLoaded()) {
                                gInterstitial22.show();
                                gInterstitial22.setAdListener(new com.google.android.gms.ads.AdListener() {
                                    @Override
                                    public void onAdLoaded() {
                                    }

                                    @Override
                                    public void onAdClosed() {
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        gInterstitial22.loadAd(new AdRequest.Builder().build());
                                    }

                                    @Override
                                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                                        super.onAdFailedToLoad(loadAdError);
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        gInterstitial22.loadAd(new AdRequest.Builder().build());  }
                                });
                            } else {
                                showInhouseInterAd(new InhouseInterstitialListener() {
                                    @Override
                                    public void onAdShown() {

                                    }

                                    @Override
                                    public void onAdDismissed() {
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }

                        } else {
                            if (adsPrefernce.showfbInter2()) {
                                if (fbInterstitial22.isAdLoaded()) {
                                    fbInterstitial22.show();
                                    InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
                                        @Override
                                        public void onInterstitialDisplayed(Ad ad) {

                                        }

                                        @Override
                                        public void onInterstitialDismissed(Ad ad) {
                                            try {
                                                methodParam.call();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            loadInterstitial2();
                                        }

                                        @Override
                                        public void onError(Ad ad, AdError adError) {
                                            try {
                                                methodParam.call();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            loadInterstitial2();
                                        }

                                        @Override
                                        public void onAdLoaded(Ad ad) {

                                        }

                                        @Override
                                        public void onAdClicked(Ad ad) {

                                        }

                                        @Override
                                        public void onLoggingImpression(Ad ad) {

                                        }
                                    };
                                    fbInterstitial22.buildLoadAdConfig().withAdListener(interstitialAdListener).build();

                                } else {
                                    showInhouseInterAd(new InhouseInterstitialListener() {
                                        @Override
                                        public void onAdShown() {

                                        }

                                        @Override
                                        public void onAdDismissed() {
                                            try {
                                                methodParam.call();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }

                            } else {
                                showInhouseInterAd(new InhouseInterstitialListener() {
                                    @Override
                                    public void onAdShown() {

                                    }

                                    @Override
                                    public void onAdDismissed() {
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        }
                    } else if (adsPrefernce.planB()) {
                        if (adsPrefernce.showanInter1()) {
                            if (an_interstitial_Ad11.isAdLoaded()) {
                                an_interstitial_Ad11.showAd();
                                an_interstitial_Ad11.setOnAdClosedCallback(new OnAdClosed() {
                                    @Override
                                    public void onAdClosed() {
                                        loadInterstitial2();
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }

                                });
                                an_interstitial_Ad11.setOnAdErrorCallback(new OnAdError() {
                                    @Override
                                    public void adError(String s) {
                                        loadInterstitial2();
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                an_interstitial_Ad11.setOnAdOpenedCallback(new OnAdOpened() {
                                    @Override
                                    public void adOpened() {
                                    }
                                });
                            } else {
                                showInhouseInterAd(new InhouseInterstitialListener() {
                                    @Override
                                    public void onAdShown() {

                                    }

                                    @Override
                                    public void onAdDismissed() {
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        } else {
                            showInhouseInterAd(new InhouseInterstitialListener() {
                                @Override
                                public void onAdShown() {

                                }

                                @Override
                                public void onAdDismissed() {
                                    try {
                                        methodParam.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }

                    } else if (adsPrefernce.planC()) {
                        if (adsPrefernce.showmpInter2()) {
                            if (mpInterstitial22 != null) {
                                if (mpInterstitial22.isReady()) {
                                    mpInterstitial22.show();
                                    mpInterstitial22.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                                        @Override
                                        public void onInterstitialLoaded(MoPubInterstitial interstitial) {

                                        }

                                        @Override
                                        public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
                                            try {
                                                methodParam.call();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onInterstitialShown(MoPubInterstitial interstitial) {
                                        }

                                        @Override
                                        public void onInterstitialClicked(MoPubInterstitial interstitial) {

                                        }

                                        @Override
                                        public void onInterstitialDismissed(MoPubInterstitial interstitial) {
                                            mpInterstitial22.load();
                                            try {
                                                methodParam.call();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    });
                                } else {
                                    showInhouseInterAd(new InhouseInterstitialListener() {
                                        @Override
                                        public void onAdShown() {

                                        }

                                        @Override
                                        public void onAdDismissed() {
                                            try {
                                                methodParam.call();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            } else {
                                showInhouseInterAd(new InhouseInterstitialListener() {
                                    @Override
                                    public void onAdShown() {

                                    }

                                    @Override
                                    public void onAdDismissed() {
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });
                            }
                        } else {
                            showInhouseInterAd(new InhouseInterstitialListener() {
                                @Override
                                public void onAdShown() {

                                }

                                @Override
                                public void onAdDismissed() {
                                    try {
                                        methodParam.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    } else {
                        showInhouseInterAd(new InhouseInterstitialListener() {
                            @Override
                            public void onAdShown() {

                            }

                            @Override
                            public void onAdDismissed() {
                                try {
                                    methodParam.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                } else {
                    showInhouseInterAd(new InhouseInterstitialListener() {
                        @Override
                        public void onAdShown() {

                        }

                        @Override
                        public void onAdDismissed() {
                            try {
                                methodParam.call();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            } else {
                showMixedInterAdsOnClosed(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        try {
                            methodParam.call();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                });
            }

        } else {
            showInhouseInterAd(new InhouseInterstitialListener() {
                @Override
                public void onAdShown() {

                }

                @Override
                public void onAdDismissed() {
                    try {
                        methodParam.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    public void showInterstitial2(final boolean loadOnClosed,
                                  final Callable<Void> mathodToFollow) {
        if (adsPrefernce.showLoading()) {
            proceedWithDelay(1000, "Showing Ad...", new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    if (!adsPrefernce.planD()) {
                        if (!adsPrefernce.isMediationActive()) {
                            InterstitialAd2(loadOnClosed, mathodToFollow);
                        } else {
                            showMixedInterAds(mathodToFollow);
                        }
                    } else {
                        try {
                            mathodToFollow.call();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return null;
                }
            });
        } else {
            if (!adsPrefernce.planD()) {
                if (!adsPrefernce.isMediationActive()) {
                    InterstitialAd2(loadOnClosed, mathodToFollow);
                } else {
                    showMixedInterAds(mathodToFollow);
                }
            } else {
                try {
                    mathodToFollow.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void proceedWithDelay(int delay, String messageText,
                                 final Callable<Void> mathodToProceed) {

        progressDialog.setMessage(messageText);
        progressDialog.show();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                try {
                    mathodToProceed.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, delay);
    }

    public void proceedWithDelay(int delay, final Callable<Void> mathodToProceed) {

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    mathodToProceed.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, delay);
    }

    public void loadInterAdsDialog(int adToLoad) {
        adsPrefernce = new AdsPrefernce(this);
        if (isNetworkAvailable(this)) {
            if (isAdsAvailable) {

                MobileAds.initialize(getApplicationContext(), adsPrefernce.gAppId());
                if (adToLoad == 1 && adsPrefernce.showgInter1()) {
                    gDialogInterstitial = new com.google.android.gms.ads.InterstitialAd(this);
                    gDialogInterstitial.setAdUnitId(adsPrefernce.gInterId1());
                    gDialogInterstitial.loadAd(new AdRequest.Builder().build());
                } else if (adToLoad == 2 && adsPrefernce.showgInter2()) {
                    gDialogInterstitial = new com.google.android.gms.ads.InterstitialAd(this);
                    gDialogInterstitial.setAdUnitId(adsPrefernce.gInterId2());
                    gDialogInterstitial.loadAd(new AdRequest.Builder().build());
                } else {
                    AudienceNetworkAds.initialize(this);
                    if (adToLoad == 1 && adsPrefernce.showfbInter1()) {
                        fbDialogInterstitial = new com.facebook.ads.InterstitialAd(this, adsPrefernce.fbInterId1());
                        fbDialogInterstitial.loadAd();
                    } else if (adToLoad == 2 && adsPrefernce.showfbInter2()) {
                        fbDialogInterstitial = new com.facebook.ads.InterstitialAd(this, adsPrefernce.fbInterId2());
                        fbDialogInterstitial.loadAd();
                    }
                }
            } else {
                MobileAds.initialize(getApplicationContext(), defaultIds.GOOGLE_APP_ID());
                if (adToLoad == 1) {
                    gDialogInterstitial = new com.google.android.gms.ads.InterstitialAd(this);
                    gDialogInterstitial.setAdUnitId(defaultIds.GOOGLE_INTER1());
                    gDialogInterstitial.loadAd(new AdRequest.Builder().build());
                    fbDialogInterstitial = new com.facebook.ads.InterstitialAd(this, defaultIds.FB_INTER1());
                    fbDialogInterstitial.loadAd();
                } else if (adToLoad == 2) {
                    gDialogInterstitial = new com.google.android.gms.ads.InterstitialAd(this);
                    gDialogInterstitial.setAdUnitId(defaultIds.GOOGLE_INTER2());
                    gDialogInterstitial.loadAd(new AdRequest.Builder().build());
                    fbDialogInterstitial = new com.facebook.ads.InterstitialAd(this, defaultIds.FB_INTER2());
                    fbDialogInterstitial.loadAd();
                }
            }
        }
    }


    public void checkAppService(String key, String appVersion, OnCheckServiceListner onCheckServiceListner) {
        if (adsPrefernce.allowAccess()) {
            if (isNetworkAvailable(this) && checkAppService) {
                runAppService(key, appVersion, onCheckServiceListner);
            }
        } else {
            if (isvalidInstall) {
                if (isNetworkAvailable(this) && checkAppService) {
                    runAppService(key, appVersion, onCheckServiceListner);
                }
            }
        }
    }

    public void checkAppService(String key, String appVersion) {
        if (adsPrefernce.allowAccess()) {
            if (isNetworkAvailable(this) && checkAppService) {
                runAppService(key, appVersion);
            }
        } else {
            if (isvalidInstall) {
                if (isNetworkAvailable(this) && checkAppService) {
                    runAppService(key, appVersion);
                }
            }
        }
    }
    public void runAppService(String app_key, final String appVersion, OnCheckServiceListner onCheckServiceListner) {
        AsyncHttpClient client = new AsyncHttpClient();

        gsonUtils = GsonUtils.getInstance();

        RequestParams params1 = new RequestParams();
        params1.put("app_key", app_key);

        try {
            client.setConnectTimeout(50000);
            client.post("http://developercompanion.get-fans-for-musically.com/iapi/app_service4.php", params1, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {


                    String Success = response.optString("success");
                    int con = Integer.parseInt(Success);
                    if (con == 1) {

                        int isUpdate = response.optInt("isUpdate");
                        int isNotification = response.optInt("isNotification");
                        int isAd = response.optInt("isAd");
                        String update_dialog_title = response.optString("update_dialog_title");
                        String update_title = response.optString("update_title");
                        String update_version_name = response.optString("update_version_name");
                        String update_message = response.optString("update_message");
                        int update_show_cancel = response.optInt("update_show_cancel");
                        String update_app_url = response.optString("update_app_url");
                        int update_force_update = response.optInt("update_force_update");
                        String update_force_v1 = response.optString("update_force_v1");
                        String update_force_v2 = response.optString("update_force_v2");
                        String update_force_v3 = response.optString("update_force_v3");

                        String not_dialog_title = response.optString("not_dialog_title");
                        String not_message = response.optString("not_message");
                        String not_image_url = response.optString("not_image_url");
                        int not_show_dialog = response.optInt("not_show_dialog");
                        String not_cancel_button_text = response.optString("not_cancel_button_text");
                        int not_show_cancel_button = response.optInt("not_show_cancel_button");
                        int not_show_ad_icon = response.optInt("not_show_ad_icon");
                        String not_btn_1_activity_text = response.optString("not_btn_1_activity_text");
                        int not_btn_1_show = response.optInt("not_btn_1_show");
                        String not_btn_1_video_url = response.optString("not_btn_1_video_url");
                        String not_btn_2_webview_text = response.optString("not_btn_2_webview_text");
                        int not_btn_2_show = response.optInt("not_btn_2_show");
                        String not_btn_2_webview_url = response.optString("not_btn_2_webview_url");
                        String not_btn_3_text = response.optString("not_btn_3_text");
                        int not_btn_3_show = response.optInt("not_btn_3_show");
                        String not_btn_3_url = response.optString("not_btn_3_url");

                        String ad_dialog_title = response.optString("ad_dialog_title");
                        int ad_show_cancel = response.optInt("ad_show_cancel");
                        String ad_message = response.optString("ad_message");
                        String ad_banner_url = response.optString("ad_banner_url");
                        String ad_icon_url = response.optString("ad_icon_url");
                        String ad_app_name = response.optString("ad_app_name");
                        String ad_app_short_desc = response.optString("ad_app_short_desc");
                        String ad_app_url = response.optString("ad_app_url");

                        checkAppService = false;

                        if (isUpdate == 1) {
                            if (!appVersion.equals(update_version_name)) {
                                serviceDialog(true, false, false, update_dialog_title, update_title, update_version_name, update_message, not_show_dialog == 1,
                                        update_show_cancel == 1, update_app_url, update_force_update == 1, update_force_v1, update_force_v2, update_force_v3, not_dialog_title,
                                        not_message, not_show_dialog == 1, not_image_url, not_cancel_button_text, not_show_cancel_button == 1, not_show_ad_icon == 1,
                                        not_btn_1_activity_text, not_btn_1_show == 1, not_btn_1_video_url,
                                        not_btn_2_webview_text, not_btn_2_show == 1, not_btn_2_webview_url,
                                        not_btn_3_text, not_btn_3_show == 1, not_btn_3_url,
                                        ad_dialog_title, ad_show_cancel == 1, ad_message, ad_banner_url, ad_icon_url, ad_app_name, ad_app_short_desc, ad_app_url, onCheckServiceListner);

//                                serviceDialog(true, false, false, update_dialog_title, update_title, update_version_name, update_message, not_show_dialog == 1,
//                                        update_show_cancel == 1, update_app_url, update_force_update == 1, update_force_v1, update_force_v2, update_force_v3, not_dialog_title,
//                                        not_message, not_image_url,not_show_dialog == 1,not_cancel_button_text, ad_dialog_title, ad_message, ad_banner_url, ad_icon_url, ad_app_name, ad_app_short_desc, ad_app_url,onCheckServiceListner);
                                if (defaultIds.SHOW_NOTIFICATION()) {
                                    if (isNotification == 1) {
                                        if (not_show_dialog == 0) {
                                            lay_notification.setVisibility(View.VISIBLE);
                                            tv_not_text.setText(not_message);
                                            iv_not_close.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    lay_notification.setVisibility(View.GONE);
                                                }
                                            });
                                        }
                                    }
                                }

                                return;
                            }

                        }
                        if (isNotification == 1) {
                            if (defaultIds.SHOW_NOTIFICATION()) {
                                if (not_show_dialog == 0) {
                                    lay_notification.setVisibility(View.VISIBLE);
                                    tv_not_text.setText(not_message);
                                    iv_not_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            lay_notification.setVisibility(View.GONE);
                                        }
                                    });
                                }
                            } else {
                                serviceDialog(false, true, false, update_dialog_title, update_title, update_version_name, update_message, not_show_dialog == 1,
                                        update_show_cancel == 1, update_app_url, update_force_update == 1, update_force_v1, update_force_v2, update_force_v3, not_dialog_title,
                                        not_message, not_show_dialog == 1, not_image_url, not_cancel_button_text, not_show_cancel_button == 1, not_show_ad_icon == 1,
                                        not_btn_1_activity_text, not_btn_1_show == 1, not_btn_1_video_url,
                                        not_btn_2_webview_text, not_btn_2_show == 1, not_btn_2_webview_url,
                                        not_btn_3_text, not_btn_3_show == 1, not_btn_3_url,
                                        ad_dialog_title, ad_show_cancel == 1, ad_message, ad_banner_url, ad_icon_url, ad_app_name, ad_app_short_desc, ad_app_url, onCheckServiceListner);
                                return;
                            }
                        }
                        if (isAd == 1) {
                            serviceDialog(false, false, true, update_dialog_title, update_title, update_version_name, update_message, not_show_dialog == 1,
                                    update_show_cancel == 1, update_app_url, update_force_update == 1, update_force_v1, update_force_v2, update_force_v3, not_dialog_title,
                                    not_message, not_show_dialog == 1, not_image_url, not_cancel_button_text, not_show_cancel_button == 1, not_show_ad_icon == 1,
                                    not_btn_1_activity_text, not_btn_1_show == 1, not_btn_1_video_url,
                                    not_btn_2_webview_text, not_btn_2_show == 1, not_btn_2_webview_url,
                                    not_btn_3_text, not_btn_3_show == 1, not_btn_3_url,
                                    ad_dialog_title, ad_show_cancel == 1, ad_message, ad_banner_url, ad_icon_url, ad_app_name, ad_app_short_desc, ad_app_url, onCheckServiceListner);

                        }
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                }
            });

        } catch (Exception e) {

        }
    }

    public void runAppService(String app_key, final String appVersion) {
        AsyncHttpClient client = new AsyncHttpClient();

        gsonUtils = GsonUtils.getInstance();

        RequestParams params1 = new RequestParams();
        params1.put("app_key", app_key);

        try {
            client.setConnectTimeout(50000);
            client.post("http://developercompanion.get-fans-for-musically.com/iapi/app_service4.php", params1, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {


                    String Success = response.optString("success");
                    int con = Integer.parseInt(Success);
                    if (con == 1) {

                        int isUpdate = response.optInt("isUpdate");
                        int isNotification = response.optInt("isNotification");
                        int isAd = response.optInt("isAd");
                        String update_dialog_title = response.optString("update_dialog_title");
                        String update_title = response.optString("update_title");
                        String update_version_name = response.optString("update_version_name");
                        String update_message = response.optString("update_message");
                        int update_show_cancel = response.optInt("update_show_cancel");
                        String update_app_url = response.optString("update_app_url");
                        int update_force_update = response.optInt("update_force_update");
                        String update_force_v1 = response.optString("update_force_v1");
                        String update_force_v2 = response.optString("update_force_v2");
                        String update_force_v3 = response.optString("update_force_v3");

                        String not_dialog_title = response.optString("not_dialog_title");
                        String not_message = response.optString("not_message");
                        String not_image_url = response.optString("not_image_url");
                        int not_show_dialog = response.optInt("not_show_dialog");
                        String not_cancel_button_text = response.optString("not_cancel_button_text");
                        int not_show_cancel_button = response.optInt("not_show_cancel_button");
                        int not_show_ad_icon = response.optInt("not_show_ad_icon");
                        String not_btn_1_activity_text = response.optString("not_btn_1_activity_text");
                        int not_btn_1_show = response.optInt("not_btn_1_show");
                        String not_btn_1_video_url = response.optString("not_btn_1_video_url");
                        String not_btn_2_webview_text = response.optString("not_btn_2_webview_text");
                        int not_btn_2_show = response.optInt("not_btn_2_show");
                        String not_btn_2_webview_url = response.optString("not_btn_2_webview_url");
                        String not_btn_3_text = response.optString("not_btn_3_text");
                        int not_btn_3_show = response.optInt("not_btn_3_show");
                        String not_btn_3_url = response.optString("not_btn_3_url");

                        String ad_dialog_title = response.optString("ad_dialog_title");
                        int ad_show_cancel = response.optInt("ad_show_cancel");
                        String ad_message = response.optString("ad_message");
                        String ad_banner_url = response.optString("ad_banner_url");
                        String ad_icon_url = response.optString("ad_icon_url");
                        String ad_app_name = response.optString("ad_app_name");
                        String ad_app_short_desc = response.optString("ad_app_short_desc");
                        String ad_app_url = response.optString("ad_app_url");

                        checkAppService = false;

                        if (isUpdate == 1) {
                            if (!appVersion.equals(update_version_name)) {
                                serviceDialog(true, false, false, update_dialog_title, update_title, update_version_name, update_message, not_show_dialog == 1,
                                        update_show_cancel == 1, update_app_url, update_force_update == 1, update_force_v1, update_force_v2, update_force_v3, not_dialog_title,
                                        not_message, not_show_dialog == 1, not_image_url, not_cancel_button_text, not_show_cancel_button == 1, not_show_ad_icon == 1,
                                        not_btn_1_activity_text, not_btn_1_show == 1, not_btn_1_video_url,
                                        not_btn_2_webview_text, not_btn_2_show == 1, not_btn_2_webview_url,
                                        not_btn_3_text, not_btn_3_show == 1, not_btn_3_url,
                                        ad_dialog_title, ad_show_cancel == 1, ad_message, ad_banner_url, ad_icon_url, ad_app_name, ad_app_short_desc, ad_app_url, null);

//                                serviceDialog(true, false, false, update_dialog_title, update_title, update_version_name, update_message, not_show_dialog == 1,
//                                        update_show_cancel == 1, update_app_url, update_force_update == 1, update_force_v1, update_force_v2, update_force_v3, not_dialog_title,
//                                        not_message, not_image_url,not_show_dialog == 1,not_cancel_button_text, ad_dialog_title, ad_message, ad_banner_url, ad_icon_url, ad_app_name, ad_app_short_desc, ad_app_url,onCheckServiceListner);
                                if (defaultIds.SHOW_NOTIFICATION()) {
                                    if (isNotification == 1) {
                                        if (not_show_dialog == 0) {
                                            lay_notification.setVisibility(View.VISIBLE);
                                            tv_not_text.setText(not_message);
                                            iv_not_close.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    lay_notification.setVisibility(View.GONE);
                                                }
                                            });
                                        }
                                    }
                                }

                                return;
                            }

                        }
                        if (isNotification == 1) {
                            if (defaultIds.SHOW_NOTIFICATION()) {
                                if (not_show_dialog == 0) {
                                    lay_notification.setVisibility(View.VISIBLE);
                                    tv_not_text.setText(not_message);
                                    iv_not_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            lay_notification.setVisibility(View.GONE);
                                        }
                                    });
                                }
                            } else {
                                serviceDialog(false, true, false, update_dialog_title, update_title, update_version_name, update_message, not_show_dialog == 1,
                                        update_show_cancel == 1, update_app_url, update_force_update == 1, update_force_v1, update_force_v2, update_force_v3, not_dialog_title,
                                        not_message, not_show_dialog == 1, not_image_url, not_cancel_button_text, not_show_cancel_button == 1, not_show_ad_icon == 1,
                                        not_btn_1_activity_text, not_btn_1_show == 1, not_btn_1_video_url,
                                        not_btn_2_webview_text, not_btn_2_show == 1, not_btn_2_webview_url,
                                        not_btn_3_text, not_btn_3_show == 1, not_btn_3_url,
                                        ad_dialog_title, ad_show_cancel == 1, ad_message, ad_banner_url, ad_icon_url, ad_app_name, ad_app_short_desc, ad_app_url, null);
                                return;
                            }
                        }
                        if (isAd == 1) {
                            serviceDialog(false, false, true, update_dialog_title, update_title, update_version_name, update_message, not_show_dialog == 1,
                                    update_show_cancel == 1, update_app_url, update_force_update == 1, update_force_v1, update_force_v2, update_force_v3, not_dialog_title,
                                    not_message, not_show_dialog == 1, not_image_url, not_cancel_button_text, not_show_cancel_button == 1, not_show_ad_icon == 1,
                                    not_btn_1_activity_text, not_btn_1_show == 1, not_btn_1_video_url,
                                    not_btn_2_webview_text, not_btn_2_show == 1, not_btn_2_webview_url,
                                    not_btn_3_text, not_btn_3_show == 1, not_btn_3_url,
                                    ad_dialog_title, ad_show_cancel == 1, ad_message, ad_banner_url, ad_icon_url, ad_app_name, ad_app_short_desc, ad_app_url, null);

                        }
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                }
            });

        } catch (Exception e) {

        }
    }

    public void serviceDialog(Boolean isUpdate, Boolean isNotification, Boolean isAd, String
            update_dialog_title, String update_title,
                              String update_version_name, String update_message, Boolean update_available, Boolean
                                      update_show_cancel, final String update_app_url,
                              Boolean update_force_update, String update_force_v1, String update_force_v2, String
                                      update_force_v3,
                              String not_dialog_title,
                              String not_message, Boolean not_show_dialog, String not_image_url, String not_cancel_button_text, Boolean not_show_cancel_button, Boolean not_show_ad_icon,
                              String not_btn_1_activity_text, Boolean not_btn_1_show, String not_btn_1_video_url,
                              String not_btn_2_webview_text, Boolean not_btn_2_show, String not_btn_2_webview_url,
                              String not_btn_3_text, Boolean not_btn_3_show, String not_btn_3_url,
                              String ad_dialog_title, Boolean ad_show_cancel, String ad_message, String ad_banner_url, String ad_icon_url,
                              String ad_app_name, String ad_app_short_desc, final String ad_app_url, OnCheckServiceListner onCheckServiceListner) {

        this.serviceDialog = new Dialog(this);
        this.serviceDialog.setCancelable(false);
        this.serviceDialog.setContentView(R.layout.dialog_service);
        Objects.requireNonNull(this.serviceDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        LinearLayout lay_updateApp = this.serviceDialog.findViewById(R.id.lay_updateApp);
        LinearLayout lay_message = this.serviceDialog.findViewById(R.id.lay_message);
        LinearLayout lay_ads = this.serviceDialog.findViewById(R.id.lay_ads);

        ImageView iv_ad_icon_title = this.serviceDialog.findViewById(R.id.iv_ad_icon_title);
        TextView tv_dialog_title = this.serviceDialog.findViewById(R.id.tv_dialog_title);

        //update
        TextView tv_updatetitle = this.serviceDialog.findViewById(R.id.tv_updatetitle);
        TextView tv_versionName = this.serviceDialog.findViewById(R.id.tv_versionName);
        TextView tv_updatemessage = this.serviceDialog.findViewById(R.id.tv_updatemessage);
        TextView tv_updatebutton = this.serviceDialog.findViewById(R.id.tv_updatebutton);
        TextView tv_canclebutton = this.serviceDialog.findViewById(R.id.tv_canclebutton);

        //message
        TextView tv_message = this.serviceDialog.findViewById(R.id.tv_message);
        ImageView iv_not_banner = this.serviceDialog.findViewById(R.id.iv_not_banner);
        TextView tv_not_cancel_button = this.serviceDialog.findViewById(R.id.tv_not_cancel_button);
        TextView tv_sponsered = this.serviceDialog.findViewById(R.id.tv_sponsered);
        TextView btn_1_acitivty = this.serviceDialog.findViewById(R.id.btn_1_acitivty);
        TextView btn_2_webview = this.serviceDialog.findViewById(R.id.btn_2_webview);
        TextView btn_3_openurl = this.serviceDialog.findViewById(R.id.btn_3_openurl);

        //ads
        TextView tv_ad_message = this.serviceDialog.findViewById(R.id.tv_ad_message);
        ImageView iv_ad_banner = this.serviceDialog.findViewById(R.id.iv_ad_banner);
        ImageView iv_app_icon = this.serviceDialog.findViewById(R.id.iv_app_icon);
        TextView tv_app_name = this.serviceDialog.findViewById(R.id.tv_app_name);
        TextView tv_app_shortdesc = this.serviceDialog.findViewById(R.id.tv_app_shortdesc);
        TextView tv_app_download = this.serviceDialog.findViewById(R.id.tv_app_download);
        TextView tv_app_cancel = this.serviceDialog.findViewById(R.id.tv_app_cancel);

        if (isUpdate) {
            iv_ad_icon_title.setVisibility(View.GONE);
            lay_message.setVisibility(View.GONE);
            lay_ads.setVisibility(View.GONE);
            lay_updateApp.setVisibility(View.VISIBLE);
            tv_dialog_title.setText(update_dialog_title);

            tv_updatetitle.setText(update_title);
            tv_versionName.setText(update_version_name);
            tv_updatemessage.setText(update_message);

            if (update_show_cancel) {
                tv_canclebutton.setVisibility(View.VISIBLE);
            } else {
                tv_canclebutton.setVisibility(View.GONE);
            }

            tv_updatebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(update_app_url));
                    startActivity(intent);
                }
            });

            tv_canclebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    serviceDialog.dismiss();
                }
            });
            this.serviceDialog.show();

        }

        if (isNotification) {
            if (not_show_dialog) {
                lay_ads.setVisibility(View.GONE);
                lay_updateApp.setVisibility(View.GONE);
                lay_message.setVisibility(View.VISIBLE);
                tv_dialog_title.setText(not_dialog_title);
                if (not_show_ad_icon) {
                    tv_sponsered.setVisibility(View.VISIBLE);
                    iv_ad_icon_title.setVisibility(View.VISIBLE);
                } else {
                    tv_sponsered.setVisibility(View.GONE);
                    iv_ad_icon_title.setVisibility(View.GONE);
                }
                if (!not_image_url.equals("na")) {
                    Glide.with(this).load(not_image_url).into(iv_not_banner);
                }

                tv_message.setText(not_message);

                if (not_show_cancel_button) {
                    tv_not_cancel_button.setVisibility(View.VISIBLE);
                    tv_not_cancel_button.setText(not_cancel_button_text);
                } else {
                    tv_not_cancel_button.setVisibility(View.GONE);
                }

                if (not_btn_1_show) {
                    btn_1_acitivty.setVisibility(View.VISIBLE);
                    btn_2_webview.setVisibility(View.GONE);
                    btn_3_openurl.setVisibility(View.GONE);
                    btn_1_acitivty.setText(not_btn_1_activity_text);
                } else if (not_btn_2_show) {
                    btn_1_acitivty.setVisibility(View.GONE);
                    btn_2_webview.setVisibility(View.VISIBLE);
                    btn_3_openurl.setVisibility(View.GONE);
                    btn_2_webview.setText(not_btn_2_webview_text);
                } else if (not_btn_3_show) {
                    btn_1_acitivty.setVisibility(View.GONE);
                    btn_2_webview.setVisibility(View.GONE);
                    btn_3_openurl.setVisibility(View.VISIBLE);
                    btn_3_openurl.setText(not_btn_3_text);
                }
                tv_not_cancel_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        serviceDialog.dismiss();
                    }
                });
                btn_1_acitivty.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            onCheckServiceListner.onButton1Clicked(not_btn_1_video_url);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                btn_2_webview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            onCheckServiceListner.onButton2Clicked(not_btn_2_webview_url);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
                btn_3_openurl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            onCheckServiceListner.onButton3Clicked(not_btn_3_url);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
                this.serviceDialog.show();

            }

        }

        if (isAd) {
            iv_ad_icon_title.setVisibility(View.VISIBLE);
            lay_updateApp.setVisibility(View.GONE);
            lay_message.setVisibility(View.GONE);
            lay_ads.setVisibility(View.VISIBLE);
            tv_dialog_title.setText(ad_dialog_title);

            if (ad_show_cancel) {
                tv_app_cancel.setVisibility(View.VISIBLE);
            } else {
                tv_app_cancel.setVisibility(View.GONE);
            }

            tv_ad_message.setText(ad_message);
            Glide.with(this).load(ad_banner_url).into(iv_ad_banner);
            Glide.with(this).load(ad_icon_url).into(iv_app_icon);
            tv_app_name.setText(ad_app_name);
            tv_app_shortdesc.setText(ad_app_short_desc);

            tv_app_download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ad_app_url));
                    startActivity(intent);
                }
            });

            tv_app_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    serviceDialog.dismiss();
                }
            });
            String link = ad_app_url;
            String[] s1 = link.split("id=");
            String[] s2 = s1[1].split("&");
            String app_id = s2[0].toString();
            Log.e("app_id_get", app_id);
            if (!appInstalledOrNot(app_id)) {
                this.serviceDialog.show();
            }

            Log.e("app_id_installed", String.valueOf(appInstalledOrNot(app_id)));

        }

    }
    public void hideStatusBar() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void exitApp() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
        finish();
    }

    public void backAgainToExit() {
        if (System.currentTimeMillis() - this.exitTime > 2000) {
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
            this.exitTime = System.currentTimeMillis();
            return;
        }
        exitApp();
    }

    private com.google.android.gms.ads.AdSize getAdSize() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);

        return com.google.android.gms.ads.AdSize.getPortraitAnchoredAdaptiveBannerAdSize(this, adWidth);
    }

    private void initializeMoPubSDK() {

        if (!mpInter1Initilized) {
            if (adsPrefernce.showmpInter1()) {
                Log.e("Ads..", "Inside Init mp Inter 1");
                SdkConfiguration sdkConfiguration = new SdkConfiguration.Builder(adsPrefernce.mpInterId1())
                        .withLegitimateInterestAllowed(false)
                        .build();
                MoPub.initializeSdk(this, sdkConfiguration, initSdkListener());
            }
        } else if (!mpInter2Initilized) {
            Log.e("Ads..", "Inside Init mp Inter 2");
            SdkConfiguration sdkConfiguration = new SdkConfiguration.Builder(adsPrefernce.mpInterId2())
                    .withLegitimateInterestAllowed(false)
                    .build();
            MoPub.initializeSdk(this, sdkConfiguration, initSdkListener());
        } else if (!mpBannerInitilized) {
            Log.e("Ads..", "Inside Init mp Banner");
            SdkConfiguration sdkConfiguration = new SdkConfiguration.Builder(adsPrefernce.mpBannerId())
                    .withLegitimateInterestAllowed(false)
                    .build();
            MoPub.initializeSdk(this, sdkConfiguration, initSdkListener());
        }

    }

    private SdkInitializationListener initSdkListener() {
        return new SdkInitializationListener() {
            @Override
            public void onInitializationFinished() {
                Log.e("Ads...", "onInitializationFinished");

                if (!mpInter1Initilized) {
                    if (adsPrefernce.showmpInter1()) {
                        if (!isMpInter1Ready) {
                            Log.e("Ads...", "isMpInter1Ready false");
                            mpInterstitial1 = new MoPubInterstitial(BaseClass.this, adsPrefernce.mpInterId1());
                            mpInterstitial1.load();
                            mpInterstitial1.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                                @Override
                                public void onInterstitialLoaded(MoPubInterstitial interstitial) {
                                    Log.e("Ads...", "isMpInter1Ready true");
                                    isMpInter1Ready = true;
                                }

                                @Override
                                public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {

                                }

                                @Override
                                public void onInterstitialShown(MoPubInterstitial interstitial) {

                                }

                                @Override
                                public void onInterstitialClicked(MoPubInterstitial interstitial) {

                                }

                                @Override
                                public void onInterstitialDismissed(MoPubInterstitial interstitial) {

                                }
                            });
                            initializeMoPubSDK();

                        } else if (!mpInter2Initilized) {
                            initializeMoPubSDK();
                        }
                    } else if (!mpInter2Initilized) {
                        initializeMoPubSDK();
                    }
                    mpInter1Initilized = true;
                } else if (!mpInter2Initilized) {
                    if (adsPrefernce.showmpInter2()) {
                        if (!isMpInter2Ready) {
                            mpInterstitial2 = new MoPubInterstitial(BaseClass.this, adsPrefernce.mpInterId2());
                            mpInterstitial2.load();
                            mpInterstitial2.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                                @Override
                                public void onInterstitialLoaded(MoPubInterstitial interstitial) {
                                    isMpInter2Ready = true;
                                }

                                @Override
                                public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {

                                }

                                @Override
                                public void onInterstitialShown(MoPubInterstitial interstitial) {

                                }

                                @Override
                                public void onInterstitialClicked(MoPubInterstitial interstitial) {

                                }

                                @Override
                                public void onInterstitialDismissed(MoPubInterstitial interstitial) {

                                }
                            });
                            if (!mpBannerInitilized) {
                                if (adsPrefernce.showmpBanner()) {
                                    initializeMoPubSDK();
                                }
                            }

                        } else if (!mpBannerInitilized) {
                            if (adsPrefernce.showmpBanner()) {
                                initializeMoPubSDK();
                            }
                        }

                    } else if (!mpBannerInitilized) {
                        if (adsPrefernce.showmpBanner()) {
                            initializeMoPubSDK();
                        }
                    }
                    mpInter2Initilized = true;

                } else if (!mpBannerInitilized) {
                    mpBannerInitilized = true;
                }


            }

        };
    }


    private SdkInitializationListener initSdkListenerBanner(Boolean loadOnInitilized) {
        return new SdkInitializationListener() {
            @Override
            public void onInitializationFinished() {

                if (loadOnInitilized) {

                }

            }
        };
    }

    boolean verifyInstallerId(Context context) {
        // A list with valid installers package name
        List<String> validInstallers = new ArrayList<>(Arrays.asList("com.android.vending", "com.google.android.feedback"));

// The package name of the app that has installed your app
        final String installer = context.getPackageManager().getInstallerPackageName(context.getPackageName());

        // true if your app has been downloaded from Play Store
        return installer != null && validInstallers.contains(installer);
    }

    public void validateInstall(Callable<Void> functionToCall) {
        if (!adsPrefernce.allowAccess()) {
            Log.e("validation", "allowAccess false");
            if (!isvalidInstall) {
                Log.e("validation", "isvalidInstall false");
                try {
                    functionToCall.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void showConsentDialog(Context context) {
        BottomSheetDialog consentDialog = new BottomSheetDialog(context, R.style.ConsentDialogTheme);
        consentDialog.setCancelable(false);
        consentDialog.setContentView(R.layout.dialog_consent);
        Window window = consentDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        window.setAttributes(wlp);
        Objects.requireNonNull(consentDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        TextView tv_positive = consentDialog.findViewById(R.id.tv_positive);
        TextView tv_negative = consentDialog.findViewById(R.id.tv_negative);
        TextView tv_consentdetails = consentDialog.findViewById(R.id.tv_consentdetails);
        TextView p1 = consentDialog.findViewById(R.id.tv_p1);
        TextView p2 = consentDialog.findViewById(R.id.tv_p2);
        TextView p3 = consentDialog.findViewById(R.id.tv_p3);
        TextView p4 = consentDialog.findViewById(R.id.tv_p4);
        TextView p5 = consentDialog.findViewById(R.id.tv_p5);
        TextView p6 = consentDialog.findViewById(R.id.tv_p6);

        p1.setMovementMethod(LinkMovementMethod.getInstance());
        p2.setMovementMethod(LinkMovementMethod.getInstance());
        p3.setMovementMethod(LinkMovementMethod.getInstance());
        p4.setMovementMethod(LinkMovementMethod.getInstance());
        p5.setMovementMethod(LinkMovementMethod.getInstance());
        p6.setMovementMethod(LinkMovementMethod.getInstance());
        tv_consentdetails.setMovementMethod(LinkMovementMethod.getInstance());
        tv_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consentDialog.dismiss();
                adsPrefernce.setConsent(true);
                adsPrefernce.setConsentShown();
                StartAppSDK.setUserConsent(context,
                        "pas",
                        System.currentTimeMillis(),
                        adsPrefernce.getConsent());
                PersonalInfoManager personalInfoManager = MoPub.getPersonalInformationManager();
                if (adsPrefernce.getConsent()) {
                    if (personalInfoManager != null) {
                        personalInfoManager.grantConsent();
                    }
                } else {
                    if (personalInfoManager != null) {
                        personalInfoManager.revokeConsent();
                    }
                }
                Appnext.setParam("consent", String.valueOf(adsPrefernce.getConsent()));
            }
        });
        tv_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consentDialog.dismiss();
                adsPrefernce.setConsent(false);
                adsPrefernce.setConsentShown();
                StartAppSDK.setUserConsent(context,
                        "pas",
                        System.currentTimeMillis(),
                        adsPrefernce.getConsent());
                PersonalInfoManager personalInfoManager = MoPub.getPersonalInformationManager();
                if (adsPrefernce.getConsent()) {
                    if (personalInfoManager != null) {
                        personalInfoManager.grantConsent();
                    }
                } else {
                    if (personalInfoManager != null) {
                        personalInfoManager.revokeConsent();
                    }
                }
                Appnext.setParam("consent", String.valueOf(adsPrefernce.getConsent()));
            }
        });

        if (!adsPrefernce.isConsentShown()) {
            consentDialog.show();
        }

    }

    String getAppIdFromAppLink(String appLink) {
        String link = appLink;
        String[] s1 = link.split("id=");
        String[] s2 = s1[1].split("&");
        return s2[0].toString();
    }

    int getCurrentInterAd(int totalAds) {
        Log.e("totalInter", String.valueOf(totalAds));
        if (!isFirstIHInter) {
            if (currentInter + 1 >= totalAds) {
                currentInter = 0;
            } else {
                currentInter = currentInter + 1;
            }
        } else {
            currentInter = 0;
            isFirstIHInter = false;

        }

//        if (!isFirstIHInter) {
//            if (currentInter + 1 >= totalAds) {
//                currentInter = 0;
//                if (interDetails.get(currentInter).getOpenin().equals("playstore")) {
//                    if (appInstalledOrNot(getAppIdFromAppLink(interDetails.get(currentInter).getApplink()))) {
//                        if (currentInter + 1 >= totalAds){
//                            currentInter = currentInter + 1;
//                        }else {
//                            currentInter = 0;
//                        }
//                    }
//                }
//            } else {
//                currentInter = currentInter + 1;
//                if (interDetails.get(currentInter).getOpenin().equals("playstore")) {
//                    if (appInstalledOrNot(getAppIdFromAppLink(interDetails.get(currentInter).getApplink()))) {
//                        if (currentInter + 1 >= totalAds){
//                            currentInter = currentInter + 1;
//                        }else {
//                            currentInter = 0;
//                        }
//                    }
//
//                }
//            }
//        } else {
//            currentInter = 0;
//            isFirstIHInter = false;
//            if (interDetails.get(currentInter).getOpenin().equals("playstore")) {
//                if (appInstalledOrNot(getAppIdFromAppLink(interDetails.get(currentInter).getApplink()))) {
//                    if (currentInter + 1 >= totalAds){
//                        currentInter = currentInter + 1;
//                    }else {
//                        currentInter = 0;
//                    }
//                }
//            }
//
//        }
        return currentInter;

    }

    int getCurrentBannerAd(int totalAds) {
        if (!isFirstIHBanner) {
            if (currentBanner + 1 >= totalAds) {
                currentBanner = 0;
            } else {
                currentBanner = currentBanner + 1;
            }
        } else {
            currentBanner = 0;
            isFirstIHBanner = false;

        }
        return currentBanner;

    }

    int getCurrentNativeAd(int totalAds) {
        if (!isFirstIHNative) {
            if (currentNative + 1 >= totalAds) {
                currentNative = 0;
            } else {
                currentNative = currentNative + 1;
            }
        } else {
            currentNative = 0;
            isFirstIHNative = false;

        }
        return currentNative;

    }

    public void showInhouseInterAd(InhouseInterstitialListener inhouseInterstitialListener) {

//        if (isNetworkAvailable(this)) {
        if (adsPrefernce.isInterstitialAdLoaded()) {
            if (isNetworkAvailable(this)) {
                if (finalInter.size() != 0) {
                    // get Interstitial Data
                    ArrayList<InterDetail> interAdDetails = adsPrefernce.getInterAds();

                    // ad to show from position
                    int current = getCurrentInterAd(finalInter.size());


                    final Dialog interDialog = new Dialog(this);
                    interDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    interDialog.setContentView(R.layout.ad_interstitial);
                    interDialog.getWindow().setBackgroundDrawableResource(android.R.color.white);
                    interDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                    Objects.requireNonNull(interDialog.getWindow()).getAttributes().windowAnimations = R.style.InterstitialAdAnimation;
                    interDialog.setCancelable(false);

                    ImageView iv_close_ad = interDialog.findViewById(R.id.iv_close_ad);
                    LinearLayout lay_close_ad = interDialog.findViewById(R.id.lay_close_ad);
                    ImageView iv_ad_icon = interDialog.findViewById(R.id.iv_ad_icon);
                    RatingBar iv_inter_star_rating = interDialog.findViewById(R.id.iv_inter_star_rating);
                    TextView tv_inter_ad_title = interDialog.findViewById(R.id.tv_inter_ad_title);
                    TextView tv_inter_ad_subtitle = interDialog.findViewById(R.id.tv_inter_ad_subtitle);
                    TextView tv_inter_review_count = interDialog.findViewById(R.id.tv_inter_review_count);

                    ImageView iv_inter_main_banner = interDialog.findViewById(R.id.iv_inter_main_banner);

                    TextView tv_inter_ad_desc = interDialog.findViewById(R.id.tv_inter_ad_desc);
                    TextView tv_inter_ad_sub_desc = interDialog.findViewById(R.id.tv_inter_ad_sub_desc);

                    ImageView iv_inter_info = interDialog.findViewById(R.id.iv_inter_info);

                    TextView tv_install_btn_inter = interDialog.findViewById(R.id.tv_install_btn_inter);

                    // set Interstitial Data
                    InterDetail interAd = interAdDetails.get(current);

                    // icon
                    Glide.with(this).load(interAd.getIcon()).into(iv_ad_icon);
                    // banner
                    Glide.with(this).load(interAd.getBigimage()).into(iv_inter_main_banner);
                    // title
                    tv_inter_ad_title.setText(interAd.getTitle());
                    // subtitle
                    tv_inter_ad_subtitle.setText(interAd.getSubtitle());
                    // install button Text
                    tv_install_btn_inter.setText(interAd.getButtontext());

                    // show rating or not and set rating image
                    if (interAd.getShowrating().equals("1")) {
                        iv_inter_star_rating.setVisibility(View.VISIBLE);
                        iv_inter_star_rating.setRating(Float.parseFloat(interAd.getRatingcount()));
                    } else {
                        iv_inter_star_rating.setVisibility(View.GONE);
                    }

                    // show reviews or not and set review count
                    if (interAd.getShowreview().equals("1")) {
                        tv_inter_review_count.setVisibility(View.VISIBLE);
                        tv_inter_review_count.setText("  ( " + interAd.getReviewcount() + " )");
                    } else {
                        tv_inter_review_count.setVisibility(View.GONE);
                    }

                    // description title
                    tv_inter_ad_desc.setText(interAd.getDescTitle());

                    // description text
                    tv_inter_ad_sub_desc.setText(interAd.getDescText());


                    proceedWithDelay(2000, new Callable<Void>() {
                        @Override
                        public Void call() throws Exception {
                            lay_close_ad.setVisibility(View.VISIBLE);
                            return null;
                        }
                    });

                    lay_close_ad.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            interDialog.dismiss();
                            inhouseInterstitialListener.onAdDismissed();
                        }
                    });

                    iv_inter_info.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showAdsPrivacyDialog();
                        }
                    });

                    tv_install_btn_inter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // open link
                            if (interAd.getOpenin().equals("playstore")) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(interAd.getApplink())));
                            } else {
                                Uri uri = Uri.parse(interAd.getApplink()); // missing 'http://' will cause crashed
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                            }
                        }
                    });

                    if (!appInstalledOrNot(getAppIdFromAppLink(interAd.getApplink()))) {
                        interDialog.show();
                        inhouseInterstitialListener.onAdShown();
                    } else {
                        inhouseInterstitialListener.onAdDismissed();
                    }
                } else {
                    inhouseInterstitialListener.onAdDismissed();
                }
            } else {
                // get Interstitial Data
                ArrayList<InterDetail> savedInterAdDetails = adsPrefernce.getInterAds();

                // ad to show from position
                int current = getCurrentInterAd(savedInterAdDetails.size());


                final Dialog interDialog = new Dialog(this);
                interDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                interDialog.setContentView(R.layout.ad_interstitial);
                interDialog.getWindow().setBackgroundDrawableResource(android.R.color.white);
                interDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                Objects.requireNonNull(interDialog.getWindow()).getAttributes().windowAnimations = R.style.InterstitialAdAnimation;
                interDialog.setCancelable(false);

                ImageView iv_close_ad = interDialog.findViewById(R.id.iv_close_ad);
                LinearLayout lay_close_ad = interDialog.findViewById(R.id.lay_close_ad);
                ImageView iv_ad_icon = interDialog.findViewById(R.id.iv_ad_icon);
                RatingBar iv_inter_star_rating = interDialog.findViewById(R.id.iv_inter_star_rating);
                TextView tv_inter_ad_title = interDialog.findViewById(R.id.tv_inter_ad_title);
                TextView tv_inter_ad_subtitle = interDialog.findViewById(R.id.tv_inter_ad_subtitle);
                TextView tv_inter_review_count = interDialog.findViewById(R.id.tv_inter_review_count);

                ImageView iv_inter_main_banner = interDialog.findViewById(R.id.iv_inter_main_banner);

                TextView tv_inter_ad_desc = interDialog.findViewById(R.id.tv_inter_ad_desc);
                TextView tv_inter_ad_sub_desc = interDialog.findViewById(R.id.tv_inter_ad_sub_desc);

                ImageView iv_inter_info = interDialog.findViewById(R.id.iv_inter_info);

                TextView tv_install_btn_inter = interDialog.findViewById(R.id.tv_install_btn_inter);

                // set Interstitial Data
                InterDetail interAd = savedInterAdDetails.get(current);

                // icon
                Glide.with(this).load(interAd.getIcon()).into(iv_ad_icon);
                // banner
                Glide.with(this).load(interAd.getBigimage()).into(iv_inter_main_banner);
                // title
                tv_inter_ad_title.setText(interAd.getTitle());
                // subtitle
                tv_inter_ad_subtitle.setText(interAd.getSubtitle());
                // install button Text
                tv_install_btn_inter.setText(interAd.getButtontext());

                // show rating or not and set rating image
                if (interAd.getShowrating().equals("1")) {
                    iv_inter_star_rating.setVisibility(View.VISIBLE);
                    iv_inter_star_rating.setRating(Float.parseFloat(interAd.getRatingcount()));
                } else {
                    iv_inter_star_rating.setVisibility(View.GONE);
                }

                // show reviews or not and set review count
                if (interAd.getShowreview().equals("1")) {
                    tv_inter_review_count.setVisibility(View.VISIBLE);
                    tv_inter_review_count.setText("  ( " + interAd.getReviewcount() + " )");
                } else {
                    tv_inter_review_count.setVisibility(View.GONE);
                }

                // description title
                tv_inter_ad_desc.setText(interAd.getDescTitle());

                // description text
                tv_inter_ad_sub_desc.setText(interAd.getDescText());


                proceedWithDelay(2000, new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        lay_close_ad.setVisibility(View.VISIBLE);
                        return null;
                    }
                });

                lay_close_ad.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        interDialog.dismiss();
                        inhouseInterstitialListener.onAdDismissed();
                    }
                });

                iv_inter_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showAdsPrivacyDialog();
                    }
                });

                tv_install_btn_inter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // open link
                        if (interAd.getOpenin().equals("playstore")) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(interAd.getApplink())));
                        } else {
                            Uri uri = Uri.parse(interAd.getApplink()); // missing 'http://' will cause crashed
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                        }
                    }
                });

                if (!appInstalledOrNot(getAppIdFromAppLink(interAd.getApplink()))) {
                    interDialog.show();
                    inhouseInterstitialListener.onAdShown();
                } else {
                    inhouseInterstitialListener.onAdDismissed();
                }
            }

        } else {
            inhouseInterstitialListener.onAdDismissed();
        }
//        } else {
//            inhouseInterstitialListener.onAdDismissed();
//        }
    }


    public void showInhouseBannerAd(InhouseBannerListener inhouseBannerListener) {
        if (adsPrefernce.isBannerAdLoaded()) {
            if (isNetworkAvailable(this)) {
                if (finalBanner.size() != 0) {
                    // get Interstitial Data
                    ArrayList<BannerDetail> bannerDetails = adsPrefernce.getBannerAds();

                    // ad to show from position
                    int current = getCurrentBannerAd(finalBanner.size());


                    ImageView iv_banner_info = findViewById(R.id.iv_banner_info);
                    ImageView iv_close_ad_banner = findViewById(R.id.iv_close_ad_banner);
                    ImageView iv_ad_icon_banner = findViewById(R.id.iv_ad_icon_banner);

                    TextView tv_banner_ad_title = findViewById(R.id.tv_banner_ad_title);
                    TextView tv_banner_ad_subtitle = findViewById(R.id.tv_banner_ad_subtitle);

                    RatingBar iv_banner_star_rating = findViewById(R.id.iv_banner_star_rating);
                    TextView tv_banner_review_count = findViewById(R.id.tv_banner_review_count);

                    TextView tv_install_btn_banner = findViewById(R.id.tv_install_btn_banner);
                    TextView tv_banner_extra_text = findViewById(R.id.tv_banner_extra_text);

                    RelativeLayout lay_first = findViewById(R.id.lay_first);
                    RelativeLayout lay_second = findViewById(R.id.lay_second);
                    RelativeLayout lay_banner_ad = findViewById(R.id.lay_banner_ad);

                    lay_banner_ad.setVisibility(View.VISIBLE);


                    // set Interstitial Data
                    BannerDetail bannerAd = bannerDetails.get(current);

                    // icon
                    Glide.with(this).load(bannerAd.getIcon()).into(iv_ad_icon_banner);
                    // title
                    tv_banner_ad_title.setText(bannerAd.getTitle());
                    // subtitle
                    tv_banner_ad_subtitle.setText(bannerAd.getSubtitle());
                    // install button Text
                    tv_install_btn_banner.setText(bannerAd.getButtontext());

                    // show rating or not and set rating image
                    if (bannerAd.getShowrating().equals("1")) {
                        iv_banner_star_rating.setVisibility(View.VISIBLE);
                        iv_banner_star_rating.setRating(Float.parseFloat(bannerAd.getRatingcount()));
                    } else {
                        iv_banner_star_rating.setVisibility(View.GONE);
                    }

                    // show reviews or not and set review count
                    if (bannerAd.getShowreview().equals("1")) {
                        tv_banner_review_count.setVisibility(View.VISIBLE);
                        tv_banner_review_count.setText("  ( " + bannerAd.getReviewcount() + " )");
                    } else {
                        tv_banner_review_count.setVisibility(View.GONE);
                    }

                    // extra text
                    tv_banner_extra_text.setText(bannerAd.getExtratext());

                    // check if double layout
                    if (bannerAd.getShowdouble().equals("1")) {
                        Handler handler = new Handler();
                        Runnable run = new Runnable() {
                            @Override
                            public void run() {
                                if (lay_first.getVisibility() == View.VISIBLE) {
                                    lay_first.setVisibility(View.GONE);
                                    lay_second.setVisibility(View.VISIBLE);
                                } else {
                                    lay_first.setVisibility(View.VISIBLE);
                                    lay_second.setVisibility(View.GONE);
                                }
                                handler.postDelayed(this, 3000);
                            }
                        };

                        handler.post(run);

                    } else {
                        lay_first.setVisibility(View.VISIBLE);
                        lay_second.setVisibility(View.GONE);
                    }

                    // set selected
                    tv_banner_ad_title.setSelected(true);
                    tv_banner_ad_subtitle.setSelected(true);
                    tv_banner_extra_text.setSelected(true);


                    iv_banner_info.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showAdsPrivacyDialog();
                        }
                    });

                    tv_install_btn_banner.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // open link
                            if (bannerAd.getOpenin().equals("playstore")) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(bannerAd.getApplink())));
                            } else {
                                Uri uri = Uri.parse(bannerAd.getApplink()); // missing 'http://' will cause crashed
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                            }
                        }
                    });
                    inhouseBannerListener.onAdLoaded();
                } else {
                    inhouseBannerListener.onAdShowFailed();
                }
            } else {
                ArrayList<BannerDetail> savedBannerDetails = adsPrefernce.getBannerAds();
                if (savedBannerDetails.size() != 0) {
                    // ad to show from position
                    int current = getCurrentBannerAd(savedBannerDetails.size());

                    ImageView iv_banner_info = findViewById(R.id.iv_banner_info);
                    ImageView iv_close_ad_banner = findViewById(R.id.iv_close_ad_banner);
                    ImageView iv_ad_icon_banner = findViewById(R.id.iv_ad_icon_banner);

                    TextView tv_banner_ad_title = findViewById(R.id.tv_banner_ad_title);
                    TextView tv_banner_ad_subtitle = findViewById(R.id.tv_banner_ad_subtitle);

                    RatingBar iv_banner_star_rating = findViewById(R.id.iv_banner_star_rating);
                    TextView tv_banner_review_count = findViewById(R.id.tv_banner_review_count);

                    TextView tv_install_btn_banner = findViewById(R.id.tv_install_btn_banner);
                    TextView tv_banner_extra_text = findViewById(R.id.tv_banner_extra_text);

                    RelativeLayout lay_first = findViewById(R.id.lay_first);
                    RelativeLayout lay_second = findViewById(R.id.lay_second);
                    RelativeLayout lay_banner_ad = findViewById(R.id.lay_banner_ad);

                    lay_banner_ad.setVisibility(View.VISIBLE);


                    // set Interstitial Data
                    BannerDetail bannerAd = savedBannerDetails.get(current);

                    // icon
                    Glide.with(this).load(bannerAd.getIcon()).into(iv_ad_icon_banner);
                    // title
                    tv_banner_ad_title.setText(bannerAd.getTitle());
                    // subtitle
                    tv_banner_ad_subtitle.setText(bannerAd.getSubtitle());
                    // install button Text
                    tv_install_btn_banner.setText(bannerAd.getButtontext());

                    // show rating or not and set rating image
                    if (bannerAd.getShowrating().equals("1")) {
                        iv_banner_star_rating.setVisibility(View.VISIBLE);
                        iv_banner_star_rating.setRating(Float.parseFloat(bannerAd.getRatingcount()));
                    } else {
                        iv_banner_star_rating.setVisibility(View.GONE);
                    }

                    // show reviews or not and set review count
                    if (bannerAd.getShowreview().equals("1")) {
                        tv_banner_review_count.setVisibility(View.VISIBLE);
                        tv_banner_review_count.setText("  ( " + bannerAd.getReviewcount() + " )");
                    } else {
                        tv_banner_review_count.setVisibility(View.GONE);
                    }

                    // extra text
                    tv_banner_extra_text.setText(bannerAd.getExtratext());

                    // check if double layout
                    if (bannerAd.getShowdouble().equals("1")) {
                        Handler handler = new Handler();
                        Runnable run = new Runnable() {
                            @Override
                            public void run() {
                                if (lay_first.getVisibility() == View.VISIBLE) {
                                    lay_first.setVisibility(View.GONE);
                                    lay_second.setVisibility(View.VISIBLE);
                                } else {
                                    lay_first.setVisibility(View.VISIBLE);
                                    lay_second.setVisibility(View.GONE);
                                }
                                handler.postDelayed(this, 3000);
                            }
                        };

                        handler.post(run);

                    } else {
                        lay_first.setVisibility(View.VISIBLE);
                        lay_second.setVisibility(View.GONE);
                    }

                    // set selected
                    tv_banner_ad_title.setSelected(true);
                    tv_banner_ad_subtitle.setSelected(true);
                    tv_banner_extra_text.setSelected(true);


                    iv_banner_info.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showAdsPrivacyDialog();
                        }
                    });

                    tv_install_btn_banner.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // open link
                            if (bannerAd.getOpenin().equals("playstore")) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(bannerAd.getApplink())));
                            } else {
                                Uri uri = Uri.parse(bannerAd.getApplink()); // missing 'http://' will cause crashed
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                            }
                        }
                    });
                    inhouseBannerListener.onAdLoaded();
                }
            }
        }
    }

    private void inflateNativeAdInHouse(RelativeLayout native_ad_view, CardView cardView) {

        // Add the Ad view into the ad container.
//        NativeAdLayout nativeAdLayout = new NativeAdLayout(context);
        LayoutInflater inflater = LayoutInflater.from(this);
        RelativeLayout adViews = (RelativeLayout) inflater.inflate(R.layout.ad_native, cardView, false);
        cardView.addView(adViews);

        // get Interstitial Data
        ArrayList<NativeDetail> nativeDetails = adsPrefernce.getNativeAds();

        // ad to show from position
        int current = getCurrentNativeAd(finalNative.size());

        ImageView iv_native_info = adViews.findViewById(R.id.iv_native_info);
        ImageView iv_ad_icon_native = adViews.findViewById(R.id.iv_ad_icon_native);
        ImageView iv_native_main_banner = adViews.findViewById(R.id.iv_native_main_banner);

        TextView tv_native_ad_title = adViews.findViewById(R.id.tv_native_ad_title);
        TextView tv_native_ad_subtitle = adViews.findViewById(R.id.tv_native_ad_subtitle);

        RatingBar native_ad_rating = adViews.findViewById(R.id.native_ad_rating);
        TextView tv_native_review_count = adViews.findViewById(R.id.tv_native_review_count);

        TextView btn_ad_install_native = adViews.findViewById(R.id.btn_ad_install_native);
        TextView tv_native_extra_text = adViews.findViewById(R.id.tv_native_extra_text);

        RelativeLayout lay_native_ad = adViews.findViewById(R.id.lay_native_ad);

        lay_native_ad.setVisibility(View.VISIBLE);


        // set Interstitial Data
        NativeDetail nativeAd = nativeDetails.get(current);

        // icon
        Glide.with(this).load(nativeAd.getIcon()).into(iv_ad_icon_native);
        // banner
        Glide.with(this).load(nativeAd.getBigimage()).into(iv_native_main_banner);
        // title
        tv_native_ad_title.setText(nativeAd.getTitle());
        // subtitle
        tv_native_ad_subtitle.setText(nativeAd.getSubtitle());
        // install button Text
        btn_ad_install_native.setText(nativeAd.getButtontext());

        // show rating or not and set rating image
        if (nativeAd.getShowrating().equals("1")) {
            native_ad_rating.setVisibility(View.VISIBLE);
            native_ad_rating.setRating(Float.parseFloat(nativeAd.getRatingcount()));
        } else {
            native_ad_rating.setVisibility(View.GONE);
        }

        // show reviews or not and set review count
        if (nativeAd.getShowreview().equals("1")) {
            tv_native_review_count.setVisibility(View.VISIBLE);
            tv_native_review_count.setText("  ( " + nativeAd.getReviewcount() + " )");
        } else {
            tv_native_review_count.setVisibility(View.GONE);
        }

        // extra text
        tv_native_extra_text.setText(nativeAd.getExtratext());

        // set selected
        tv_native_ad_title.setSelected(true);
        tv_native_ad_subtitle.setSelected(true);
        tv_native_extra_text.setSelected(true);


        iv_native_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAdsPrivacyDialog();
            }
        });

        btn_ad_install_native.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open link
                if (nativeAd.getOpenin().equals("playstore")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(nativeAd.getApplink())));
                } else {
                    Uri uri = Uri.parse(nativeAd.getApplink()); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }
        });

    }


    public void showInhouseNativeAd(CardView cardView, InhouseNativeListener inhouseNativeListener) {

        if (adsPrefernce.isNativeAdLoaded()) {
            if (isNetworkAvailable(this)) {
                if (finalNative.size() != 0) {

                    inflateNativeAdInHouse((RelativeLayout) findViewById(R.id.lay_native_ad), cardView);
                    inhouseNativeListener.onAdLoaded();

                } else {
                    inhouseNativeListener.onAdShowFailed();
                }
            } else {
                inflateNativeAdInHouse((RelativeLayout) findViewById(R.id.lay_native_ad), cardView);
                inhouseNativeListener.onAdLoaded();
            }

        } else {
            inhouseNativeListener.onAdShowFailed();
        }
    }

//    public void showInhouseNativeAd(InhouseNativeListener inhouseNativeListener) {
//
//        if (adsPrefernce.isNativeAdLoaded()) {
//            if (isNetworkAvailable(this)) {
//                if (finalNative.size() != 0) {
//
//                    inflateNativeAdInHouse((RelativeLayout) findViewById(R.id.lay_native_ad), (CardView) findViewById(R.id.native_ad_cardview));
//                    inhouseNativeListener.onAdLoaded();
//
//                } else {
//                    inhouseNativeListener.onAdShowFailed();
//                }
//            } else {
//                inflateNativeAdInHouse((RelativeLayout) findViewById(R.id.lay_native_ad), (CardView) findViewById(R.id.native_ad_cardview));
//                inhouseNativeListener.onAdLoaded();
//            }
//
//        } else {
//            inhouseNativeListener.onAdShowFailed();
//        }
//    }

    void showAdsPrivacyDialog() {
        Dialog privacyDialog = new Dialog(BaseClass.this);
        privacyDialog.setContentView(R.layout.ads_privacy_dialog);
        Objects.requireNonNull(privacyDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        privacyDialog.setCancelable(false);
        TextView tv_ok_btn_ad_privacy = privacyDialog.findViewById(R.id.tv_ok_btn_ad_privacy);
        tv_ok_btn_ad_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                privacyDialog.dismiss();
            }
        });
        privacyDialog.show();

    }

    public void getInterstitalAdsInHouse(final String appKey) {

        interDetails = new ArrayList<InterDetail>();
        finalInter = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        gsonUtils = GsonUtils.getInstance();
        RequestParams params1 = new RequestParams();
        params1.put("app_key", appKey);
        try {

            client.setConnectTimeout(50000);

            client.post("http://inhouseads.winwingaming.com/api/get_interastital.php", params1, new BaseJsonHttpResponseHandler<InterstitialAdIH>() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, InterstitialAdIH response) {

                    Log.e("IHAds...", "Success");

                    interDetails.clear();
                    interDetails = new ArrayList<>();

                    if (response == null) {
                        return;
                    }
                    if (response.getSuccess() == 0) {

                        return;
                    }

                    interDetails.addAll(response.getInterDetail());


                    for (int i = 0; i < interDetails.size(); i++) {
                        InterDetail newInter = interDetails.get(i);


                        if (interDetails.get(i).getOpenin().equals("playstore")) {
                            Log.e("interinter...", "fromplaystore");

                            if (!appInstalledOrNot(getAppIdFromAppLink(interDetails.get(i).getApplink()))) {
                                Log.e("interinter...", "adeedfromplaystore");

                                finalInter.add(new InterDetail(interDetails.get(i).getIntId(), interDetails.get(i).getShowad(), interDetails.get(i).getOpenin(), interDetails.get(i).getApplink(), interDetails.get(i).getShowreview(), interDetails.get(i).getReviewcount(), interDetails.get(i).getShowrating(),
                                        interDetails.get(i).getRatingcount(), interDetails.get(i).getTitle(), interDetails.get(i).getSubtitle(), interDetails.get(i).getIcon(), interDetails.get(i).getButtontext(), interDetails.get(i).getBigimage(), interDetails.get(i).getBigimage2(), interDetails.get(i).getDescTitle(), interDetails.get(i).getDescText()));
                            }
                        } else {
                            Log.e("interinter...", "notfromplaystore");
                            finalInter.add(new InterDetail(interDetails.get(i).getIntId(), interDetails.get(i).getShowad(), interDetails.get(i).getOpenin(), interDetails.get(i).getApplink(), interDetails.get(i).getShowreview(), interDetails.get(i).getReviewcount(), interDetails.get(i).getShowrating(),
                                    interDetails.get(i).getRatingcount(), interDetails.get(i).getTitle(), interDetails.get(i).getSubtitle(), interDetails.get(i).getIcon(), interDetails.get(i).getButtontext(), interDetails.get(i).getBigimage(), interDetails.get(i).getBigimage2(), interDetails.get(i).getDescTitle(), interDetails.get(i).getDescText()));
                        }


                    }

                    adsPrefernce.setInterAdDetails(finalInter);
                    isInterAdLoadedIH = true;
                    Log.e("IHAds...", "Success = 1");
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, InterstitialAdIH errorResponse) {


                }

                @Override
                protected InterstitialAdIH parseResponse(String rawJsonData, boolean isFailure) throws Throwable {

                    try {
                        if (!isFailure && !rawJsonData.isEmpty()) {
                            return gsonUtils.getGson().fromJson(rawJsonData, InterstitialAdIH.class);
                        }
                    } catch (Exception ignored) {

                    }
                    return null;
                }
            });

        } catch (Exception ignored) {

        }

    }

    public void getBannerAdsInHouse(final String appKey) {
        finalBanner = new ArrayList<>();
        bannerDetails = new ArrayList<BannerDetail>();
        AsyncHttpClient client = new AsyncHttpClient();
        gsonUtils = GsonUtils.getInstance();
        RequestParams params1 = new RequestParams();
        params1.put("app_key", appKey);
        try {

            client.setConnectTimeout(50000);

            client.post("http://inhouseads.winwingaming.com/api/get_banner.php", params1, new BaseJsonHttpResponseHandler<BannerAdIH>() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, BannerAdIH response) {

                    Log.e("IHAdsBanner...", "Success");

                    bannerDetails.clear();
                    bannerDetails = new ArrayList<>();

                    if (response == null) {
                        return;
                    }
                    if (response.getSuccess() == 0) {

                        return;
                    }

                    bannerDetails.addAll(response.getBannerDetail());

                    for (int i = 0; i < bannerDetails.size(); i++) {

                        if (bannerDetails.get(i).getOpenin().equals("playstore")) {
                            if (!appInstalledOrNot(getAppIdFromAppLink(bannerDetails.get(i).getApplink()))) {
                                finalBanner.add(new BannerDetail(bannerDetails.get(i).getBannerId(),
                                        bannerDetails.get(i).getShowad(),
                                        bannerDetails.get(i).getOpenin(),
                                        bannerDetails.get(i).getApplink(),
                                        bannerDetails.get(i).getShowreview(),
                                        bannerDetails.get(i).getReviewcount(),
                                        bannerDetails.get(i).getShowrating(),
                                        bannerDetails.get(i).getShowdouble(),
                                        bannerDetails.get(i).getRatingcount(),
                                        bannerDetails.get(i).getTitle(),
                                        bannerDetails.get(i).getSubtitle(),
                                        bannerDetails.get(i).getIcon(),
                                        bannerDetails.get(i).getExtratext(),
                                        bannerDetails.get(i).getButtontext()));
                            }
                        } else {
                            finalBanner.add(new BannerDetail(bannerDetails.get(i).getBannerId(),
                                    bannerDetails.get(i).getShowad(),
                                    bannerDetails.get(i).getOpenin(),
                                    bannerDetails.get(i).getApplink(),
                                    bannerDetails.get(i).getShowreview(),
                                    bannerDetails.get(i).getReviewcount(),
                                    bannerDetails.get(i).getShowrating(),
                                    bannerDetails.get(i).getShowdouble(),
                                    bannerDetails.get(i).getRatingcount(),
                                    bannerDetails.get(i).getTitle(),
                                    bannerDetails.get(i).getSubtitle(),
                                    bannerDetails.get(i).getIcon(),
                                    bannerDetails.get(i).getExtratext(),
                                    bannerDetails.get(i).getButtontext()));

                        }


                    }
                    adsPrefernce.setBannerAdDetails(finalBanner);
                    isBannerAdLoadedIH = true;
                    Log.e("IHAdsBanner...", "Success = 1");
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, BannerAdIH errorResponse) {


                }

                @Override
                protected BannerAdIH parseResponse(String rawJsonData, boolean isFailure) throws Throwable {

                    try {
                        if (!isFailure && !rawJsonData.isEmpty()) {
                            return gsonUtils.getGson().fromJson(rawJsonData, BannerAdIH.class);
                        }
                    } catch (Exception ignored) {

                    }
                    return null;
                }
            });

        } catch (Exception ignored) {

        }

    }

    public void getNativeAdsInHouse(final String appKey) {

        nativeDetails = new ArrayList<NativeDetail>();
        finalNative = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        gsonUtils = GsonUtils.getInstance();
        RequestParams params1 = new RequestParams();
        params1.put("app_key", appKey);
        try {

            client.setConnectTimeout(50000);

            client.post("http://inhouseads.winwingaming.com/api/get_native.php", params1, new BaseJsonHttpResponseHandler<NativeAdIH>() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, NativeAdIH response) {

                    Log.e("IHAdsNative...", "Success");

                    nativeDetails.clear();
                    nativeDetails = new ArrayList<>();


                    if (response == null) {
                        return;
                    }
                    if (response.getSuccess() == 0) {

                        return;
                    }

                    nativeDetails.addAll(response.getNativeDetail());

                    for (int i = 0; i < nativeDetails.size(); i++) {

                        if (nativeDetails.get(i).getOpenin().equals("playstore")) {
                            if (!appInstalledOrNot(getAppIdFromAppLink(nativeDetails.get(i).getApplink()))) {
                                finalNative.add(new NativeDetail(nativeDetails.get(i).getNativeId(),
                                        nativeDetails.get(i).getShowad()
                                        , nativeDetails.get(i).getOpenin()
                                        , nativeDetails.get(i).getApplink()
                                        , nativeDetails.get(i).getShowreview()
                                        , nativeDetails.get(i).getReviewcount()
                                        , nativeDetails.get(i).getShowrating()
                                        , nativeDetails.get(i).getRatingcount()
                                        , nativeDetails.get(i).getTitle()
                                        , nativeDetails.get(i).getSubtitle()
                                        , nativeDetails.get(i).getExtratext()
                                        , nativeDetails.get(i).getIcon()
                                        , nativeDetails.get(i).getBigimage()
                                        , nativeDetails.get(i).getButtontext()
                                ));
                            }
                        } else {
                            finalNative.add(new NativeDetail(nativeDetails.get(i).getNativeId(),
                                    nativeDetails.get(i).getShowad()
                                    , nativeDetails.get(i).getOpenin()
                                    , nativeDetails.get(i).getApplink()
                                    , nativeDetails.get(i).getShowreview()
                                    , nativeDetails.get(i).getReviewcount()
                                    , nativeDetails.get(i).getShowrating()
                                    , nativeDetails.get(i).getRatingcount()
                                    , nativeDetails.get(i).getTitle()
                                    , nativeDetails.get(i).getSubtitle()
                                    , nativeDetails.get(i).getExtratext()
                                    , nativeDetails.get(i).getIcon()
                                    , nativeDetails.get(i).getBigimage()
                                    , nativeDetails.get(i).getButtontext()
                            ));
                        }


                    }
                    adsPrefernce.setNativeAdDetails(finalNative);
                    isNativeAdLoadedIH = true;
                    Log.e("IHAdsNative...", "Success = 1");


                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, NativeAdIH errorResponse) {


                }

                @Override
                protected NativeAdIH parseResponse(String rawJsonData, boolean isFailure) throws Throwable {

                    try {
                        if (!isFailure && !rawJsonData.isEmpty()) {
                            return gsonUtils.getGson().fromJson(rawJsonData, NativeAdIH.class);
                        }
                    } catch (Exception ignored) {

                    }
                    return null;
                }
            });

        } catch (Exception ignored) {

        }

    }

    public boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }


    @Override
    public void networkAvailable() {
        if (!isInterAdLoadedIH) {
            getInterstitalAdsInHouse(defaultIds.APP_KEY());
        }
        if (!isBannerAdLoadedIH) {
            getBannerAdsInHouse(defaultIds.APP_KEY());
        }
        if (!isNativeAdLoadedIH) {
            getNativeAdsInHouse(defaultIds.APP_KEY());
        }

        if (isNetworkAvailable(BaseClass.this)) {
            if (!isAdsAvailable) {
                getAds(defaultIds.APP_KEY());
            }
        }

        onNetworkChangeListner = (OnNetworkChangeListner) this;
        onNetworkChangeListner.onInternetConnected();


    }

    @Override
    public void networkUnavailable() {
        onNetworkChangeListner = (OnNetworkChangeListner) this;
        onNetworkChangeListner.onInternetDisconnected();
    }
}
