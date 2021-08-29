package com.example.brimore2.domain.models.main.brands;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BrandsData {

    @SerializedName("data")
    @Expose
    private List<BrandsDetails> data = null;

    public List<BrandsDetails> getData() {
        return data;
    }

    public void setData(List<BrandsDetails> data) {
        this.data = data;
    }

}