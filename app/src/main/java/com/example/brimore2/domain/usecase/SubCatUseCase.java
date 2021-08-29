package com.example.brimore2.domain.usecase;

import com.example.brimore2.data.mainrepository.RepositoriesImp;
import com.example.brimore2.domain.models.categories.Categories;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SubCatUseCase {

    RepositoriesImp repositoriesImp;
    @Inject
    public SubCatUseCase(RepositoriesImp repositoriesImp) {
        this.repositoriesImp = repositoriesImp;
    }

    public Observable<Categories> getSubCategoryApi(String header, int id) {
        return repositoriesImp.getSubCategoryApi(header, id);
    }
}
