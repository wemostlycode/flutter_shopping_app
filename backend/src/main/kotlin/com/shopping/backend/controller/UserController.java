package com.shopping.backend.controller;

import com.shopping.backend.constants.ApiNameConstants;
import com.shopping.backend.model.request.UserLoginRequestModel;
import com.shopping.backend.model.request.UserRegisterRequestModel;
import com.shopping.backend.model.response.APIResponse;
import com.shopping.backend.model.response.ResponseUtil;
import com.shopping.backend.model.response.UserResponse;
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
    ResponseEntity<APIResponse> registerUser(@RequestBody UserRegisterRequestModel userRegisterRequestModel) throws Exception {
        UserResponse userResponse = userService.registerUser(userRegisterRequestModel);
        return responseUtil.successResponse(userResponse);
    }

    @RequestMapping(path = ApiNameConstants.LOGIN, method = RequestMethod.POST)
    ResponseEntity<APIResponse> loginUser(@RequestBody UserLoginRequestModel loginRequestModel) throws Exception {
        UserResponse userResponse = userService.loginUser(loginRequestModel);
        return responseUtil.successResponse(userResponse);
    }

}
