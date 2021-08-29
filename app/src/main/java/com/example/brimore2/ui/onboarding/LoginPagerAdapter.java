package com.example.brimore2.ui.onboarding;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.brimore2.ui.Login.LoginFragment;
import com.example.brimore2.ui.SignUp.SignupFragments;

import org.jetbrains.annotations.NotNull;


public class LoginPagerAdapter extends FragmentPagerAdapter {
    public LoginPagerAdapter(@NonNull @NotNull FragmentManager fm) {
        super(fm);
    }

    Fragment [] fragments = {new SignupFragments(),new LoginFragment()};


    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
