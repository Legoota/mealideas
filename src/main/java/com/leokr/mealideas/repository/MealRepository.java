package com.leokr.mealideas.repository;

import com.leokr.mealideas.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long>, CustomMealRepository {
    List<Meal> findByType(long type);
}
