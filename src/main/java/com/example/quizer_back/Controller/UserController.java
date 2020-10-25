package com.example.quizer_back.Controller;


import com.example.quizer_back.Model.User;
import com.example.quizer_back.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/user")
public class UserController {


    private UserService userService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView getUserList(){
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
    public ModelAndView addQuizPage(Model model){
        model.addAttribute(new User());
        return new ModelAndView("addUserPage");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteQuiz(@PathVariable int id){
        userService.deleteUserById(id);
        return new ModelAndView("redirect:/user/");
    }

}
