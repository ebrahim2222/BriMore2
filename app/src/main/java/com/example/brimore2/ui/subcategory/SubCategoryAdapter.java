package com.example.brimore2.ui.subcategory;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brimore2.R;
import com.example.brimore2.di.module.Modules;
import com.example.brimore2.domain.models.main.maincategory.MainCategoryDetails;
import com.example.brimore2.databinding.FragmentSubItemsBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.MyHolder> {

    @NonNull
    @NotNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        FragmentSubItemsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_sub_items,parent,false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SubCategoryAdapter.MyHolder holder, int position) {
        MainCategoryDetails categoriesDetails = categoriesDetailsList.get(position);
        holder.binding.setModel(categoriesDetails);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putParcelable("subCatId",categoriesDetails);

                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_subCategoryFragment_to_productsFragment,bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoriesDetailsList !=null? categoriesDetailsList.size():0;
    }
    Context context;
    List<MainCategoryDetails> categoriesDetailsList;
    public void setData(Context context, List<MainCategoryDetails> categoriesDetailsList){
        this.context = context;
        this.categoriesDetailsList = categoriesDetailsList;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        FragmentSubItemsBinding binding;
        public MyHolder(@NonNull @NotNull FragmentSubItemsBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
