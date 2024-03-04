package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.*;
import com.example.demo.Models.User;

@Entity
@Table(name = "Contacts")
@Data
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ContactId")
    private Long ContactId;

    @ManyToOne
    @JoinColumn(name = "user_1", referencedColumnName = "UserId")
    private User user_1;
    
    @ManyToOne
    @JoinColumn(name = "user_2", referencedColumnName = "UserId")
    private User user_2;

    public Contact() {
    
    }

    public Contact(User user_1, User user_2) {
        this.user_1 = user_1;
        this.user_2 = user_2;
    }

    public User getUser_1() {
        return user_1;
    }

    public User getUser_2() {
        return user_2;
    }
}
