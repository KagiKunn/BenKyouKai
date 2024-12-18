package com.cjh.Benkyoukai.Controller;

import com.cjh.Benkyoukai.DTO.RegisterVO;
import com.cjh.Benkyoukai.Service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/register")
    public String register(){
        return "Register";
    }

    @GetMapping("/registery")
    public String registery(RegisterVO registerVO) {
      int check =  registerService.registerAccount(registerVO);
        System.out.println(check);


        return "redirect:/show";
    }
    @GetMapping("/show")
    public String show(Model model) {
        model.addAttribute("accounts", registerService.selectAllAccount());
        System.out.println(registerService.selectAllAccount());
        return "Registery";
    }

}
