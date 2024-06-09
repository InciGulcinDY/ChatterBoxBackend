package com.tobeto.ChatterBoxBackend.repositories;

import com.tobeto.ChatterBoxBackend.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserName (String userName);

    Boolean existsUserByUserName(String userName);

    Boolean existsUserByEmail(String email);

}
