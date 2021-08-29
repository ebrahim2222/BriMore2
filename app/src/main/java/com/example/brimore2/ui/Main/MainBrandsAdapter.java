package com.example.brimore2.ui.Main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brimore2.R;
import com.example.brimore2.domain.models.main.brands.BrandsDetails;
import com.example.brimore2.databinding.MainBrandsItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainBrandsAdapter extends RecyclerView.Adapter<MainBrandsAdapter.MyHolder> {

    @NonNull
    @NotNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        MainBrandsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.main_brands_item,parent,false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MainBrandsAdapter.MyHolder holder, int position) {
        BrandsDetails datum = brandsData.get(position);
        holder.binding.setBrandsModel(datum);
    }


    Context context;
    List<BrandsDetails> brandsData;
    public void setData(Context context, List<BrandsDetails> brandsData){
        this.context = context;
        this.brandsData = brandsData;
    }

    @Override
    public int getItemCount() {
        return brandsData!=null?brandsData.size():0;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        MainBrandsItemBinding binding;
        public MyHolder(@NonNull @NotNull MainBrandsItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
