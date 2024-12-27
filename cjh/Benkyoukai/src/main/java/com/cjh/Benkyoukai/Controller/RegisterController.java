package com.cjh.Benkyoukai.Controller;

import com.cjh.Benkyoukai.DTO.RegisterVO;
import com.cjh.Benkyoukai.Service.RegisterService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/")
    public String Home(HttpSession session, Model model) {
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");

        if (loggedIn == null) {
            loggedIn = false;
        }
        System.out.println(loggedIn);


        model.addAttribute("loggedIn", loggedIn);
        return "Home";
    }

    @GetMapping("/register")
    public String register() {
        return "Register";
    }

    @GetMapping("/registery")
    public @ResponseBody String registery(RegisterVO registerVO ,@RequestParam String id , @RequestParam String pw , @RequestParam String name , @RequestParam int age) {
        registerVO.setId(id);
        registerVO.setPw(pw);
        registerVO.setName(name);
        registerVO.setAge(age);
        System.out.println(registerVO);
        int validCheck = registerService.validChecker(registerVO);
        System.out.println(validCheck);
        if (validCheck == 1) {

            return "same";
        }else {
            int check = registerService.registerAccount(registerVO);
            System.out.println(check);
            return "okay";
        }





    }

    @GetMapping("/show")
    public String show(Model model) {
        model.addAttribute("accounts", registerService.selectAllAccount());
        System.out.println(registerService.selectAllAccount());
        return "Registery";
    }

    @GetMapping("/login")
    public String login() {
        return "Login";
    }

    @GetMapping("/tryLogin")
    public @ResponseBody String tryLogin(@RequestParam String id, @RequestParam String pw, HttpSession session) {
        RegisterVO registerVO = new RegisterVO();
        registerVO.setId(id);
        registerVO.setPw(pw);
        System.out.println(registerVO);
        int loginCheck = registerService.loginAccount(registerVO);
        System.out.println(loginCheck);
        if (loginCheck == 0) {
            return "denied";
        } else {

            session.setAttribute("loggedIn", true);
            session.setAttribute("id", registerVO.getId());
            session.setAttribute("pw", registerVO.getPw());
            return "approved";
        }

    }

    @GetMapping("/mypage")
    public String myPage(HttpSession session, RegisterVO registerVO, Model model) {


        registerVO.setId(session.getAttribute("id").toString());
        registerVO.setPw(session.getAttribute("pw").toString());
        System.out.println(registerService.selectAccountById(registerVO));
        model.addAttribute("accountInfo", registerService.selectAccountById(registerVO));


        return "MyPage";
    }
    @GetMapping("/update")
    public String update(RegisterVO registerVO, Model model) {

        model.addAttribute("accountInfo" ,registerVO);

        return "Update";
    }


    @GetMapping("/updateInfo")
    public String updateInfo(RegisterVO registerVO, HttpSession session) {
        int check = registerService.updateAccount(registerVO);
        if (check == 1) {
            System.out.println("update success");

        }else {
            System.out.println("update fail");
        }
        session.setAttribute("pw" , registerVO.getPw());
        return "redirect:/mypage";
    };

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    @GetMapping("/delete")
    public @ResponseBody String delete(@RequestParam String id, HttpSession session) {
    RegisterVO registerVO = new RegisterVO();
    registerVO.setId(id);
   int check = registerService.deleteAccountById(registerVO);
    if (check == 1) {
        System.out.println("delete success");
        session.invalidate();
        return "deleted";
    }else {
        return "delete fail";
    }


    }
}
