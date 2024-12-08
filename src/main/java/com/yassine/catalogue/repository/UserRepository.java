package com.yassine.catalogue.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yassine.catalogue.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
