package com.tobeto.ChatterBoxBackend.controllers;

import com.tobeto.ChatterBoxBackend.core.utilities.results.Result;
import com.tobeto.ChatterBoxBackend.services.abstracts.MessagesService;
import com.tobeto.ChatterBoxBackend.services.dtos.message.requests.AddMessageRequest;
import com.tobeto.ChatterBoxBackend.services.dtos.message.responses.GetAllMessagesResponse;
import com.tobeto.ChatterBoxBackend.services.dtos.user.responses.GetAllUsersResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/messages")
@AllArgsConstructor
@CrossOrigin
public class MessageController {

    private final MessagesService messagesService;

    @GetMapping("/getAll/{userId}")
    //  Getters all messages of the user
    public List<GetAllMessagesResponse> getAll(@PathVariable int userId) {
        return messagesService.getAll(userId);
    }

    @GetMapping("/getAllByFriend/{userId}/{friendId}")
    //  Getters all messages of the user and his/her friend
    public List<GetAllMessagesResponse> getAllByFriends(@PathVariable int userId, @PathVariable int friendId) {
        return messagesService.getAllByFriend(userId, friendId);
    }

    @GetMapping("/unread/{userId}/{friendId}")
    //  Getters all UNREAD messages of the user and his/her friend
    public List<GetAllMessagesResponse> getUnreadMessages(@PathVariable int userId, @PathVariable Integer friendId) {
        return messagesService.getUnreadMessages(userId, friendId);
    }

    @GetMapping("/read/{userId}/{friendId}")
    //  Getters all READ messages of the user and his/her friend
    public List<GetAllMessagesResponse> getReadMessages(@PathVariable int userId, @PathVariable int friendId) {
        return messagesService.getReadMessages(userId,friendId);
    }

    @PostMapping("/add")
    //  Creates new message on the DB
    public Result add(@RequestBody @Valid AddMessageRequest request) {
        return messagesService.add(request);
    }

    @GetMapping("/users/{userId}")
    //  Lists friends that match the user
    public List<GetAllUsersResponse> getUsersInvolvedWithUser(@PathVariable int userId) {
        return messagesService.getUsersInvolvedWithUser(userId);
    }

    @GetMapping("/unreadMessageCounts/{userId}")
    //  List counts of unread messages for each friend
    public ResponseEntity<Map<Integer, Integer>> getUnreadMessageCounts(@PathVariable int userId) {
        try {
            Map<Integer, Integer> unreadCounts = messagesService.getUnreadMessageCounts(userId);
            return ResponseEntity.ok(unreadCounts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/updateMessageRead/{messageId}")
    //  Updates the message by marking as READ
    public Result update(@PathVariable int messageId){
        return messagesService.updateMessageRead(messageId);
    }

}
