package com.tobeto.ChatterBoxBackend.services.abstracts;

import com.tobeto.ChatterBoxBackend.services.dtos.message.SaveMessageModel;
import com.tobeto.ChatterBoxBackend.services.dtos.message.responses.GetAllMessageRoomsResponse;
import com.tobeto.ChatterBoxBackend.services.dtos.message.responses.GetAllMessagesResponse;

import java.util.List;

public interface ChatMessagesService {

    List<GetAllMessageRoomsResponse> getAllMessageRooms(int userId);

    List<GetAllMessagesResponse> getAllMessages(String room);

    String saveMessage(SaveMessageModel messageModel);

}
