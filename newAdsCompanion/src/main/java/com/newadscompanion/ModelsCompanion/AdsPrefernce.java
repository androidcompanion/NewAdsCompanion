package com.newadscompanion.ModelsCompanion;

import android.content.Context;
import android.content.SharedPreferences;


import com.newadscompanion.AdsConfig.DefaultIds;

import java.util.Objects;

public class AdsPrefernce {

    Context context;
    SharedPreferences adsPreference;
    SharedPreferences.Editor editor;
    DefaultIds ads;

    public AdsPrefernce(Context context) {
        this.context = context;
        adsPreference = context.getSharedPreferences("MyAdsPrefrence", Context.MODE_PRIVATE);
        editor = adsPreference.edit();
        ads = new DefaultIds(context);

    }

    public void setAdsDefaults(String showAds, String show_loading, String allow_access, String mediation,

                               String ad1, String ad2, String ad3, String ad4, String ad5,

                               String g_app_id, String g_banner, String show_gbanner, String g_inter1, String show_ginter1,
                               String g_inter2, String show_ginter2, String g_rewarded, String show_grewarded,
                               String g_native1, String show_gnative1, String g_native2, String show_gnative2,

                               String fb_banner, String show_fbbanner,
                               String fb_inter1, String show_fbinter1, String fb_inter2, String show_fbinter2,
                               String fb_rewarded, String show_fbrewarded,
                               String fb_native1, String show_fbnative1, String fb_native2, String show_fbnative2,
                               String fb_native_banner, String show_fb_nativebanner,

                               String an_ad_id, String show_anbanner, String show_aninter1,
                               String show_aninter2, String show_annative1, String show_annative2, String show_anrewarded,

                               String mp_banner, String show_mpbanner, String mp_inter1, String show_mpinter1,
                               String mp_inter2, String show_mpinter2, String mp_native1, String show_mpnative1,
                               String mp_native2, String show_mpnative2, String mp_rewarded, String show_mprewarded,

                               String u_app_id, String u_banner, String show_ubanner, String u_inter1, String show_uinter1,
                               String u_inter2, String show_uinter2, String u_rewarded, String show_urewarded,


                               String extraPara1, String extraPara2, String extraPara3, String extraPara4,
                               String sa_ad_count) {
        if (adsPreference != null) {
            editor = adsPreference.edit();
            editor.putString("showAds", showAds);
            editor.putString("show_loading", show_loading);
            editor.putString("allow_access", allow_access);
            editor.putString("mediation", mediation);

            editor.putString("ad1", ad1);
            editor.putString("ad2", ad2);
            editor.putString("ad3", ad3);
            editor.putString("ad4", ad4);
            editor.putString("ad5", ad5);

            editor.putString("g_app_id", g_app_id);
            editor.putString("g_banner", g_banner);
            editor.putString("show_gbanner", show_gbanner);
            editor.putString("g_inter1", g_inter1);
            editor.putString("show_ginter1", show_ginter1);
            editor.putString("g_inter2", g_inter2);
            editor.putString("show_ginter2", show_ginter2);
            editor.putString("g_rewarded", g_rewarded);
            editor.putString("show_grewarded", show_grewarded);
            editor.putString("g_native1", g_native1);
            editor.putString("show_gnative1", show_gnative1);
            editor.putString("g_native2", g_native2);
            editor.putString("show_gnative2", show_gnative2);

            editor.putString("fb_banner", fb_banner);
            editor.putString("show_fbbanner", show_fbbanner);
            editor.putString("fb_inter1", fb_inter1);
            editor.putString("show_fbinter1", show_fbinter1);
            editor.putString("fb_inter2", fb_inter2);
            editor.putString("show_fbinter2", show_fbinter2);
            editor.putString("fb_rewarded", fb_rewarded);
            editor.putString("show_fb_rewarded", show_fbrewarded);
            editor.putString("fb_native1", fb_native1);
            editor.putString("show_fbnative1", show_fbnative1);
            editor.putString("fb_native2", fb_native2);
            editor.putString("show_fbnative2", show_fbnative2);
            editor.putString("fb_native_banner", fb_native_banner);
            editor.putString("show_fb_nativebanner", show_fb_nativebanner);

            editor.putString("an_ad_id", an_ad_id);
            editor.putString("show_anbanner", show_anbanner);
            editor.putString("show_aninter1", show_aninter1);
            editor.putString("show_aninter2", show_aninter2);
            editor.putString("show_annative1", show_annative1);
            editor.putString("show_annative2", show_annative2);
            editor.putString("show_anrewarded1", show_anrewarded);

            editor.putString("mp_banner", mp_banner);
            editor.putString("show_mpbanner", show_mpbanner);
            editor.putString("mp_inter1", mp_inter1);
            editor.putString("show_mpinter1", show_mpinter1);
            editor.putString("mp_inter2", mp_inter2);
            editor.putString("show_mpinter2", show_mpinter2);
            editor.putString("mp_native1", mp_native1);
            editor.putString("show_mpnative1", show_mpnative1);
            editor.putString("mp_native2", mp_native2);
            editor.putString("show_mpnative2", show_mpnative2);
            editor.putString("mp_rewarded", mp_rewarded);
            editor.putString("show_mprewarded1", show_mprewarded);

            editor.putString("u_app_id", u_app_id);
            editor.putString("u_banner", u_banner);
            editor.putString("show_ubanner", show_ubanner);
            editor.putString("u_inter1", u_inter1);
            editor.putString("show_uinter1", show_uinter1);
            editor.putString("u_inter2", u_inter2);
            editor.putString("show_uinter2", show_uinter2);
            editor.putString("u_rewarded", u_rewarded);
            editor.putString("show_urewarded1", show_urewarded);

            editor.putString("extraPara1", extraPara1);
            editor.putString("extraPara2", extraPara2);
            editor.putString("extraPara3", extraPara3);
            editor.putString("extraPara4", extraPara4);

            editor.putString("sa_ad_count", sa_ad_count);

            editor.apply();
        }
    }


