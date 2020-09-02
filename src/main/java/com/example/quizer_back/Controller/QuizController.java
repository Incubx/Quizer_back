package com.example.quizer_back.Controller;

import com.example.quizer_back.Model.Quiz;
import com.example.quizer_back.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        Quiz quiz = quizService.getQuizById(id);
        return modelAndView.addObject("quiz", quiz);
    }

    @PostMapping("/add")
    public ModelAndView addQuiz(@ModelAttribute Quiz quiz) {
        System.out.println(quiz);
        quizService.addQuiz(quiz);
        return new ModelAndView("redirect:/quiz/");
    }

    @GetMapping("/add")
    public ModelAndView addQuizPage(Model model){
        model.addAttribute(new Quiz());
        return new ModelAndView("addQuizPage");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteQuiz(@PathVariable int id){
        quizService.deleteQuizById(id);
        return new ModelAndView("redirect:/quiz/");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editQuizPage(Model model,@PathVariable int id){
        model.addAttribute(quizService.getQuizById(id));
        return new ModelAndView("addQuizPage");
    }

    @PostMapping("/edit")
    public ModelAndView editQuiz(@ModelAttribute Quiz quiz){
        System.out.println(quiz);
        quizService.addQuiz(quiz);
        return new ModelAndView("redirect:/quiz/");
    }
}
