package com.shopping.backend.service;

import com.shopping.backend.model.User;
import com.shopping.backend.model.request.UserLoginRequestModel;
import com.shopping.backend.model.request.UserRegisterRequestModel;
import com.shopping.backend.model.response.UserResponse;

public interface UserService {

    public User getUserByEmail(String email);

    public User save(User users);

    public UserResponse registerUser(UserRegisterRequestModel userRegisterRequestModel) throws Exception;

    UserResponse loginUser(UserLoginRequestModel loginRequestModel) throws Exception;
}
