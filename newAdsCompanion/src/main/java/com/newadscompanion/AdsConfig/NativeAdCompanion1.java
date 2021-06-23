package com.newadscompanion.AdsConfig;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd.OnUnifiedNativeAdLoadedListener;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.mopub.nativeads.AdapterHelper;
import com.mopub.nativeads.MoPubNative;
import com.mopub.nativeads.MoPubStaticNativeAdRenderer;
import com.mopub.nativeads.NativeErrorCode;
import com.mopub.nativeads.RequestParameters;
import com.mopub.nativeads.ViewBinder;
import com.newadscompanion.BaseUtils.BaseClass;
import com.newadscompanion.Interfaces.InhouseNativeListener;
import com.newadscompanion.ModelsCompanion.AdsPrefernce;
import com.newadscompanion.R;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static com.newadscompanion.BaseUtils.BaseClass.isAdsAvailable;
import static com.newadscompanion.BaseUtils.BaseClass.isFbN1Shown;
import static com.newadscompanion.BaseUtils.BaseClass.isFbN2Shown;
import static com.newadscompanion.BaseUtils.BaseClass.isGN1Shown;
import static com.newadscompanion.BaseUtils.BaseClass.isGN2Shown;
import static com.newadscompanion.BaseUtils.BaseClass.isMpN1Shown;
import static com.newadscompanion.BaseUtils.BaseClass.isMpN2Shown;
import static com.newadscompanion.BaseUtils.BaseClass.isNetworkAvailable;
//new NativeAdCompanion1(this).loadNativeAd((CardView) findViewById(R.id.native_ad_cardview), (CardView) findViewById(R.id.native_ad_container));

public class NativeAdCompanion1 {

    private ImageView imageView;
    private TextView textView, rating, description;
    private ProgressBar progressBar;
    private Button button;
    private ArrayList<View> viewArrayList;

    MoPubNative moPubNative;
    ViewBinder viewBinder;
    ///////

    private Context context;
    AdsPrefernce adsPrefernce;
    DefaultIds defaultIds;
    AdLoader.Builder builder;


    public NativeAdCompanion1(Context context) {
        this.context = context;
    }

