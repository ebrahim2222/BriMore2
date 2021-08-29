package com.example.brimore2.domain.usecase;

import com.example.brimore2.data.mainrepository.RepositoriesImp;
import com.example.brimore2.domain.models.main.HomePage;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MainUseCase {
    RepositoriesImp repositoriesImp;
    @Inject
    public MainUseCase(RepositoriesImp repositoriesImp) {
        this.repositoriesImp = repositoriesImp;
    }
    public Observable<HomePage> getMainSlider(String header) {
        return repositoriesImp.getMainSlider(header);
    }

}
