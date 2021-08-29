package com.example.brimore2.data.mainrepository;


import com.example.brimore2.data.source.local.SharedHelperImp;
import com.example.brimore2.data.source.remote.Api;
import com.example.brimore2.domain.models.categories.Categories;
import com.example.brimore2.domain.models.LoginResponse;
import com.example.brimore2.domain.models.main.HomePage;
import com.example.brimore2.domain.models.products.Products;
import com.example.brimore2.domain.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RepositoriesImp implements Repository {

    Api api;
    SharedHelperImp sharedHelperImp;

    @Inject
    public RepositoriesImp(Api api,SharedHelperImp sharedHelperImp) {
        this.api = api;
        this.sharedHelperImp = sharedHelperImp;
    }

    @Override
    public Observable<LoginResponse> getLoginApi(String key, String password) {
        return api.loginApi(key, password);
    }

    @Override
    public Observable<Categories> getCategoryApi(String header) {
        return api.getCategories(header);
    }

    @Override
    public Observable<HomePage> getMainSlider(String header) {
        return api.HomeApi(header);
    }

    @Override
    public Observable<Categories> getSubCategoryApi(String header, int id) {
        return api.getSubCategoriesApi(header, id);
    }

    @Override
    public Observable<Products> getProductsApi(String header, int id) {
        return api.productsApi(header, id);
    }

    @Override
    public Observable<Products> getProductsDetailsApi(String header, int id) {
        return api.getProductsDetailsApi(header, id);
    }

    @Override
    public LoginResponse.Data getUser() {
        return sharedHelperImp.getUser();
    }



    @Override
    public void saveUserInShared(LoginResponse.Data data) {
        sharedHelperImp.saveUser(data);
    }


    @Override
    public void saveToken(String token,String email,String pass) {
        sharedHelperImp.saveTokenInShared(token,email,pass);
    }


    @Override
    public String getToken() {
        return sharedHelperImp.getToken();
    }

    @Override
    public String getEmail() {
        return sharedHelperImp.getEmail();
    }

    @Override
    public String getPass() {
        return sharedHelperImp.getPassword();
    }

    @Override
    public void removeEmail() {
        sharedHelperImp.removeEmail();
    }

    @Override
    public void saveTok(String token) {
        sharedHelperImp.saveTok(token);
    }




    /*
    ApiHelper apiHelper;
    @Inject
    public RepositoriesImp(ApiHelper apiHelper) {
        this.apiHelper = apiHelper;
    }

    public Observable<LoginResponse> getLoginApi(String key, String password){
        return apiHelper.loginApi(key,password);
    }

    public Observable<Categories> getCategoryApi(String header ){
        return apiHelper.getCategories(header);
    }

    public Observable<HomePage> getMainSlider(String header){
        return apiHelper.HomeApi(header);
    }

    public Observable<Categories> getSubCategoryApi(String header , int id){
        return apiHelper.getSubCategoriesApi(header,id);
    }

    public Observable<Products> getProductsApi(String header , int id){
        return apiHelper.productsApi(header,id);
    }


    public Observable<Products> getProductsDetailsApi(String header , int id){
        return apiHelper.getProductsDetailsApi(header , id);
    }

     */

}
