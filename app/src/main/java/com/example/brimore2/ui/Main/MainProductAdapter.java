package com.example.brimore2.ui.Main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brimore2.R;
import com.example.brimore2.domain.models.main.dynamicsectionone.DynamicSectionVariant;
import com.example.brimore2.databinding.MainItemsSliderBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class MainProductAdapter extends RecyclerView.Adapter<MainProductAdapter.MyHolder> {

    @NonNull
    @NotNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        MainItemsSliderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.main_items_slider,parent,false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MainProductAdapter.MyHolder holder, int position) {
        DynamicSectionVariant dynamicSectionVariant = dynamicSectionVariantList.get(position);
        holder.binding.setModel(dynamicSectionVariant);

    }

    @Override
    public int getItemCount() {
        return dynamicSectionVariantList !=null? dynamicSectionVariantList.size():0;
    }
    List<DynamicSectionVariant> dynamicSectionVariantList;
    Context context;
    public void setData(@ApplicationContext Context context, List<DynamicSectionVariant> dynamicSectionVariantList){
        this.context = context;
        this.dynamicSectionVariantList = dynamicSectionVariantList;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        MainItemsSliderBinding binding;
        public MyHolder(@NonNull @NotNull MainItemsSliderBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
