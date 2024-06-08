package com.tobeto.ChatterBoxBackend.services.abstracts;

import com.tobeto.ChatterBoxBackend.core.utilities.results.Result;
import com.tobeto.ChatterBoxBackend.services.dtos.user.request.AddUserRequest;
import com.tobeto.ChatterBoxBackend.services.dtos.user.request.DeleteUserRequest;
import com.tobeto.ChatterBoxBackend.services.dtos.user.request.UpdateUserRequest;
import com.tobeto.ChatterBoxBackend.services.dtos.user.response.GetAllUsersResponse;
import com.tobeto.ChatterBoxBackend.services.dtos.user.response.GetUserByIdResponse;

import java.util.List;

public interface UserService {
    List<GetAllUsersResponse> getAll();
    GetUserByIdResponse getById(int id);

    Result add(AddUserRequest request);

    Result update(UpdateUserRequest request);

    Result delete(DeleteUserRequest request);

}
