package com.tobeto.ChatterBoxBackend.services.concretes;

import com.tobeto.ChatterBoxBackend.core.utilities.mappers.ModelMapperService;
import com.tobeto.ChatterBoxBackend.core.utilities.messages.ProjectMessageService;
import com.tobeto.ChatterBoxBackend.entities.concretes.ChatMessage;
import com.tobeto.ChatterBoxBackend.repositories.ChatMessageRepository;
import com.tobeto.ChatterBoxBackend.services.abstracts.ChatMessagesService;
import com.tobeto.ChatterBoxBackend.services.constants.Messages;
import com.tobeto.ChatterBoxBackend.services.dtos.message.SaveMessageModel;
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
        return this.projectMessageService.getMessage(Messages.Message.messageSaveSuccess);
    }
}
