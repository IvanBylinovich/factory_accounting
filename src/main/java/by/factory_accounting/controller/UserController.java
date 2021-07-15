package by.factory_accounting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    //logout в Security уже создан в методе POST

    @GetMapping("/login")
    public ModelAndView auth(){
        return new ModelAndView("auth");
    }

}
