package com.example.quizer_back.Controller;

import com.example.quizer_back.Model.Quiz;

import com.example.quizer_back.Model.User;
import com.example.quizer_back.Service.QuizService;
import com.example.quizer_back.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monitorjbl.json.JsonView;
import com.monitorjbl.json.JsonViewModule;
import com.monitorjbl.json.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/rest/quiz")
public class QuizRESTController {

    private QuizService quizService;
    private UserService userService;

    @Autowired
    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /*@GetMapping("/")
    public ResponseEntity<String> getQuizList() {
        List<Quiz> quizList = quizService.getNotEmptyQuizList();
        //exclude question list from JSON
        String json = configureJSON(quizList);
        if (json != null) {
            return new ResponseEntity<>(json, HttpStatus.OK);
        } else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }*/

    @GetMapping("/user/{userId}")
    public ResponseEntity<String> getCompletedQuizList(@PathVariable int userId) {
        User user = userService.getUserById(userId);
        HashMap<Quiz,Boolean> quizCompletedMap = quizService.getUserQuizMap(user);
        System.out.println(quizCompletedMap);
        //exclude question list from JSON
        String json = configureJSON(quizCompletedMap);
        if (json != null) {
            return new ResponseEntity<>(json, HttpStatus.OK);
        } else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }


    private String configureJSON(HashMap<Quiz,Boolean> quizList) {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JsonViewModule());
        System.out.println(quizList);
        try {
            return mapper.writeValueAsString(JsonView.with(quizList).onClass(Quiz.class, Match.match().exclude("questions")));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable int id) {
        try {
            Quiz quiz = quizService.getQuizById(id);
            return new ResponseEntity<>(quiz, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


}
