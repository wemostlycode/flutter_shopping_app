package com.shopping.backend.repository;

import com.shopping.backend.data_model.UserToken;
import org.springframework.data.repository.CrudRepository;

public interface UserTokenRepository extends CrudRepository<UserToken, String> {

}
