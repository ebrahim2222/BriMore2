package com.example.brimore2.domain.models.main.maincategory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class MainCategory {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("background_color")
    @Expose
    private Object backgroundColor;
    @SerializedName("order")
    @Expose
    private Object order;
    @SerializedName("section_type")
    @Expose
    private String sectionType;
    @SerializedName("data")
    @Expose
    private MainCategoryData data;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Object getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Object backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Object getOrder() {
        return order;
    }

    public void setOrder(Object order) {
        this.order = order;
    }

    public String getSectionType() {
        return sectionType;
    }

    public void setSectionType(String sectionType) {
        this.sectionType = sectionType;
    }

    public MainCategoryData getData() {
        return data;
    }

    public void setData(MainCategoryData data) {
        this.data = data;
    }
}
