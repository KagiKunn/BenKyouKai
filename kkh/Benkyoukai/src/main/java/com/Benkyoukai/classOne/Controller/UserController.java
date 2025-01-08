package com.Benkyoukai.classOne.Controller;

import com.Benkyoukai.Service.UserService;
import com.Benkyoukai.classOne.DTO.UserInfoDto;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) { // 생성자 주입
        this.userService = userService;
    }

    @GetMapping("/study")
    public String Study(Model model) {
        return "home";
    }

    @GetMapping("/login")
    public String Login(Model model) {
        return "login";
    }

    @PostMapping("loginProcess")
    public String LoginProcess(@Param("id") String id, @Param("pw") String pw, Model model, HttpSession session) {
        if(userService.getUserAuthenticate(id,pw)>0){
            session.setAttribute("user",id);
            return "redirect:/userinfopanel";

        }
        else{
            return "redirect:/study";
        }
    }

    @GetMapping("/registry")
    public String RegistryPage(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "Registry";
    }

    @GetMapping("/register")
    public String Register(Model model, UserInfoDto userInfo) {
        userService.Registry(userInfo);
        return "redirect:study";
    }

    @GetMapping("userinfopanel")
    public String UserinfoPanel(Model model,HttpSession session) {
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("loginUser", userService.getUserById((String)session.getAttribute("userid")));
        return "userInfoPanel";
    }

    @GetMapping("/deleteuser")
    public String DeleteUser(@Param("id") String id) {
        if(userService.deleteUserById(id)>0){
            System.out.println("deleted!");
        }
        return "redirect:registry";
    }

    @GetMapping("/updateuser")
    public String UpdateUserPage(@Param("id") String id, Model model) {
        model.addAttribute("id", id);
        return "Update";
    }

    @GetMapping("/userupdater")
    public String UpdateUser(UserInfoDto userInfo) {
        if(userService.updateUserById(userInfo)==1){
            System.out.println("updated!");
        }
        return "redirect:userinfopanel";
    }
}

