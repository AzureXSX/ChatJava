package com.example.demo.repositories;

import com.example.demo.Models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Models.User;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContactRepo extends JpaRepository<Contact, Long>{
    @Query("SELECT c FROM Contact c WHERE c.user_1 = :user")
    List<Contact> findByUser_1(@Param("user") User user);

    @Query("SELECT c FROM Contact c WHERE c.user_2 = :user")
    List<Contact> findByUser_2(@Param("user") User user);
}
