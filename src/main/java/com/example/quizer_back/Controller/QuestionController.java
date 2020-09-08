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
    public ModelAndView saveQuestion(@ModelAttribute Question question){
        System.out.println(question);
        quizService.addQuestion(question);
        return new ModelAndView(redirectionToQuiz(question));

    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteQuestion(@PathVariable int id){
        Question curQuestion = quizService.getQuestionById(id);
        quizService.deleteQuestion(curQuestion);
        return new ModelAndView(redirectionToQuiz(curQuestion));

    }

    @GetMapping("/edit/{id}")
    public ModelAndView editQuestionPage(@PathVariable int id, Model model){
        Question question = quizService.getQuestionById(id);
        model.addAttribute("question",question);
        return new ModelAndView("addQuestionPage");
    }

    @PostMapping("/edit")
    public ModelAndView editQuestion(@ModelAttribute Question question){
        System.out.println(question);
        quizService.editQuestion(question);
        return new ModelAndView(redirectionToQuiz(question));
    }

    private String redirectionToQuiz(Question question){
        return "redirect:/quiz/"+question.getQuiz().getId();

    }
}
