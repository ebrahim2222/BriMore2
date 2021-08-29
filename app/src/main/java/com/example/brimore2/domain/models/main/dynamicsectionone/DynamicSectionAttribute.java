package com.example.brimore2.domain.models.main.dynamicsectionone;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DynamicSectionAttribute {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("values")
    @Expose
    private List<DynamicSectionAttributeValues> values = null;

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

    public List<DynamicSectionAttributeValues> getValues() {
        return values;
    }

    public void setValues(List<DynamicSectionAttributeValues> values) {
        this.values = values;
    }

}
