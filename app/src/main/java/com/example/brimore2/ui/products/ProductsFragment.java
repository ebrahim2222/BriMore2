package com.example.brimore2.ui.products;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.brimore2.R;
import com.example.brimore2.utils.SpaceBetweenItems;
import com.example.brimore2.domain.models.main.dynamicsectionone.DynamicSectionDetails;
import com.example.brimore2.domain.models.main.dynamicsectionone.DynamicSectionVariant;
import com.example.brimore2.domain.models.main.maincategory.MainCategoryDetails;
import com.example.brimore2.databinding.FragmentProductsBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class ProductsFragment extends Fragment {
    @Inject
    SpaceBetweenItems spaceBetweenItems;

    @Inject
    GridLayoutManager manager;

    ProductsViewModel viewModel;

    FragmentProductsBinding binding;

    @Inject
    ProductAdapter adapter;

    MainCategoryDetails mainCategoryDetails;

    List<DynamicSectionVariant> variantList;

    private static final String TAG = "ProductsFragment";
    private String token;

    public ProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_products,container,false);
        View view = binding.getRoot();
        //binding.productsProgresBar.setVisibility(View.VISIBLE);

        viewModel = new ViewModelProvider(requireActivity()).get(ProductsViewModel.class);

        setUpRecycler();

        getDataFromPreviousFrag();

        getUserToken();

        binding.productsSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.productsSwipe.setRefreshing(true);
                productsObservation();
            }
        });


        viewModel.getProducts(token,mainCategoryDetails.getId());

        productsObservation();

        return view;
    }

    private void productsObservation() {
        viewModel.mutableLiveData.observe(requireActivity(), new Observer<List<DynamicSectionDetails>>() {
            @Override
            public void onChanged(List<DynamicSectionDetails> data) {
                if(data !=null)
                {
                    variantList = new ArrayList<>();
                    for(int i=0; i<data.size();i++)
                    {
                        List<DynamicSectionVariant> variant = data.get(i).getVariants();
                        variantList.addAll(variant);

                    }
                    Log.d(TAG, "aly onChanged: "+variantList);
                    binding.productsProgresBar.setVisibility(View.GONE);
                    adapter.setData(requireContext(),variantList);
                    adapter.notifyDataSetChanged();
                    binding.productsSwipe.setRefreshing(false);


                }else {
                    binding.productsSwipe.setRefreshing(false);
                    binding.productsProgresBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void getUserToken() {
        token = viewModel.getToken();
    }

    private void getDataFromPreviousFrag() {
        mainCategoryDetails = getArguments().getParcelable("subCatId");
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(binding.productToolbar,navController,appBarConfiguration);
       // binding.productToolbarTitle.setText(mainCategoryDetails.getName());
    }

    private void setUpRecycler() {

        binding.productRv.setAdapter(adapter);
        binding.productRv.addItemDecoration(spaceBetweenItems);

    }
}