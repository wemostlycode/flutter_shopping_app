package com.shopping.backend.service.impl;

import com.shopping.backend.dto.request.UserLoginRequestDTO;
import com.shopping.backend.dto.request.UserRegisterRequestDTO;
import com.shopping.backend.dto.response.UserResponseDTO;
import com.shopping.backend.model.User;
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
    public UserResponseDTO registerUser(UserRegisterRequestDTO userRegisterRequestDTO) throws Exception {
        User existedUser = null;
        try {
            existedUser
                    = getUserByEmail(userRegisterRequestDTO.email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (existedUser == null) {
            String email = userRegisterRequestDTO.getEmail();
            Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                throw new Exception(APIStatus.ERR_INVALID_DATA.getDescription());
            }
            User user = new User();
            user.setEmail(email);
            user.setName(userRegisterRequestDTO.getName());
            user.setUserId(UniqueID.getUUID());
            user.setCreateDate(new Date());
            user.setSalt(UniqueID.getUUID());
            user.setDeviceId(userRegisterRequestDTO.deviceId);
            try {
                user.setPasswordHash(MD5Hash.MD5Encrypt(userRegisterRequestDTO.password + user.getSalt()));
            } catch (NoSuchAlgorithmException ex) {
                throw new RuntimeException("Encrypt user password error", ex);
            }

            User user1 = save(user);
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setEmail(user1.getEmail());
            userResponseDTO.setName(user1.getName());
            userResponseDTO.setCreateDate(user1.getCreateDate());
            userResponseDTO.setUserId(user1.getUserId());
            userResponseDTO.setDeviceId(user1.getDeviceId());
            return userResponseDTO;
        } else {
            throw new Exception(APIStatus.USER_ALREADY_EXIST.getDescription());
        }
    }

    @Override
    public UserResponseDTO loginUser(UserLoginRequestDTO loginRequestModel) throws Exception {
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
                    UserResponseDTO userResponseDTO = new UserResponseDTO();
                    userResponseDTO.setEmail(user.getEmail());
                    userResponseDTO.setName(user.getName());
                    userResponseDTO.setCreateDate(user.getCreateDate());
                    userResponseDTO.setUserId(user.getUserId());
                    userResponseDTO.setDeviceId(user.getDeviceId());
                    return userResponseDTO;
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
