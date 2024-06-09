package com.tobeto.ChatterBoxBackend.controllers;


import com.tobeto.ChatterBoxBackend.core.utilities.results.Result;
import com.tobeto.ChatterBoxBackend.services.abstracts.AuthService;
import com.tobeto.ChatterBoxBackend.services.dtos.user.requests.LoginRequest;
import com.tobeto.ChatterBoxBackend.services.dtos.user.requests.RegisterUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
@CrossOrigin
public class UserController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Result register(@RequestBody RegisterUserRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Result login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

}
