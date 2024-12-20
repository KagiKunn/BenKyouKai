package com.soldesk.study.controller.Sign;

import com.soldesk.study.dto.UserDTO;
import com.soldesk.study.service.Sign.SignUpDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sign")
public class SignController {
    @Autowired
    private SignUpDAO signDAO;

    @GetMapping("/goSignUp")
    public String goSignUp() {
        return "sign/signUp";
    }

    @GetMapping("/result")
    public String goSignResult(Model model) {
        List<UserDTO> userDTOs = signDAO.getAllUsers();
        model.addAttribute("users", userDTOs);
        return "result";
    }

    @PostMapping("/doSignUp")
    public String doSignUp(@ModelAttribute UserDTO userDTO,
                           @RequestBody UserDTO param,
                           Model model) {
        System.out.println("포스트다.");
        System.out.println("check request body => "+param.getId());
        System.out.println("check request body => "+param.getPw());
        System.out.println("check request body => "+param.getNick());
        System.out.println("check request body => "+param.getAge());
        //파라미터 받는것부터
        userDTO.setId(param.getId());
        userDTO.setPw(param.getPw());
        userDTO.setNick(param.getNick());
        userDTO.setAge(param.getAge());

        int signUpRes = signDAO.insertUser(userDTO);
        if(signUpRes == 1) {
            System.out.println("가입 성공");
            return "result";
        } else {
            System.out.println("가입 실패");
            return "sign/signUp";
        }
    }
}