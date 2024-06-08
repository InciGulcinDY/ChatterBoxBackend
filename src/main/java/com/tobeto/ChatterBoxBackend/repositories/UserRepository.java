package com.tobeto.ChatterBoxBackend.repositories;

import com.tobeto.ChatterBoxBackend.entities.concretes.User;
import com.tobeto.ChatterBoxBackend.services.dtos.message.response.GetAllMessagesResponse;
import com.tobeto.ChatterBoxBackend.services.dtos.user.response.GetAllUsersResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

}
