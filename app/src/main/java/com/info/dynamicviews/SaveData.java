package com.info.dynamicviews;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class SaveData {
    @SerializedName("template_id")
    @Expose
    private Long templateId;

    @SerializedName("user_id")
    @Expose
    private Long userId;

    @SerializedName("offer_detail")
    @Expose
    private HashMap<String, String> offerDetail;

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public HashMap<String, String> getOfferDetail() {
        return offerDetail;
    }

    public void setOfferDetail(HashMap<String, String> offerDetail) {
        this.offerDetail = offerDetail;
    }
}
