package com.name.namedemo.User;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.name.namedemo.Repository.UserRepository;

public class user {
     private final UserRepository userRepository;

    @Autowired
    public void UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/createUser")
    public String createUser(
            @RequestParam String firstName,
            @RequestParam String middleName,
            @RequestParam String lastName) {
    
        String password = generatePassword(firstName, middleName, lastName);

        
        User user = new User();
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setPassword(password);

        
        userRepository.save(user);

        return "redirect:/";
    }

    private String generatePassword(String firstName, String middleName, String lastName) {
        
        Random random = new Random();
        String firstChars = firstName.substring(0, 2);
        String middleChars = middleName.substring(0, 2);
        String lastChars = lastName.substring(0, 2);
        int randomDigits = random.nextInt(900) + 100; 

        return firstChars + middleChars + lastChars + randomDigits;
    }
}

