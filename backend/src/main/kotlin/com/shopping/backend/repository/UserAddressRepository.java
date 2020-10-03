package com.shopping.backend.repository;

import com.shopping.backend.data_model.UserAddress;
import org.springframework.data.repository.CrudRepository;

public interface UserAddressRepository extends CrudRepository<UserAddress, String> {
    UserAddress findByUserIdAndStatus(String userId, int status);

    UserAddress findByAdressIdAndStatus(Long adressId, int status);
}
