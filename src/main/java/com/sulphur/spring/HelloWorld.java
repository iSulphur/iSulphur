package com.sulphur.spring;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorld {
    @RequestMapping("/helloworld")
    public String hello(Model model){
    	model.addAttribute("Message", "Say HelloWorld!!!");
        return "success";
    }
}