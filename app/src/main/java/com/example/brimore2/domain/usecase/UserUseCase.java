package com.example.brimore2.domain.usecase;

import com.example.brimore2.data.mainrepository.RepositoriesImp;
import com.example.brimore2.domain.models.LoginResponse;

import javax.inject.Inject;

public class UserUseCase {

    RepositoriesImp repositoriesImp;
    @Inject
    public UserUseCase(RepositoriesImp repositoriesImp) {
        this.repositoriesImp = repositoriesImp;
    }

    public LoginResponse.Data getUser(){
        return repositoriesImp.getUser();
    }

    public void saveUser(LoginResponse.Data data){
        repositoriesImp.saveUserInShared(data);
    }

    public void saveToken(String token,String email ,String password){
        repositoriesImp.saveToken(token,email,password);
    }

    public String getEmail(){
        return repositoriesImp.getEmail();
    } public String getPass(){
        return repositoriesImp.getPass();
    } public String getToken(){
        return repositoriesImp.getToken();
    }

    public void removeEmail(){
        repositoriesImp.removeEmail();
    }

    public void saveTok(String token){
        repositoriesImp.saveTok(token);
    }

}
