package com.ltk.Benkyoukai.controller;

import com.ltk.Benkyoukai.dto.BenkyoukaiVO;
import com.ltk.Benkyoukai.service.BenkyoukaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HC {

    private final BenkyoukaiService benkyoukaiService;

    public HC(BenkyoukaiService benkyoukaiService) {
        this.benkyoukaiService = benkyoukaiService;
    }

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @PostMapping("/users/register")
    public String registerUser(BenkyoukaiVO benkyoukaiVO, Model model) {
        benkyoukaiService.registerUser(benkyoukaiVO);

        model.addAttribute("users", benkyoukaiService.getAllUsers());
        return "userlist";
    }

//    @GetMapping("/show")
//    public String getUsers(Model model) {
//        model.addAttribute("users", benkyoukaiService.getAllUsers());
//        return "userlist";
//    }
}
