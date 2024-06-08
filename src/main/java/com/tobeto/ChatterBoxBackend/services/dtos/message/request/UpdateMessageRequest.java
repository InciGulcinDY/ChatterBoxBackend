package com.tobeto.ChatterBoxBackend.services.dtos.message.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMessageRequest {

    @NotNull
    @Positive(message = "The assigned value must not assume a negative numerical value!")
    private int id;

    @NotBlank(message = "Message cannot be blank!")
    @Length(max = 300, message = "The message content cannot exceed 300 characters!")
    @Pattern(regexp = "^[\\p{L}0-9\\s!\"#$%&'()*+,-./:;<=>?@[\\\\\\\\]^_`{|}~]*$",
            message = "Please use only letters, digits, spaces, and special characters.")
    private String content;

    @NotNull
    @Positive(message = "The assigned value must not assume a negative numerical value!")
    private int senderId;

    @NotNull
    @Positive(message = "The assigned value must not assume a negative numerical value!")
    private int recipientId;

    @NotNull(message = "The read status must not be null!")
    private boolean isRead;

}
