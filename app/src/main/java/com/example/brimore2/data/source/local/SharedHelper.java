package com.example.brimore2.data.source.local;

import com.example.brimore2.domain.models.LoginResponse;

public interface SharedHelper {

    void saveUser(LoginResponse.Data data);

   LoginResponse.Data getUser();

    void saveTokenInShared(String token,String email,String password);

    void saveTok(String token);

    String getToken();

    String getEmail();

    String getPassword();

    void removeEmail();




}
