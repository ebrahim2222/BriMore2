package com.example.brimore2.ui.products;

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
import com.example.brimore2.domain.models.main.dynamicsectionone.DynamicSectionVariant;
import com.example.brimore2.databinding.FragmentProductItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyHolder> {

    @Inject
    Bundle bundle;
    @NonNull
    @NotNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        FragmentProductItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_product_item,parent,false);

        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductAdapter.MyHolder holder, int position) {
        DynamicSectionVariant datum = datumList.get(position);

        holder.binding.setModel(datum);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putParcelable("productDetail",datum);
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_productsFragment_to_productDetailsFragment,bundle);
            }
        });

    }
    Context context;
    List<DynamicSectionVariant> datumList;
    public void setData(Context context, List<DynamicSectionVariant> datumList){
        this.context = context;
        this.datumList = datumList;
    }

    @Override
    public int getItemCount() {
        return datumList!=null?datumList.size():0;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        FragmentProductItemBinding binding;
        public MyHolder(@NonNull @NotNull FragmentProductItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
