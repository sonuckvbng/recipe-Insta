package com.sonu.recipeinsta.controller;

import com.sonu.recipeinsta.entity.Recipe;
import com.sonu.recipeinsta.entity.User;
import com.sonu.recipeinsta.exception.RecipeNotFoundException;
import com.sonu.recipeinsta.exception.UserNotFoundException;
import com.sonu.recipeinsta.service.RecipeService;
import com.sonu.recipeinsta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @PostMapping("/recipe/user/{userId}")
    public Recipe createRecipe(@RequestBody Recipe recipe, @PathVariable Long userId) throws UserNotFoundException {
        User user = userService.getUserById(userId);
        return recipeService.createRecipe(recipe, user);
    }

    @GetMapping("/recipe")
    public List<Recipe> getAllRecipe() throws RecipeNotFoundException {
        return recipeService.findAllRecipe();
    }

    @GetMapping("/recipe/{recipeId}")
    public Recipe getRecipeById(@PathVariable Long recipeId) throws RecipeNotFoundException {
        return recipeService.findRecipeById(recipeId);
    }

    @DeleteMapping("/recipe/{recipeId}")
    public String deleteRecipe(@PathVariable Long recipeId) throws RecipeNotFoundException {
        recipeService.deleteRecipeById(recipeId);
        return "Recipe deleted successfully";
    }

    @PutMapping("/recipe/{userId}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long userId) throws RecipeNotFoundException {
        return recipeService.updateRecipe(recipe, userId);
    }

    @PutMapping("/recipe/{recipeId}/user/{userId}")
    public Recipe likeRecipe(@PathVariable(name = "userId") Long userId, @PathVariable(name = "recipeId") Long recipeId)
            throws RecipeNotFoundException, UserNotFoundException {

        User user = userService.getUserById(userId);
        return recipeService.likeRecipe(recipeId, user);
    }


}
