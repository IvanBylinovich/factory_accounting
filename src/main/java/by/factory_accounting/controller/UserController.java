package by.factory_accounting.controller;

import by.factory_accounting.entity.User;
import by.factory_accounting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.View;
import java.lang.module.ModuleFinder;

@Controller
@RequestMapping("/user")
public class UserController {

    //logout в Security уже создан в методе POST

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public ModelAndView auth(){
        return new ModelAndView("auth");
    }

    @GetMapping("/reg")
    public ModelAndView registration(Model model){
            model.addAttribute("user", new User());
            return new ModelAndView("reg");
    }

    @PostMapping("/reg")
    public ModelAndView registration(@ModelAttribute("user") User user, Model model) {
        try {
            userService.saveUser(user);
            return new ModelAndView("auth");
        } catch (RuntimeException e) {
            model.addAttribute("message", e.getMessage());
            return new ModelAndView("reg");
        }
    }

}
