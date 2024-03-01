package com.sonu.recipeinsta.service.impl;

import com.sonu.recipeinsta.entity.Recipe;
import com.sonu.recipeinsta.entity.User;
import com.sonu.recipeinsta.exception.RecipeNotFoundException;
import com.sonu.recipeinsta.repository.RecipeRepository;
import com.sonu.recipeinsta.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    /**
     * @param recipe
     * @param user
     * @return Recipe
     */
    @Override
    public Recipe createRecipe(Recipe recipe, User user) {

        return recipeRepository.save(Recipe.builder().recipeTitle(recipe.getRecipeTitle())
                .recipeImage(recipe.getRecipeImage())
                .description(recipe.getDescription())
                .isVegetarian(recipe.getIsVegetarian())
                .likes(recipe.getLikes())
                .user(user)
                .build());
    }

    /**
     * @param id
     * @return Recipe
     */
    @Override
    public Recipe findRecipeById(Long id) throws RecipeNotFoundException {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            return recipe.get();
        }
        throw new RecipeNotFoundException("Recipe not found with id: " + id);
    }

    /**
     * @param id
     */
    @Override
    public void deleteRecipeById(Long id) throws RecipeNotFoundException {
        findRecipeById(id);
        recipeRepository.deleteById(id);
    }

    /**
     * @param recipe
     * @param id
     * @return Recipe
     */
    @Override
    public Recipe updateRecipe(Recipe recipe, Long id) throws RecipeNotFoundException {
        Recipe oldRecipe = findRecipeById(id);
        if (Objects.nonNull(recipe)) {
            oldRecipe.setRecipeTitle(Objects.nonNull(recipe.getRecipeTitle()) ? recipe.getRecipeTitle() : oldRecipe.getRecipeTitle());
            oldRecipe.setDescription(Objects.nonNull(recipe.getDescription()) ? recipe.getDescription() : oldRecipe.getDescription());
            oldRecipe.setRecipeImage(Objects.nonNull(recipe.getRecipeImage()) ? recipe.getRecipeImage() : oldRecipe.getRecipeImage());
            oldRecipe.setIsVegetarian(recipe.getIsVegetarian());
        }
        return recipeRepository.save(oldRecipe);
    }

    /**
     * @return List<Recipe>
     */
    @Override
    public List<Recipe> findAllRecipe() {
        return recipeRepository.findAll();
    }

    /**
     * @param recipeId
     * @param user
     * @return Recipe
     */
    @Override
    public Recipe likeRecipe(Long recipeId, User user) throws RecipeNotFoundException {
        Recipe recipe = findRecipeById(recipeId);
        if(recipe.getLikes().contains(user.getId())){
            recipe.getLikes().remove(user.getId());
        }
        recipe.getLikes().add(user.getId());
        return recipe;
    }
}
