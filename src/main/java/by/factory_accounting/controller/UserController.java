package by.factory_accounting.controller;

import by.factory_accounting.entity.user.Role;
import by.factory_accounting.entity.user.Status;
import by.factory_accounting.entity.user.User;
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

    @GetMapping("/reg")
    public ModelAndView registration(Model model){
        model.addAttribute("userDto", new UserDto());
        return new ModelAndView("reg");
    }

    @PostMapping("/reg")
    public ModelAndView saveUserReg(UserDto userDto, Model model) {
        if(userService.findUserByEmail(userDto.getUsername()).isPresent()){
            model.addAttribute("message", "this username is already occupied");
            return new ModelAndView("reg");
        }
        userService.saveUser(new User(10, userDto.getUsername(), userDto.getPassword(), Role.ADMIN, Status.USER_ACTIVE));
        model.addAttribute("message", "User created successfully");
        return new ModelAndView("reg");



    }
}
