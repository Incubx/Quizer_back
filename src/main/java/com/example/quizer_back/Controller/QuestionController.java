package com.example.quizer_back.Controller;


import com.example.quizer_back.Model.Question;
import com.example.quizer_back.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/question")
public class QuestionController {

    private QuizService quizService;

    @Autowired
    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/add/{id}")
    public ModelAndView addQuestionPage(@PathVariable int id, Model model){
        ModelAndView modelAndView = new ModelAndView("addQuestionPage");
        Question question =new Question(quizService.getQuizById(id));
        model.addAttribute("question",question);
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addQuestion(@ModelAttribute Question question){
        System.out.println(question);
        quizService.saveQuestion(question);
        return new ModelAndView("redirect:/");

    }
}
