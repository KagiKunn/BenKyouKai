package com.Benkyoukai.classOne;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Test01 {
    @GetMapping("Benkyoukai")
    public String Test(){
        System.out.println("GoodBye Korea");
        System.out.println("Hello Japan");
        return "Benkyoukai01";
    }
}
