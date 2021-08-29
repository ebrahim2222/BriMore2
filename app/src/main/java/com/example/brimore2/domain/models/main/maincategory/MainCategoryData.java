package com.example.brimore2.domain.models.main.maincategory;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class MainCategoryData {
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
