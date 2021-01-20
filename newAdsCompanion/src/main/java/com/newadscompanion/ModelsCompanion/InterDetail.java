package com.newadscompanion.ModelsCompanion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InterDetail {

@SerializedName("int_id")
@Expose
private String intId;
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
@SerializedName("icon")
@Expose
private String icon;
@SerializedName("buttontext")
@Expose
private String buttontext;
@SerializedName("bigimage")
@Expose
private String bigimage;
@SerializedName("bigimage2")
@Expose
private String bigimage2;
@SerializedName("desc_title")
@Expose
private String descTitle;
@SerializedName("desc_text")
@Expose
private String descText;

    public InterDetail(String intId, String showad, String openin, String applink, String showreview, String reviewcount, String showrating, String ratingcount, String title, String subtitle, String icon, String buttontext, String bigimage, String bigimage2, String descTitle, String descText) {
        this.intId = intId;
        this.showad = showad;
        this.openin = openin;
        this.applink = applink;
        this.showreview = showreview;
        this.reviewcount = reviewcount;
        this.showrating = showrating;
        this.ratingcount = ratingcount;
        this.title = title;
        this.subtitle = subtitle;
        this.icon = icon;
        this.buttontext = buttontext;
        this.bigimage = bigimage;
        this.bigimage2 = bigimage2;
        this.descTitle = descTitle;
        this.descText = descText;
    }

    public String getIntId() {
return intId;
}

public void setIntId(String intId) {
this.intId = intId;
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

public String getButtontext() {
return buttontext;
}

public void setButtontext(String buttontext) {
this.buttontext = buttontext;
}

public String getBigimage() {
return bigimage;
}

public void setBigimage(String bigimage) {
this.bigimage = bigimage;
}

public String getBigimage2() {
return bigimage2;
}

public void setBigimage2(String bigimage2) {
this.bigimage2 = bigimage2;
}

public String getDescTitle() {
return descTitle;
}

public void setDescTitle(String descTitle) {
this.descTitle = descTitle;
}

public String getDescText() {
return descText;
}

public void setDescText(String descText) {
this.descText = descText;
}

}