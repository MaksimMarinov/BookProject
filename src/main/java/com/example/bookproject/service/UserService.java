package com.example.bookproject.service;

import com.example.bookproject.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    boolean isUserNameFree(String userName);

    void initAdmin();
}
