package com.example.brimore2.ui.Main;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.brimore2.data.mainrepository.RepositoriesImp;
import com.example.brimore2.domain.usecase.MainUseCase;
import com.example.brimore2.domain.usecase.UseCase;
import com.example.brimore2.domain.usecase.UserUseCase;
import com.example.brimore2.utils.ErrorCode;
import com.example.brimore2.domain.models.main.HomePage;
import com.example.brimore2.domain.models.main.brands.BrandsDetails;
import com.example.brimore2.domain.models.main.dynamicsectionone.DynamicSectionDetails;
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
public class MainViewModel extends ViewModel {

    private static final String TAG = "MainViewModel";
    MainUseCase mainUseCase;
    MutableLiveData<List<MainCategoryDetails>> mainCategoryDetailsMLiveData;
    MutableLiveData<List<BrandsDetails>> brandsDetailsMutableLiveData;
    MutableLiveData<List<DynamicSectionDetails>> detailsMutableLiveData;
    MutableLiveData<String> integerMutableLiveData;
    @Inject
    CompositeDisposable compositeDisposable;
    Context context;
    UserUseCase userUseCase;

    @Inject
    public MainViewModel(UserUseCase userUseCase,MainUseCase mainUseCase, CompositeDisposable compositeDisposable , @ApplicationContext Context context) {
        this.mainUseCase = mainUseCase;
        this.compositeDisposable = compositeDisposable;
        this.context = context;
        this.userUseCase = userUseCase;

    }

    public String getToken(){
        return userUseCase.getToken();
    }



    public void getMainCategory(String header){
        Observable<HomePage> mainSlider = mainUseCase.getMainSlider("Bearer "+header);
        mainCategoryDetailsMLiveData = new MutableLiveData<>();
        brandsDetailsMutableLiveData = new MutableLiveData<>();
        detailsMutableLiveData = new MutableLiveData<>();
        integerMutableLiveData = new MutableLiveData<>();




        compositeDisposable.add(mainSlider.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<HomePage>() {
            @Override
            public void accept(HomePage homePage) throws Exception {
                List<MainCategoryDetails> data = homePage.getCategory().getData().getData();
                List<BrandsDetails> brandsDetails = homePage.getBrands().getData().getData();
                List<DynamicSectionDetails> dynamicSectionDetails = homePage.getDynamicSectionOne().getData().getData();
                Log.d(TAG, "accept: "+dynamicSectionDetails);
                mainCategoryDetailsMLiveData.setValue(data);
                brandsDetailsMutableLiveData.setValue(brandsDetails);
                detailsMutableLiveData.setValue(dynamicSectionDetails);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "accept: "+throwable.getMessage());
                if(throwable instanceof HttpException){
                    HttpException httpException = (HttpException)throwable;
                    ErrorCode.errorMessage(context,httpException.code());
                }

            }
        }));
    }
}
