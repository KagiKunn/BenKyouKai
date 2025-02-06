package com.kkh.Vue;

import com.kkh.Vue.Service.HomeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/")
    public String index() {
        System.out.println(homeService.findAllUser());
        return "index.html";
    }
}