package by.factory_accounting.controller;

import by.factory_accounting.entity.user.User;
import by.factory_accounting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController{
    @Autowired
    UserService userService;

    @GetMapping
    public ModelAndView home(Model model){

        model.addAttribute("users", userService.allUsers());
        return new ModelAndView("home");
    }

}
