package com.shopping.backend.service;

import com.shopping.backend.model.User;
import com.shopping.backend.dto.request.UserLoginRequestDTO;
import com.shopping.backend.dto.request.UserRegisterRequestDTO;
import com.shopping.backend.dto.response.UserResponseDTO;

public interface UserService {

    public User getUserByEmail(String email);

    public User save(User users);

    public UserResponseDTO registerUser(UserRegisterRequestDTO userRegisterRequestDTO) throws Exception;

    UserResponseDTO loginUser(UserLoginRequestDTO loginRequestModel) throws Exception;
}
