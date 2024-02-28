package com.sonu.recipeinsta.repository;

import com.sonu.recipeinsta.entity.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}
