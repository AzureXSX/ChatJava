package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.Models.Contact;
import com.example.demo.Models.Message;
import com.example.demo.Models.User;
import com.example.demo.services.MessageService;
import com.example.demo.services.UserService;
import lombok.AllArgsConstructor;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.JWTManager.JWTManager;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class UserController {
    private final UserService userService;
    private final MessageService messageService;
    private final JWTManager jwt = new JWTManager();

    @Autowired
    public UserController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @PostMapping("/add-contact")
    public ResponseEntity<String> addContact(@RequestHeader("Authorization") String token, @RequestBody String contact) throws JsonProcessingException {
        
        var tempToken = token.substring(7);
        
        if(!jwt.verifyToken(tempToken).getSecond())
            return new ResponseEntity<>("Invalid token", HttpStatus.BAD_REQUEST);
        
        String username = jwt.decodeToken(tempToken);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(contact);
        String contactName = jsonNode.get("username").asText();
        User user = userService.getUser(username);
        User contactUser = userService.getUser(contactName);

        
        if(contactUser == null)
            return new ResponseEntity<>("Contact not found", HttpStatus.BAD_REQUEST);
        if(userService.addContact(user, contactUser))
            return new ResponseEntity<>("Contact added", HttpStatus.OK);
        else
            return new ResponseEntity<>("Contact already exists", HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/get-contacts")
    public ResponseEntity<List<Contact>> getContacts(@RequestHeader("Authorization") String token) {
        var tempToken = token.substring(7);
        
        if(!jwt.verifyToken(tempToken).getSecond())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
        String username = jwt.decodeToken(tempToken);
        User user = userService.getUser(username);
        List<Contact> contacts = userService.getContacts(user);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }
}
