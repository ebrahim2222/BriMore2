package com.example.brimore2.ui.categories;

import android.content.Context;
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
import com.example.brimore2.domain.models.main.maincategory.MainCategoryDetails;
import com.example.brimore2.databinding.CategoriesRightSideItemBinding;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyHolder> {

    Bundle bundle;

    @NonNull
    @NotNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        CategoriesRightSideItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.categories_right_side_item,parent,false);
        return new MyHolder(binding);
    }

    @Override
        public void onBindViewHolder(@NonNull @NotNull CategoryAdapter.MyHolder holder, int position) {
        MainCategoryDetails categoriesDetails = this.categories.get(position);
        holder.binding.setCategoryModel(categoriesDetails);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                Integer id = categoriesDetails.getId();
                preferences = context.getSharedPreferences("categoryId",Context.MODE_PRIVATE );
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("id",id);
                editor.commit();



 */
                bundle = new Bundle();
                bundle.putParcelable("category", categoriesDetails);

                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_categoriesFragment_to_subCategoryFragment,bundle);
               // Toast.makeText(context, id+"", Toast.LENGTH_SHORT).show();
            }
        });




    }

    @Override
    public int getItemCount() {
        return categories!=null?categories.size():0;
    }
    Context context;
    List<MainCategoryDetails> categories;
    public void setData(Context context , List<MainCategoryDetails> categories){
        this.context = context;
        this.categories = categories;
    }

    class MyHolder extends RecyclerView.ViewHolder {

        CategoriesRightSideItemBinding binding;
        public MyHolder(@NonNull @NotNull CategoriesRightSideItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;

        }
    }


}
