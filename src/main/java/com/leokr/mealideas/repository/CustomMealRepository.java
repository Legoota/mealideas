package com.leokr.mealideas.repository;

import com.leokr.mealideas.model.Meal;

import java.util.Date;
import java.util.List;

public interface CustomMealRepository {
    List<Meal> GetMealsBeforeLastUse(Date date_lastuse);
    List<Meal> GetMealsBeforeLastUseAndType(Date date_lastuse, long type);
    int UpdateLastUseForMeals(List<Long> ids);
}
