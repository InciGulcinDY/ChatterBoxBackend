package com.tobeto.ChatterBoxBackend.controllers;

import com.tobeto.ChatterBoxBackend.services.abstracts.ChatMessagesService;
import com.tobeto.ChatterBoxBackend.services.dtos.message.responses.GetAllMessagesResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@AllArgsConstructor
@CrossOrigin
public class ChatMessageController {

    private ChatMessagesService chatMessagesService;

    @GetMapping("/{room}")
    public List<GetAllMessagesResponse> getAllMessages(@PathVariable String room) {
        return this.chatMessagesService.getAllMessages(room);
    }

}
