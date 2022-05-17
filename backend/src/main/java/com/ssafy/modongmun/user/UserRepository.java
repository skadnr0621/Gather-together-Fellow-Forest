package com.ssafy.modongmun.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserNumber(Long userNumber);
    User findByEmail(String userEmail);

}
