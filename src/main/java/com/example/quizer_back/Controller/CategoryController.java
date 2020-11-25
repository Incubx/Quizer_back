package com.example.quizer_back.Controller;

import com.example.quizer_back.Model.Category;
import com.example.quizer_back.Model.Quiz;
import com.example.quizer_back.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private QuizService quizService;

    @Autowired
    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/")
    public ModelAndView getCategoryList() {
        ModelAndView modelAndView = new ModelAndView("CategoryPage");
        List<Category> categoryList = quizService.getCategoryList();
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.addObject("newCategory", new Category());
        modelAndView.addObject("error", 0);
        return modelAndView;
    }


    @PostMapping("/save")
    public ModelAndView saveCategory(@ModelAttribute Category category) {
        ModelAndView modelAndView = new ModelAndView("redirect:/category/");
        quizService.saveCategory(category);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCategory(@PathVariable int id) {
        List<Quiz> quizList = quizService.getQuizListByCategory(quizService.getCategoryById(id));
        if (quizList.isEmpty()) {
            quizService.deleteCategoryById(id);
            return new ModelAndView("redirect:/category/");
        } else {
            ModelAndView modelAndView = new ModelAndView("CategoryPage");
            List<Category> categoryList = quizService.getCategoryList();
            modelAndView.addObject("categoryList", categoryList);
            modelAndView.addObject("newCategory", new Category());
            modelAndView.addObject("error", id);
            return modelAndView;
        }

    }

    @GetMapping("/forceDelete/{id}")
    public ModelAndView forceDeleteCategory(@PathVariable int id) {
        quizService.deleteCategoryById(id);
        return new ModelAndView("redirect:/category/");
    }

    @GetMapping("/{id}")
    public ModelAndView getCategoryQuizList(@PathVariable int id) {
        Category category = quizService.getCategoryById(id);
        ModelAndView modelAndView = new ModelAndView("quizListPage");
        List<Quiz> quizList = quizService.getQuizListByCategory(category);
        modelAndView.addObject("quizList", quizList);
        modelAndView.addObject("category", category);
        return modelAndView;
    }

}
