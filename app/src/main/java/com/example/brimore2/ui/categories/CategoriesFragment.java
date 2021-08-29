package com.example.brimore2.ui.categories;

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
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.brimore2.di.module.Modules;
import com.example.brimore2.R;
import com.example.brimore2.domain.models.main.maincategory.MainCategoryDetails;
import com.example.brimore2.databinding.FragmentCategoriesBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CategoriesFragment extends Fragment {

    FragmentCategoriesBinding binding;
    CategoryViewModel CategoryViewModel;
    @Inject
    CategoryAdapter adapter;
    private static final String TAG = "CategoriesFragment";
    private String token;
    private NavController controller;

    public CategoriesFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(controller.getGraph()).build();
        NavigationUI.setupWithNavController(binding.categoryToolbar,controller,appBarConfiguration);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         binding = DataBindingUtil.inflate(inflater,R.layout.fragment_categories,container,false);

         View view = binding.getRoot();

        setUpRecycler();

        getToken();

        CategoryViewModel = new ViewModelProvider(requireActivity()).get(CategoryViewModel.class);

        CategoryViewModel.getCategories(token);

        categoryObservation();




        return view;

    }

    private void categoryObservation() {
        CategoryViewModel.mutableLiveData.observe(requireActivity(), new Observer<List<MainCategoryDetails>>() {
            @Override
            public void onChanged(List<MainCategoryDetails> data) {
                if(data !=null){
                    binding.categoryProgresBar.setVisibility(View.GONE);
                    adapter.setData(requireContext(),data);
                    adapter.notifyDataSetChanged();
                }else
                {
                    binding.categoryProgresBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void setUpRecycler() {
        LinearLayoutManager manager = new LinearLayoutManager(requireContext());
        binding.categoryRv.setLayoutManager(manager);
        binding.categoryRv.setAdapter(adapter);
    }

    public void getToken(){
        token = CategoryViewModel.getToken();
    }
}