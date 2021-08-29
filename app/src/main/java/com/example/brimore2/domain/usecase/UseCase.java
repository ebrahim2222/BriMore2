package com.example.brimore2.domain.usecase;

import com.example.brimore2.data.mainrepository.RepositoriesImp;
import com.example.brimore2.domain.models.categories.Categories;
import com.example.brimore2.domain.models.main.HomePage;
import com.example.brimore2.domain.models.products.Products;
import com.example.brimore2.domain.models.LoginResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UseCase {


    RepositoriesImp repositoriesImp;
    @Inject
    public UseCase(RepositoriesImp repositoriesImp) {
        this.repositoriesImp = repositoriesImp;
    }

    public Observable<Products> getProductsDetailsApi(String header, int id) {
        return repositoriesImp.getProductsDetailsApi(header, id);
    }



}
