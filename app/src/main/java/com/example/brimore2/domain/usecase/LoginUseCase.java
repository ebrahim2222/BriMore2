package com.example.brimore2.domain.usecase;

import com.example.brimore2.data.mainrepository.RepositoriesImp;
import com.example.brimore2.domain.models.LoginResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

public class LoginUseCase {

    RepositoriesImp repositoriesImp;
    @Inject
    public LoginUseCase(RepositoriesImp repositoriesImp) {
        this.repositoriesImp = repositoriesImp;
    }

    public Observable<LoginResponse> getLoginApi(String key, String password) {
        return repositoriesImp.getLoginApi(key, password);
    }
}
