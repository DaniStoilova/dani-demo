package com.dictionaryapp.service;

import com.dictionaryapp.model.binding.UserLoginBindingModel;
import com.dictionaryapp.model.binding.UserRegisterBindingModel;
import com.dictionaryapp.model.entity.User;

import java.util.List;

public interface UserService {
    void register(UserRegisterBindingModel userRegisterBindingModel);

    boolean findByUsernameAndPassword(String username, String password);

    void login(UserLoginBindingModel userLoginBindingModel);


}
