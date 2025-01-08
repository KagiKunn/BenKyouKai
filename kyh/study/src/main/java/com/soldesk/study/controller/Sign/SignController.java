package com.soldesk.study.controller.Sign;

import com.soldesk.study.dto.UserDTO;
import com.soldesk.study.service.Sign.SignUpDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sign")
public class SignController {
    @Autowired
    private SignUpDAO signDAO;

    //crypt object
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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

    @GetMapping("/doCheckId")
    @ResponseBody //json 타입으로 반환값
    public int doCheckId(@RequestParam("id") String id) {
        if(signDAO.getUserById(id) != null) {
            return 0;
        } else {
            return 1;
        }
    }

    @PostMapping("/doSignUp")
    public String doSignUp(@ModelAttribute UserDTO userDTO,
                           @RequestBody UserDTO param) {
        //pw crypt
        String encodePw = passwordEncoder.encode(param.getPw());

        //set UserDTO
        userDTO.setId(param.getId());
        userDTO.setPw(encodePw);
        userDTO.setNick(param.getNick());
        userDTO.setAge(param.getAge());

        int signUpRes = signDAO.insertUser(userDTO);
        if(signUpRes == 1) {
            System.out.println("가입 성공");
            return "result";
        } else {
            System.out.println("가입 실패");
            return "redirect:/";
        }
    }

    @PostMapping("/doSign")
    @ResponseBody
    public int doSign(@RequestBody UserDTO param,
                               HttpServletRequest request) {
        String id = param.getId();
        String pw = param.getPw();
        System.out.println("id => " + id);
        System.out.println("pw => " + pw);
        UserDTO loginUserInfo = signDAO.getUserDTOById(id);
        System.out.println("session => "+signDAO.getUserDTOById(id));

        String encodePw = passwordEncoder.encode(param.getPw());
        boolean isMatch = passwordEncoder.matches(param.getPw(), encodePw);
        System.out.println("check pw => "+encodePw);
        System.out.println("check isMatch => "+isMatch);

        //pw match
        if (isMatch && loginUserInfo != null) {
                System.out.println("성공");
                HttpSession session = request.getSession();
                session.setAttribute("loginUserInfo", loginUserInfo);

                return 1;
        } else {
            System.out.println("실패");
            return 0;
        }
    }
}