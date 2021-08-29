package com.example.brimore2.data.source.remote;

import com.example.brimore2.domain.models.categories.Categories;
import com.example.brimore2.domain.models.LoginResponse;
import com.example.brimore2.domain.models.main.HomePage;
import com.example.brimore2.domain.models.products.Products;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    @FormUrlEncoded
    @POST("api/eve/login")
    public Observable<LoginResponse> loginApi(@Field("loginKey") String loginKey, @Field("password") String password);


    @GET("api/eve/home")
    public Observable<HomePage> HomeApi(@Header("Authorization") String header);

    @GET("api/eve/categories")
    public Observable<Categories> getCategories(@Header("Authorization") String header);



    @GET("api/eve/category/{id}")
    public Observable<Categories> getSubCategoriesApi(@Header("Authorization") String header,@Path("id") int id);

    @GET("api/eve/subcategory/{id}/products")
    public Observable<Products> productsApi(@Header("Authorization") String header, @Path("id") int id);


    @GET("api/eve/product/{id}")
    public Observable<Products> getProductsDetailsApi(@Header("Authorization") String header , @Path("id") int id );

}
