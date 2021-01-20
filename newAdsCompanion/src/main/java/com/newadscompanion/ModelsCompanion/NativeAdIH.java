package com.newadscompanion.ModelsCompanion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NativeAdIH {


    @SerializedName("NativeDetail")
    @Expose
    private ArrayList<NativeDetail> nativeDetail = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public ArrayList<NativeDetail> getNativeDetail() {
        return nativeDetail;
    }

    public void setNativeDetail(ArrayList<NativeDetail> nativeDetail) {
        this.nativeDetail = nativeDetail;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }
}
