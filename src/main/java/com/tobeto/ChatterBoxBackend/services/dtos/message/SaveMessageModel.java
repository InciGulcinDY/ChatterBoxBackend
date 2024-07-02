package com.tobeto.ChatterBoxBackend.services.dtos.message;

import com.tobeto.ChatterBoxBackend.entities.concretes.MessageType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveMessageModel {

    @NotBlank(message = "Message cannot be blank!")
    @Length(max = 300, message = "The message content cannot exceed 300 characters!")
    @Pattern(regexp = "^[\\p{L}0-9\\s!\"#$%&'()*+,-./:;<=>?@[\\\\\\\\]^_`{|}~]*$",
            message = "Please use only letters, digits, spaces, and special characters.")
    private String content;

    @NotBlank
    private String room;

    @NotNull
    @Positive(message = "The assigned value must not assume a negative numerical value!")
    private int senderId;

    @NotNull
    @Positive(message = "The assigned value must not assume a negative numerical value!")
    private int recipientId;


    private MessageType messageType;

}
