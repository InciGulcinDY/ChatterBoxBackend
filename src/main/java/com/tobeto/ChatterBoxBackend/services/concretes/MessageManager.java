package com.tobeto.ChatterBoxBackend.services.concretes;

import com.tobeto.ChatterBoxBackend.core.exceptions.types.NotFoundException;
import com.tobeto.ChatterBoxBackend.core.utilities.mappers.ModelMapperService;
import com.tobeto.ChatterBoxBackend.core.utilities.messages.ProjectMessageService;
import com.tobeto.ChatterBoxBackend.core.utilities.results.Result;
import com.tobeto.ChatterBoxBackend.core.utilities.results.results.SuccessResult;
import com.tobeto.ChatterBoxBackend.entities.concretes.Message;
import com.tobeto.ChatterBoxBackend.repositories.MessageRepository;
import com.tobeto.ChatterBoxBackend.services.abstracts.MessagesService;
import com.tobeto.ChatterBoxBackend.services.constants.Messages;
import com.tobeto.ChatterBoxBackend.services.dtos.message.request.AddMessageRequest;
import com.tobeto.ChatterBoxBackend.services.dtos.message.request.UpdateMessageRequest;
import com.tobeto.ChatterBoxBackend.services.dtos.message.response.GetAllMessagesResponse;
import com.tobeto.ChatterBoxBackend.services.dtos.message.response.GetMessageByIdResponse;
import com.tobeto.ChatterBoxBackend.services.dtos.user.request.DeleteUserRequest;
import com.tobeto.ChatterBoxBackend.services.dtos.user.response.GetAllUsersResponse;
import com.tobeto.ChatterBoxBackend.services.rules.MessageBusinessRule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class MessageManager implements MessagesService {

    private final MessageRepository messageRepository;
    private final ModelMapperService modelMapperService;
    private final MessageBusinessRule messageBusinessRule;
    private final ProjectMessageService messageService;


    @Override
    public List<GetAllMessagesResponse> getAll(int userId) {

        return messageRepository.getAllMessages(userId);


    }
    @Override
    public List<GetAllMessagesResponse> getAllByFriend(int userId, int friendId) {

        return messageRepository.getAllMessagesByFriends(userId, friendId);


    }

    @Override
    public List<GetAllMessagesResponse> getUnreadMessages(int userId, Integer friendId) {
        return messageRepository.getUnreadMessages(userId,friendId);
    }

    @Override
    public List<GetAllMessagesResponse> getReadMessages(int userId, int friendId) {
        return messageRepository.getReadMessages(userId, friendId);
    }

    @Override
    public List<GetAllUsersResponse> getUsersInvolvedWithUser(int id) {
        return messageRepository.getUsersInvolvedWithUser(id);
    }

    @Override
    public Map<Integer, Integer> getUnreadMessageCounts(int userId) {
        List<Object[]> results = messageRepository.findUnreadMessageCountsByUserId(userId);
        Map<Integer, Integer> unreadCounts = new HashMap<>();
        for (Object[] result : results) {
            Integer senderId = (Integer) result[0];
            Long messageCount = (Long) result[1];
            unreadCounts.put(senderId, messageCount.intValue());
        }
        return unreadCounts;
    }

    /////////////////////


    @Override
    public GetMessageByIdResponse getById(int id) {

        Message message = messageRepository.findById(id).orElseThrow(()->
                new NotFoundException(messageService.getMessage(Messages.Message.getMessageNotFoundMessage)));
        return null;
    }

    @Override
    public Result add(AddMessageRequest request) {
        Message message = this.modelMapperService.forRequest().map(request, Message.class);
        messageRepository.save(message);
        return new SuccessResult("ok");
    }

    @Override
    public Result updateMessageRead(int messageId) {
        Message message = this.messageRepository.findById(messageId).orElseThrow();
        message.setRead(true);
        messageRepository.save(message);
        return new SuccessResult("ok");
    }

    @Override
    public Result delete(DeleteUserRequest request) {
        return null;
    }
}
