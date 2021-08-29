package com.example.brimore2.di.module;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.brimore2.data.mainrepository.RepositoriesImp;
import com.example.brimore2.data.source.local.SharedHelperImp;
import com.example.brimore2.domain.usecase.CategoryUseCase;
import com.example.brimore2.domain.usecase.MainUseCase;
import com.example.brimore2.domain.usecase.ProductsUseCase;
import com.example.brimore2.domain.usecase.SubCatUseCase;
import com.example.brimore2.domain.usecase.UseCase;
import com.example.brimore2.domain.usecase.UserUseCase;
import com.example.brimore2.ui.categories.CategoryAdapter;
import com.example.brimore2.ui.Main.MainBrandsAdapter;
import com.example.brimore2.ui.Main.MainItemAdapter;
import com.example.brimore2.ui.Main.MainProductAdapter;
import com.example.brimore2.ui.products.ProductAdapter;
import com.example.brimore2.ui.subcategory.SubCategoryAdapter;
import com.example.brimore2.data.source.remote.Api;
import com.example.brimore2.utils.SpaceBetweenItems;

import com.google.gson.Gson;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Named;
import javax.inject.Qualifier;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class Modules {
    String baseUrl = "https://test.hercules.brimore.com/";

    @Provides
    @Singleton
    public Retrofit provideRetrofit(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseUrl);
                builder.addConverterFactory(GsonConverterFactory.create());
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                builder.client(client);
        Retrofit retrofit = builder.build();
        return retrofit;
    }
    @Provides
    @Singleton
    public Api provideApi(Retrofit retrofit){
        return retrofit.create(Api.class);
    }

    @Singleton
    @Provides
    public MainUseCase provideMainUseCase(RepositoriesImp repositoriesImp){
        return new MainUseCase(repositoriesImp);
    }@Singleton
    @Provides
    public UserUseCase provideUserUserCase(RepositoriesImp repositoriesImp){
        return new UserUseCase(repositoriesImp);
    }


    @Singleton
    @Provides
    public CategoryUseCase provideCategoryUseCase(RepositoriesImp repositoriesImp){
        return new CategoryUseCase(repositoriesImp);
    }
    @Singleton
    @Provides
    public SubCatUseCase provideSubCatUseCase(RepositoriesImp repositoriesImp){
        return new SubCatUseCase(repositoriesImp);
    } @Singleton
    @Provides
    public ProductsUseCase provideProductUseCase(RepositoriesImp repositoriesImp){
        return new ProductsUseCase(repositoriesImp);
    }

    @Provides
    @Singleton
    public LinearLayoutManager getLayoutManager(@ApplicationContext Context context){
        return new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
    }
    @Provides
    public MainItemAdapter provideMainItemAdapter(){
        return new MainItemAdapter();
    }
    @Provides
    public MainBrandsAdapter provideMainBrandsAdapter(){
        return new MainBrandsAdapter();
    }
    @Provides
    public MainProductAdapter provideMainProductAdapter(){
        return new MainProductAdapter();
    }

    @Provides
    @Singleton
    public CompositeDisposable provideCompositeDisposable(){
        return new CompositeDisposable();
    }

    @Provides
    public CategoryAdapter provideCategoryAdapter(){
        return new CategoryAdapter();
    }

    @Singleton
    @Provides
    public ProgressDialog provideDialog(@ApplicationContext Context context)
    {
        return new ProgressDialog(context);
    }
    @Provides
    public SpaceBetweenItems provideSpaceBetween(){
        return new SpaceBetweenItems(15,0,15,15);
    }

    @Provides
    public SubCategoryAdapter provideSubCatAdapter(){
        return new SubCategoryAdapter();
    }

    @Provides
    public ProductAdapter provideProductAdapter(){
        return new ProductAdapter();
    }

    @Singleton
    @Provides
    public GridLayoutManager provideGridLayoutManager(@ApplicationContext Context context){
        return new GridLayoutManager(context,2);
    }

    @Singleton
    @Provides
    public RepositoriesImp provideRepositoryImp(Api api,SharedHelperImp sharedHelperImp){
        return new RepositoriesImp(api,sharedHelperImp);
    }


    @Singleton
    @Provides
    public Bundle provideBundle(){
        return new Bundle();
    }

    @Provides
    public Gson provideGson(){
        return new Gson();
    }

    @Singleton
    @Provides
    public static SharedHelperImp provideUserToken(@ApplicationContext Context context,Gson gson){
        return new SharedHelperImp(context,"userToken",gson) ;
    }

}
