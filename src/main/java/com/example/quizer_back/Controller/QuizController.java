package com.example.quizer_back.Controller;

import com.example.quizer_back.Model.Category;
import com.example.quizer_back.Model.Quiz;
import com.example.quizer_back.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    private QuizService quizService;

    @Autowired
    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }


    @GetMapping("/")
    public ModelAndView getQuizList() {
        ModelAndView modelAndView = new ModelAndView("quizListPage");
        Iterable<Quiz> quizList = quizService.getQuizList();
        modelAndView.addObject("quizList", quizList);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getQuizById(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("quizPage");
        try {
            Quiz quiz = quizService.getQuizById(id);
            modelAndView.addObject("quiz", quiz);
            return modelAndView;
        } catch (NoSuchElementException e) {
            return modelAndView;
        }
    }

    @GetMapping("/add")
    public ModelAndView addQuizPage() {
        Quiz quiz = new Quiz();
        ModelAndView modelAndView = new ModelAndView("saveQuizPage");
        modelAndView.addObject(quiz);
        List<Category> categoryList = quizService.getCategoryList();
        modelAndView.addObject("categoryList",categoryList);
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView addQuiz(@ModelAttribute Quiz quiz) {
        ModelAndView modelAndView = new ModelAndView("redirect:/quiz/");
        quizService.saveQuiz(quiz);
        return modelAndView;
    }


    @GetMapping("/delete/{id}")
    public ModelAndView deleteQuiz(@PathVariable int id) {
        quizService.deleteQuizById(id);
        return new ModelAndView("redirect:/quiz/");
    }


    @GetMapping("/edit/{id}")
    public ModelAndView editQuizPage(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("saveQuizPage");
        try {
            Quiz quiz = quizService.getQuizById(id);
            modelAndView.addObject("quiz", quiz);
            List<Category> categoryList = quizService.getCategoryList();
            modelAndView.addObject("categoryList",categoryList);
            return modelAndView;
        } catch (NoSuchElementException e) {
            return modelAndView;
        }
    }
}
