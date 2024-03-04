package com.example.demo.services;

import com.example.demo.repositories.ContactRepo;
import com.example.demo.repositories.UserRepo;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Models.Contact;
import com.example.demo.Models.User;


@Service
public class UserService {
    private final UserRepo userRepo;
    private final ContactRepo contactRepo;

    public UserService(UserRepo userRepo, ContactRepo contactRepo) {
        this.userRepo = userRepo;
        this.contactRepo = contactRepo;
    }

    public User getUser(String userName) {
        return userRepo.findByUserName(userName);
    }

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public boolean addUser(User user) {
        try{
            User savedUser = userRepo.save(user);
            return savedUser != null;
        }
        catch(Exception e) {
            return false;
        }
    }

    public boolean verifyUser(String userName, String userPassword) {
        User user = userRepo.findByUserName(userName);
        if(user == null)
            return false;
        return user.getUserPassword().equals(userPassword);
    }

    public boolean addContact(User user, User contact) {
        Contact newContact = new Contact(user, contact);
        try{
            contactRepo.save(newContact);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    public List<Contact> getContacts(User user) {
        var list = contactRepo.findByUser_1(user);
        list.addAll(contactRepo.findByUser_2(user));
        return list;
    }
}
