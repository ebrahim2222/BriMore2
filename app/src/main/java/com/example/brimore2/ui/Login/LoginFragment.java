package com.example.brimore2.ui.Login;

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

import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.brimore2.data.source.remote.Api;
import com.example.brimore2.di.module.Modules;
import com.example.brimore2.domain.models.LoginResponse;
import com.example.brimore2.data.mainrepository.RepositoriesImp;
import com.example.brimore2.R;
import com.example.brimore2.databinding.FragmentLoginBinding;
import com.example.brimore2.domain.usecase.UserUseCase;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.paperdb.Paper;

@AndroidEntryPoint
public class LoginFragment extends Fragment {
    @Inject
    Api api;
    @Inject
    Gson gson;
    @Inject
    RepositoriesImp repositories;
    NavController controller;
    ProgressDialog dialog;
    FragmentLoginBinding binding;
    LoginViewModel loginViewModel;
    private static final String TAG = "LoginFragment";
    public LoginFragment() {
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
        controller = Navigation.findNavController(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false);
        View root = binding.getRoot();
        loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

        textChangColor();

        dialog = new ProgressDialog(requireContext());



        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
                String email = binding.emailField.getText().toString();
                String password = binding.passField.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getContext(), "please enter your email", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getContext(), "please enter your password", Toast.LENGTH_SHORT).show();

                } else {
                    logIn(email,password);
                }
            }
        });


        return root;
    }

    private void textChangColor() {
        String txt1 = "Don’t have an account? Swipe right to";
        String txt2 = "create a new account.";
        String s1 = getColoredSpanned(txt1, "#515C6F");
        String s2 = getColoredSpanned(txt2, "#FF6969");
        binding.signupText.setText(Html.fromHtml(s1+" "+s2));
    }


    private void logIn(String email, String password) {
        loginViewModel.getLoginApi(email,password);
        loginViewModel.loginResponseMutableLiveData.observe(requireActivity(), new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {

                if(loginResponse != null){
                    manageResponse(loginResponse,email,password);

                }else{
                    dialog.dismiss();
                }

            }
        });


    }

    private void manageResponse(LoginResponse response, String email, String password) {
        Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
        LoginResponse.Data data = response.getData();
        String token = data.getApiToken();
        saveTokenInSharedPreference(token,email,password,data);
        dialog.dismiss();
        controller.navigate(R.id.action_viewPagerFragment_to_mainFragment);
    }


    private void saveTokenInSharedPreference(String token, String email, String password, LoginResponse.Data data) {

        loginViewModel.saveUserInShared(data,token,email,password);
    }

    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }

    private void showDialog(){
        dialog.setTitle("Loading");
        dialog.setMessage("Please wait");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}