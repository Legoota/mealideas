package com.leokr.mealideas.controller;

import com.leokr.mealideas.model.Meal;
import com.leokr.mealideas.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MealController {

    @Autowired
    MealRepository mealRepository;

    @GetMapping("meals")
    public ResponseEntity<List<Meal>> getAll() {
        try {
            List<Meal> meals = new ArrayList<Meal>();

            mealRepository.findAll().forEach(meals::add);

            if(meals.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(meals, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("meals/type/{type}")
    public ResponseEntity<List<Meal>> findByType(@PathVariable("type")long type) {
        try {
            List<Meal> meals = mealRepository.findByType(type);

            if (meals.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(meals, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("meals/{id}")
    public ResponseEntity<Meal> getMealById(@PathVariable("id")long id) {
        Optional<Meal> meal = mealRepository.findById(id);

        if (meal.isPresent()) {
            return new ResponseEntity<>(meal.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("meals/getAllBefore")
    public ResponseEntity<List<Meal>> getAllBefore(@RequestBody Date date) {
        List<Meal> meals = mealRepository.GetMealsBeforeLastUse(date);
        return new ResponseEntity<>(meals, HttpStatus.OK);
    }

    @GetMapping("meals/getAllBeforeForType/{type}")
    public ResponseEntity<List<Meal>> getAllBeforeForType(@RequestBody Date date, @PathVariable("type")long type) {
        List<Meal> meals = mealRepository.GetMealsBeforeLastUseAndType(date, type);
        return new ResponseEntity<>(meals, HttpStatus.OK);
    }

    @PostMapping("/meals")
    public ResponseEntity<Meal> createMeal(@RequestBody Meal meal) {
        try {
            Meal _meal = mealRepository
                    .save(new Meal(meal.getName(), meal.getDate_add(), meal.getDate_lastuse(), meal.getCounter(), meal.getType()));
            return new ResponseEntity<>(_meal, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/meals/{id}")
    public ResponseEntity<Meal> updateMeal(@PathVariable("id") long id, @RequestBody Meal meal) {
        Optional<Meal> dbMeal = mealRepository.findById(id);

        if (dbMeal.isPresent()) {
            Meal _meal = dbMeal.get();
            _meal.setName(meal.getName());
            _meal.setDate_add(meal.getDate_add());
            _meal.setDate_lastuse(meal.getDate_lastuse());
            _meal.setCounter(meal.getCounter());
            _meal.setType(meal.getType());
            return new ResponseEntity<>(mealRepository.save(_meal), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/meals/updateuse")
    public ResponseEntity<Integer> UpdateLastUseForMeals(@RequestBody List<Long> ids) {
        int changed = mealRepository.UpdateLastUseForMeals(ids);
        return new ResponseEntity<>(changed, HttpStatus.OK);
    }

    @DeleteMapping("/meals/{id}")
    public ResponseEntity<HttpStatus> deleteMeal(@PathVariable("id") long id) {
        try {
            mealRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/meals")
    public ResponseEntity<HttpStatus> deleteAllMeals() {
        try {
            mealRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
