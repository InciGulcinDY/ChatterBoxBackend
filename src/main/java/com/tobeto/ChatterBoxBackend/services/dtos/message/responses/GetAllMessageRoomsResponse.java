package com.tobeto.ChatterBoxBackend.services.dtos.message.responses;

import com.tobeto.ChatterBoxBackend.entities.concretes.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllMessageRoomsResponse {

    private String room;

    //private User recipient;

    private int userId;

    private String username;

    private String image;

    private String status;

    private int unreadMessagesNumber;

}
