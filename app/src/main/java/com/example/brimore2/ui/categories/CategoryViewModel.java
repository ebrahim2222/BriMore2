package com.example.brimore2.ui.categories;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.brimore2.data.mainrepository.RepositoriesImp;
import com.example.brimore2.domain.usecase.CategoryUseCase;
import com.example.brimore2.domain.usecase.UseCase;
import com.example.brimore2.domain.usecase.UserUseCase;
import com.example.brimore2.utils.ErrorCode;
import com.example.brimore2.domain.models.LoginResponse;

import com.example.brimore2.domain.models.categories.Categories;
import com.example.brimore2.domain.models.main.maincategory.MainCategoryDetails;

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
public class CategoryViewModel extends ViewModel {
    private static final String TAG = "CategoryViewModel";
    MutableLiveData<List<MainCategoryDetails>> mutableLiveData= new MutableLiveData<>();

    UserUseCase userUseCase;
    CompositeDisposable compositeDisposable;
    CategoryUseCase categoryUseCase;
    Context context;
    @Inject
    public CategoryViewModel(UserUseCase userUseCase,CategoryUseCase categoryUseCase, CompositeDisposable compositeDisposable, @ApplicationContext Context context) {
        this.categoryUseCase = categoryUseCase;
        this.compositeDisposable = compositeDisposable;
        this.context = context;
        this.userUseCase = userUseCase;
    }

    public void getCategories(String header){

        Observable<Categories> subCategoryApi = categoryUseCase.getCategoryApi("Bearer "+header);
        compositeDisposable.add(subCategoryApi.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Categories>() {
            @Override
            public void accept(Categories categories) throws Exception {

                List<MainCategoryDetails> categoriesDetailsList = categories.getData();
                Log.d(TAG, "accept: "+ categoriesDetailsList);
                mutableLiveData.setValue(categoriesDetailsList);

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

    public String getToken(){
        return userUseCase.getToken();
    }
}
