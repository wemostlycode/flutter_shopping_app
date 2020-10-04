package com.shopping.backend.service;

import com.shopping.backend.model.User;
import com.shopping.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User users) {
        return userRepository.save(users);
    }


}
