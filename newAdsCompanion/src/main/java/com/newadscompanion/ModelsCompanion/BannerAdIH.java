package com.newadscompanion.ModelsCompanion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BannerAdIH {

    @SerializedName("BannerDetail")
    @Expose
    private ArrayList<BannerDetail> bannerDetail = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public ArrayList<BannerDetail> getBannerDetail() {
        return bannerDetail;
    }

    public void setBannerDetail(ArrayList<BannerDetail> bannerDetail) {
        this.bannerDetail = bannerDetail;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }
}
