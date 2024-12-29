package com.soldesk.study.controller.mypage;

import com.soldesk.study.dto.UserDTO;
import com.soldesk.study.service.MyPage.MyPageDAO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
    @Autowired
    private MyPageDAO myPageDAO;

    //delete
    @PostMapping("/delete")
    @ResponseBody
    public int delete(@RequestBody UserDTO param) {
        myPageDAO.deleteUser(param.getId());
        return 1;
    }

    //update
    @PostMapping("/update")
    @ResponseBody
    public int update(@RequestBody UserDTO param, HttpSession session) {
        //crypt object
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //check param
        System.out.println("check param => "+param.getId());
        System.out.println("check param => "+param.getPw());
        System.out.println("check param => "+param.getNick());
        //pw crypt
        String encodePw = passwordEncoder.encode(param.getPw());
        //setting DTO
        param.setPw(encodePw);
        UserDTO login_user = (UserDTO) session.getAttribute("loginUserInfo");
        String login_id = login_user.getId();
        myPageDAO.updateUser(param.getId(), param.getPw(), param.getNick(), login_id);
        return 1;
    }

    //update page
    @GetMapping("/updatepage")
    public String updatepage(HttpSession session, Model model) {
        UserDTO login_user = (UserDTO) session.getAttribute("loginUserInfo");
        model.addAttribute("login_user", login_user);
        return "mypage/updatePage";
    }
}
