package com.sonu.recipeinsta.service.impl;

import com.sonu.recipeinsta.repository.RecipeRepository;
import com.sonu.recipeinsta.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    RecipeRepository recipeRepository;
}
