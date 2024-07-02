package com.tobeto.ChatterBoxBackend.repositories;

import com.tobeto.ChatterBoxBackend.entities.concretes.Status;
import com.tobeto.ChatterBoxBackend.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByStatus(Status status);

    Optional<User> findByUsername(String username);

    Boolean existsUserByUsername(String username);

    Boolean existsUserByEmail(String email);

}
