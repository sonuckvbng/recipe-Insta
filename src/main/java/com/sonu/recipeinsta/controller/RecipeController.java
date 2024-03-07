package com.sonu.recipeinsta.controller;

import com.sonu.recipeinsta.entity.Recipe;
import com.sonu.recipeinsta.entity.User;
import com.sonu.recipeinsta.exception.RecipeNotFoundException;
import com.sonu.recipeinsta.exception.UserNotFoundException;
import com.sonu.recipeinsta.service.RecipeService;
import com.sonu.recipeinsta.service.UserService;
import jakarta.ws.rs.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
//@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @PostMapping("/recipe")
    public Recipe createRecipe(@RequestBody Recipe recipe, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
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
        if (Objects.nonNull(recipe)) {
            return recipeService.updateRecipe(recipe, userId);
        }
        throw new BadRequestException("Please provide Correct data");
    }

    @PutMapping("/recipe/like/{recipeId}")
    public Recipe likeRecipe(@RequestHeader("Authorization") String jwt, @PathVariable(name = "recipeId") Long recipeId)
            throws Exception {
        User user = userService.findUserByJwt(jwt);
        return recipeService.likeRecipe(recipeId, user);
    }


}
