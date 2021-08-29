package com.example.brimore2.ui.profile;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.brimore2.R;
import com.example.brimore2.di.module.Modules;
import com.example.brimore2.data.mainrepository.RepositoriesImp;
import com.example.brimore2.domain.models.LoginResponse;
import com.example.brimore2.databinding.FragmentProfileBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.paperdb.Paper;


@AndroidEntryPoint
public class ProfileFragment extends Fragment {
    ProfileViewModel viewModel;

    FragmentProfileBinding binding;
    NavController navController;
    private static final String TAG = "ProfileFragment";

    @Inject
    RepositoriesImp repositories;

    public ProfileFragment() {
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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile,container,false);
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);
        LoginResponse.Data userInfo = viewModel.getUserInfo();
        setUpBottomNavigation();

        binding.setModel(userInfo);

        binding.profileBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                navigationClickCheck(item.getItemId());
                return true;
            }
        });

        return view;
    }

    private void navigationClickCheck(int itemId) {
        switch (itemId)
        {
            case R.id.bottom_nav_profile:
                navController.navigate(R.id.profileFragment);
                break;
            case R.id.bottom_nav_home:
                navController.navigate(R.id.mainFragment);
                break;
        }
    }

    private void setUpBottomNavigation() {
        binding.profileBottomNavigation.enableAnimation(false);
        binding.profileBottomNavigation.enableItemShiftingMode(false);
        binding.profileBottomNavigation.enableShiftingMode(false);

        Menu menu = binding.profileBottomNavigation.getMenu();
        MenuItem item = menu.getItem(1);
        item.setChecked(true);
    }
}