package com.newadscompanion.AdsConfig;

import android.content.Context;
import android.content.SharedPreferences;

import com.newadscompanion.R;


public class DefaultIds {

    Context context;
    SharedPreferences defaultAdsPreference;
    SharedPreferences.Editor editor;

    public DefaultIds(Context context) {
        this.context = context;
        defaultAdsPreference = context.getSharedPreferences("defaultsIdsPrefrence", Context.MODE_PRIVATE);
        editor = defaultAdsPreference.edit();
    }

    public void setDefaults(String app_key, String ad1, String ad2, String ad3, String ad4, String ad5,
                            String g_app_id, String g_banner, String g_inter1, String g_inter2, String g_native1, String g_native2, String g_rewarded,
                            String fb_banner, String fb_inter1, String fb_inter2, String fb_native1, String fb_native2, String fb_native_banner, String fb_rewarded,
                            String an_ad_id,
                            String mp_banner, String mp_inter1, String mp_inter2, String mp_native1, String mp_native2, String mp_rewarded,
                            String is_app_id,
                            String is_banner, String is_inter1, String is_inter2, String is_rewarded,
                            String extraPara1Default, String extraPara2Default, String extraPara3Default, String extraPara4Default,
                            String sa_app_id, String sa_ad_count,
                            Boolean disable_sa_splash, Boolean show_notification, Boolean show_loading, Boolean u_test_mode, Boolean allow_access,Boolean mediation_status, int tint_Mode) {

        editor = defaultAdsPreference.edit();
        editor.putString("app_key", app_key);

        editor.putString("ad1", ad1);
        editor.putString("ad2", ad2);
        editor.putString("ad3", ad3);
        editor.putString("ad4", ad4);
        editor.putString("ad5", ad5);

        editor.putString("g_app_id", g_app_id);
        editor.putString("g_banner", g_banner);
        editor.putString("g_inter1", g_inter1);
        editor.putString("g_inter2", g_inter2);
        editor.putString("g_native1", g_native1);
        editor.putString("g_native2", g_native2);
        editor.putString("g_rewarded", g_rewarded);

        editor.putString("fb_banner", fb_banner);
        editor.putString("fb_inter1", fb_inter1);
        editor.putString("fb_inter2", fb_inter2);
        editor.putString("fb_native1", fb_native1);
        editor.putString("fb_native2", fb_native2);
        editor.putString("fb_native_banner", fb_native_banner);
        editor.putString("fb_rewarded", fb_rewarded);

        editor.putString("an_ad_id", an_ad_id);

        editor.putString("mp_banner", mp_banner);
        editor.putString("mp_inter1", mp_inter1);
        editor.putString("mp_inter2", mp_inter2);
        editor.putString("mp_native1", mp_native1);
        editor.putString("mp_native2", mp_native2);
        editor.putString("mp_rewarded", mp_rewarded);

        editor.putString("is_app_id", is_app_id);
        editor.putString("is_banner", is_banner);
        editor.putString("is_inter1", is_inter1);
        editor.putString("is_inter2", is_inter2);
        editor.putString("is_rewarded", is_rewarded);


        editor.putString("sa_app_id", sa_app_id);
        editor.putString("sa_ad_count", sa_ad_count);
        editor.putBoolean("disable_sa_splash", disable_sa_splash);
        editor.putBoolean("show_notification", show_notification);
        editor.putBoolean("show_loading", show_loading);

        editor.putBoolean("u_test_mode", u_test_mode);
        editor.putBoolean("allow_access", allow_access);
        editor.putBoolean("mediation_status", mediation_status);

        editor.putString("extraPara1", extraPara1Default);
        editor.putString("extraPara2", extraPara2Default);
        editor.putString("extraPara3", extraPara3Default);
        editor.putString("extraPara4", extraPara4Default);
        editor.putInt("tint_Mode", tint_Mode);
        editor.apply();

    }
    public String AD1() {
        return defaultAdsPreference.getString("ad1", "0");
    }
    public String AD2() {
        return defaultAdsPreference.getString("ad2", "0");
    }
    public String AD3() {
        return defaultAdsPreference.getString("ad3", "0");
    }
    public String AD4() {
        return defaultAdsPreference.getString("ad4", "0");
    }
    public String AD5() {
        return defaultAdsPreference.getString("ad5", "0");
    }

    public String AN_AD_ID() {
        return defaultAdsPreference.getString("an_ad_id", "");
    }

