package com.tobeto.ChatterBoxBackend.core.services;

import com.corundumstudio.socketio.SocketIOClient;
import com.tobeto.ChatterBoxBackend.core.utilities.mappers.ModelMapperService;
import com.tobeto.ChatterBoxBackend.entities.concretes.ChatMessage;
import com.tobeto.ChatterBoxBackend.entities.concretes.MessageType;
import com.tobeto.ChatterBoxBackend.services.abstracts.ChatMessagesService;
import com.tobeto.ChatterBoxBackend.services.dtos.message.SaveMessageModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SocketService {

    private final ChatMessagesService chatMessagesService;
    private final ModelMapperService modelMapperService;

    public <T> void sendSocketMessage(SocketIOClient senderClient, T message, String room) {
        for (
                SocketIOClient client : senderClient.getNamespace().getRoomOperations(room).getClients()) {
            if (!client.getSessionId().equals(senderClient.getSessionId())) {
                client.sendEvent("read_message",
                        message);
            }
        }
    }

    public void saveMessage(SocketIOClient senderClient, ChatMessage chatMessage) {
        SaveMessageModel storedMessage = this.modelMapperService.forRequest().map(chatMessage, SaveMessageModel.class);

        this.chatMessagesService.saveMessage(storedMessage);

        sendSocketMessage(senderClient, storedMessage, chatMessage.getRoom());
    }

    public void saveInfoMessage(SocketIOClient socketIOClient, String messageContent, String room) {
        SaveMessageModel messageModel = SaveMessageModel.builder()
                .messageType(MessageType.SERVER)
                .content(messageContent)
                .room(room)
                .build();
        this.chatMessagesService.saveMessage(messageModel);
        sendSocketMessage(socketIOClient, messageModel, room);
    }
}
