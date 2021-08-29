package com.example.brimore2.ui.Main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brimore2.R;
import com.example.brimore2.domain.models.main.maincategory.MainCategoryDetails;
import com.example.brimore2.databinding.MainCategoriesItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainItemAdapter extends RecyclerView.Adapter<MainItemAdapter.MyHolder> {

    @NonNull
    @NotNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        MainCategoriesItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.main_categories_item,parent,false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MainItemAdapter.MyHolder holder, int position) {
        MainCategoryDetails mainCategoryDetails = mainCategoryDetailsList.get(position);
        holder.binding.setModel(mainCategoryDetails);
    }

    @Override
    public int getItemCount() {
        return mainCategoryDetailsList!=null?mainCategoryDetailsList.size():0;
    }
    List<MainCategoryDetails> mainCategoryDetailsList;
    Context context;
    public void setData(Context context, List<MainCategoryDetails> mainCategoryDetailsList) {
        this.context = context;
        this.mainCategoryDetailsList = mainCategoryDetailsList;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        MainCategoriesItemBinding binding;
        public MyHolder(@NonNull @NotNull MainCategoriesItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
