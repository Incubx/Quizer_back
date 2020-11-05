package com.example.quizer_back.Controller;


import com.example.quizer_back.Model.Quiz;
import com.example.quizer_back.Model.User;
import com.example.quizer_back.Model.UserQuiz;
import com.example.quizer_back.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;


@Controller
@RequestMapping("/user")
public class UserController {


    private UserService userService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView getUserList() {
        ModelAndView modelAndView = new ModelAndView("userListPage");
        Iterable<User> userList = userService.getUserList();
        modelAndView.addObject("userList", userList);
        return modelAndView;
    }


    @PostMapping("/add")
    public ModelAndView addQuiz(@ModelAttribute User user) {
        System.out.println(user);
        userService.saveUser(user);
        return new ModelAndView("redirect:/user/");
    }

    @GetMapping("/add")
    public ModelAndView addQuizPage(Model model) {
        model.addAttribute(new User());
        return new ModelAndView("addUserPage");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editQuizPage(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("addUserPage");
        try {
            User user = userService.getUserById(id);
            modelAndView.addObject("user", user);
            return modelAndView;
        } catch (NoSuchElementException e) {
            return modelAndView;
        }
    }


    @GetMapping("/delete/{id}")
    public ModelAndView deleteQuiz(@PathVariable int id) {
        userService.deleteUserById(id);
        return new ModelAndView("redirect:/user/");
    }

    @GetMapping("rating/{id}")
    public ModelAndView getUserRating(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("userRatingPage");
        try {
            User user = userService.getUserById(id);
            List<UserQuiz> userQuizList = userService.getUserRatingTable(user);
            modelAndView.addObject("userQuizList", userQuizList);
            modelAndView.addObject("user", user);
            return modelAndView;
        } catch (NoSuchElementException e) {
            return modelAndView;
        }
    }

}
