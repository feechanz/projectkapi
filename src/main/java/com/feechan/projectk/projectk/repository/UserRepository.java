package com.feechan.projectk.projectk.repository;

import com.feechan.projectk.projectk.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Feechan
 * @since 9/9/2019
 */

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
