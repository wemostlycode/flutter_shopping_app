package com.shopping.backend.service;

import com.shopping.backend.data_model.UserToken;
import com.shopping.backend.repository.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTokenService {

    @Autowired
    private UserTokenRepository userTokenRepository;

    public UserToken save(UserToken userToken) {
        return userTokenRepository.save(userToken);
    }

    public UserToken getTokenById(String token) {
        return userTokenRepository.findById(token).get();
    }

    public void invalidateToken(UserToken userToken) {
        userTokenRepository.delete(userToken);
    }

}
