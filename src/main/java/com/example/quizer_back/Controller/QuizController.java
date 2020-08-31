package com.example.quizer_back.Controller;

import com.example.quizer_back.Model.Quiz;
import com.example.quizer_back.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    private QuizRepository quizRepository;


    @Autowired
    public void setQuizRepository(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }



    @GetMapping("/")
    public ModelAndView getQuizList() {
        ModelAndView modelAndView = new ModelAndView("quizListPage");
        Iterable<Quiz> quizList =  quizRepository.findAll();
        modelAndView.addObject("quizList", quizList);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getQuizById(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("QuizPage");
        Optional<Quiz> quizOpt = quizRepository.findById(id);
        return quizOpt.map(quiz -> modelAndView.addObject("quiz", quiz)).orElseGet(() -> modelAndView.addObject("quiz", null));
    }
}
