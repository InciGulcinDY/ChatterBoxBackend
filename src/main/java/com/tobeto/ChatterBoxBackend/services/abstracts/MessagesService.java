package com.tobeto.ChatterBoxBackend.services.abstracts;

import com.tobeto.ChatterBoxBackend.core.utilities.results.Result;
import com.tobeto.ChatterBoxBackend.services.dtos.message.request.AddMessageRequest;
import com.tobeto.ChatterBoxBackend.services.dtos.message.request.UpdateMessageRequest;
import com.tobeto.ChatterBoxBackend.services.dtos.message.response.GetAllMessagesResponse;
import com.tobeto.ChatterBoxBackend.services.dtos.message.response.GetMessageByIdResponse;
import com.tobeto.ChatterBoxBackend.services.dtos.user.request.DeleteUserRequest;
import com.tobeto.ChatterBoxBackend.services.dtos.user.response.GetAllUsersResponse;

import java.util.List;
import java.util.Map;

public interface MessagesService {
    List<GetAllMessagesResponse> getAll(int userId);
    List<GetAllMessagesResponse> getAllByFriend(int userId, int friendId);

    List<GetAllMessagesResponse> getUnreadMessages(int userId, Integer friendId);
    List<GetAllMessagesResponse> getReadMessages(int userId, int friendId);
    List<GetAllUsersResponse> getUsersInvolvedWithUser(int id);
    GetMessageByIdResponse getById(int id);
    public Map<Integer, Integer> getUnreadMessageCounts(int userId);

    Result add(AddMessageRequest request);

    Result updateMessageRead(int messageId);

    Result delete(DeleteUserRequest request);
}
