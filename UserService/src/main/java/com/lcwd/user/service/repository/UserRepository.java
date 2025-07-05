package com.lcwd.user.service.repository;

import com.lcwd.user.service.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    // You can add custom query methods here if needed, e.g.
    User findByEmail(String email);
}