    public boolean showAds() {
        boolean output = false;
        String var;
        if (adsPreference != null) {
            var = adsPreference.getString("showAds", "1");
            output = Objects.equals(var, "1");
        }
        return output;
    }
    public boolean isMediationActive() {
        boolean output = false;
        String var;
        String mediationDefault = "false";
        if (adsPreference != null) {
            if (String.valueOf(ads.MEDIATION_STATUS()).equals("true")){
                mediationDefault = "1";
            }else {
                mediationDefault = "0";
            }
            var = adsPreference.getString("mediation", mediationDefault);
            output = Objects.equals(var, "1");
        }
        return output;
    }

    public boolean allowAccess() {
        boolean output = false;
        String var;
        String accessDefault = "false";
        if (adsPreference != null) {
            if (String.valueOf(ads.ALLOW_ACCESS()).equals("true")){
                accessDefault = "1";
            }else {
                accessDefault = "0";
            }
            var = adsPreference.getString("allow_access", accessDefault);
            output = Objects.equals(var, "1");
        }
        return output;
    }

    public boolean planA() {
        boolean output = false;
        String var;
        if (adsPreference != null) {
            var = adsPreference.getString("ad1", ads.AD1());
            output = Objects.equals(var, "1");
        }
        return output;
    }

    public boolean planB() {
        boolean output = false;
        String var;
        if (adsPreference != null) {
            var = adsPreference.getString("ad2", ads.AD2());
            output = Objects.equals(var, "1");
        }
        return output;
    }

    public boolean planC() {
        boolean output = false;
        String var;
        if (adsPreference != null) {
            var = adsPreference.getString("ad3", ads.AD3());
            output = Objects.equals(var, "1");
        }
        return output;
    }

    public boolean planD() {
        boolean output = false;
        String var;
        if (adsPreference != null) {
            var = adsPreference.getString("ad4", ads.AD4());
            output = Objects.equals(var, "1");
        }
        return output;
    }

