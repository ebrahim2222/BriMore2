package com.example.brimore2.ui.product_details;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.brimore2.data.mainrepository.RepositoriesImp;
import com.example.brimore2.domain.usecase.UseCase;
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
public class ProductsDetailsViewModel extends ViewModel {

    UseCase useCase;
    UserUseCase userUseCase;
    CompositeDisposable compositeDisposable;
    @SuppressLint("StaticFieldLeak")
    @ApplicationContext
    Context context;
    private static final String TAG = "ProductsDetailsViewMode";
    MutableLiveData<List<DynamicSectionDetails>> mutableLiveData = new MutableLiveData<>();

    @Inject
    public ProductsDetailsViewModel(UserUseCase userUseCase ,UseCase useCase, CompositeDisposable compositeDisposable, @ApplicationContext Context context) {
        this.useCase = useCase;
        this.compositeDisposable = compositeDisposable;
        this.context = context;
        this.userUseCase = userUseCase;
    }

    public void getProductsDetails(String header , int id){

        Observable<Products> productsDetailsApi = useCase.getProductsDetailsApi("Bearer " + header, id);
        compositeDisposable.add(productsDetailsApi.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Products>() {
            @Override
            public void accept(Products products) throws Exception {
                List<DynamicSectionDetails> data = products.getData();

                mutableLiveData.setValue(data);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if(throwable instanceof HttpException){
                    HttpException httpException = (HttpException)throwable;
                    ErrorCode.errorMessage(context,httpException.code());
                }
            }
        }));

    }

    public void removeEmail(){

        userUseCase.removeEmail();

    }
}
