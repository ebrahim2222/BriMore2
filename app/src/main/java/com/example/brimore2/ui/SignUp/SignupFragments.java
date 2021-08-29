package com.example.brimore2.ui.SignUp;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.brimore2.R;
import com.example.brimore2.databinding.FragmentSignUpBinding;
import com.example.brimore2.ui.Splash.SplashActivity;

import dagger.hilt.android.AndroidEntryPoint;
@AndroidEntryPoint
public class SignupFragments extends Fragment {

    public SignupFragments() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentSignUpBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up,container,false);
        View root = binding.getRoot();
        String txt1 = "By creating an account, you agree to our";
        String txt2 = "Terms of Service";
        String txt3 = "and";
        String txt4 = "Privacy Policy";

        String s1 = getColoredSpanned(txt1, "#515C6F");
        String s2 = getColoredSpanned(txt2, "#FF6969");
        String s3 = getColoredSpanned(txt3,"#515C6F");
        String s4 = getColoredSpanned(txt4,"#FF6969");

        binding.privacyText.setText(Html.fromHtml(s1+" "+s2+" "+s3+" "+s4+" "));

        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Faild", Toast.LENGTH_SHORT).show();

            }
        });


        return root;
    }

    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }
}