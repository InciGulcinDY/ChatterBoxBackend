package com.tobeto.ChatterBoxBackend.services.concretes;

import com.tobeto.ChatterBoxBackend.core.utilities.messages.ProjectMessageManager;
import com.tobeto.ChatterBoxBackend.repositories.UserRepository;
import com.tobeto.ChatterBoxBackend.services.abstracts.UserService;
import com.tobeto.ChatterBoxBackend.services.constants.Messages;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private final UserRepository userRepository;
    private final ProjectMessageManager projectMessageManager;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByUserName(userName).orElseThrow(() ->
                new UsernameNotFoundException(projectMessageManager.getMessage(Messages.User.getUserNotFoundMessage)));
    }
}