    public void loadNativeAd(final CardView cardView, final CardView nativeAdContainer) {
        defaultIds = new DefaultIds(context);
        adsPrefernce = new AdsPrefernce(context);
        AdLoader.Builder builder;
        if (isNetworkAvailable(context)) {
            if (isAdsAvailable) {
                if (!adsPrefernce.planD()) {
                    if (!adsPrefernce.isMediationActive()) {
                        MobileAds.initialize(context, adsPrefernce.gAppId());
                        if (adsPrefernce.showgNative1()) {
                            builder = new AdLoader.Builder(this.context, adsPrefernce.gNativeId1());
                            builder.forUnifiedNativeAd(new OnUnifiedNativeAdLoadedListener() {
                                public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                                    NativeAdCompanion1.this.inflateNativeAdGoogle(unifiedNativeAd, cardView);
                                }
                            });
                            builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(true).build()).build());
                            builder.withAdListener(new AdListener() {

                                @Override
                                public void onAdFailedToLoad(LoadAdError loadAdError) {
                                    super.onAdFailedToLoad(loadAdError);
                                    if (context instanceof BaseClass){
                                        ((BaseClass)context).showInhouseNativeAd(nativeAdContainer,new InhouseNativeListener() {
                                            @Override
                                            public void onAdLoaded() {
                                                nativeAdContainer.setVisibility(View.VISIBLE);
                                                cardView.setVisibility(View.VISIBLE);
                                            }

                                            @Override
                                            public void onAdShowFailed() {
                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onAdLoaded() {
                                    super.onAdLoaded();
                                    nativeAdContainer.setVisibility(View.VISIBLE);
                                    cardView.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onAdImpression() {
                                    super.onAdImpression();
                                    nativeAdContainer.setVisibility(View.VISIBLE);
                                    cardView.setVisibility(View.VISIBLE);
                                }
                            }).build().loadAd(new Builder().build());
                        } else if (adsPrefernce.showfbNative1()) {
                            final NativeAd nativeAd;

                            nativeAd = new NativeAd(context, adsPrefernce.fbNativeId1());

                            NativeAdListener nativeAdListener = new NativeAdListener() {
                                @Override
                                public void onMediaDownloaded(Ad ad) {
                                }

                                @Override
                                public void onError(Ad ad, AdError adError) {
                                    if (context instanceof BaseClass){
                                        ((BaseClass)context).showInhouseNativeAd(nativeAdContainer,new InhouseNativeListener() {
                                            @Override
                                            public void onAdLoaded() {
                                                nativeAdContainer.setVisibility(View.VISIBLE);
                                                cardView.setVisibility(View.VISIBLE);
                                            }

                                            @Override
                                            public void onAdShowFailed() {
                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onAdLoaded(Ad ad) {
                                    if (nativeAd != ad) {
                                        return;
                                    }
                                    nativeAdContainer.setVisibility(View.VISIBLE);
                                    cardView.setVisibility(View.VISIBLE);
                                    // Inflate Native Ad into Container
                                    inflateNativeAdFacebook(nativeAd, cardView);
                                }

                                @Override
                                public void onAdClicked(Ad ad) {
                                }

                                @Override
                                public void onLoggingImpression(Ad ad) {
                                    nativeAdContainer.setVisibility(View.VISIBLE);
                                    cardView.setVisibility(View.VISIBLE);
                                }
                            };
                            nativeAd.loadAd(nativeAd.buildLoadAdConfig().withAdListener(nativeAdListener).build());
                        } else if (adsPrefernce.showmpNative1()) {
                            MoPubNative.MoPubNativeNetworkListener moPubNativeNetworkListener = new MoPubNative.MoPubNativeNetworkListener() {
                                @Override
                                public void onNativeLoad(com.mopub.nativeads.NativeAd nativeAd) {
                                    nativeAdContainer.setVisibility(View.VISIBLE);
                                    cardView.removeAllViews();
                                    cardView.setBackground(context.getResources().getDrawable(R.drawable.ad_border));
                                    cardView.setVisibility(View.VISIBLE);

                                    FrameLayout.LayoutParams nativePara =
                                            new FrameLayout.LayoutParams(
                                                    FrameLayout.LayoutParams.MATCH_PARENT,
                                                    FrameLayout.LayoutParams.WRAP_CONTENT);
                                    // Add the Ad view into the ad container.
                                    LayoutInflater inflater = LayoutInflater.from(context);
                                    RelativeLayout adViews = (RelativeLayout) inflater.inflate(R.layout.native_ad_layout_mopub, cardView, false);
                                    AdapterHelper ah = new AdapterHelper(context, 0, 2);
                                    View v = ah.getAdView(null, adViews, nativeAd, viewBinder);

                                    cardView.addView(v, nativePara);
                                }

                                @Override
                                public void onNativeFail(NativeErrorCode errorCode) {
                                    if (context instanceof BaseClass){
                                        ((BaseClass)context).showInhouseNativeAd(nativeAdContainer,new InhouseNativeListener() {
                                            @Override
                                            public void onAdLoaded() {
                                                nativeAdContainer.setVisibility(View.VISIBLE);
                                                cardView.setVisibility(View.VISIBLE);
                                            }

                                            @Override
                                            public void onAdShowFailed() {
                                            }
                                        });
                                    }
                                }
                                // We will be populating this below
                            };

                            moPubNative = new MoPubNative(context, adsPrefernce.mpNativeId1(), moPubNativeNetworkListener);
                            viewBinder = new ViewBinder.Builder(R.layout.native_ad_layout_mopub)
                                    .mainImageId(R.id.native_ad_main_image)
                                    .iconImageId(R.id.native_ad_icon_image)
                                    .titleId(R.id.native_ad_title)
                                    .textId(R.id.native_ad_text)
                                    .privacyInformationIconImageId(R.id.native_ad_privacy_information_icon_image)
//                                .addExtra("sponsoredimage", R.id.sponsored_image) // If you display direct-sold native ads, you can add additional subviews for custom assets
                                    .build();

                            MoPubStaticNativeAdRenderer moPubStaticNativeAdRenderer = new MoPubStaticNativeAdRenderer(viewBinder);
                            moPubNative.registerAdRenderer(moPubStaticNativeAdRenderer);

                            EnumSet<RequestParameters.NativeAdAsset> desiredAssets = EnumSet.of(
                                    RequestParameters.NativeAdAsset.TITLE,
                                    RequestParameters.NativeAdAsset.TEXT,
                                    RequestParameters.NativeAdAsset.CALL_TO_ACTION_TEXT,
                                    RequestParameters.NativeAdAsset.MAIN_IMAGE,
                                    RequestParameters.NativeAdAsset.ICON_IMAGE,
                                    RequestParameters.NativeAdAsset.STAR_RATING
                            );

                            RequestParameters mRequestParameters = new RequestParameters.Builder()
                                    .desiredAssets(desiredAssets)
                                    .build();
                            moPubNative.makeRequest(mRequestParameters);
                        }
                    } else {
                        if (adsPrefernce.showgNative1()) {
                            if (!isGN1Shown) {
                                showGNative1(cardView, nativeAdContainer);
                            } else {
                                showFbNative1(cardView, nativeAdContainer);
                            }
                        } else {
                            showFbNative1(cardView, nativeAdContainer);
                        }
                    }
                }else {
                    if (context instanceof BaseClass){
                        ((BaseClass)context).showInhouseNativeAd(nativeAdContainer,new InhouseNativeListener() {
                            @Override
                            public void onAdLoaded() {
                                nativeAdContainer.setVisibility(View.VISIBLE);
                                cardView.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAdShowFailed() {
                            }
                        });
                    }
                }
            } else {
                if (context instanceof BaseClass){
                    ((BaseClass)context).showInhouseNativeAd(nativeAdContainer,new InhouseNativeListener() {
                        @Override
                        public void onAdLoaded() {
                            nativeAdContainer.setVisibility(View.VISIBLE);
                            cardView.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAdShowFailed() {
                        }
                    });
                }
            }
        }else {
            if (context instanceof BaseClass){
                ((BaseClass)context).showInhouseNativeAd(nativeAdContainer,new InhouseNativeListener() {
                    @Override
                    public void onAdLoaded() {
                        nativeAdContainer.setVisibility(View.VISIBLE);
                        cardView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAdShowFailed() {
                    }
                });
            }
        }
    }

    public void inflateNativeAdGoogle(UnifiedNativeAd unifiedNativeAd, CardView cardView) {
        cardView.setVisibility(View.VISIBLE);
        UnifiedNativeAdView unifiedNativeAdView = (UnifiedNativeAdView) LayoutInflater.from(this.context).inflate(R.layout.native_ad_layout_google, null);
        cardView.removeAllViews();
        cardView.setBackground(context.getResources().getDrawable(R.drawable.ad_border));
        cardView.addView(unifiedNativeAdView);
        unifiedNativeAdView.setMediaView((MediaView) unifiedNativeAdView.findViewById(R.id.ad_media));
        unifiedNativeAdView.setHeadlineView(unifiedNativeAdView.findViewById(R.id.ad_headline));
        unifiedNativeAdView.setBodyView(unifiedNativeAdView.findViewById(R.id.ad_body));
        unifiedNativeAdView.setCallToActionView(unifiedNativeAdView.findViewById(R.id.ad_call_to_action));
        unifiedNativeAdView.setIconView(unifiedNativeAdView.findViewById(R.id.ad_app_icon));
        unifiedNativeAdView.setPriceView(unifiedNativeAdView.findViewById(R.id.ad_price));
        unifiedNativeAdView.setStarRatingView(unifiedNativeAdView.findViewById(R.id.ad_stars));
        unifiedNativeAdView.setStoreView(unifiedNativeAdView.findViewById(R.id.ad_store));
        unifiedNativeAdView.setAdvertiserView(unifiedNativeAdView.findViewById(R.id.ad_advertiser));
        ((TextView) unifiedNativeAdView.getHeadlineView()).setText(unifiedNativeAd.getHeadline());
        unifiedNativeAdView.getMediaView().setMediaContent(unifiedNativeAd.getMediaContent());
        if (unifiedNativeAd.getBody() == null) {
            unifiedNativeAdView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            unifiedNativeAdView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) unifiedNativeAdView.getBodyView()).setText(unifiedNativeAd.getBody());
        }
        if (unifiedNativeAd.getCallToAction() == null) {
            unifiedNativeAdView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            unifiedNativeAdView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) unifiedNativeAdView.getCallToActionView()).setText(unifiedNativeAd.getCallToAction());
        }
        if (unifiedNativeAd.getIcon() == null) {
            unifiedNativeAdView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) unifiedNativeAdView.getIconView()).setImageDrawable(unifiedNativeAd.getIcon().getDrawable());
            unifiedNativeAdView.getIconView().setVisibility(View.VISIBLE);
        }
        if (unifiedNativeAd.getStarRating() == null) {
            unifiedNativeAdView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) unifiedNativeAdView.getStarRatingView()).setRating(unifiedNativeAd.getStarRating().floatValue());
            unifiedNativeAdView.getStarRatingView().setVisibility(View.VISIBLE);
        }
        if (unifiedNativeAd.getAdvertiser() == null) {
            unifiedNativeAdView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) unifiedNativeAdView.getAdvertiserView()).setText(unifiedNativeAd.getAdvertiser());
            unifiedNativeAdView.getAdvertiserView().setVisibility(View.VISIBLE);
        }
        unifiedNativeAdView.setNativeAd(unifiedNativeAd);
        unifiedNativeAd.getVideoController();
    }

    private void inflateNativeAdFacebook(NativeAd nativeAd, CardView cardView) {
        nativeAd.unregisterView();

        // Add the Ad view into the ad container.
        NativeAdLayout nativeAdLayout = new NativeAdLayout(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        RelativeLayout adViews = (RelativeLayout) inflater.inflate(R.layout.native_ad_layout_facebook, cardView, false);
        cardView.removeAllViews();
        cardView.setBackground(context.getResources().getDrawable(R.drawable.ad_border));
        cardView.addView(adViews);

        // Add the AdOptionsView
        LinearLayout adChoicesContainer = adViews.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(context, nativeAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        com.facebook.ads.MediaView nativeAdIcon = adViews.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adViews.findViewById(R.id.native_ad_title);
        com.facebook.ads.MediaView nativeAdMedia = adViews.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adViews.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adViews.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adViews.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = adViews.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdTitle.setText(nativeAd.getAdvertiserName());
        nativeAdBody.setText(nativeAd.getAdBodyText());
        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        // Register the Title and CTA button to listen for clicks.
        nativeAd.registerViewForInteraction(
                adViews,
                nativeAdMedia,
                nativeAdIcon,
                clickableViews);
    }

    public void showGNative1(final CardView cardView, final CardView nativeAdContainer) {
        MobileAds.initialize(context, adsPrefernce.gAppId());
        builder = new AdLoader.Builder(this.context, adsPrefernce.gNativeId1());
        builder.forUnifiedNativeAd(new OnUnifiedNativeAdLoadedListener() {
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                NativeAdCompanion1.this.inflateNativeAdGoogle(unifiedNativeAd, cardView);
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(true).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(int i) {
                isGN1Shown = true;
                showFbNative1(cardView, nativeAdContainer);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                isGN1Shown = true;
                nativeAdContainer.setVisibility(View.VISIBLE);
                cardView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
                nativeAdContainer.setVisibility(View.VISIBLE);
                cardView.setVisibility(View.VISIBLE);
            }
        }).build().loadAd(new Builder().build());
    }

    public void showFbNative1(final CardView cardView, final CardView nativeAdContainer) {
        if (adsPrefernce.showfbNative1()) {
            if (!isFbN1Shown) {
                final NativeAd nativeAd;
                nativeAd = new NativeAd(context, adsPrefernce.fbNativeId1());
                NativeAdListener nativeAdListener = new NativeAdListener() {
                    @Override
                    public void onMediaDownloaded(Ad ad) {
                    }

                    @Override
                    public void onError(Ad ad, AdError adError) {
                        BaseClass.isFbN1Shown = true;
                        showGNative2(cardView, nativeAdContainer);
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        BaseClass.isFbN1Shown = true;
                        if (nativeAd != ad) {
                            return;
                        }
                        nativeAdContainer.setVisibility(View.VISIBLE);
                        cardView.setVisibility(View.VISIBLE);
                        // Inflate Native Ad into Container
                        inflateNativeAdFacebook(nativeAd, cardView);
                    }

                    @Override
                    public void onAdClicked(Ad ad) {
                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {
                        nativeAdContainer.setVisibility(View.VISIBLE);
                        cardView.setVisibility(View.VISIBLE);
                    }
                };
                nativeAd.loadAd(nativeAd.buildLoadAdConfig().withAdListener(nativeAdListener).build());
            } else {
                showMpNative1(cardView, nativeAdContainer);            }
        } else {
            showMpNative1(cardView, nativeAdContainer);        }
    }

    public void showMpNative1(final CardView cardView, final CardView nativeAdContainer) {

        if (adsPrefernce.showmpNative1()) {
            if (!isMpN1Shown) {
                MoPubNative.MoPubNativeNetworkListener moPubNativeNetworkListener = new MoPubNative.MoPubNativeNetworkListener() {
                    @Override
                    public void onNativeLoad(com.mopub.nativeads.NativeAd nativeAd) {
                        isMpN1Shown = true;
                        nativeAdContainer.setVisibility(View.VISIBLE);
                        cardView.removeAllViews();
                        cardView.setBackground(context.getResources().getDrawable(R.drawable.ad_border));
                        cardView.setVisibility(View.VISIBLE);

                        FrameLayout.LayoutParams nativePara =
                                new FrameLayout.LayoutParams(
                                        FrameLayout.LayoutParams.MATCH_PARENT,
                                        FrameLayout.LayoutParams.WRAP_CONTENT);
                        // Add the Ad view into the ad container.
                        LayoutInflater inflater = LayoutInflater.from(context);
                        RelativeLayout adViews = (RelativeLayout) inflater.inflate(R.layout.native_ad_layout_mopub, cardView, false);
                        AdapterHelper ah = new AdapterHelper(context, 0, 2);
                        View v = ah.getAdView(null, adViews, nativeAd, viewBinder);

                        cardView.addView(v, nativePara);
                    }

                    @Override
                    public void onNativeFail(NativeErrorCode errorCode) {
                        isMpN1Shown = true;
                        showGNative2(cardView, nativeAdContainer);
                    }
                    // We will be populating this below
                };

                moPubNative = new MoPubNative(context, adsPrefernce.mpNativeId1(), moPubNativeNetworkListener);
                viewBinder = new ViewBinder.Builder(R.layout.native_ad_layout_mopub)
                        .mainImageId(R.id.native_ad_main_image)
                        .iconImageId(R.id.native_ad_icon_image)
                        .titleId(R.id.native_ad_title)
                        .textId(R.id.native_ad_text)
                        .privacyInformationIconImageId(R.id.native_ad_privacy_information_icon_image)
//                                .addExtra("sponsoredimage", R.id.sponsored_image) // If you display direct-sold native ads, you can add additional subviews for custom assets
                        .build();

                MoPubStaticNativeAdRenderer moPubStaticNativeAdRenderer = new MoPubStaticNativeAdRenderer(viewBinder);
                moPubNative.registerAdRenderer(moPubStaticNativeAdRenderer);

                EnumSet<RequestParameters.NativeAdAsset> desiredAssets = EnumSet.of(
                        RequestParameters.NativeAdAsset.TITLE,
                        RequestParameters.NativeAdAsset.TEXT,
                        RequestParameters.NativeAdAsset.CALL_TO_ACTION_TEXT,
                        RequestParameters.NativeAdAsset.MAIN_IMAGE,
                        RequestParameters.NativeAdAsset.ICON_IMAGE,
                        RequestParameters.NativeAdAsset.STAR_RATING
                );

                RequestParameters mRequestParameters = new RequestParameters.Builder()
                        .desiredAssets(desiredAssets)
                        .build();
                moPubNative.makeRequest(mRequestParameters);
            } else {
                showGNative2(cardView, nativeAdContainer);
            }
        } else {
            showGNative2(cardView, nativeAdContainer);
        }
    }

    public void showGNative2(final CardView cardView, final CardView nativeAdContainer) {

        if (adsPrefernce.showgNative2()) {
            if (!isGN2Shown) {
                MobileAds.initialize(context, adsPrefernce.gAppId());
                builder = new AdLoader.Builder(this.context, adsPrefernce.gNativeId2());
                builder.forUnifiedNativeAd(new OnUnifiedNativeAdLoadedListener() {
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        NativeAdCompanion1.this.inflateNativeAdGoogle(unifiedNativeAd, cardView);
                    }
                });
                builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(true).build()).build());
                builder.withAdListener(new AdListener() {
                    public void onAdFailedToLoad(int i) {
                        isGN2Shown = true;
                        showFbNative2(cardView, nativeAdContainer);
                    }

                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        isGN2Shown = true;
                        nativeAdContainer.setVisibility(View.VISIBLE);
                        cardView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                        nativeAdContainer.setVisibility(View.VISIBLE);
                        cardView.setVisibility(View.VISIBLE);
                    }
                }).build().loadAd(new Builder().build());
            } else {
                showFbNative2(cardView, nativeAdContainer);
            }
        } else {
            showFbNative2(cardView, nativeAdContainer);
        }


    }

