package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private Long userId;

    @Column(name = "UserName")
    private String userName;

    @Column(name = "UserEmail")
    private String userEmail;

    @Column(name = "UserPassword")
    private String userPassword;

    @Column(name = "UserAvatar")
    private byte[] userAvatar;


    public User() {
    
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public byte[] getUserAvatar() {
        return this.userAvatar;
    }
}
