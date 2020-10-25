package com.example.quizer_back.Controller;

import com.example.quizer_back.Model.Quiz;
import com.example.quizer_back.Repository.QuizRepository;
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

import java.util.Optional;



@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest/quiz")
public class QuizRESTController {


    private QuizRepository quizRepository;

    @Autowired
    public void setQuizRepository(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @GetMapping("/")
    public ResponseEntity<String> getQuizList(){
           Iterable<Quiz> quizList=quizRepository.findAll();
           //exclude question list from JSON
        ObjectMapper mapper = new ObjectMapper().registerModule(new JsonViewModule());
        try {
            String json = mapper.writeValueAsString(JsonView.with(quizList).onClass(Quiz.class, Match.match().exclude("questions")));
            return new ResponseEntity<>(json,HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable int id) {
        Optional<Quiz> quizOpt = quizRepository.findById(id);
        return quizOpt.map(quiz -> new ResponseEntity<>(quiz, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

}
