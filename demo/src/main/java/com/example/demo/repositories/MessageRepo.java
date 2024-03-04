package com.example.demo.repositories;

import com.example.demo.Models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long>{
    
}
