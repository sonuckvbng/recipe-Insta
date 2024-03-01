package com.sonu.recipeinsta.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    @GetMapping()
    public String check(){
        return "Application up an running! Lets get started with RECIPE_INSTA Sharing";
    }
}
