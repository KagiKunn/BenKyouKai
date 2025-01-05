package com.Benkyoukai.classOne.Controller;

import com.Benkyoukai.Service.UserService;
import com.Benkyoukai.classOne.DTO.UserInfoDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("idCheck")
    public Map<String, String> idCheck(@RequestBody Map<String, String> id) {
        int userNum = userService.getCountUserById(id.get("id"));
        Map<String, String> response = new HashMap<>();
        if (userNum >= 1) {
            response.put("message", "false");
        } else {
            response.put("message", "true");
        }
        return response; // JSON 형식으로 반환
    }
}
