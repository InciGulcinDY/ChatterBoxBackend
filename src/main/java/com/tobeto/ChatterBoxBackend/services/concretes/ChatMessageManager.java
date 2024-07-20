package com.tobeto.ChatterBoxBackend.services.concretes;

import com.tobeto.ChatterBoxBackend.core.utilities.mappers.ModelMapperService;
import com.tobeto.ChatterBoxBackend.core.utilities.messages.ProjectMessageService;
import com.tobeto.ChatterBoxBackend.entities.concretes.ChatMessage;
import com.tobeto.ChatterBoxBackend.entities.concretes.MessageType;
import com.tobeto.ChatterBoxBackend.repositories.ChatMessageRepository;
import com.tobeto.ChatterBoxBackend.services.abstracts.ChatMessagesService;
import com.tobeto.ChatterBoxBackend.services.constants.Messages;
import com.tobeto.ChatterBoxBackend.services.dtos.message.SaveMessageModel;
import com.tobeto.ChatterBoxBackend.services.dtos.message.responses.GetAllMessageRoomsResponse;
import com.tobeto.ChatterBoxBackend.services.dtos.message.responses.GetAllMessagesResponse;
import com.tobeto.ChatterBoxBackend.services.rules.MessageBusinessRule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChatMessageManager implements ChatMessagesService {

    private final ChatMessageRepository chatMessageRepository;
    private final ModelMapperService modelMapperService;
    private final MessageBusinessRule messageBusinessRule;
    private final ProjectMessageService projectMessageService;


    @Override
    public List<GetAllMessageRoomsResponse> getAllMessageRooms(int userId) {

        //  Getting all messages
        List<ChatMessage> messages = chatMessageRepository.findAll();

        //  Filter the messages in accordance with the userId and messageType,
        //  Then Grouping them in accordance with Message Room
        Map<String, List<ChatMessage>> groupedMessages = messages.stream()
                .filter(message -> message.getSender().getId() == userId ||
                        message.getRecipient().getId() == userId)
                .filter(message -> message.getMessageType().equals(MessageType.CLIENT))
                .filter(message -> message.getRoom() != null)
                .collect(Collectors.groupingBy(ChatMessage::getRoom)); // Group by room

        //  Getting unRead Message Number and Mapping the response
        return groupedMessages.values().stream()
                .map(messageList -> {
                    int unreadMessagesNumber = (int) messageList.stream()
                            .filter(message -> !message.isRead())
                            .count();
                    GetAllMessageRoomsResponse response = new GetAllMessageRoomsResponse();
                    response.setRoom(messageList.get(0).getRoom());
                    response.setUserId(messageList.get(0).getSender().getId());
                    response.setUsername(messageList.get(0).getSender().getUsername());
                    response.setImage(messageList.get(0).getSender().getImage());
                    response.setStatus(messageList.get(0).getSender().getStatus().toString());
                    //response.setRecipient(messageList.get(0).getRecipient());
                    response.setUnreadMessagesNumber(unreadMessagesNumber);
                    return response;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<GetAllMessagesResponse> getAllMessages(String room) {
        List<ChatMessage> messages = chatMessageRepository.findChatMessageByRoom(room);
        return messages.stream()
                .map(message -> this.modelMapperService.forResponse()
                        .map(message, GetAllMessagesResponse.class)
        ).collect(Collectors.toList());
    }

    @Override
    public String saveMessage(SaveMessageModel messageModel) {

        ChatMessage chatMessage = this.modelMapperService.forRequest().map(messageModel, ChatMessage.class);
        this.chatMessageRepository.save(chatMessage);
        System.out.println("ChatMessageManager saveMessage");
        return this.projectMessageService.getMessage(Messages.Message.messageSaveSuccess);
    }
}
