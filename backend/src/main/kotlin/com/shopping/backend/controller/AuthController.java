package com.shopping.backend.controller;

import com.shopping.backend.constants.ApiNameConstants;
import com.shopping.backend.model.User;
import com.shopping.backend.model.request.UserRequestModel;
import com.shopping.backend.model.response.APIResponse;
import com.shopping.backend.model.response.ResponseUtil;
import com.shopping.backend.service.UserService;
import com.shopping.backend.util.APIStatus;
import com.shopping.backend.util.MD5Hash;
import com.shopping.backend.util.RandomStringUtils;
import com.shopping.backend.util.UniqueID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController()
@RequestMapping(ApiNameConstants.USERS)
public class AuthController {
    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    UserService userService;

    @RequestMapping(path = ApiNameConstants.REGISTER, method = RequestMethod.POST)
    ResponseEntity<APIResponse> registerUser(@RequestBody UserRequestModel userRequestModel) throws Exception {
        User existedUser = null;
        try {
            existedUser
                    = userService.getUserByEmail(userRequestModel.email);
        } catch (Exception e) {
        }
        if (existedUser == null) {
            String email = userRequestModel.getEmail();
            Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                throw new Exception(APIStatus.ERR_INVALID_DATA.getDescription());
            }
            User user = new User();
            user.setEmail(email);
            user.setName(userRequestModel.getName());
            user.setUserId(UniqueID.getUUID());
            user.setCreateDate(new Date());
            user.setSalt(UniqueID.getUUID());
            try {
                String generatedString = RandomStringUtils.randomAlphabetic(6);
                String password = MD5Hash.MD5Encrypt(generatedString);
                user.setPasswordHash(MD5Hash.MD5Encrypt(password + user.getSalt()));
            } catch (NoSuchAlgorithmException ex) {
                throw new RuntimeException("Encrypt user password error", ex);
            }

            return responseUtil.successResponse(userService.save(user));
        } else {
            throw new Exception(APIStatus.USER_ALREADY_EXIST.getDescription());
        }
    }

}
