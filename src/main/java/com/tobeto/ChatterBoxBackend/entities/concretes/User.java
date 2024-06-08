package com.tobeto.ChatterBoxBackend.entities.concretes;

import com.tobeto.ChatterBoxBackend.entities.abstracts.BasicEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User extends BasicEntity {

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @Column(name = "image")
    private String image;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "recipient")
    private List<Message> receivedMessages;

    @OneToMany(mappedBy = "sender")
    private List<Message> sentMessages;


    @PrePersist
    @PreUpdate
    private void setDefaultImagePath() {
        if (this.image == null || this.image.isEmpty()) {
            this.image = "/assets/defaultProfileImage.png";
        }
    }

}