    public void showFbNative2(final CardView cardView, final CardView nativeAdContainer) {
        if (adsPrefernce.showfbNative2()) {
            if (!isFbN2Shown) {
                final NativeAd nativeAd;
                nativeAd = new NativeAd(context, adsPrefernce.fbNativeId2());
                NativeAdListener nativeAdListener = new NativeAdListener() {
                    @Override
                    public void onMediaDownloaded(Ad ad) {
                    }

                    @Override
                    public void onError(Ad ad, AdError adError) {
                        BaseClass.isFbN2Shown = true;
                        resetNativeShownBoolean();
                        if (context instanceof BaseClass){
                            ((BaseClass)context).showInhouseNativeAd(nativeAdContainer,new InhouseNativeListener() {
                                @Override
                                public void onAdLoaded() {
                                    nativeAdContainer.setVisibility(View.VISIBLE);
                                    cardView.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onAdShowFailed() {
                                }
                            });
                        }                      }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        BaseClass.isFbN2Shown = true;
                        if (nativeAd != ad) {
                            return;
                        }
                        nativeAdContainer.setVisibility(View.VISIBLE);
                        cardView.setVisibility(View.VISIBLE);
                        // Inflate Native Ad into Container
                        inflateNativeAdFacebook(nativeAd, cardView);
                    }

                    @Override
                    public void onAdClicked(Ad ad) {
                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {
                        nativeAdContainer.setVisibility(View.VISIBLE);
                        cardView.setVisibility(View.VISIBLE);
                    }
                };
                nativeAd.loadAd(nativeAd.buildLoadAdConfig().withAdListener(nativeAdListener).build());
            } else {
                showMpNative2(cardView, nativeAdContainer);
            }
        } else {
            showMpNative2(cardView, nativeAdContainer);
        }

    }


    public void showMpNative2(final CardView cardView, final CardView nativeAdContainer) {
        if (adsPrefernce.showmpNative2()) {
            if (!isMpN2Shown) {
                MoPubNative.MoPubNativeNetworkListener moPubNativeNetworkListener = new MoPubNative.MoPubNativeNetworkListener() {
                    @Override
                    public void onNativeLoad(com.mopub.nativeads.NativeAd nativeAd) {
                        isMpN2Shown = true;
                        resetNativeShownBoolean();

                        nativeAdContainer.setVisibility(View.VISIBLE);
                        cardView.removeAllViews();
                        cardView.setBackground(context.getResources().getDrawable(R.drawable.ad_border));
                        cardView.setVisibility(View.VISIBLE);

                        FrameLayout.LayoutParams nativePara =
                                new FrameLayout.LayoutParams(
                                        FrameLayout.LayoutParams.MATCH_PARENT,
                                        FrameLayout.LayoutParams.WRAP_CONTENT);
                        // Add the Ad view into the ad container.
                        LayoutInflater inflater = LayoutInflater.from(context);
                        RelativeLayout adViews = (RelativeLayout) inflater.inflate(R.layout.native_ad_layout_mopub, cardView, false);
                        AdapterHelper ah = new AdapterHelper(context, 0, 2);
                        View v = ah.getAdView(null, adViews, nativeAd, viewBinder);

                        cardView.addView(v, nativePara);
                    }

                    @Override
                    public void onNativeFail(NativeErrorCode errorCode) {
                        isMpN2Shown = true;
                        resetNativeShownBoolean();
                        if (context instanceof BaseClass){
                            ((BaseClass)context).showInhouseNativeAd(nativeAdContainer,new InhouseNativeListener() {
                                @Override
                                public void onAdLoaded() {
                                    nativeAdContainer.setVisibility(View.VISIBLE);
                                    cardView.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onAdShowFailed() {
                                }
                            });
                        }
                    }
                    // We will be populating this below
                };

                moPubNative = new MoPubNative(context, adsPrefernce.mpNativeId2(), moPubNativeNetworkListener);
                viewBinder = new ViewBinder.Builder(R.layout.native_ad_layout_mopub)
                        .mainImageId(R.id.native_ad_main_image)
                        .iconImageId(R.id.native_ad_icon_image)
                        .titleId(R.id.native_ad_title)
                        .textId(R.id.native_ad_text)
                        .privacyInformationIconImageId(R.id.native_ad_privacy_information_icon_image)
//                                .addExtra("sponsoredimage", R.id.sponsored_image) // If you display direct-sold native ads, you can add additional subviews for custom assets
                        .build();

                MoPubStaticNativeAdRenderer moPubStaticNativeAdRenderer = new MoPubStaticNativeAdRenderer(viewBinder);
                moPubNative.registerAdRenderer(moPubStaticNativeAdRenderer);

                EnumSet<RequestParameters.NativeAdAsset> desiredAssets = EnumSet.of(
                        RequestParameters.NativeAdAsset.TITLE,
                        RequestParameters.NativeAdAsset.TEXT,
                        RequestParameters.NativeAdAsset.CALL_TO_ACTION_TEXT,
                        RequestParameters.NativeAdAsset.MAIN_IMAGE,
                        RequestParameters.NativeAdAsset.ICON_IMAGE,
                        RequestParameters.NativeAdAsset.STAR_RATING
                );

                RequestParameters mRequestParameters = new RequestParameters.Builder()
                        .desiredAssets(desiredAssets)
                        .build();
                moPubNative.makeRequest(mRequestParameters);
            } else {
                resetNativeShownBoolean();
                if (context instanceof BaseClass){
                    ((BaseClass)context).showInhouseNativeAd(nativeAdContainer,new InhouseNativeListener() {
                        @Override
                        public void onAdLoaded() {
                            nativeAdContainer.setVisibility(View.VISIBLE);
                            cardView.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAdShowFailed() {
                        }
                    });
                } }
        } else {
            resetNativeShownBoolean();
            if (context instanceof BaseClass){
                ((BaseClass)context).showInhouseNativeAd(nativeAdContainer,new InhouseNativeListener() {
                    @Override
                    public void onAdLoaded() {
                        nativeAdContainer.setVisibility(View.VISIBLE);
                        cardView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAdShowFailed() {
                    }
                });
            }

        }

    }

    public void resetNativeShownBoolean() {
        isGN1Shown = false;
        isFbN1Shown = false;
         isMpN1Shown = false;
        isGN2Shown = false;
        isFbN2Shown = false;
        isMpN2Shown = false;

    }

}
