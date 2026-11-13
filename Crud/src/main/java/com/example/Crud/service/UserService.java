package com.example.Crud.service;


import com.example.Crud.models.User;
import com.example.Crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createuser(User user) {
        return userRepository.save(user);
    }

    public User getuserid(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public User getuseremail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User Email Not found"));
    }
}