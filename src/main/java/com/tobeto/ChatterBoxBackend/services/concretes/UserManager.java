package com.tobeto.ChatterBoxBackend.services.concretes;

import com.tobeto.ChatterBoxBackend.core.utilities.mappers.ModelMapperService;
import com.tobeto.ChatterBoxBackend.core.utilities.messages.ProjectMessageManager;
import com.tobeto.ChatterBoxBackend.entities.concretes.Status;
import com.tobeto.ChatterBoxBackend.entities.concretes.User;
import com.tobeto.ChatterBoxBackend.repositories.UserRepository;
import com.tobeto.ChatterBoxBackend.services.abstracts.UserService;
import com.tobeto.ChatterBoxBackend.services.constants.Messages;
import com.tobeto.ChatterBoxBackend.services.dtos.user.responses.GetAllUsersResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private final UserRepository userRepository;
    private final ProjectMessageManager projectMessageManager;
    private final ModelMapperService modelMapperService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByUsername(userName).orElseThrow(() ->
                new UsernameNotFoundException(projectMessageManager.getMessage(Messages.User.getUserNotFoundMessage)));
    }

    @Override
    public List<GetAllUsersResponse> findConnectedUsers() {

        //  Finding all online users form the repository
        List<User> users = userRepository.findAllByStatus(Status.ONLINE);

        //  Mapping
        return users.stream()
                .map(user -> this.modelMapperService.forResponse()
                        .map(user, GetAllUsersResponse.class))
                .collect(Collectors.toList());
    }
}
