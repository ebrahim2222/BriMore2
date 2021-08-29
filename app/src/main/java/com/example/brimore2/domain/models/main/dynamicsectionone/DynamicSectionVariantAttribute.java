package com.example.brimore2.domain.models.main.dynamicsectionone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DynamicSectionVariantAttribute {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("value_id")
    @Expose
    private Integer valueId;
    @SerializedName("value_name")
    @Expose
    private String valueName;
    @SerializedName("color_code")
    @Expose
    private String colorCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValueId() {
        return valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }


}
