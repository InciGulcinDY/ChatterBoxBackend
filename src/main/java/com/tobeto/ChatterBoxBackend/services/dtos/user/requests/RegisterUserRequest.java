package com.tobeto.ChatterBoxBackend.services.dtos.user.requests;

import com.tobeto.ChatterBoxBackend.entities.concretes.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {

    @NotBlank(message = "Registration is not possible without a username!")
    @Length(max = 30, message = "The username cannot exceed 30 characters!")
    @Pattern(regexp = "^[A-Za-zÇĞİÖŞÜçğıöşü]*$",
            message = "Please use only letters. Spaces and special characters are not allowed!")
    private String username;

    @NotBlank(message = "Registration is not possible without an email!")
    @Length(max = 40, message = "The email cannot exceed 40 characters!")
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+(?:.[a-zA-Z0-9-]+)*$",
            message = "Please enter a valid email address.")
    private String email;

    @NotBlank(message = "Registration is not possible without a name!")
    @Length(max = 30, message = "The firstname cannot exceed 30 characters!")
    @Pattern(regexp = "^[A-Za-zÇĞİÖŞÜçğıöşü\\s]*$",
            message = "Please use only letters and spaces. Special characters are not allowed!")
    private String firstname;

    @NotBlank(message = "Registration is not possible without a surname!")
    @Length(max = 40, message = "The lastname cannot exceed 40 characters!")
    @Pattern(regexp = "^[A-Za-zÇĞİÖŞÜçğıöşü\\s]*$",
            message = "Please use only letters and spaces. Special characters are not allowed!")
    private String lastname;

    @Pattern(regexp = "^/assets/[^\\s]+\\.(jpg|jpeg|png)$",
            message = "Please provide a valid image path (e.g., /images/filename.jpg) with extensions " +
                    ".jpg, .jpeg, or .png!")
    private String image;

    @NotBlank(message = "Registration is not possible without a password!")
    private String password;

    private List<Role> roles;

}
