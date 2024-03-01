package com.sonu.recipeinsta.controller;

import com.sonu.recipeinsta.entity.Recipe;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    @GetMapping()
    public Recipe createRecipe(){
        return null;
    }
}
