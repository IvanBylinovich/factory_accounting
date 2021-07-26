package by.factory_accounting.controller;

import by.factory_accounting.entity.Role;
import by.factory_accounting.entity.Status;
import by.factory_accounting.entity.User;
import by.factory_accounting.entity.dto.UserDto;
import by.factory_accounting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;

    //logout в Security уже создан в методе POST

    @GetMapping("/login")
    public ModelAndView auth(){
        return new ModelAndView("auth");
    }

    //@PoseMapping login уже готов у секюрити

    @GetMapping("/testUser")
    public ModelAndView testUser(){
        return new ModelAndView("testUser");
    }
    @GetMapping("/testAdmin")
    public ModelAndView testAdmin(){
        return new ModelAndView("testAdmin");
    }

    @GetMapping("/reg")
    public ModelAndView registration(Model model){
        model.addAttribute("userDto", new UserDto());
        return new ModelAndView("reg");
    }

    @PostMapping("/reg")
    public ModelAndView saveUserReg(UserDto userDto) {
        userService.saveUser(new User(10, userDto.getUsername(), userDto.getPassword(), Role.ADMIN, Status.USER_ACTIVE));
        return new ModelAndView("auth");
//        try {
//            userService.saveUser(user);
//            return new ModelAndView("auth");
//        } catch (RuntimeException e) {
//            model.addAttribute("message", e.getMessage());
//            return new ModelAndView("testRegPage");
//        }
    }
}
