package com.newadscompanion.ModelsCompanion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NativeDetail {

    @SerializedName("native_id")
    @Expose
    private String nativeId;
    @SerializedName("showad")
    @Expose
    private String showad;
    @SerializedName("openin")
    @Expose
    private String openin;
    @SerializedName("applink")
    @Expose
    private String applink;
    @SerializedName("showreview")
    @Expose
    private String showreview;
    @SerializedName("reviewcount")
    @Expose
    private String reviewcount;
    @SerializedName("showrating")
    @Expose
    private String showrating;
    @SerializedName("ratingcount")
    @Expose
    private String ratingcount;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("extratext")
    @Expose
    private String extratext;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("bigimage")
    @Expose
    private String bigimage;
    @SerializedName("buttontext")
    @Expose
    private String buttontext;

    public NativeDetail(String nativeId, String showad, String openin, String applink, String showreview, String reviewcount, String showrating, String ratingcount, String title, String subtitle, String extratext, String icon, String bigimage, String buttontext) {
        this.nativeId = nativeId;
        this.showad = showad;
        this.openin = openin;
        this.applink = applink;
        this.showreview = showreview;
        this.reviewcount = reviewcount;
        this.showrating = showrating;
        this.ratingcount = ratingcount;
        this.title = title;
        this.subtitle = subtitle;
        this.extratext = extratext;
        this.icon = icon;
        this.bigimage = bigimage;
        this.buttontext = buttontext;
    }

    public String getExtratext() {
        return extratext;
    }

    public void setExtratext(String extratext) {
        this.extratext = extratext;
    }

    public String getNativeId() {
        return nativeId;
    }

    public void setNativeId(String nativeId) {
        this.nativeId = nativeId;
    }

    public String getShowad() {
        return showad;
    }

    public void setShowad(String showad) {
        this.showad = showad;
    }

    public String getOpenin() {
        return openin;
    }

    public void setOpenin(String openin) {
        this.openin = openin;
    }

    public String getApplink() {
        return applink;
    }

    public void setApplink(String applink) {
        this.applink = applink;
    }

    public String getShowreview() {
        return showreview;
    }

    public void setShowreview(String showreview) {
        this.showreview = showreview;
    }

    public String getReviewcount() {
        return reviewcount;
    }

    public void setReviewcount(String reviewcount) {
        this.reviewcount = reviewcount;
    }

    public String getShowrating() {
        return showrating;
    }

    public void setShowrating(String showrating) {
        this.showrating = showrating;
    }

    public String getRatingcount() {
        return ratingcount;
    }

    public void setRatingcount(String ratingcount) {
        this.ratingcount = ratingcount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBigimage() {
        return bigimage;
    }

    public void setBigimage(String bigimage) {
        this.bigimage = bigimage;
    }

    public String getButtontext() {
        return buttontext;
    }

    public void setButtontext(String buttontext) {
        this.buttontext = buttontext;
    }

}