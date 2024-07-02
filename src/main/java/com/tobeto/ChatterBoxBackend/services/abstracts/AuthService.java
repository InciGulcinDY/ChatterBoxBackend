package com.tobeto.ChatterBoxBackend.services.abstracts;

import com.tobeto.ChatterBoxBackend.core.utilities.results.Result;
import com.tobeto.ChatterBoxBackend.services.dtos.user.requests.LoginRequest;
import com.tobeto.ChatterBoxBackend.services.dtos.user.requests.RegisterUserRequest;

public interface AuthService {

    Result register(RegisterUserRequest request);

    Result login(LoginRequest loginRequest);

    Result logout(String username);

}
