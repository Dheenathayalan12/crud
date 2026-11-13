package com.example.Crud.controller;


import com.example.Crud.models.User;
import com.example.Crud.repository.UserRepository;
import com.example.Crud.service.UserService;
import com.example.Crud.utils.JwtUtils;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

//    @Autowired  //required argsconstructior
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    // it user for authorization

    @PostMapping("/register")
    public ResponseEntity<String > registeruser(@RequestBody Map<String, String> body){
        String email=body.get("email");
//        String password=body.get("password");
//        passwordEncoder.encode(body.get(password));  or
        String password=passwordEncoder.encode((body.get("password")));

        if(userRepository.findByEmail(email).isPresent()){
           return new ResponseEntity<>("Email already Registered",HttpStatus.CONFLICT);
        }
        userService.createuser(User.builder().email(email).password(password).build());
            return new ResponseEntity<>("successfully", HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginuser(@RequestBody Map<String, String> body) {

        String email = body.get("email");
        String password = body.get("password");

        var userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>("No Register data", HttpStatus.UNAUTHORIZED);
        }

        var user = userOptional.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return new ResponseEntity<>(" invalid user", HttpStatus.UNAUTHORIZED);

        }
        String token= jwtUtils.generateToken(email);
        return ResponseEntity.ok(Map.of("token" , token));


    }



}
