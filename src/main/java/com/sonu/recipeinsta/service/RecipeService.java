package com.sonu.recipeinsta.service;

import com.sonu.recipeinsta.entity.Recipe;
import com.sonu.recipeinsta.entity.User;
import com.sonu.recipeinsta.exception.RecipeNotFoundException;

import java.util.List;

public interface RecipeService {

    public Recipe createRecipe(Recipe recipe, User user);
    public Recipe findRecipeById(Long id) throws RecipeNotFoundException;
    public void deleteRecipeById(Long id) throws RecipeNotFoundException;
    public Recipe updateRecipe(Recipe recipe, Long id) throws RecipeNotFoundException;
    public List<Recipe> findAllRecipe();
    public Recipe likeRecipe(Long recipeId, User user) throws RecipeNotFoundException;

}
