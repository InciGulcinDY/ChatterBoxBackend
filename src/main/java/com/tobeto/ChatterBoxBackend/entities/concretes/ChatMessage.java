package com.tobeto.ChatterBoxBackend.entities.concretes;

import com.tobeto.ChatterBoxBackend.entities.abstracts.BasicEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chat_messages")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatMessage extends BasicEntity {

    @Column(name = "content")
    private String content;

    @Column(name = "room")
    private String room;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

}
