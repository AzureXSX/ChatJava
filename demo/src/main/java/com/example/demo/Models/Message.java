package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.*;
import com.example.demo.Models.User;

@Entity
@Table(name = "Messages")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MessageId")
    private Long MessageId;

    @Column(name = "MessageText")
    private String MessageText;

    @ManyToOne
    @JoinColumn(name = "SenderId", referencedColumnName = "UserId")
    private User sender;
    
    @ManyToOne
    @JoinColumn(name = "ReceiverId", referencedColumnName = "UserId")
    private User receiver;
}
