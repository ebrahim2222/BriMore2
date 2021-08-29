package com.example.brimore2.ui.Main;

import android.app.ProgressDialog;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.brimore2.R;
import com.example.brimore2.di.module.Modules;
import com.example.brimore2.domain.models.main.brands.BrandsDetails;
import com.example.brimore2.domain.models.main.dynamicsectionone.DynamicSectionDetails;
import com.example.brimore2.domain.models.main.dynamicsectionone.DynamicSectionVariant;
import com.example.brimore2.domain.models.main.maincategory.MainCategoryDetails;
import com.example.brimore2.databinding.FragmentMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainFragment extends Fragment {
    NavController navController;
    MainViewModel viewModel;
    FragmentMainBinding binding;
    @Inject
    LinearLayoutManager manager;
    @Inject
    MainItemAdapter adapter;
    @Inject
    MainBrandsAdapter mainBrandsAdapter;
    @Inject
    MainProductAdapter mainProductAdapter;
    @Inject
    ProgressDialog progressDialog;

    List<DynamicSectionVariant> variantList;

    private static final String TAG = "MainFragment";


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false);
        View view = binding.getRoot();

        setupRecyclerView();

        setUpBottomNavigation();


        binding.mainSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.mainSwipe.setRefreshing(true);
                mainCategoryObservation();
                mainBrandsObservation();
                mainProductsObservation();
            }
        });


        binding.mainBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                checkItemId(item.getItemId());
                return true;
            }
        });



        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        String token = viewModel.getToken();

        viewModel.getMainCategory(token);

        mainCategoryObservation();

        mainBrandsObservation();

        mainProductsObservation();

        binding.fragmentMainSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_mainFragment_to_categoriesFragment);
            }
        });

        return view;
    }


    private void checkItemId(int itemId) {
        switch (itemId)
        {
            case R.id.bottom_nav_home:
                navController.navigate(R.id.mainFragment);
                break;
            case R.id.bottom_nav_profile:
                navController.navigate(R.id.profileFragment);
                break;

        }
    }


    private void mainCategoryObservation() {
        viewModel.mainCategoryDetailsMLiveData.observe(requireActivity(), new Observer<List<MainCategoryDetails>>() {
            @Override
            public void onChanged(List<MainCategoryDetails> mainCategoryDetailsList) {
                if(mainCategoryDetailsList !=null){
                    binding.mainProBar.setVisibility(View.GONE);
                    Log.d(TAG, "onChanged: "+mainCategoryDetailsList);
                    adapter.setData(requireContext(),mainCategoryDetailsList);
                    adapter.notifyDataSetChanged();
                    binding.mainSwipe.setRefreshing(false);
                }else {
                    binding.mainSwipe.setRefreshing(false);
                    binding.mainProBar.setVisibility(View.GONE);

                }
            }
        });
    }

    private void mainBrandsObservation() {
        viewModel.brandsDetailsMutableLiveData.observe(requireActivity(), new Observer<List<BrandsDetails>>() {
            @Override
            public void onChanged(List<BrandsDetails> brandsDetails) {
                if(brandsDetails != null){
                    Log.d(TAG, "onChanged: "+brandsDetails);
                    mainBrandsAdapter.setData(requireContext(),brandsDetails);
                    mainBrandsAdapter.notifyDataSetChanged();
                    binding.mainSwipe.setRefreshing(false);
                }else{
                    binding.mainSwipe.setRefreshing(false);
                    binding.mainProBar.setVisibility(View.GONE);

                }
            }
        });
    }

    private void mainProductsObservation() {
        viewModel.detailsMutableLiveData.observe(requireActivity(), new Observer<List<DynamicSectionDetails>>() {
            @Override
            public void onChanged(List<DynamicSectionDetails> dynamicSectionDetails) {
                if(dynamicSectionDetails !=null){
                    Log.d(TAG, "onChanged: "+dynamicSectionDetails);
                    variantList = new ArrayList<>();
                    for(int i=0; i<dynamicSectionDetails.size();i++)
                    {
                        List<DynamicSectionVariant> variants = dynamicSectionDetails.get(i).getVariants();
                        variantList.addAll(variants);
                    }
                    mainProductAdapter.setData(requireContext(),variantList);
                    mainProductAdapter.notifyDataSetChanged();
                    binding.mainSwipe.setRefreshing(false);
                }else {
                    binding.mainSwipe.setRefreshing(false);
                    binding.mainProBar.setVisibility(View.GONE);

                }


            }
        });
    }


    public void setUpBottomNavigation() {
        binding.mainBottomNavigation.enableAnimation(false);
        binding.mainBottomNavigation.enableItemShiftingMode(false);
        binding.mainBottomNavigation.enableShiftingMode(false);
        Menu menu = binding.mainBottomNavigation.getMenu();
        MenuItem item = menu.getItem(0);
        item.setChecked(true);
    }

    private void setupRecyclerView() {
        binding.mainRv.setAdapter(adapter);
        binding.mainBrandsRv.setAdapter(mainBrandsAdapter);
        binding.mainProductRv.setAdapter(mainProductAdapter);
    }
}