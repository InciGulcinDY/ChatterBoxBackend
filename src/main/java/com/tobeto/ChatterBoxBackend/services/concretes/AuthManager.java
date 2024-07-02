package com.tobeto.ChatterBoxBackend.services.concretes;

import com.tobeto.ChatterBoxBackend.core.services.JwtService;
import com.tobeto.ChatterBoxBackend.core.utilities.mappers.ModelMapperService;
import com.tobeto.ChatterBoxBackend.core.utilities.messages.ProjectMessageManager;
import com.tobeto.ChatterBoxBackend.core.utilities.results.Result;
import com.tobeto.ChatterBoxBackend.core.utilities.results.authentication.AuthCResult;
import com.tobeto.ChatterBoxBackend.core.utilities.results.authentication.LoginResponse;
import com.tobeto.ChatterBoxBackend.core.utilities.results.results.SuccessResult;
import com.tobeto.ChatterBoxBackend.entities.concretes.Status;
import com.tobeto.ChatterBoxBackend.entities.concretes.User;
import com.tobeto.ChatterBoxBackend.repositories.UserRepository;
import com.tobeto.ChatterBoxBackend.services.abstracts.AuthService;
import com.tobeto.ChatterBoxBackend.services.constants.Messages;
import com.tobeto.ChatterBoxBackend.services.dtos.user.requests.LoginRequest;
import com.tobeto.ChatterBoxBackend.services.dtos.user.requests.RegisterUserRequest;
import com.tobeto.ChatterBoxBackend.services.rules.UserBusinessRule;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthManager implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapperService modelMapperService;
    private final UserBusinessRule userBusinessRule;
    private final ProjectMessageManager projectMessageManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public Result register(RegisterUserRequest request) {

        //  Business Rules:
        userBusinessRule.existsUserByEmail(request.getEmail());
        userBusinessRule.existsUserByUserName(request.getEmail());

        //  Password encoding:
        request.setPassword(passwordEncoder.encode(request.getPassword()));

        //   Mapping:
        User user = this.modelMapperService.forRequest().map(request, User.class);
        user.setStatus(Status.ONLINE);

        //  Saving to DB:
        this.userRepository.save(user);

        return new SuccessResult((projectMessageManager.getMessage(Messages.User.userAddSuccess)));
    }

    @Override
    public Result login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

        //  Finding the relevant user
        User user = userRepository.findByUsername(loginRequest.getUserName())
                .orElseThrow();
        user.setStatus(Status.ONLINE);

        //  After successful authentication, generates Token
        String token = jwtService.generateToken(user);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        return new AuthCResult(true, projectMessageManager
                .getMessage(Messages.User.userLoginSuccess), loginResponse);

    }

    @Override
    public Result logout(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new RuntimeException(this.projectMessageManager
                        .getMessage(Messages.User.getUserNotFoundMessage)));

        user.setStatus(Status.OFFLINE);
        this.userRepository.save(user);

        return new SuccessResult((projectMessageManager.getMessage(Messages.User.userLogoutSuccess)));

    }

}
