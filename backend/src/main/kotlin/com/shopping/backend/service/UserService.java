package com.shopping.backend.service;

import com.shopping.backend.data_model.User;
import com.shopping.backend.data_model.UserToken;
import com.shopping.backend.repository.UserRepository;
import com.shopping.backend.repository.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTokenRepository userTokenRepository;

    public User getUserByEmail(String email, int status) {
        return userRepository.findByEmailAndStatus(email, status);
    }

    public User save(User users) {
        return userRepository.save(users);
    }

    public User getUserByUserIdAndComIdAndStatus(String userId, Long companyId, int status) {
        return userRepository.findByUserIdAndCompanyIdAndStatus(userId, companyId, status);
    }

    public User getUserByActivationCode(String token) {
        UserToken userToken = userTokenRepository.findById(token).get();

        return userRepository.findById(userToken.getUserId()).get();
    }

}
