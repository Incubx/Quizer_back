package com.example.quizer_back.Controller;


import com.example.quizer_back.Model.Quiz;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest/quiz")
public class QuizRestController {


    @GetMapping(value = "/{id}")
    public Quiz getQuizById(@PathVariable int id) {

        return new Quiz("quiz 1",5,true,true);

    }

}
