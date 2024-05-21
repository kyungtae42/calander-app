package com.sparta.calanderapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MappingController {
    @GetMapping("/")
    public String getIndex(){
        return "index";
    }
    @GetMapping("/{id}")
    public String getDetail(@PathVariable Long id){
        return "detail";
    }
    @GetMapping("/update/{id}")
    public String getUpdate(@PathVariable Long id){
        return "update";
    }
}
