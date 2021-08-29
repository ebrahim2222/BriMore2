package com.example.brimore2.ui.Splash;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.brimore2.data.mainrepository.RepositoriesImp;
import com.example.brimore2.domain.usecase.UserUseCase;
import com.example.brimore2.utils.ErrorCode;
import com.example.brimore2.domain.models.LoginResponse;

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
public class SplashViewModel extends ViewModel {
    RepositoriesImp repositories;
    CompositeDisposable compositeDisposable;
    MutableLiveData<LoginResponse> SplashMutableLiveData;
    UserUseCase userUseCase;
    Context context;
    @Inject
    public SplashViewModel(UserUseCase userUseCase,RepositoriesImp repositories, CompositeDisposable compositeDisposable, @ApplicationContext Context context) {
        this.repositories = repositories;
        this.compositeDisposable = compositeDisposable;
        this.context = context;
        this.userUseCase = userUseCase;
    }
    public void getLoginApi(String email ,String password){
        SplashMutableLiveData = new MutableLiveData<>();
        Observable<LoginResponse> loginApi = repositories.getLoginApi(email, password);
        compositeDisposable.add(loginApi.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<LoginResponse>() {
            @Override
            public void accept(LoginResponse loginResponse) throws Exception {
                if(loginResponse != null){
                    SplashMutableLiveData.setValue(loginResponse);
                }else
                {
                    Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if(throwable instanceof HttpException)
                {
                    HttpException httpException = (HttpException)throwable;
                    ErrorCode.errorMessage(context,httpException.code());
                }
            }
        }));
    }

    public String getEmail(){
        return userUseCase.getEmail();
    }
    public String getPass(){
        return userUseCase.getPass();
    }

    public void saveToken(String token){
        userUseCase.saveTok(token);
    }

}
