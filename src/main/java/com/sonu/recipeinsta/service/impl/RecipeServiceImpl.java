package com.sonu.recipeinsta.service.impl;

import com.sonu.recipeinsta.entity.Recipe;
import com.sonu.recipeinsta.entity.User;
import com.sonu.recipeinsta.exception.RecipeNotFoundException;
import com.sonu.recipeinsta.repository.RecipeRepository;
import com.sonu.recipeinsta.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

            Optional<Recipe> oldRecipe = Optional.ofNullable(findRecipeById(id));
        if (oldRecipe.isPresent()) {
            Recipe updateRecipe = oldRecipe.get();
            updateRecipe.setRecipeTitle(Objects.nonNull(recipe.getRecipeTitle()) ? recipe.getRecipeTitle() : updateRecipe.getRecipeTitle());
            updateRecipe.setDescription(Objects.nonNull(recipe.getDescription()) ? recipe.getDescription() : updateRecipe.getDescription());
            updateRecipe.setRecipeImage(Objects.nonNull(recipe.getRecipeImage()) ? recipe.getRecipeImage() : updateRecipe.getRecipeImage());
            updateRecipe.setIsVegetarian(recipe.getIsVegetarian());
            return recipeRepository.save(updateRecipe);
        }
        throw new RecipeNotFoundException("Not ale to update as Recipe not found");
    }

    /**
     * @return List<Recipe>
     */
    @Override
    public List<Recipe> findAllRecipe() throws RecipeNotFoundException {
        List<Recipe> recipeList = recipeRepository.findAll();
        if(CollectionUtils.isEmpty(recipeList)){
            throw new RecipeNotFoundException("No Recipe list found");
        }
        return  recipeList;
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
        }else {
            recipe.getLikes().add(user.getId());
        }
        return recipeRepository.save(recipe);
    }
}
