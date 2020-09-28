package com.example.quizer_back.Controller;

import com.example.quizer_back.Model.User;
import com.example.quizer_back.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@RestController
@RequestMapping("/rest/user")
public class UserRESTController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<User>> getUserList(){
        Iterable<User> userList= userService.getUserList();
        return new ResponseEntity<>(userList, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        try {
            User user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/")
    public int registerUser(@RequestBody User user){
        userService.saveUser(user);
        return user.getId();
    }
    
    @GetMapping("/{email}")
    public ResponseEntity<User> getUserById(@PathVariable String email){
        try {
            User user = userService.getUserByEmail(email);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }
}
