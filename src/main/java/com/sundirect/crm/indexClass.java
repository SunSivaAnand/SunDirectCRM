package com.sundirect.crm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class indexClass {

	
	@GetMapping("/login")
    public String viewHomePage(Model model) {
		model.addAttribute("message", "Hello Thymeleaf");
        return "login";
    }
	
	

	@GetMapping("/layout")
    public String layout(Model model) {
		model.addAttribute("message", "Hello Thymeleaf");
        return "layoutLogged";
    }
	
	@GetMapping("/subscriber1/info1")
    public String subscriber(Model model) {
		model.addAttribute("message", "Hello Thymeleaf");
        return "subscriber";
    }
}
