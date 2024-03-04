package com.example.demo.services;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Models.Message;
import com.example.demo.repositories.MessageRepo;

@Service
public class MessageService {
    
    private final MessageRepo messageRepo;

    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public List<Message> getMessages() {
        return messageRepo.findAll();
    }
}
