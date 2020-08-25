package com.newadscompanion.BaseUtils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.appnext.ads.interstitial.Interstitial;
import com.appnext.banners.BannerAdRequest;
import com.appnext.banners.BannerSize;
import com.appnext.banners.BannerView;
import com.appnext.base.Appnext;
import com.appnext.core.AppnextAdCreativeType;
import com.appnext.core.AppnextError;
import com.appnext.core.callbacks.OnAdClosed;
import com.appnext.core.callbacks.OnAdLoaded;
import com.appnext.core.callbacks.OnAdOpened;
import com.bumptech.glide.Glide;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdIconView;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mopub.common.MoPub;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.common.privacy.PersonalInfoManager;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubView;
import com.newadscompanion.AdsConfig.DefaultIds;
import com.newadscompanion.Interfaces.OnPlayVerificationFailed;
import com.newadscompanion.ModelsCompanion.AdsData;
import com.newadscompanion.ModelsCompanion.AdsIdsList;
import com.newadscompanion.ModelsCompanion.AdsPrefernce;
import com.newadscompanion.ModelsCompanion.GsonUtils;
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
import java.util.Objects;
import java.util.concurrent.Callable;

import cz.msebera.android.httpclient.Header;

public class BaseClass extends AppCompatActivity {


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

    public static boolean isvalidInstall = false;


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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        isvalidInstall = verifyInstallerId(this);
        Log.e("validation", String.valueOf(isvalidInstall));

        // initialize appnext sdk
        Appnext.init(this);
        progressDialog = new ProgressDialog(this);
        defaultIds = new DefaultIds(this);
        adsPrefernce = new AdsPrefernce(this);

        //initilize mopub banner
//        initializeMoPubSDKforBanner(defaultIds.MP_BANNER(), false);


