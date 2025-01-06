package com.kyj.Benkyoukai.S001.Controller;

import com.kyj.Benkyoukai.S001.DTO.RegisterDTO;
import com.kyj.Benkyoukai.S001.Service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    // 회원가입 페이지로 이동
    @GetMapping("/register")   // url
    public String register() {
        return "Register";  // html
    }

    // 계정 등록 처리
    @PostMapping("/toRegister")
    public String toRegister(RegisterDTO registerDTO) {

        int checkRegister = registerService.registerAccount(registerDTO);
//        System.out.println(checkRegister);
        if (checkRegister == 1) {
            return "redirect:/showAccounts";
        } else {
//            return "Register";
            return "redirect:/register?error=true";
        }

    }

    // 등록된 계정 목록 조회
    @GetMapping("/showAccounts")
    public String showAccounts(RegisterDTO registerDTO, Model model) {
        List<RegisterDTO> checkRegister = registerService.selectAccounts(registerDTO);
//        System.out.println(checkRegister);
        model.addAttribute("checkRegister", checkRegister);

        return "ShowAccounts";
    }

    // ID 중복 검사 API (RESTful 방식)
    @GetMapping("/checkId/{id}")
    @ResponseBody
    public Map<String, Boolean> checkId(@PathVariable String id) {
        System.out.println("Received ID: " + id); // 디버깅용 출력
        boolean isAvailable = registerService.checkDuplicateId(id) == 0;
        System.out.println("Is Available: " + isAvailable); // 디버깅용 출력

        Map<String, Boolean> response = new HashMap<>();
        response.put("available", isAvailable);
        return response;
    }

}
