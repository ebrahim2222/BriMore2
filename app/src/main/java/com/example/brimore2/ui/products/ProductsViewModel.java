package com.example.brimore2.ui.products;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.brimore2.data.source.remote.Api;
import com.example.brimore2.domain.usecase.ProductsUseCase;
import com.example.brimore2.domain.usecase.UserUseCase;
import com.example.brimore2.utils.ErrorCode;
import com.example.brimore2.domain.models.main.dynamicsectionone.DynamicSectionDetails;
import com.example.brimore2.domain.models.products.Products;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

@HiltViewModel
public class ProductsViewModel extends ViewModel {

    Api api;
    ProductsUseCase productsUseCase;
    CompositeDisposable compositeDisposable;
    UserUseCase userUseCase;
    MutableLiveData<List<DynamicSectionDetails>> mutableLiveData = new MutableLiveData<>();
    Context context;
    private static final String TAG = "ProductsViewModel";

    @Inject
    public ProductsViewModel(UserUseCase userUseCase,Api api, ProductsUseCase productsUseCase, CompositeDisposable compositeDisposable, @ApplicationContext Context context) {
        this.api = api;
        this.productsUseCase = productsUseCase;
        this.compositeDisposable = compositeDisposable;
        this.context = context;
        this.userUseCase = userUseCase;
    }

    public  void getProducts(String header , int id)
    {
        Observable<Products> productsApi = productsUseCase.getProductsApi("Bearer " +header, id);
        compositeDisposable.add(productsApi.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Products>() {
            @Override
            public void accept(Products products) throws Exception {
                List<DynamicSectionDetails> data = products.getData();
                Log.d(TAG, "aly accept: "+data);
                mutableLiveData.setValue(data);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (throwable instanceof HttpException)
                {
                    HttpException httpException = (HttpException)throwable;
                    ErrorCode.errorMessage(context,httpException.code());
                }
            }
        }));


    }

    public String getToken(){
        return userUseCase.getToken();
    }
}
