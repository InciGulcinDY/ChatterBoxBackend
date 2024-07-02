package com.tobeto.ChatterBoxBackend.services.dtos.message;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageModel {

    @NotBlank(message = "Message cannot be blank!")
    @Length(max = 300, message = "The message content cannot exceed 300 characters!")
    @Pattern(regexp = "^[\\p{L}0-9\\s!\"#$%&'()*+,-./:;<=>?@[\\\\\\\\]^_`{|}~]*$",
            message = "Please use only letters, digits, spaces, and special characters.")
    private String content;

}
