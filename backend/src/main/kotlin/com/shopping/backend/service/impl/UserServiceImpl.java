package com.shopping.backend.service.impl;

import com.shopping.backend.model.User;
import com.shopping.backend.model.request.UserLoginRequestModel;
import com.shopping.backend.model.request.UserRegisterRequestModel;
import com.shopping.backend.model.response.UserResponse;
import com.shopping.backend.repository.UserRepository;
import com.shopping.backend.service.UserService;
import com.shopping.backend.util.APIStatus;
import com.shopping.backend.util.MD5Hash;
import com.shopping.backend.util.UniqueID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User users) {
        return userRepository.save(users);
    }

    @Override
    public UserResponse registerUser(UserRegisterRequestModel userRegisterRequestModel) throws Exception {
        User existedUser = null;
        try {
            existedUser
                    = getUserByEmail(userRegisterRequestModel.email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (existedUser == null) {
            String email = userRegisterRequestModel.getEmail();
            Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                throw new Exception(APIStatus.ERR_INVALID_DATA.getDescription());
            }
            User user = new User();
            user.setEmail(email);
            user.setName(userRegisterRequestModel.getName());
            user.setUserId(UniqueID.getUUID());
            user.setCreateDate(new Date());
            user.setSalt(UniqueID.getUUID());
            user.setDeviceId(userRegisterRequestModel.deviceId);
            try {
                user.setPasswordHash(MD5Hash.MD5Encrypt(userRegisterRequestModel.password + user.getSalt()));
            } catch (NoSuchAlgorithmException ex) {
                throw new RuntimeException("Encrypt user password error", ex);
            }

            User user1 = save(user);
            UserResponse userResponse = new UserResponse();
            userResponse.setEmail(user1.getEmail());
            userResponse.setName(user1.getName());
            userResponse.setCreateDate(user1.getCreateDate());
            userResponse.setUserId(user1.getUserId());
            userResponse.setDeviceId(user1.getDeviceId());
            return userResponse;
        } else {
            throw new Exception(APIStatus.USER_ALREADY_EXIST.getDescription());
        }
    }

    @Override
    public UserResponse loginUser(UserLoginRequestModel loginRequestModel) throws Exception {
        if ("".equals(loginRequestModel.email) || "".equals(loginRequestModel.password)) {
            throw new Exception(APIStatus.INVALID_PARAMETER.getDescription());
        } else {
            User user = getUserByEmail(loginRequestModel.email);

            if (user != null) {
                String passwordHash = null;
                try {
                    passwordHash = MD5Hash.MD5Encrypt(loginRequestModel.password + user.getSalt());
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException("User login encrypt password error", ex);
                }
                if (passwordHash.equals(user.getPasswordHash())) {
                    user.setDeviceId(loginRequestModel.deviceId);
                    user = save(user);
                    UserResponse userResponse = new UserResponse();
                    userResponse.setEmail(user.getEmail());
                    userResponse.setName(user.getName());
                    userResponse.setCreateDate(user.getCreateDate());
                    userResponse.setUserId(user.getUserId());
                    userResponse.setDeviceId(user.getDeviceId());
                    return userResponse;
                } else {
                    // wrong password
                    throw new Exception(APIStatus.ERR_USER_NOT_VALID.getDescription());
                }

            } else {
                throw new Exception(APIStatus.ERR_USER_NOT_FOUND.getDescription());

            }

        }
    }

}
