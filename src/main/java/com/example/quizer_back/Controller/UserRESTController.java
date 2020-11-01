package com.example.quizer_back.Controller;

import com.example.quizer_back.Model.User;
import com.example.quizer_back.Service.QuizService;
import com.example.quizer_back.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

//code x - OK
//code -402 - wrong password
//code -403 - no such user
@RestController
@RequestMapping("/rest/user")
public class UserRESTController {

    private UserService userService;
    private QuizService quizService;

    @Autowired
    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/authorize")
    public ResponseEntity<Integer> authorizeUser(@RequestBody User user) {
        try {
            User storedUser = userService.getUserByNickname(user.getNickname());
            if (storedUser.getPassword().equals(user.getPassword())) {
                System.out.println(storedUser);
                return new ResponseEntity<>(storedUser.getId(), HttpStatus.OK);
            } else return new ResponseEntity<>(-402, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(-403, HttpStatus.OK);
        }
    }

    @PostMapping("/finishQuiz")
    public ResponseEntity<Integer> finishQuiz(@RequestParam int userId, @RequestParam int quizId,@RequestParam int rating) {
        System.out.println(userId+" "+ quizId+" "+rating);
        userService.finishQuiz(userService.getUserById(userId), quizService.getQuizById(quizId),rating);
        return new ResponseEntity<>(0, HttpStatus.OK);
    }
}