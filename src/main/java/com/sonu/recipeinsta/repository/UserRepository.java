package com.sonu.recipeinsta.repository;

import com.sonu.recipeinsta.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmailId(String emailId);
}
