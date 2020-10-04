package com.shopping.backend.controller;

import com.shopping.backend.constants.ApiNameConstants;
import com.shopping.backend.dto.request.UserLoginRequestDTO;
import com.shopping.backend.dto.request.UserRegisterRequestDTO;
import com.shopping.backend.dto.response.APIResponse;
import com.shopping.backend.dto.response.ResponseUtil;
import com.shopping.backend.dto.response.UserResponseDTO;
import com.shopping.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(ApiNameConstants.USERS)
public class UserController {
    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    UserService userService;

    @RequestMapping(path = ApiNameConstants.REGISTER, method = RequestMethod.POST)
    ResponseEntity<APIResponse> registerUser(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) throws Exception {
        UserResponseDTO userResponseDTO = userService.registerUser(userRegisterRequestDTO);
        return responseUtil.successResponse(userResponseDTO);
    }

    @RequestMapping(path = ApiNameConstants.LOGIN, method = RequestMethod.POST)
    ResponseEntity<APIResponse> loginUser(@RequestBody UserLoginRequestDTO loginRequestModel) throws Exception {
        UserResponseDTO userResponseDTO = userService.loginUser(loginRequestModel);
        return responseUtil.successResponse(userResponseDTO);
    }

}
