package com.example.brimore2.ui.subcategory;

import android.content.SharedPreferences;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.brimore2.R;
import com.example.brimore2.di.module.Modules;
import com.example.brimore2.domain.models.main.maincategory.MainCategoryDetails;
import com.example.brimore2.databinding.FragmentSubCategoryBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SubCategoryFragment extends Fragment {

    SubCategoryViewModel viewModel;

    NavController controller;
    @Inject
    SubCategoryAdapter adapter;

    FragmentSubCategoryBinding binding;
    MainCategoryDetails categoriesDetails;

    private static final String TAG = "SubCategoryFragment";
    private String token;

    public SubCategoryFragment() {
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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sub_category,container,false);
        View view = binding.getRoot();

        getDataFromPreviousFrag();

        setUpRecycler();

        binding.fragmentSubTxt1.setText(categoriesDetails.getName());

        binding.subcatSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.subcatSwipe.setRefreshing(true);
                subCategoryObservation();
            }
        });

        getUserToken();

        viewModel = new ViewModelProvider(requireActivity()).get(SubCategoryViewModel.class);

        viewModel.getSubCategory(token,categoriesDetails.getId());

        subCategoryObservation();



        return view;
    }

    private void subCategoryObservation() {
        viewModel.mutableLiveData.observe(requireActivity(), (Observer<? super List<MainCategoryDetails>>) new Observer<List<MainCategoryDetails>>() {
            @Override
            public void onChanged(List<MainCategoryDetails> data) {
              if(data !=null){
                  binding.subcategProgresBar.setVisibility(View.GONE);
                  Log.d(TAG, "aly onChanged: "+data);
                  adapter.setData(requireContext(),data);
                  adapter.notifyDataSetChanged();
                  binding.subcatSwipe.setRefreshing(false);

              }else
              {
                  binding.subcatSwipe.setRefreshing(false);
                  binding.subcategProgresBar.setVisibility(View.GONE);
              }
            }
        });
    }

    private void getUserToken() {
        token = viewModel.getToken();
    }

    private void getDataFromPreviousFrag() {
        categoriesDetails = getArguments().getParcelable("category");
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(controller.getGraph()).build();
        NavigationUI.setupWithNavController(binding.categoriesToolbar,controller,appBarConfiguration);
        binding.subCatToolbarTitle.setText("SubCategory");
    }

    private void setUpRecycler() {
        binding.subcategoryRv.setAdapter(adapter);
    }
}