package com.example.brimore2.ui.onboarding;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.brimore2.R;
import com.example.brimore2.databinding.FragmentViewPagerBinding;

import dagger.hilt.android.AndroidEntryPoint;
@AndroidEntryPoint
public class ViewPagerFragment extends Fragment {

    LoginPagerAdapter pagerAdapter ;

    public ViewPagerFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        FragmentViewPagerBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_view_pager,container,false);
        View view = binding.getRoot();
        pagerAdapter = new LoginPagerAdapter(requireActivity().getSupportFragmentManager());
        binding.loginPager.setAdapter(pagerAdapter);
        return view;
    }
}