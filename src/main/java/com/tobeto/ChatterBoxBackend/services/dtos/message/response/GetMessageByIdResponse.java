package com.tobeto.ChatterBoxBackend.services.dtos.message.response;

import com.tobeto.ChatterBoxBackend.entities.concretes.User;
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

    private User sender;

    private User recipient;

    private boolean isRead;

    private LocalDate createdDate;
}
