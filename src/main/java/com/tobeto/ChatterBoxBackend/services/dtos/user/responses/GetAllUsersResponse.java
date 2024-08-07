package com.tobeto.ChatterBoxBackend.services.dtos.user.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUsersResponse {

    private Integer id;

    private String username;

    private String email;

    private String firstname;

    private String lastname;

    private String image;


}
