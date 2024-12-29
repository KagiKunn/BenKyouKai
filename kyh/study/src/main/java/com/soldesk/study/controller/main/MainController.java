package com.soldesk.study.controller.main;

import com.soldesk.study.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {
    @GetMapping("/")
    public String goMain(HttpSession session, Model model) {
        UserDTO login_user = (UserDTO) session.getAttribute("loginUserInfo");
        System.out.println("check user => "+login_user);
        model.addAttribute("login_user", login_user);
        return "main/main";
    }
}
