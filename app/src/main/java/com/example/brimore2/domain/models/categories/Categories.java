package com.example.brimore2.domain.models.categories;

import com.example.brimore2.domain.models.main.maincategory.MainCategoryDetails;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Categories {

    @SerializedName("data")
    @Expose
    private List<MainCategoryDetails> data = null;

    public List<MainCategoryDetails> getData() {
        return data;
    }

    public void setData(List<MainCategoryDetails> data) {
        this.data = data;
    }

}
