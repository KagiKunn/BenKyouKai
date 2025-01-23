package com.soldesk.study.controller.main;

import com.soldesk.study.dto.UserDTO;
import com.soldesk.study.service.Sign.SignUpDAO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {
    @Autowired
    private SignUpDAO signDAO;

    @GetMapping("/")
    public String goMain(HttpSession session, Model model) {
        UserDTO login_user = (UserDTO) session.getAttribute("loginUserInfo");
        if (login_user == null) {
            return "redirect:/"; // 세션에 정보가 없으면 로그인 페이지로 리다이렉트
        }
        model.addAttribute("login_user", login_user);
        return "main/main";
    }
}
