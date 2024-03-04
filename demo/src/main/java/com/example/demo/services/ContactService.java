package com.example.demo.services;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Models.Contact;
import com.example.demo.repositories.ContactRepo;

@Service 
public class ContactService {
    private final ContactRepo contactRepo;

    public ContactService(ContactRepo contactRepo) {
        this.contactRepo = contactRepo;
    }

    public List<Contact> allContacts() {
        return contactRepo.findAll();
    }
}
