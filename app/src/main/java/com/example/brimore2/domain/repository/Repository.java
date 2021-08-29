package com.example.brimore2.domain.repository;

import com.example.brimore2.domain.models.categories.Categories;
import com.example.brimore2.domain.models.main.HomePage;
import com.example.brimore2.domain.models.products.Products;
import com.example.brimore2.domain.models.LoginResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

public interface Repository {

    public Observable<LoginResponse> getLoginApi(String key, String password);

    public Observable<Categories> getCategoryApi(String header);

    public Observable<HomePage> getMainSlider(String header);

    public Observable<Categories> getSubCategoryApi(String header , int id);

    public Observable<Products> getProductsApi(String header , int id);


    public Observable<Products> getProductsDetailsApi(String header , int id);

    public LoginResponse.Data getUser();

    public void saveUserInShared(LoginResponse.Data data);

    public void saveToken(String token,String email,String pass);
    public String getToken();
    public String  getEmail();
    public String  getPass();

    public void removeEmail();

    void saveTok(String token);



}
