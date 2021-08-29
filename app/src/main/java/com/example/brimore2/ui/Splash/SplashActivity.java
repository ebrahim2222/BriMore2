package com.example.brimore2.ui.Splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.multidex.MultiDex;

import android.content.Context;
import android.os.Bundle;

import com.example.brimore2.R;
import com.example.brimore2.databinding.ActivitySplashBinding;

import dagger.hilt.android.AndroidEntryPoint;
@AndroidEntryPoint
public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this,R.layout.activity_splash);

    }

}