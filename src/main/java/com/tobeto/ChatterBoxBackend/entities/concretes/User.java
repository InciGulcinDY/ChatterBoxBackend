package com.tobeto.ChatterBoxBackend.entities.concretes;

import com.tobeto.ChatterBoxBackend.entities.abstracts.BasicEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User extends BasicEntity implements UserDetails {

    @Column(name = "user_name")
    private String username;

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

    @JoinTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private List<Role> authorities;

    @JoinTable(name = "status", joinColumns = @JoinColumn(name = "status_id"))
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "recipient")
    private List<ChatMessage> receivedChatMessages;

    @OneToMany(mappedBy = "sender")
    private List<ChatMessage> sentChatMessages;


    @PrePersist
    @PreUpdate
    //  Setting default profile image to use in case of absent image
    private void setDefaultImagePath() {
        if (this.image == null || this.image.isEmpty()) {
            this.image = "/assets/defaultProfileImage.png";
        }
    }

    //  USER DETAILS IMPLEMENTATION | SPRING SECURITY BASE METHODS
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

}
