package com.app.fierce_backend.domain.user.interfaces;

import com.app.fierce_backend.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByVerificationCode(String code);
    Optional<User> findUserByEmail(String email);
}
