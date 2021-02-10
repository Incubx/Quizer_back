package com.example.quizer_back.Controller;

import com.example.quizer_back.Model.Category;
import com.example.quizer_back.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/category")
public class CategoryRESTController {

    private QuizService quizService;
    @Autowired
    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Category>> getCategoryList(){
        List<Category> categoryList = quizService.getCategoryList();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

}
