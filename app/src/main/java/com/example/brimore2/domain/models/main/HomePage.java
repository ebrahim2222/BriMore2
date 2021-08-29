package com.example.brimore2.domain.models.main;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.example.brimore2.R;
import com.example.brimore2.domain.models.main.brands.Brands;
import com.example.brimore2.domain.models.main.dynamicsectionone.DynamicSectionOne;
import com.example.brimore2.domain.models.main.maincategory.MainCategory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class HomePage {
    @SerializedName("BRANDS")
    @Expose
    private Brands brands;
    @SerializedName("CATEGORY")
    @Expose
    private MainCategory mainCategory;

    @SerializedName("DYNAMIC_SECTION_ONE")
    @Expose
    private DynamicSectionOne dynamicSectionOne;

    public Brands getBrands() {
        return brands;
    }

    public void setBrands(Brands brands) {
        this.brands = brands;
    }

    public MainCategory getCategory() {
        return mainCategory;
    }

    public void setCategory(MainCategory mainCategory) {
        this.mainCategory = mainCategory;
    }

    public DynamicSectionOne getDynamicSectionOne() {
        return dynamicSectionOne;
    }

    public void setDynamicSectionOne(DynamicSectionOne dynamicSectionOne) {
        this.dynamicSectionOne = dynamicSectionOne;
    }

    @BindingAdapter("setImage")
    public static  void setImage(View view , String url){
       ImageView imageView =  (ImageView)view;
       Glide.with(view).load(url).placeholder(R.drawable.glide_placeholder).into(imageView);

    }
}
