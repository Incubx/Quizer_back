package com.example.quizer_back.Controller;

import com.example.quizer_back.Model.Quiz;

import com.example.quizer_back.Service.QuizService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monitorjbl.json.JsonView;
import com.monitorjbl.json.JsonViewModule;
import com.monitorjbl.json.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;



@RestController
@RequestMapping("/rest/quiz")
public class QuizRESTController {

    private QuizService quizService;

    @Autowired
    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/")
    public ResponseEntity<String> getQuizList() {
        Iterable<Quiz> quizList = quizService.getNotEmptyQuizList();
        //exclude question list from JSON
        ObjectMapper mapper = new ObjectMapper().registerModule(new JsonViewModule());
        try {
            String json = mapper.writeValueAsString(JsonView.with(quizList).onClass(Quiz.class, Match.match().exclude("questions")));
            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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
