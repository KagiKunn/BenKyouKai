package com.Benkyoukai.classOne;

import com.Benkyoukai.Service.UserService;
import com.Benkyoukai.classOne.DTO.UserInfoDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Registry {

    private final UserService userService;

    public Registry(UserService userService) { // 생성자 주입
        this.userService = userService;
    }

    @GetMapping("/registry")
    public String RegistryPage(Model model) {
        userService.getAllUser();
        model.addAttribute("users", userService.getAllUser());
        return "Registry";
    }

    @GetMapping("/register")
    public String Register(Model model, UserInfoDto userInfo) {
        userService.Registry(userInfo);
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("loginUser", userService.getUserById(userInfo.getId()));
        return "Register";
    }
}

