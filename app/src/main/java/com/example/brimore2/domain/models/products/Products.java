package com.example.brimore2.domain.models.products;

import java.util.List;

import com.example.brimore2.domain.models.main.dynamicsectionone.DynamicSectionDetails;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Products {

    @SerializedName("data")
    @Expose
    private List<DynamicSectionDetails> data = null;

    public List<DynamicSectionDetails> getData() {
        return data;
    }

    public void setData(List<DynamicSectionDetails> data) {
        this.data = data;
    }

}