    public boolean planE() {
        boolean output = false;
        String var;
        if (adsPreference != null) {
            var = adsPreference.getString("ad5", ads.AD5());
            output = Objects.equals(var, "1");
        }
        return output;
    }

    public String gAppId() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("g_app_id", ads.GOOGLE_APP_ID());
        }
        return var;
    }

    public String gBannerId() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("g_banner", ads.GOOGLE_BANNER());
        }
        return var;
    }

    public boolean showgBanner() {
        boolean output = false;
        String var;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_gbanner", "0"), "1");
        }
        return output;
    }

    public String gInterId1() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("g_inter1", ads.GOOGLE_INTER1());
        }
        return var;
    }

    public boolean showgInter1() {
        boolean output = false;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_ginter1", "0"), "1");
        }
        return output;
    }

    public String gInterId2() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("g_inter2", ads.GOOGLE_INTER2());
        }
        return var;
    }

    public boolean showgInter2() {
        boolean output = false;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_ginter2", "0"), "1");
        }
        return output;
    }

    public String gRewardedId() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("g_rewarded", ads.GOOGLE_REWARDED());
        }
        return var;
    }

    public boolean showgRewarded() {
        boolean output = false;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_grewarded", "0"), "1");
        }
        return output;
    }

    public String gNativeId1() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("g_native1", ads.GOOGLE_NATIVE1());
        }
        return var;
    }

    public boolean showgNative1() {
        boolean output = false;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_gnative1", "0"), "1");
        }
        ;
        return output;
    }

    public String gNativeId2() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("g_native2", ads.GOOGLE_NATIVE2());
        }
        return var;
    }

    public boolean showgNative2() {
        boolean output = false;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_gnative2", "0"), "1");
        }
        ;
        return output;
    }

    /////////////////////
    public String anAdId() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("an_ad_id", ads.AN_AD_ID());
        }
        return var;
    }

    public boolean showanBanner() {
        boolean output = false;
        String var;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_anbanner", "1"), "1");
        }
        return output;
    }


    public boolean showanInter1() {
        boolean output = false;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_aninter1", "1"), "1");
        }
        return output;
    }

    public boolean showanInter2() {
        boolean output = false;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_aninter2", "1"), "1");
        }
        return output;
    }

    public boolean showanNative1() {
        boolean output = false;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_annative1", "1"), "1");
        }
        ;
        return output;
    }


    public boolean showanNative2() {
        boolean output = false;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_annative2", "1"), "1");
        }
        ;
        return output;
    }

    public boolean showanRewarded() {
        boolean output = false;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_anrewarded", "1"), "1");
        }
        return output;
    }


    ///////////////////////
    /////////////////////
    public String mpBannerId() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("mp_banner", ads.MP_BANNER());
        }
        return var;
    }

    public boolean showmpBanner() {
        boolean output = false;
        String var;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_mpbanner", "1"), "1");
        }
        return output;
    }

    public String mpInterId1() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("mp_inter1", ads.MP_INTER1());
        }
        return var;
    }

    public boolean showmpInter1() {
        boolean output = false;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_mpinter1", "1"), "1");
        }
        return output;
    }

    public String mpInterId2() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("mp_inter2", ads.MP_INTER2());
        }
        return var;
    }

    public boolean showmpInter2() {
        boolean output = false;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_mpinter2", "1"), "1");
        }
        return output;
    }

    public String mpNativeId1() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("mp_native1", ads.MP_NATIVE1());
        }
        return var;
    }

    public boolean showmpNative1() {
        boolean output = false;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_mpnative1", "1"), "1");
        }
        ;
        return output;
    }

    public String mpNativeId2() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("mp_native2", ads.MP_NATIVE2());
        }
        return var;
    }

    public boolean showmpNative2() {
        boolean output = false;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_mpnative2", "1"), "1");
        }
        ;
        return output;
    }

    public String mpRewardedId() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("mp_rewarded", ads.MP_REWARDED());
        }
        return var;
    }

    public boolean showmpRewarded() {
        boolean output = false;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_mprewarded", "1"), "1");
        }
        return output;
    }

    public String uAppId() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("u_app_id", ads.U_APP_ID());
        }
        return var;
    }
  public String uBannerId() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("u_banner", ads.U_BANNER());
        }
        return var;
    }

    public boolean showuBanner() {
        boolean output = false;
        String var;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_ubanner", "0"), "1");
        }
        return output;
    }

    public String uInterId1() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("u_inter1", ads.U_INTER1());
        }
        return var;
    }

    public boolean showuInter1() {
        boolean output = false;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_uinter1", "0"), "1");
        }
        return output;
    }

    public String uInterId2() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("u_inter2", ads.U_INTER2());
        }
        return var;
    }

    public boolean showuInter2() {
        boolean output = false;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_uinter2", "0"), "1");
        }
        return output;
    }

    public String uRewardedId() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("u_rewarded", ads.U_REWARDED());
        }
        return var;
    }

    public boolean showuRewarded() {
        boolean output = false;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_urewarded", "0"), "1");
        }
        return output;
    }


    public String fbBannerId() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("fb_banner", ads.FB_BANNER());
        }
        return var;
    }

    public boolean showfbBanner() {
        boolean output = false;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_fbbanner", "1"), "1");
        }
        ;
        return output;
    }

    public String fbInterId1() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("fb_inter1", ads.FB_INTER1());
        }
        return var;
    }

    public boolean showfbInter1() {
        boolean output = false;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_fbinter1", "1"), "1");
        }
        return output;
    }

    public String fbInterId2() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("fb_inter2", ads.FB_INTER2());
        }
        return var;
    }

    public boolean showfbInter2() {
        boolean output = false;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_fbinter2", "1"), "1");
        }
        return output;
    }

    public String fbRewardedId() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("fb_rewarded", ads.FB_REWARDED());
        }
        return var;
    }

    public boolean showfbRewarded() {
        boolean output = false;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_fbrewarded", "1"), "1");
        }
        return output;
    }

    public String fbNativeId1() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("fb_native1", ads.FB_NATIVE1());
        }
        return var;
    }

    public boolean showfbNative1() {
        boolean output = false;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_fbnative1", "1"), "1");
        }
        return output;
    }

    public String fbNativeId2() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("fb_native2", ads.FB_NATIVE2());
        }
        return var;
    }

    public boolean showfbNative2() {
        boolean output = false;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_fbnative2", "1"), "1");
        }
        return output;
    }

    public String fbNativeBannerId() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("fb_native_banner", ads.FB_NATIVE_BANNER());
        }
        return var;
    }

    public boolean showfbNativeBanner() {
        boolean output = false;
        if (adsPreference != null) {
            output = showAds() && Objects.equals(adsPreference.getString("show_fb_nativebanner", "1"), "1");
        }
        return output;
    }


    public String extraPara1() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("extraPara1", "1");
        }
        return var;
    }

    public String extraPara2() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("extraPara2", "1");
        }
        return var;
    }

    public String extraPara3() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("extraPara3", "1");
        }
        return var;
    }

    public String extraPara4() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("extraPara4", "1");
        }
        return var;
    }


    public boolean showLoading() {
        boolean output = false;
        if (adsPreference != null) {
            output = Objects.equals(adsPreference.getString("show_loading", "0"), "1");
        }
        return output;
    }

    public String adCountSA() {
        String var = "";
        if (adsPreference != null) {
            var = adsPreference.getString("sa_ad_count", "2");
        }
        return var;
    }

    public boolean isConsentShown() {
        return adsPreference.getBoolean("isConsentShown",false);
    }

    public void setConsentShown() {
         editor.putBoolean("isConsentShown",true);
         editor.apply();    }

    public void setConsent(Boolean userConsent) {
        editor.putBoolean("userConsent",userConsent);
        editor.apply();    }


    public boolean getConsent(){
        return adsPreference.getBoolean("userConsent",false);
    }
}



