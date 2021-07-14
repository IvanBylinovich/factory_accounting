package by.factory_accounting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/login")
    public ModelAndView auth(){
        return new ModelAndView("auth");
    }

}
