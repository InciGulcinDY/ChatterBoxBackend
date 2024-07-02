package com.tobeto.ChatterBoxBackend.controllers;


import com.tobeto.ChatterBoxBackend.core.utilities.results.Result;
import com.tobeto.ChatterBoxBackend.services.abstracts.AuthService;
import com.tobeto.ChatterBoxBackend.services.abstracts.UserService;
import com.tobeto.ChatterBoxBackend.services.dtos.user.requests.LoginRequest;
import com.tobeto.ChatterBoxBackend.services.dtos.user.requests.RegisterUserRequest;
import com.tobeto.ChatterBoxBackend.services.dtos.user.responses.GetAllUsersResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@CrossOrigin
public class UserController {

    private final AuthService authService;
    private final UserService userService;

    //@PostMapping("/register")
    @MessageMapping("/register")
    @SendTo("/public")
    @ResponseStatus(HttpStatus.CREATED)
    public Result register(@Payload RegisterUserRequest request) {
        return authService.register(request);
    }

    //@PostMapping("/login")
    @MessageMapping("/login")
    @SendTo("/public")
    @ResponseStatus(HttpStatus.OK)
    public Result login(@Payload LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @MessageMapping("/logout")
    @SendTo("/public")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Result logout(@Payload String username) {
        return authService.logout(username);
    }

    @GetMapping("/getAllOnline")
    public List<GetAllUsersResponse> findConnectedUsers() {
        return this.userService.findConnectedUsers();
    }

}