    public String MP_BANNER() {
        return defaultAdsPreference.getString("mp_banner", "");
    }

    public String MP_INTER1() {
        return defaultAdsPreference.getString("mp_inter1", "");
    }

    public String MP_INTER2() {
        return defaultAdsPreference.getString("mp_inter2", "");
    }

    public String MP_NATIVE1() {
        return defaultAdsPreference.getString("mp_native1", "");
    }

    public String MP_NATIVE2() {
        return defaultAdsPreference.getString("mp_native2", "");
    }

    public String MP_REWARDED() {
        return defaultAdsPreference.getString("mp_rewarded", "");
    }

    public String IS_APP_ID() {
        return defaultAdsPreference.getString("is_app_id", "");
    }

    public String IS_BANNER() {
        return defaultAdsPreference.getString("is_banner", "");
    }

    public String IS_INTER1() {
        return defaultAdsPreference.getString("is_inter1", "");
    }

    public String IS_INTER2() {
        return defaultAdsPreference.getString("is_inter2", "");
    }

    public String IS_REWARDED() {
        return defaultAdsPreference.getString("is_rewarded", "");
    }


    public String APP_KEY() {
        return defaultAdsPreference.getString("app_key", "");
    }

    public String GOOGLE_APP_ID() {
        return defaultAdsPreference.getString("g_app_id", "");
    }

    public String GOOGLE_BANNER() {
        return defaultAdsPreference.getString("g_banner", "");
    }

    public String GOOGLE_INTER1() {
        return defaultAdsPreference.getString("g_inter1", "");
    }

    public String GOOGLE_INTER2() {
        return defaultAdsPreference.getString("g_inter2", "");
    }

    public String GOOGLE_NATIVE1() {
        return defaultAdsPreference.getString("g_native1", "");
    }

    public String GOOGLE_NATIVE2() {
        return defaultAdsPreference.getString("g_native2", "");
    }

    public String GOOGLE_REWARDED() {
        return defaultAdsPreference.getString("g_rewarded", "");
    }

    public String FB_BANNER() {
        return defaultAdsPreference.getString("fb_banner", "");
    }

    public String FB_INTER1() {
        return defaultAdsPreference.getString("fb_inter1", "");
    }

    public String FB_INTER2() {
        return defaultAdsPreference.getString("fb_inter2", "");
    }

    public String FB_NATIVE1() {
        return defaultAdsPreference.getString("fb_native1", "");
    }

    public String FB_NATIVE2() {
        return defaultAdsPreference.getString("fb_native2", "");
    }

    public String FB_NATIVE_BANNER() {
        return defaultAdsPreference.getString("fb_native_banner", "");
    }

    public String FB_REWARDED() {
        return defaultAdsPreference.getString("fb_rewarded", "");
    }

    public String SA_APP_ID() {
        return defaultAdsPreference.getString("sa_app_id", "");
    }

    public String SA_AD_COUNT() {
        return defaultAdsPreference.getString("sa_ad_count", "2");
    }


    public int TINT_COLOR() {
        return defaultAdsPreference.getInt("tint_Mode", context.getResources().getColor(R.color._tint_color));
    }

    public Boolean DISABLE_SA_SPLASH() {
        return defaultAdsPreference.getBoolean("disable_sa_splash", true);
    }

    public Boolean SHOW_NOTIFICATION() {
        return defaultAdsPreference.getBoolean("show_notification", false);
    }
   public Boolean U_TEST_MODE() {
        return defaultAdsPreference.getBoolean("u_test_mode", false);
    }

  public Boolean ALLOW_ACCESS() {
        return defaultAdsPreference.getBoolean("allow_access", false);
    }

   public Boolean MEDIATION_STATUS() {
        return defaultAdsPreference.getBoolean("mediation_status", false);
    }

    public Boolean SHOW_LOADING() {
        return defaultAdsPreference.getBoolean("show_loading", false);
    }

    public String PARA1DEFAULT() {
        return defaultAdsPreference.getString("extraPara1", "0");
    }

    public String PARA2DEFAULT() {
        return defaultAdsPreference.getString("extraPara2", "0");
    }

    public String PARA3DEFAULT() {
        return defaultAdsPreference.getString("extraPara3", "0");
    }

    public String PARA4DEFAULT() {
        return defaultAdsPreference.getString("extraPara4", "0");
    }


}
