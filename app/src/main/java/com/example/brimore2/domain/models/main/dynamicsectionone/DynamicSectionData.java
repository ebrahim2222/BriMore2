package com.example.brimore2.domain.models.main.dynamicsectionone;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DynamicSectionData {

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
