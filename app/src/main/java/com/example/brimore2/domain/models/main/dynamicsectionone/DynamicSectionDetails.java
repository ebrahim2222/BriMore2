package com.example.brimore2.domain.models.main.dynamicsectionone;

import java.util.List;

import com.example.brimore2.domain.models.main.brands.BrandsDetails;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DynamicSectionDetails {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("brief")
    @Expose
    private String brief;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("brand")
    @Expose
    private BrandsDetails brand;
    @SerializedName("hot_deal")
    @Expose
    private Object hotDeal;
    @SerializedName("attributes")
    @Expose
    private List<DynamicSectionAttribute> attributes = null;
    @SerializedName("variants")
    @Expose
    private List<DynamicSectionVariant> variants = null;
    @SerializedName("tag_list")
    @Expose
    private List<Object> tagList = null;

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

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BrandsDetails getBrand() {
        return brand;
    }

    public void setBrand(BrandsDetails brand) {
        this.brand = brand;
    }

    public Object getHotDeal() {
        return hotDeal;
    }

    public void setHotDeal(Object hotDeal) {
        this.hotDeal = hotDeal;
    }

    public List<DynamicSectionAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<DynamicSectionAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<DynamicSectionVariant> getVariants() {
        return variants;
    }

    public void setVariants(List<DynamicSectionVariant> variants) {
        this.variants = variants;
    }

    public List<Object> getTagList() {
        return tagList;
    }

    public void setTagList(List<Object> tagList) {
        this.tagList = tagList;
    }

}

