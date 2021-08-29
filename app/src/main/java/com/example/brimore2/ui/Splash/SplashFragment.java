package com.example.brimore2.ui.Splash;

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

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.brimore2.R;
import com.example.brimore2.di.module.Modules;
import com.example.brimore2.domain.models.LoginResponse;
import com.example.brimore2.databinding.FragmentSplashBinding;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.paperdb.Paper;

@AndroidEntryPoint
public class SplashFragment extends Fragment {
    SplashViewModel viewModel;
    NavController navController;

    public SplashFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Paper.init(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentSplashBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_splash,container,false);
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(requireActivity()).get(SplashViewModel.class);
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               String email = viewModel.getEmail();
               String pass = viewModel.getPass();
               //navController.navigate(R.id.action_splashFragment_to_viewPagerFragment);

               if(email.isEmpty() && pass.isEmpty())
               {
                   navController.navigate(R.id.action_splashFragment_to_viewPagerFragment);
               }else
               {

                   viewModel.getLoginApi(email,pass);
                    viewModel.SplashMutableLiveData.observe(requireActivity(), new Observer<LoginResponse>() {
                        @Override
                        public void onChanged(LoginResponse loginResponse) {
                           if(loginResponse !=null){
                               LoginResponse.Data data = loginResponse.getData();
                               String token = data.getApiToken();
                               viewModel.saveToken(token);
                               navController.navigate(R.id.action_splashFragment_to_mainFragment);
                            //   navController.navigate(R.id.action_splashFragment_to_viewPagerFragment);
                           }else{
                               navController.navigate(R.id.action_splashFragment_to_viewPagerFragment);
                           }
                        }
                    });


               }
           }
       },5000);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }
}