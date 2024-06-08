package com.tobeto.ChatterBoxBackend.services.dtos.message.response;

import com.tobeto.ChatterBoxBackend.entities.concretes.User;
import com.tobeto.ChatterBoxBackend.services.dtos.user.response.GetUserByIdResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllMessagesResponse {

    private int id;

    private String content;

    private GetUserByIdResponse sender;

    private GetUserByIdResponse recipient;

    private boolean isRead;

    private LocalDate createdDate;

}
