package com.example.brimore2.ui.subcategory;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.brimore2.domain.usecase.SubCatUseCase;
import com.example.brimore2.domain.models.categories.Categories;
import com.example.brimore2.domain.models.main.maincategory.MainCategoryDetails;
import com.example.brimore2.domain.usecase.UserUseCase;
import com.example.brimore2.utils.ErrorCode;

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
public class SubCategoryViewModel extends ViewModel {

    MutableLiveData<List<MainCategoryDetails>> mutableLiveData = new MutableLiveData<>();
    UserUseCase userUseCase;
    SubCatUseCase subCatUseCase;
    CompositeDisposable compositeDisposable;
    Context context;
    private static final String TAG = "SubCategoryViewModel";

    @Inject
    public SubCategoryViewModel(UserUseCase userUseCase,SubCatUseCase subCatUseCase, CompositeDisposable compositeDisposable, @ApplicationContext Context context) {
        this.compositeDisposable = compositeDisposable;
        this.subCatUseCase = subCatUseCase;
        this.context = context;
        this.userUseCase = userUseCase;
    }

    public void getSubCategory(String header , int id){

        Observable<Categories> subCategoryApi = subCatUseCase.getSubCategoryApi("Bearer "+header, id);
        compositeDisposable.add(subCategoryApi.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Categories>() {
            @Override
            public void accept(Categories categories) throws Exception {
                List<MainCategoryDetails> data = categories.getData();
                mutableLiveData.setValue(data);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if(throwable instanceof HttpException){
                    HttpException  httpException = (HttpException)throwable;
                    ErrorCode.errorMessage(context,httpException.code());
                }
            }
        }));

    }

    public String getToken(){
        return userUseCase.getToken();
    }
}
