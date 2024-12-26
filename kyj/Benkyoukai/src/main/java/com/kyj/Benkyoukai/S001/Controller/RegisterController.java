package com.kyj.Benkyoukai.S001.Controller;

import com.kyj.Benkyoukai.S001.DTO.RegisterDTO;
import com.kyj.Benkyoukai.S001.Service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping ("/register")   // url
    public String register(){
        return "Register";  // html
    }

    @PostMapping ("/toRegister")
    public String toRegister(RegisterDTO registerDTO){
//        System.out.println(registerDTO.getId());
//        System.out.println(registerDTO.getPw());
//        System.out.println(registerDTO.getName());
//        System.out.println(registerDTO.getAge());
        
        int checkRegister = registerService.registerAccount(registerDTO);
        System.out.println(checkRegister);

        return "redirect:/showAccounts";
    }

    @GetMapping ("/showAccounts")
    public String showAccounts(RegisterDTO registerDTO, Model model){
        List<RegisterDTO> checkRegister = registerService.selectAccounts(registerDTO);
        System.out.println(checkRegister);

        model.addAttribute("checkRegister",checkRegister);

        return "ShowAccounts";
    }

}
