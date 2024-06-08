package com.tobeto.ChatterBoxBackend.services.concretes;

import com.tobeto.ChatterBoxBackend.core.utilities.mappers.ModelMapperService;
import com.tobeto.ChatterBoxBackend.core.utilities.results.Result;
import com.tobeto.ChatterBoxBackend.repositories.UserRepository;
import com.tobeto.ChatterBoxBackend.services.abstracts.MessagesService;
import com.tobeto.ChatterBoxBackend.services.abstracts.UserService;
import com.tobeto.ChatterBoxBackend.services.dtos.user.request.AddUserRequest;
import com.tobeto.ChatterBoxBackend.services.dtos.user.request.DeleteUserRequest;
import com.tobeto.ChatterBoxBackend.services.dtos.user.request.UpdateUserRequest;
import com.tobeto.ChatterBoxBackend.services.dtos.user.response.GetAllUsersResponse;
import com.tobeto.ChatterBoxBackend.services.dtos.user.response.GetUserByIdResponse;
import com.tobeto.ChatterBoxBackend.services.rules.UserBusinessRule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private final UserRepository userRepository;
    private final ModelMapperService modelMapperService;
    private final UserBusinessRule userBusinessRule;
    private final MessagesService messageService;


    @Override
    public List<GetAllUsersResponse> getAll() {
        return null;
    }

    @Override
    public GetUserByIdResponse getById(int id) {
        return null;
    }

    @Override
    public Result add(AddUserRequest request) {
        return null;
    }

    @Override
    public Result update(UpdateUserRequest request) {
        return null;
    }

    @Override
    public Result delete(DeleteUserRequest request) {
        return null;
    }
}
