package com.example.brimore2.domain.usecase;

import com.example.brimore2.data.mainrepository.RepositoriesImp;
import com.example.brimore2.domain.models.categories.Categories;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CategoryUseCase {
    RepositoriesImp repositoriesImp;
    @Inject
    public CategoryUseCase(RepositoriesImp repositoriesImp) {
        this.repositoriesImp = repositoriesImp;
    }

    public Observable<Categories> getCategoryApi(String header) {
        return repositoriesImp.getCategoryApi(header);
    }
}