        // initialize startapp sdk
        StartAppSDK.init(BaseClass.this, defaultIds.SA_APP_ID(), false);
        StartAppAd.disableAutoInterstitial();
        StartAppAd.enableConsent(this, false);
        //disable startapp splash
        if (defaultIds.DISABLE_SA_SPLASH()) {
            StartAppAd.disableSplash();
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
                        });
                    } else {
                        final NativeBannerAd nativeBannerAd;
                        if (adsPrefernce.showfbNativeBanner()) {
                            AudienceNetworkAds.initialize(this);
                            nativeBannerAd = new NativeBannerAd(this, adsPrefernce.fbNativeBannerId());
                            final FrameLayout adContainerView = findViewById(R.id.native_banner_container);
                            adContainerView.setVisibility(View.VISIBLE);
                            adContainerView.setPadding(0, top, 0, bottom);
                            nativeBannerAd.loadAd();
                            nativeBannerAd.setAdListener(new NativeAdListener() {
                                @Override
                                public void onMediaDownloaded(Ad ad) {
                                    // Native ad finished downloading all assets
                                }

                                @Override
                                public void onError(Ad ad, AdError adError) {
                                    // Native ad failed to load
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
                            });
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
                final NativeBannerAd nativeBannerAd;
                AudienceNetworkAds.initialize(this);
                nativeBannerAd = new NativeBannerAd(this, defaultIds.FB_NATIVE_BANNER());
                final FrameLayout adContainerView = findViewById(R.id.native_banner_container);
                adContainerView.setVisibility(View.VISIBLE);
                adContainerView.setPadding(0, top, 0, bottom);
                nativeBannerAd.loadAd();
                nativeBannerAd.setAdListener(new NativeAdListener() {
                    @Override
                    public void onMediaDownloaded(Ad ad) {
                        // Native ad finished downloading all assets
                    }

                    @Override
                    public void onError(Ad ad, AdError adError) {
                        // Native ad failed to load
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
        AdIconView nativeAdIconView = adView.findViewById(R.id.native_icon_view);
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
                        });
                    } else if (adsPrefernce.showfbBanner()) {
                        com.facebook.ads.AdView adView;

                        AudienceNetworkAds.initialize(this);
                        adView = new com.facebook.ads.AdView(this, adsPrefernce.fbBannerId(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                        final FrameLayout adContainerView = findViewById(R.id.banner_container);
                        adContainerView.setVisibility(View.VISIBLE);
                        adContainerView.addView(adView);
                        adContainerView.setPadding(0, top, 0, bottom);
                        adView.loadAd();
                        adView.setAdListener(new AdListener() {
                            @Override
                            public void onError(Ad ad, AdError adError) {

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
                        });

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
                                Log.e("an banner error: ", String.valueOf(appnextError));
                            }
                        });
                    } else if (adsPrefernce.showmpBanner()) {
                        MoPubView moPubView;
                        moPubView = new MoPubView(this);
                        Log.e("MP Ads...", "Banner Call");

                        Log.e("MP Ads...", "showmpBanner");
                        if (mpBannerInitilized) {
                            Log.e("MP Ads...", "mpBannerInitilized true");
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
                                    Log.e("mp banner error: ", String.valueOf(errorCode));

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
                            Log.e("MP Ads...", "mpBannerInitilized true");
                            initializeMoPubSDK();
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
                com.facebook.ads.AdView adView;
                AudienceNetworkAds.initialize(this);
                adView = new com.facebook.ads.AdView(this, defaultIds.FB_BANNER(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                final FrameLayout adContainerView = findViewById(R.id.banner_container);
                adContainerView.setVisibility(View.VISIBLE);
                adContainerView.setPadding(0, top, 0, bottom);
                adContainerView.addView(adView);
                adView.loadAd();
                adView.setAdListener(new AdListener() {
                    @Override
                    public void onError(Ad ad, AdError adError) {

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
                });

            }
        }
    }

    public void resetAllBannerBoolean() {
        isGBannerShown = false;
        isFbBannerShown = false;
        isAnBannerShown = false;
        isMpBannerShown = false;
    }

    public void showMpBanner(int top, int bottom) {
        MoPubView moPubView;
        moPubView = new MoPubView(this);
        if (mpBannerInitilized) {
            Log.e("MP Ads...", "mpBannerInitilized true");
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
                    Log.e("mp banner error: ", String.valueOf(errorCode));

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
            Log.e("MP Ads...", "mpBannerInitilized true");
            initializeMoPubSDK();
        }
    }


    public void showAnBanner(int top, int bottom) {
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
                Log.e("an banner error: ", String.valueOf(appnextError));
            }
        });
    }


    public void showFbBanner(int top, int bottom) {
        com.facebook.ads.AdView adView;
        AudienceNetworkAds.initialize(this);
        adView = new com.facebook.ads.AdView(this, adsPrefernce.fbBannerId(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
        final FrameLayout adContainerView = findViewById(R.id.banner_container);
        adContainerView.setVisibility(View.VISIBLE);
        adContainerView.addView(adView);
        adContainerView.setPadding(0, top, 0, bottom);
        adView.loadAd();
        adView.setAdListener(new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
                isFbBannerShown = true;
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
        });
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
        });
    }

    public void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void toast(String text, Boolean longToast) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    //new without validation static ads

//    public void loadSplashInterstitial() {
//        adsPrefernce = new AdsPrefernce(this);
//        if (isNetworkAvailable(this)) {
//            if (!adsPrefernce.planD()) {
//                if (!adsPrefernce.isMediationActive()) {
//                    if (adsPrefernce.planA()) {
//                        Log.e("splash","planA true");
//                        if (adsPrefernce.showgInter1()) {
//                            Log.e("splash","showgInter1 true");
//                                MobileAds.initialize(getApplicationContext(), adsPrefernce.gAppId());
//                                gInterstitial1 = new com.google.android.gms.ads.InterstitialAd(this);
//                                gInterstitial1.setAdUnitId(adsPrefernce.gInterId1());
//                                gInterstitial1.loadAd(new AdRequest.Builder().build());
//                                gInterstitial1.setAdListener(new com.google.android.gms.ads.AdListener() {
//                                    @Override
//                                    public void onAdLoaded() {
//                                        super.onAdLoaded();
//                                        Log.e("splash","gInter1 AdLoaded");
//
////                                        isGInter1Ready = true;
//                                    }
//                                });
//
//                        }
//                        if (adsPrefernce.showfbInter1()) {
//                                AudienceNetworkAds.initialize(this);
//                                fbInterstitial1 = new com.facebook.ads.InterstitialAd(this, adsPrefernce.fbInterId1());
//                                fbInterstitial1.loadAd();
//                                fbInterstitial1.setAdListener(new InterstitialAdListener() {
//                                    @Override
//                                    public void onInterstitialDisplayed(Ad ad) {
//
//                                    }
//
//                                    @Override
//                                    public void onInterstitialDismissed(Ad ad) {
//
//                                    }
//
//                                    @Override
//                                    public void onError(Ad ad, AdError adError) {
//
//                                    }
//
//                                    @Override
//                                    public void onAdLoaded(Ad ad) {
////                                        isFbInter1Ready = true;
//                                    }
//
//                                    @Override
//                                    public void onAdClicked(Ad ad) {
//
//                                    }
//
//                                    @Override
//                                    public void onLoggingImpression(Ad ad) {
//
//                                    }
//                                });
//
//
//                        }
//
//                    }
//                    if (adsPrefernce.planB()) {
//                        if (adsPrefernce.showanInter1()) {
//                            an_interstitial_Ad1 = new Interstitial(this, adsPrefernce.anAdId());
//                                an_interstitial_Ad1.loadAd();
//                                an_interstitial_Ad1.setOnAdLoadedCallback(new OnAdLoaded() {
//                                    @Override
//                                    public void adLoaded(String s, AppnextAdCreativeType appnextAdCreativeType) {
////                                        isAnInter1Ready = true;
//                                    }
//                                });
//
//                        }
//                    }
//                    if (adsPrefernce.planC()) {
//                        if (adsPrefernce.showmpInter1()) {
//                            if (mpInter1Initilized) {
//                                mpInterstitial1 = new MoPubInterstitial((Activity) this, adsPrefernce.mpInterId1());
//                                     mpInterstitial1.load();
//                                    mpInterstitial1.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
//                                        @Override
//                                        public void onInterstitialLoaded(MoPubInterstitial interstitial) {
////                                            isMpInter1Ready = true;
//                                        }
//
//                                        @Override
//                                        public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
//
//                                        }
//
//                                        @Override
//                                        public void onInterstitialShown(MoPubInterstitial interstitial) {
//
//                                        }
//
//                                        @Override
//                                        public void onInterstitialClicked(MoPubInterstitial interstitial) {
//
//                                        }
//
//                                        @Override
//                                        public void onInterstitialDismissed(MoPubInterstitial interstitial) {
//
//                                        }
//                                    });
//
//                            } else {
//                                initializeMoPubSDK();
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    public void showSplashInterstitial() {
//        if (!adsPrefernce.planD()) {
//            Log.e("splash","planD false");
//            if (adsPrefernce.allowAccess()) {
//                Log.e("splash","allowAccess true");
////                if (isvalidInstall) {
//                    if (!adsPrefernce.isMediationActive()) {
//                        Log.e("splash","isMediationActive false");
//                        showSplashAd();
//                    } else {
//                        showMixedInterAds();
//                    }
////                }
//            }
//
//        }
//    }
//
//    public void  showSplashAd() {
//        if (isNetworkAvailable(this)) {
//            if (adsPrefernce.planA()) {
//                Log.e("splash show","in Plan A");
//                if (adsPrefernce.showgInter1()) {
//                    Log.e("splash show","howgInter1 true");
//                    if (gInterstitial1.isLoaded() && gInterstitial1 != null) {
//                        Log.e("splash show"," ginter 1 loaded");
//                        gInterstitial1.show();
//                            gInterstitial1.setAdListener(new com.google.android.gms.ads.AdListener() {
//                                public void onAdClosed() {
//                                    gInterstitial1.loadAd(new AdRequest.Builder().build());
//                                }
//
//                                @Override
//                                public void onAdOpened() {
//                                    super.onAdOpened();
////                                    isGInter1Ready = false;
////                                    isGInter1Shown = true;
//                                }
//                            });
//                        }
//
//                } else if (adsPrefernce.showfbInter1()) {
////                    if (isFbInter1Ready) {
//                        if (fbInterstitial1.isAdLoaded()) {
//                            fbInterstitial1.show();
//                            fbInterstitial1.setAdListener(new InterstitialAdListener() {
//                                @Override
//                                public void onInterstitialDisplayed(Ad ad) {
////                                    isFbInter1Ready = false;
////                                    isFbInter1Shown = true;
//                                }
//
//                                @Override
//                                public void onInterstitialDismissed(Ad ad) {
//                                    fbInterstitial1.loadAd();
//                                }
//
//                                @Override
//                                public void onError(Ad ad, AdError adError) {
//
//                                }
//
//                                @Override
//                                public void onAdLoaded(Ad ad) {
//
//                                }
//
//                                @Override
//                                public void onAdClicked(Ad ad) {
//
//                                }
//
//                                @Override
//                                public void onLoggingImpression(Ad ad) {
//
//                                }
//                            });
//                        }
//
////                    }
//                }
//            } else if (adsPrefernce.planB()) {
//                if (adsPrefernce.showanInter1()) {
////                    if (isAnInter1Ready) {
//                        if (an_interstitial_Ad1.isAdLoaded()) {
//                            an_interstitial_Ad1.showAd();
//                            an_interstitial_Ad1.setOnAdClosedCallback(new OnAdClosed() {
//                                @Override
//                                public void onAdClosed() {
//                                    loadInterstitial1();
//                                }
//                            });
//                            an_interstitial_Ad1.setOnAdOpenedCallback(new OnAdOpened() {
//                                @Override
//                                public void adOpened() {
////                                    isAnInter1Ready = false;
////                                    isAnInter1Shown = true;
//                                }
//                            });
//                        }
////                    }
//                }
//            } else if (adsPrefernce.planC()) {
//                if (adsPrefernce.showmpInter1()) {
////                    if (isMpInter1Ready) {
//                        if (mpInterstitial1 != null) {
//                            if (mpInterstitial1.isReady()) {
//                                mpInterstitial1.show();
//                                mpInterstitial1.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
//                                    @Override
//                                    public void onInterstitialLoaded(MoPubInterstitial interstitial) {
//
//                                    }
//
//                                    @Override
//                                    public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
//
//                                    }
//
//                                    @Override
//                                    public void onInterstitialShown(MoPubInterstitial interstitial) {
////                                        isMpInter1Ready = false;
//                                    }
//
//                                    @Override
//                                    public void onInterstitialClicked(MoPubInterstitial interstitial) {
//
//                                    }
//
//                                    @Override
//                                    public void onInterstitialDismissed(MoPubInterstitial interstitial) {
//
//                                        mpInterstitial1.load();
//                                        mpInterstitial1.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
//                                            @Override
//                                            public void onInterstitialLoaded(MoPubInterstitial interstitial) {
//                                                Log.e("inLoadonClosed", "ismpinter1 ready = true");
////                                                isMpInter1Ready = true;
//                                            }
//
//                                            @Override
//                                            public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
//
//                                            }
//
//                                            @Override
//                                            public void onInterstitialShown(MoPubInterstitial interstitial) {
//
//                                            }
//
//                                            @Override
//                                            public void onInterstitialClicked(MoPubInterstitial interstitial) {
//
//                                            }
//
//                                            @Override
//                                            public void onInterstitialDismissed(MoPubInterstitial interstitial) {
//
//                                            }
//                                        });
//
//
//                                    }
//                                });
//                            }
//                        }
////                    }
//                }
//            }
//
//
//        }
//    }


    //old with validation

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
                                fbInterstitial1.loadAd();
                                fbInterstitial1.setAdListener(new InterstitialAdListener() {
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
                                });
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

    public void showSplashInterstitial() {
        if (!adsPrefernce.planD()) {
            if (adsPrefernce.allowAccess()) {
//                if (isvalidInstall) {
                if (!adsPrefernce.isMediationActive()) {
                    showSplashAd();
                } else {
                    showMixedInterAds();
                }
//                }
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
                            fbInterstitial1.setAdListener(new InterstitialAdListener() {
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
                            });
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
                                fbInterstitial1.loadAd();
                                fbInterstitial1.setAdListener(new InterstitialAdListener() {
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
                                });
                            }
                        }
                        if (adsPrefernce.showfbInter2()) {
                            if (!isFbInter2Ready) {
                                AudienceNetworkAds.initialize(this);
                                fbInterstitial2 = new com.facebook.ads.InterstitialAd(this, adsPrefernce.fbInterId2());
                                fbInterstitial2.loadAd();
                                fbInterstitial2.setAdListener(new InterstitialAdListener() {
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
                                });
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
            } else {
                // if ad data not downloaded
                if (!isFbInter1Ready) {
                    fbInterstitial1 = new com.facebook.ads.InterstitialAd(this, defaultIds.FB_INTER1());
                    fbInterstitial1.loadAd();
                    fbInterstitial1.setAdListener(new InterstitialAdListener() {
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
                    });
                }

            }
        }

    }

    public void showMixedInterAds() {
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
                                        });
                                    } else {
                                        goToPlanA2();
                                    }
                                } else {
                                    goToPlanA2();
                                }
                            } else {
                                goToPlanA2();
                            }
                        } else {
                            goToPlanA2();
                        }
                    } else {
                        goToPlanB();
                    }
                } else {
                    if (isFbInter1Ready) {
                        if (fbInterstitial1.isAdLoaded()) {
                            fbInterstitial1.show();
                            fbInterstitial1.setAdListener(new InterstitialAdListener() {
                                @Override
                                public void onInterstitialDisplayed(Ad ad) {
                                    isFbInter1Ready = false;
                                    isFbInter1Shown = true;
                                    Log.e("Ads...", "in else");

                                }

                                @Override
                                public void onInterstitialDismissed(Ad ad) {
                                    fbInterstitial1.loadAd();
                                    fbInterstitial1.setAdListener(new InterstitialAdListener() {
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
                                    });
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
                            });
                        }
                    }
                }
            }

        }

    }

    public void goToPlanA2() {

        if (adsPrefernce.showfbInter1()) {
            if (!isFbInter1Shown) {
                if (isFbInter1Ready) {
                    if (fbInterstitial1.isAdLoaded()) {
                        fbInterstitial1.show();
                        fbInterstitial1.setAdListener(new InterstitialAdListener() {
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
                                fbInterstitial1.loadAd();
                                fbInterstitial1.setAdListener(new InterstitialAdListener() {
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
                                });
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
                        });
                    } else {
                        goToPlanB();
                    }
                } else {
                    goToPlanB();
                }
            } else {
                goToPlanB();
            }

        } else {
            goToPlanB();
        }

    }

    public void goToPlanB() {
        if (adsPrefernce.planB()) {
            if (adsPrefernce.showanInter1()) {
                if (!isAnInter1Shown) {
                    if (isAnInter1Ready) {
                        if (an_interstitial_Ad1.isAdLoaded()) {
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
                        } else {
                            goToPlanC();
                        }
                    } else {
                        goToPlanC();
                    }
                } else {
                    goToPlanC();
                }
            } else {
                goToPlanC();
            }

        } else {
            goToPlanC();
        }
    }

    public void goToPlanC() {
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
                            goToPlanAI2();
                        }
                    } else {
                        goToPlanAI2();
                    }
                } else {
                    goToPlanAI2();
                }
            } else {
                goToPlanAI2();
            }

        } else {
            goToPlanAI2();
        }

    }

    public void goToPlanAI2() {
        if (adsPrefernce.planA()) {
            if (adsPrefernce.showgInter2()) {
                if (!isGInter2Shown) {
                    if (isGInter2Ready) {
                        if (gInterstitial2.isLoaded() && gInterstitial2 != null) {
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
                            });
                        } else {
                            goToPlanA2I2();
                        }
                    } else {
                        goToPlanA2I2();
                    }
                } else {
                    goToPlanA2I2();
                }
            } else {
                goToPlanA2I2();
            }
        } else {
            goToPlanBI2();
        }

    }

    public void goToPlanA2I2() {

        if (adsPrefernce.showfbInter2()) {
            if (!isFbInter2Shown) {
                if (isFbInter2Ready) {
                    if (fbInterstitial2.isAdLoaded()) {
                        fbInterstitial2.show();
                        fbInterstitial2.setAdListener(new InterstitialAdListener() {
                            @Override
                            public void onInterstitialDisplayed(Ad ad) {
                                Log.e("Ads...", "fb 2 displayed");
                                isFbInter2Ready = false;
                                isFbInter2Shown = true;
                            }

                            @Override
                            public void onInterstitialDismissed(Ad ad) {
                                Log.e("Ads...", "fb 2 dismissed");
                                AudienceNetworkAds.initialize(BaseClass.this);
                                fbInterstitial2 = new com.facebook.ads.InterstitialAd(BaseClass.this, adsPrefernce.fbInterId2());
                                fbInterstitial2.loadAd();
                                fbInterstitial2.setAdListener(new InterstitialAdListener() {
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
                                });
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
                        });
                    } else {
                        goToPlanBI2();
                    }
                } else {
                    goToPlanBI2();
                }
            } else {
                goToPlanBI2();
            }

        } else {
            goToPlanBI2();
        }

    }

    public void goToPlanBI2() {
        if (adsPrefernce.planB()) {
            if (adsPrefernce.showanInter2()) {
                if (!isAnInter2Shown) {
                    if (isAnInter1Ready) {
                        if (an_interstitial_Ad1.isAdLoaded()) {
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
                        } else {
                            goToPlanCI2();
                        }
                    } else {
                        goToPlanCI2();
                    }
                } else {
                    goToPlanCI2();
                }
            } else {
                goToPlanCI2();
            }

        } else {
            goToPlanCI2();
        }
    }

    public void goToPlanCI2() {
        if (adsPrefernce.planC()) {
            if (adsPrefernce.showmpInter2()) {
                if (!isMpInter2Shown) {
                    if (mpInterstitial2 != null) {
                        if (mpInterstitial2.isReady()) {
                            mpInterstitial2.show();
                            mpInterstitial2.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                                @Override
                                public void onInterstitialLoaded(MoPubInterstitial interstitial) {

                                }

                                @Override
                                public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {

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
                            resetAllShownBoolean();
                        }
                    } else {
                        resetAllShownBoolean();
                    }
                } else {
                    resetAllShownBoolean();
                }
            } else {
                resetAllShownBoolean();
            }

        } else {
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
                    if (isFbInter1Ready) {
                        if (fbInterstitial1.isAdLoaded()) {
                            fbInterstitial1.show();
                            fbInterstitial1.setAdListener(new InterstitialAdListener() {
                                @Override
                                public void onInterstitialDisplayed(Ad ad) {
                                    isFbInter1Ready = false;
                                    isFbInter1Shown = true;


                                }

                                @Override
                                public void onInterstitialDismissed(Ad ad) {
                                    fbInterstitial1.loadAd();
                                    fbInterstitial1.setAdListener(new InterstitialAdListener() {
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
                                    });
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
                            });
                        }
                    }
                }
            }

        }

    }

    public void goToPlanA2OnClosed(Callable<Void> mathodToPerform) {
        if (adsPrefernce.showfbInter1()) {
            if (!isFbInter1Shown) {
                if (isFbInter1Ready) {
                    if (fbInterstitial1.isAdLoaded()) {
                        fbInterstitial1.show();
                        fbInterstitial1.setAdListener(new InterstitialAdListener() {
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
                                fbInterstitial1.loadAd();
                                fbInterstitial1.setAdListener(new InterstitialAdListener() {
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
                                });
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
                        });
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
                            super.onBackPressed();
                        }
                    } else {
                        resetAllShownBoolean();
                        super.onBackPressed();
                    }
                } else {
                    resetAllShownBoolean();
                    super.onBackPressed();
                }
            } else {
                resetAllShownBoolean();
                super.onBackPressed();
            }

        } else {
            resetAllShownBoolean();
            super.onBackPressed();
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
            } else {
                fbInterstitial11 = new com.facebook.ads.InterstitialAd(this, defaultIds.FB_INTER1());
                fbInterstitial11.loadAd();
                fbInterstitial11.setAdListener(new InterstitialAdListener() {
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

                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                });
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
        } else {
            fbInterstitial22 = new com.facebook.ads.InterstitialAd(this, defaultIds.FB_INTER2());
            fbInterstitial22.loadAd();
            fbInterstitial22.setAdListener(new InterstitialAdListener() {
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

                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            });
        }

    }


    public void InterstitialAd2(final boolean loadOnClosed) {
        if (isNetworkAvailable(this)) {
            if (isAdsAvailable) {
                if (adsPrefernce.planA()) {
                    if (adsPrefernce.showgInter2()) {
                        Log.e("inter2", "show: showgInter2");
                        if (gInterstitial22.isLoaded() && gInterstitial22 != null) {
                            Log.e("inter2", "show: isGInter2 isLoaded = true");
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
                            });
                        }
                    } else if (adsPrefernce.showfbInter2()) {
                        if (fbInterstitial22.isAdLoaded()) {
                            fbInterstitial22.show();
                            fbInterstitial22.setAdListener(new InterstitialAdListener() {
                                @Override
                                public void onInterstitialDisplayed(Ad ad) {
                                    Log.e("Inter2", "fb2");
                                }

                                @Override
                                public void onInterstitialDismissed(Ad ad) {
                                    if (loadOnClosed) {
                                        loadInterstitial2();
                                    }
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
                            });
                        }


                    }

                } else if (adsPrefernce.planB()) {
                    Log.e("inShow", "in plan B");
                    if (adsPrefernce.showanInter1()) {
                        Log.e("inShow", "in showmp inter 1");

                        if (an_interstitial_Ad11.isAdLoaded()) {
                            Log.e("inShow", "in mp inter loaded = true");
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
                        }

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

                            }
                        }
                    }
                }
            }
        } else {
            if (fbInterstitial22.isAdLoaded()) {
                fbInterstitial22.show();
                fbInterstitial22.setAdListener(new InterstitialAdListener() {
                    @Override
                    public void onInterstitialDisplayed(Ad ad) {
                        Log.e("Inter2", "fb 2 in else");
                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        if (loadOnClosed) {
                            loadInterstitial2();
                        }
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
                });
            }


        }
    }


    public void InterstitialAd1(final boolean loadOnClosed) {
        if (isNetworkAvailable(this)) {
            if (isAdsAvailable) {
                if (adsPrefernce.planA()) {
                    if (adsPrefernce.showgInter1()) {
                        if (gInterstitial11.isLoaded() && gInterstitial11 != null) {
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


                        }

                    } else {
                        if (adsPrefernce.showfbInter1()) {

                            if (fbInterstitial11.isAdLoaded()) {
                                fbInterstitial11.show();
                                fbInterstitial11.setAdListener(new InterstitialAdListener() {
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
                                });
                            }


                        }
                    }
                } else if (adsPrefernce.planB()) {
                    Log.e("inShow", "in plan B");
                    if (adsPrefernce.showanInter1()) {
                        Log.e("inShow", "in showmp inter 1");

                        if (an_interstitial_Ad11.isAdLoaded()) {
                            Log.e("inShow", "in mp inter loaded = true");
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
                        }

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
                            }
                        }

                    }
                }
            } else {
                if (fbInterstitial11.isAdLoaded()) {
                    fbInterstitial11.show();
                    fbInterstitial11.setAdListener(new InterstitialAdListener() {
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
                    });
                }


            }
        }
    }

    public void showInterstitial1(final boolean loadOnClosed,
                                  final Callable<Void> mathodToFollow) {
        if (adsPrefernce.showLoading()) {
            proceedWithDelay(1000, "Showing Ad...", new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    try {
                        mathodToFollow.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (!adsPrefernce.planD()) {
                        if (!adsPrefernce.isMediationActive()) {
                            InterstitialAd1(loadOnClosed);
                        } else {
                            showMixedInterAds();
                        }
                    }
                    return null;
                }
            });
        } else {
            try {
                mathodToFollow.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!adsPrefernce.planD()) {
                if (!adsPrefernce.isMediationActive()) {
                    InterstitialAd1(loadOnClosed);
                } else {
                    showMixedInterAds();
                }
            }
        }
    }

    public void showInterstitial2(final boolean loadOnClosed,
                                  final Callable<Void> mathodToFollow) {
        if (adsPrefernce.showLoading()) {
            proceedWithDelay(1000, "Showing Ad...", new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    try {
                        mathodToFollow.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (!adsPrefernce.planD()) {
                        if (!adsPrefernce.isMediationActive()) {
                            InterstitialAd2(loadOnClosed);
                        } else {
                            showMixedInterAds();
                        }
                    }
                    return null;
                }
            });
        } else {
            try {
                mathodToFollow.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!adsPrefernce.planD()) {
                if (!adsPrefernce.isMediationActive()) {
                    InterstitialAd2(loadOnClosed);
                } else {
                    showMixedInterAds();
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

    public void showInterAdsDialog(final boolean loadOnClosed, final int adToShow) {
        if (isNetworkAvailable(BaseClass.this)) {
            if (isAdsAvailable) {
                if (adToShow == 1 && adsPrefernce.showgInter1()) {
                    if (gDialogInterstitial.isLoaded() && gDialogInterstitial != null) {
                        gDialogInterstitial.show();
                    }
                    gDialogInterstitial.setAdListener(new com.google.android.gms.ads.AdListener() {
                        public void onAdClosed() {
                            if (loadOnClosed) {
                                gDialogInterstitial.loadAd(new AdRequest.Builder().build());
                            }
                        }
                    });
                } else if (adToShow == 2 && adsPrefernce.showgInter2()) {
                    if (gDialogInterstitial.isLoaded() && gDialogInterstitial != null) {
                        gDialogInterstitial.show();
                    }
                    gDialogInterstitial.setAdListener(new com.google.android.gms.ads.AdListener() {
                        public void onAdClosed() {
                            if (loadOnClosed) {
                                gDialogInterstitial.loadAd(new AdRequest.Builder().build());
                            }
                        }
                    });
                } else {
                    if (adToShow == 1 && adsPrefernce.showfbInter1()) {
                        if (fbDialogInterstitial.isAdLoaded()) {
                            fbDialogInterstitial.show();
                            fbDialogInterstitial.setAdListener(new InterstitialAdListener() {
                                @Override
                                public void onInterstitialDisplayed(Ad ad) {

                                }

                                @Override
                                public void onInterstitialDismissed(Ad ad) {
                                    if (loadOnClosed) {
                                        fbDialogInterstitial.loadAd();
                                    }
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
                            });
                        }
                    } else if (adToShow == 2 && adsPrefernce.showfbInter2()) {
                        if (fbDialogInterstitial.isAdLoaded()) {
                            fbDialogInterstitial.show();
                            fbDialogInterstitial.setAdListener(new InterstitialAdListener() {
                                @Override
                                public void onInterstitialDisplayed(Ad ad) {

                                }

                                @Override
                                public void onInterstitialDismissed(Ad ad) {
                                    if (loadOnClosed) {
                                        fbDialogInterstitial.loadAd();
                                    }
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
                            });
                        }
                    }
                }
            } else {
                if (fbDialogInterstitial.isAdLoaded()) {
                    fbDialogInterstitial.show();
                    fbDialogInterstitial.setAdListener(new InterstitialAdListener() {
                        @Override
                        public void onInterstitialDisplayed(Ad ad) {

                        }

                        @Override
                        public void onInterstitialDismissed(Ad ad) {
                            if (loadOnClosed) {
                                fbDialogInterstitial.loadAd();
                            }
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
                    });
                }
            }
        }
    }

    public void showBackPressAd(final Callable<Void> methodParam) {
        if (isNetworkAvailable(this)) {
            adsPrefernce = new AdsPrefernce(this);
            if (!adsPrefernce.isMediationActive()) {
                if (!adsPrefernce.planD()) {
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
                                    });
                                } else {
                                    try {
                                        methodParam.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                            } else {
                                if (adsPrefernce.showfbInter1()) {
                                    if (fbInterstitial11.isAdLoaded()) {
                                        fbInterstitial11.show();
                                        fbInterstitial11.setAdListener(new InterstitialAdListener() {
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
                                        });
                                    } else {
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }

                                } else {
                                    try {
                                        methodParam.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
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
                                    an_interstitial_Ad11.setOnAdOpenedCallback(new OnAdOpened() {
                                        @Override
                                        public void adOpened() {
                                        }
                                    });
                                } else {
                                    try {
                                        methodParam.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                try {
                                    methodParam.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
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

                                            }

                                            @Override
                                            public void onInterstitialShown(MoPubInterstitial interstitial) {
//                                                isMpInter1Ready = false;
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
                                                mpInterstitial11.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
                                                    @Override
                                                    public void onInterstitialLoaded(MoPubInterstitial interstitial) {
//                                                        isMpInter1Ready = true;
                                                    }

                                                    @Override
                                                    public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {

                                                    }

                                                    @Override
                                                    public void onInterstitialShown(MoPubInterstitial interstitial) {
//                                                        isMpInter1Ready = false;
//                                                        isMpInter1Shown = true;

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
                                    } else {
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                } else {
                                    try {
                                        methodParam.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                try {
                                    methodParam.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            try {
                                methodParam.call();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    } else {
                        if (fbInterstitial11.isAdLoaded()) {
                            fbInterstitial11.show();
                            fbInterstitial11.setAdListener(new InterstitialAdListener() {
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
                            });
                        } else {
                            try {
                                methodParam.call();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                    super.onBackPressed();
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
            super.onBackPressed();
        }
    }

    // backpress ads with validation
//    public void showBackPressAd(final Callable<Void> methodParam) {
//        if (isNetworkAvailable(this)) {
//            adsPrefernce = new AdsPrefernce(this);
//            if (!adsPrefernce.isMediationActive()) {
//                if (!adsPrefernce.planD()) {
//                    if (isAdsAvailable) {
//                        if (adsPrefernce.planA()) {
//                            if (adsPrefernce.showgInter1()) {
//                                if (isGInter1Ready) {
//                                    if (gInterstitial1.isLoaded()) {
//                                        gInterstitial1.show();
//                                        gInterstitial1.setAdListener(new com.google.android.gms.ads.AdListener() {
//                                            @Override
//                                            public void onAdLoaded() {
//                                            }
//
//                                            @Override
//                                            public void onAdClosed() {
//                                                try {
//                                                    methodParam.call();
//                                                } catch (Exception e) {
//                                                    e.printStackTrace();
//                                                }
//                                                gInterstitial1.loadAd(new AdRequest.Builder().build());
//                                            }
//                                        });
//                                    } else {
//                                        try {
//                                            methodParam.call();
//                                        } catch (Exception e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                } else {
//                                    try {
//                                        methodParam.call();
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//
//                            } else {
//                                if (adsPrefernce.showfbInter1()) {
//                                    if (isFbInter1Ready) {
//                                        if (fbInterstitial1.isAdLoaded()) {
//                                            fbInterstitial1.show();
//                                            fbInterstitial1.setAdListener(new InterstitialAdListener() {
//                                                @Override
//                                                public void onInterstitialDisplayed(Ad ad) {
//
//                                                }
//
//                                                @Override
//                                                public void onInterstitialDismissed(Ad ad) {
//                                                    try {
//                                                        methodParam.call();
//                                                    } catch (Exception e) {
//                                                        e.printStackTrace();
//                                                    }
//                                                }
//
//                                                @Override
//                                                public void onError(Ad ad, AdError adError) {
//
//                                                }
//
//                                                @Override
//                                                public void onAdLoaded(Ad ad) {
//
//                                                }
//
//                                                @Override
//                                                public void onAdClicked(Ad ad) {
//
//                                                }
//
//                                                @Override
//                                                public void onLoggingImpression(Ad ad) {
//
//                                                }
//                                            });
//                                        } else {
//                                            try {
//                                                methodParam.call();
//                                            } catch (Exception e) {
//                                                e.printStackTrace();
//                                            }
//                                        }
//                                    } else {
//                                        try {
//                                            methodParam.call();
//                                        } catch (Exception e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                } else {
//                                    try {
//                                        methodParam.call();
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//                        } else if (adsPrefernce.planB()) {
//                            if (adsPrefernce.showanInter1()) {
//                                if (isAnInter1Ready) {
//                                    if (an_interstitial_Ad1.isAdLoaded()) {
//                                        an_interstitial_Ad1.showAd();
//                                        an_interstitial_Ad1.setOnAdClosedCallback(new OnAdClosed() {
//                                            @Override
//                                            public void onAdClosed() {
//                                                loadInterstitial1();
//                                                try {
//                                                    methodParam.call();
//                                                } catch (Exception e) {
//                                                    e.printStackTrace();
//                                                }
//
//                                            }
//                                        });
//                                        an_interstitial_Ad1.setOnAdOpenedCallback(new OnAdOpened() {
//                                            @Override
//                                            public void adOpened() {
//                                                isAnInter1Ready = false;
//                                            }
//                                        });
//                                    } else {
//                                        try {
//                                            methodParam.call();
//                                        } catch (Exception e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//
//
//                                } else {
//                                    try {
//                                        methodParam.call();
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            } else {
//                                try {
//                                    methodParam.call();
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        } else if (adsPrefernce.planC()) {
//                            if (adsPrefernce.showmpInter1()) {
//                                if (isMpInter1Ready) {
//                                    if (mpInterstitial1 != null) {
//                                        if (mpInterstitial1.isReady()) {
//                                            mpInterstitial1.show();
//                                            mpInterstitial1.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
//                                                @Override
//                                                public void onInterstitialLoaded(MoPubInterstitial interstitial) {
//
//                                                }
//
//                                                @Override
//                                                public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
//
//                                                }
//
//                                                @Override
//                                                public void onInterstitialShown(MoPubInterstitial interstitial) {
//                                                    isMpInter1Ready = false;
//                                                }
//
//                                                @Override
//                                                public void onInterstitialClicked(MoPubInterstitial interstitial) {
//
//                                                }
//
//                                                @Override
//                                                public void onInterstitialDismissed(MoPubInterstitial interstitial) {
//                                                    mpInterstitial1.load();
//                                                    try {
//                                                        methodParam.call();
//                                                    } catch (Exception e) {
//                                                        e.printStackTrace();
//                                                    }
//                                                    mpInterstitial1.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
//                                                        @Override
//                                                        public void onInterstitialLoaded(MoPubInterstitial interstitial) {
//                                                            isMpInter1Ready = true;
//                                                        }
//
//                                                        @Override
//                                                        public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
//
//                                                        }
//
//                                                        @Override
//                                                        public void onInterstitialShown(MoPubInterstitial interstitial) {
//                                                            isMpInter1Ready = false;
//                                                            isMpInter1Shown = true;
//
//                                                        }
//
//                                                        @Override
//                                                        public void onInterstitialClicked(MoPubInterstitial interstitial) {
//
//                                                        }
//
//                                                        @Override
//                                                        public void onInterstitialDismissed(MoPubInterstitial interstitial) {
//
//                                                        }
//                                                    });
//
//
//                                                }
//                                            });
//                                        } else {
//                                            try {
//                                                methodParam.call();
//                                            } catch (Exception e) {
//                                                e.printStackTrace();
//                                            }
//                                        }
//                                    } else {
//                                        try {
//                                            methodParam.call();
//                                        } catch (Exception e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//
//                                } else {
//                                    try {
//                                        methodParam.call();
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            } else {
//                                try {
//                                    methodParam.call();
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        } else {
//                            try {
//                                methodParam.call();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                    } else {
//                        if (isFbInter1Ready) {
//                            if (fbInterstitial1.isAdLoaded()) {
//                                fbInterstitial1.show();
//                                fbInterstitial1.setAdListener(new InterstitialAdListener() {
//                                    @Override
//                                    public void onInterstitialDisplayed(Ad ad) {
//
//                                    }
//
//                                    @Override
//                                    public void onInterstitialDismissed(Ad ad) {
//                                        try {
//                                            methodParam.call();
//                                        } catch (Exception e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onError(Ad ad, AdError adError) {
//
//                                    }
//
//                                    @Override
//                                    public void onAdLoaded(Ad ad) {
//
//                                    }
//
//                                    @Override
//                                    public void onAdClicked(Ad ad) {
//
//                                    }
//
//                                    @Override
//                                    public void onLoggingImpression(Ad ad) {
//
//                                    }
//                                });
//                            } else {
//                                try {
//                                    methodParam.call();
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        } else {
//                            try {
//                                methodParam.call();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                } else {
//                    super.onBackPressed();
//                }
//            } else {
//                showMixedInterAdsOnClosed(new Callable<Void>() {
//                    @Override
//                    public Void call() throws Exception {
//                        try {
//                            methodParam.call();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        return null;
//                    }
//                });
//            }
//
//        } else {
//            super.onBackPressed();
//        }
//    }

    public void checkAppService(String key, String appVersion) {
        if (isNetworkAvailable(this) && checkAppService) {
            runAppService(key, appVersion);
        }
    }

    public void runAppService(String app_key, final String appVersion) {
        AsyncHttpClient client = new AsyncHttpClient();

        gsonUtils = GsonUtils.getInstance();

        RequestParams params1 = new RequestParams();
        params1.put("app_key", app_key);

        try {
            client.setConnectTimeout(50000);
            client.post("http://developercompanion.get-fans-for-musically.com/iapi/app_service.php", params1, new JsonHttpResponseHandler() {
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
                        int not_show_dialog = response.optInt("not_show_dialog");
                        String ad_dialog_title = response.optString("ad_dialog_title");
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
                                        not_message, ad_dialog_title, ad_message, ad_banner_url, ad_icon_url, ad_app_name, ad_app_short_desc, ad_app_url);
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
                                } else {
                                    serviceDialog(false, true, false, update_dialog_title, update_title, update_version_name, update_message, not_show_dialog == 1,
                                            update_show_cancel == 1, update_app_url, update_force_update == 1, update_force_v1, update_force_v2, update_force_v3, not_dialog_title,
                                            not_message, ad_dialog_title, ad_message, ad_banner_url, ad_icon_url, ad_app_name, ad_app_short_desc, ad_app_url);
                                    return;
                                }
                            }
                        }
                        if (isAd == 1) {
                            serviceDialog(false, false, true, update_dialog_title, update_title, update_version_name, update_message, not_show_dialog == 1,
                                    update_show_cancel == 1, update_app_url, update_force_update == 1, update_force_v1, update_force_v2, update_force_v3, not_dialog_title,
                                    not_message, ad_dialog_title, ad_message, ad_banner_url, ad_icon_url, ad_app_name, ad_app_short_desc, ad_app_url);

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
                                      update_force_v3, String not_dialog_title,
                              String not_message, String ad_dialog_title, String ad_message, String
                                      ad_banner_url, String ad_icon_url,
                              String ad_app_name, String ad_app_short_desc, final String ad_app_url) {


        this.serviceDialog = new Dialog(this);
        this.serviceDialog.setCancelable(false);
        this.serviceDialog.setContentView(R.layout.dialog_service);
        Objects.requireNonNull(this.serviceDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        this.serviceDialog.show();
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
        TextView tv_okButton = this.serviceDialog.findViewById(R.id.tv_not_okButton);

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
        }

        if (isNotification) {
            if (update_available) {
                iv_ad_icon_title.setVisibility(View.GONE);
                lay_ads.setVisibility(View.GONE);
                lay_updateApp.setVisibility(View.GONE);
                lay_message.setVisibility(View.VISIBLE);
                tv_dialog_title.setText(not_dialog_title);

                tv_message.setText(not_message);

                tv_okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        serviceDialog.dismiss();
                    }
                });
            }

        }

        if (isAd) {
            iv_ad_icon_title.setVisibility(View.VISIBLE);
            lay_updateApp.setVisibility(View.GONE);
            lay_message.setVisibility(View.GONE);
            lay_ads.setVisibility(View.VISIBLE);
            tv_dialog_title.setText(ad_dialog_title);

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

    //                    adsPrefernce.setAdsDefaults(ads.getShowAds(),ads.getShowLoading(),ads.getAd1(),ads.getAd2(),ads.getAd3(),ads.getAd4(),ads.getAd5(),
//                            ads.getGAppId(),ads.getGBanner(),ads.getShowGbanner(),ads.getGInter1(),ads.getShowGinter1(),ads.getGInter2(),ads.getShowGinter2(),
//                            ads.getGRewarded(),ads.getShowGrewarded(),ads.getGNative1(),ads.getShowGnative1(),ads.getGNative2(),ads.getShowGnative2(),
//
//                            ads.getFbBanner(),ads.getShowFbbanner(),ads.getFbInter1(),ads.getShowFbinter1(),ads.getFbInter2(),ads.getShowFbinter2(),
//                            ads.getFbRewarded(),ads.getShowFbRewarded(),ads.getFbNative1(),ads.getShowFbnative1(),ads.getFbNative2(),ads.getShowFbnative2(),
//                            ads.getFbNativebanner(),ads.getFbNativebanner(),
//
//                            ads.getAnBanner(),ads.getShowAnbanner(),ads.getAnInter1(),ads.getShowAninter1(),ads.getAnInter2(),ads.getShowAninter2(),
//                            ads.getAnNative1(),ads.getShowAnnative1(),ads.getAnNative2(),ads.getShowAnnative2(),ads.getAnRewarded(),ads.getShowAnrewarded(),
//
//                            ads.getMpBanner(),ads.getShowMpbanner(),ads.getMpInter1(),ads.getShowMpinter1(),ads.getMpInter2(),ads.getShowMpinter2(),
//                            ads.getMpNative1(),ads.getShowMpnative1(),ads.getMpNative2(),ads.getShowMpnative2(),ads.getMpRewarded(),ads.getShowMprewarded(),
//
//                            ads.getUBanner(),ads.getShowUbanner(),ads.getUInter1(),ads.getShowUinter1(),ads.getUInter2(),ads.getShowUinter2(),
//                            ads.getUNative1(),ads.getShowUnative1(), ads.getUNative2(),ads.getShowUnative2(),ads.getURewarded(),ads.getShowUrewarded(),
//
//                            ads.getExtraPara1(),ads.getExtraPara2(),ads.getExtraPara3(),ads.getExtraPara4(),
//
//                            ads.getSaAdCount()
//                    );
// if (ads.getAd1().equals("1")) {
//        adsPrefernce.setAdsDefaults(ads.getShowAds(), ads.getShowLoading(), ads.getAd1(), ads.getAd2(), ads.getAd3(), ads.getAd4(), ads.getAd5(),
//                ads.getGAppId(), ads.getGBanner(), ads.getShowGbanner(), ads.getGInter1(), ads.getShowGinter1(), ads.getGInter2(), ads.getShowGinter2(),
//                ads.getGRewarded(), ads.getShowGrewarded(), ads.getGNative1(), ads.getShowGnative1(), ads.getGNative2(), ads.getShowGnative2(),
//
//                ads.getFbBanner(), ads.getShowFbbanner(), ads.getFbInter1(), ads.getShowFbinter1(), ads.getFbInter2(), ads.getShowFbinter2(),
//                ads.getFbRewarded(), ads.getShowFbRewarded(), ads.getFbNative1(), ads.getShowFbnative1(), ads.getFbNative2(), ads.getShowFbnative2(),
//                ads.getFbNativebanner(), ads.getFbNativebanner(),
//
//                "na", "0", "0", "0", "0", "0", "0",
//                "na", "0", "na", "0", "na", "0", "na", "0", "na", "0", "na", "0",
//                "na", "0", "na", "0", "na", "0", "na", "0", "na", "0", "na", "0",
//
//                ads.getExtraPara1(), ads.getExtraPara2(), ads.getExtraPara3(), ads.getExtraPara4(),
//
//                ads.getSaAdCount()
//        );
//    }

}
