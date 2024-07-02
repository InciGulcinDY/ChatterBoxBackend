package com.tobeto.ChatterBoxBackend.services.abstracts;


import com.tobeto.ChatterBoxBackend.services.dtos.user.responses.GetAllUsersResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {

    List<GetAllUsersResponse> findConnectedUsers();

}
