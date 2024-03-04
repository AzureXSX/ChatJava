
package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
import org.springframework.data.util.Pair;


@RestController
public class AuthController {
    
    private final UserService userService;
    private final MessageService messageService;
    private final JWTManager jwt = new JWTManager();

    @Autowired
    public AuthController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String user) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(user);
        System.out.println("\033[0;32m" + "\n\n\n" + jsonNode.get("username").asText() + "\n\n\n" + "\033[0m");
        System.out.println("\033[0;32m" + "\n\n\n" + jsonNode.get("password").asText() + "\n\n\n" + "\033[0m");

        if(userService.verifyUser(jsonNode.get("username").asText(),jsonNode.get("password").asText()))
            return new ResponseEntity<>(jwt.createToken(jsonNode.get("username").asText()), HttpStatus.OK);
        else
            return new ResponseEntity<>("User Wasn't found", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody String user)  throws JsonProcessingException  {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(user);

        String username = jsonNode.get("username").asText();
        String password = jsonNode.get("password").asText();
        String email = jsonNode.get("email").asText();

        System.out.println("\033[0;32m" + "\n\n\n" + username + "\n\n\n" + "\033[0m");
        System.out.println("\033[0;32m" + "\n\n\n" + password + "\n\n\n" + "\033[0m");
        System.out.println("\033[0;32m" + "\n\n\n" + email + "\n\n\n" + "\033[0m");

        User userx = new User();

        userx.setUserName(username);
        userx.setUserEmail(password);
        userx.setUserPassword(email);

        if(userService.addUser(userx))
            return new ResponseEntity<>(jwt.createToken(jsonNode.get("username").asText()), HttpStatus.OK);
        else
            return new ResponseEntity<>("User Wasn't added", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/logout")
    public String logout(@RequestBody User user) {
        return "Logout successful";
    }

    @PostMapping("/get-token")
    public String getToken(@RequestBody User user) {
        return jwt.createToken(user.getUserName());
    }

    @GetMapping("/verify-token")
    public Pair<String, Boolean> verifyToken(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);  // Remove the "Bearer " prefix
        System.out.println("\033[0;32m" + "\n\n\n" + token + "\n\n\n" + "\033[0m");
        return jwt.verifyToken(token);
    }

    public static class Token {
        private String token;

        public String getToken() {
            return token;
        }
    }
}
