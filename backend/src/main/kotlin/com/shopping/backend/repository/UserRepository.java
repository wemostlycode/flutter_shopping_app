package com.shopping.backend.repository;

import com.shopping.backend.data_model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends PagingAndSortingRepository<User, String>, JpaSpecificationExecutor<User> {

    User findByEmailAndStatus(String email, int status);

    User findByUserIdAndCompanyIdAndStatus(String userId, Long companyId, int status);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.companyId = :companyId")
    User findByEmail(@Param("email") String email, @Param("companyId") long companyId);
}
