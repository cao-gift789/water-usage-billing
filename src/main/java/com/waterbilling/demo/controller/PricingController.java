package com.waterbilling.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller("pricing")
public class PricingController {
	@GetMapping("/home")
    public String homePage() {
        return "home";  
    }
}
