package com.tobeto.ChatterBoxBackend.services.rules;

import com.tobeto.ChatterBoxBackend.core.exceptions.types.BusinessException;
import com.tobeto.ChatterBoxBackend.core.utilities.messages.ProjectMessageService;
import com.tobeto.ChatterBoxBackend.repositories.UserRepository;
import com.tobeto.ChatterBoxBackend.services.constants.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBusinessRule {

    private final UserRepository userRepository;
    private final ProjectMessageService projectMessageService;

    public void existsUserById(int id) {
        if (!userRepository.existsById(id)) {
            throw new BusinessException(projectMessageService.getMessage(Messages.User.getUserNotFoundMessage));
        }
    }

    public void existsUserByEmail(String email) {
        if (userRepository.existsUserByEmail(email)) {
            throw new BusinessException
                    (projectMessageService.getMessage(Messages.User.getUserMailAlreadyExistsMessage));
        }
    }

    public void existsUserByUserName(String userName) {
        if(userRepository.existsUserByUsername(userName)) {
            throw new BusinessException(
                    (projectMessageService.getMessage(Messages.User.getUserUsernameAlreadyExistsMessage)));
        }
    }
}
