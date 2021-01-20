package com.newadscompanion.ModelsCompanion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class InterstitialAdIH {
    @SerializedName("InterDetail")
    @Expose
    private ArrayList<InterDetail> interDetail = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public ArrayList<InterDetail> getInterDetail() {
        return interDetail;
    }

    public void setInterDetail(ArrayList<InterDetail> interDetail) {
        this.interDetail = interDetail;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

}
