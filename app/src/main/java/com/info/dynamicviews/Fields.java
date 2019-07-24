package com.info.dynamicviews;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fields {

    @SerializedName("field_id")
    @Expose
    private Long fieldId;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("attribute_type_id")
    @Expose
    private Long attributeTypeId;
    @SerializedName("component")
    @Expose
    private String component;
    @SerializedName("display_order")
    @Expose
    private Long displayOrder;
    @SerializedName("is_mandatory")
    @Expose
    private int isMandatory;
    @SerializedName("max_length")
    @Expose
    private int maxLength;
    @SerializedName("component_type")
    @Expose
    private String componentType;
    @SerializedName("label_code")
    @Expose
    private String labelCode;

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getAttributeTypeId() {
        return attributeTypeId;
    }

    public void setAttributeTypeId(Long attributeTypeId) {
        this.attributeTypeId = attributeTypeId;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Long getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
    }

    public int getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(int isMandatory) {
        this.isMandatory = isMandatory;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }


}
