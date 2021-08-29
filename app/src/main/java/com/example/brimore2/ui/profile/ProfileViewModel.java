package com.example.brimore2.ui.profile;

import androidx.lifecycle.ViewModel;

import com.example.brimore2.domain.models.LoginResponse;
import com.example.brimore2.domain.usecase.UserUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProfileViewModel extends ViewModel {

    UserUseCase userUseCase;
    @Inject
    public ProfileViewModel(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    public LoginResponse.Data getUserInfo(){
        return userUseCase.getUser();
    }
}
