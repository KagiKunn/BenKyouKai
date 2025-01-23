package com.cjh.Benkyoukai.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VueController {

    @GetMapping("/vue")
    public String vue() {
        return "VueTest";
    }
}
