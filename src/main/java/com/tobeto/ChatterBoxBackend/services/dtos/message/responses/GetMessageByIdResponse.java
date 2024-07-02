package com.tobeto.ChatterBoxBackend.services.dtos.message.responses;

import com.tobeto.ChatterBoxBackend.entities.concretes.User;
import com.tobeto.ChatterBoxBackend.services.dtos.user.responses.GetUserByIdResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMessageByIdResponse {

    private int id;

    private String content;

    private String room;

    private GetUserByIdResponse sender;

    private GetUserByIdResponse recipient;

    private LocalDate createdDate;

}
