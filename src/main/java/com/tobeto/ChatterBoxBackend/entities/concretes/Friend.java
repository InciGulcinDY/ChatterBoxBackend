package com.tobeto.ChatterBoxBackend.entities.concretes;

import com.tobeto.ChatterBoxBackend.entities.abstracts.BasicEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/*
@Entity
@Table(name = "friends")
@AllArgsConstructor
@NoArgsConstructor
@Data*/
public class Friend extends BasicEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private List<User> friends;

}
