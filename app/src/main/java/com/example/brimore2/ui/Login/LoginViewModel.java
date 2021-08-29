package com.example.brimore2.ui.Login;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.brimore2.data.mainrepository.RepositoriesImp;
import com.example.brimore2.domain.usecase.LoginUseCase;
import com.example.brimore2.domain.usecase.UseCase;
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
public class LoginViewModel extends ViewModel {
    UserUseCase userUseCase;
    LoginUseCase loginUseCase;
    CompositeDisposable compositeDisposable;
    MutableLiveData<LoginResponse> loginResponseMutableLiveData;

    Context context;
    @Inject
    public LoginViewModel(LoginUseCase loginUseCase, CompositeDisposable compositeDisposable,UserUseCase userUseCase, @ApplicationContext Context context) {
        this.loginUseCase = loginUseCase;
        this.compositeDisposable = compositeDisposable;
        this.context = context;
        this.userUseCase = userUseCase;
    }

    public void getLoginApi(String email ,String password){
        loginResponseMutableLiveData = new MutableLiveData<>();

        Observable<LoginResponse> loginApi = loginUseCase.getLoginApi(email, password);
        compositeDisposable.add(loginApi.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<LoginResponse>() {
            @Override
            public void accept(LoginResponse loginResponse) throws Exception {
                if(loginResponse != null){
                    loginResponseMutableLiveData.setValue(loginResponse);
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

    public void saveUserInShared(LoginResponse.Data data,String token, String email ,String password){
        userUseCase.saveUser(data);
        userUseCase.saveToken(token,email,password);
    }
}
