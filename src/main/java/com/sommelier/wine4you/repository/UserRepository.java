package com.sommelier.wine4you.repository;

import com.sommelier.wine4you.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByPhoneOrEmail(String phone, String email);

    Boolean existsByPhone(String login);

    Boolean existsByEmail(String email);
}
