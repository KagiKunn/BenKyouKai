package com.ltk.Benkyoukai.controller;

import com.ltk.Benkyoukai.dto.BenkyoukaiVO;
import com.ltk.Benkyoukai.service.BenkyoukaiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HC {

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @PostMapping("/users/register")
    public String registerUser(BenkyoukaiVO benkyoukaiVO) {
        BenkyoukaiService.registerUser(benkyoukaiVO);
        return "redirect:/show";
    }

    @GetMapping("/list")
    public String getUsers(Model model) {
        model.addAttribute("users", BenkyoukaiService.getAllUsers());
        return "userlist";
    }
}
