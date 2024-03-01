package com.sonu.recipeinsta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class ControllerTest {

    @GetMapping()
    public String check(){
        return "Application up an running! Lets get started with RECIPE_INSTA Sharing";
    }
}
