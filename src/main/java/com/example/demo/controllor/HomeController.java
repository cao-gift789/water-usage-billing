package com.example.demo.controllor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RestController
public class HomeController {

    @GetMapping("/home")
    public String homePage() {
        return "index";  
    }
}
