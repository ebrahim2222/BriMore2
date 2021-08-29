package com.example.brimore2.ui.product_details;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.brimore2.R;
import com.example.brimore2.di.module.Modules;
import com.example.brimore2.domain.models.main.dynamicsectionone.DynamicSectionVariant;
import com.example.brimore2.databinding.FragmentProductDetailsBinding;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import io.paperdb.Paper;

public class ProductDetailsFragment extends Fragment {
    private static final String TAG = "ProductDetailsFragment";
    FragmentProductDetailsBinding binding;
    NavController navController;
    ProductsDetailsViewModel viewModel;


    public ProductDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Paper.init(requireContext());


    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_product_details,container,false);
        View view = binding.getRoot();
        DynamicSectionVariant productDetail = getArguments().getParcelable("productDetail");
        viewModel = new ViewModelProvider(requireActivity()).get(ProductsDetailsViewModel.class);
        binding.setModel(productDetail);

        binding.productDetailPrice.setText(productDetail.getMemberPrice()+" جنية");
        binding.productDetailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.popBackStack();
            }
        });
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               viewModel.removeEmail();
               navController.navigate(R.id.action_productDetailsFragment_to_viewPagerFragment);
            }
        });

        return view;
    }

}