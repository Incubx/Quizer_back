package com.example.quizer_back.Controller;

import com.example.quizer_back.Model.User;
import com.example.quizer_back.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

//code x - OK
//code -1 - already registered
//code -2 - wrong password
//code -3 - no such user
@RestController
@RequestMapping("/rest/user")
public class UserRESTController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<Integer> registerUser(@RequestBody User user) {
        try {
            userService.getUserByEmail(user.getEmail());
            return new ResponseEntity<>(-1, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            userService.saveUser(user);
            return new ResponseEntity<>(user.getId(), HttpStatus.OK);
        }

    }

    @PostMapping("/authorize")
    public ResponseEntity<Integer> authorizeUser(@RequestBody User user) {
        try {
            User storedUser = userService.getUserByEmail(user.getEmail());
            if (storedUser.getPassword().equals(user.getPassword())) {
                System.out.println(storedUser);
                return new ResponseEntity<>(storedUser.getId(), HttpStatus.OK);
            }else return new ResponseEntity<>(-2,HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(-3, HttpStatus.OK);
        }

    }
}
