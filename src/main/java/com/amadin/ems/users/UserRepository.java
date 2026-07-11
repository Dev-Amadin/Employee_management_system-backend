package com.amadin.ems.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsernameAndIsActive(String username, Boolean isActive);

    Optional<User> findByIdAndIsActive(String id, Boolean isActive);

}
