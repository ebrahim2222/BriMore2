package com.example.brimore2.domain.usecase;

import com.example.brimore2.data.mainrepository.RepositoriesImp;
import com.example.brimore2.domain.models.products.Products;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ProductsUseCase {
    RepositoriesImp repositoriesImp;
    @Inject
    public ProductsUseCase(RepositoriesImp repositoriesImp) {
        this.repositoriesImp = repositoriesImp;
    }
    public Observable<Products> getProductsApi(String header, int id) {
        return repositoriesImp.getProductsApi(header, id);
    }
}
