package com.leokr.mealideas.controller;

import com.leokr.mealideas.model.Meal;
import com.leokr.mealideas.model.Type;
import com.leokr.mealideas.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class TypeController {

    @Autowired
    TypeRepository typeRepository;

    @GetMapping("/types")
    public ResponseEntity<List<Type>> getAll() {
        try {

            List<Type> types = new ArrayList<Type>(typeRepository.findAll());

            if(types.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(types, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/types")
    public ResponseEntity<Type> createType(@RequestBody Type type) {
        try {
            Type _type = typeRepository
                    .save(new Type(type.getName()));
            return new ResponseEntity<>(_type, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/types/{id}")
    public ResponseEntity<Type> updateType(@PathVariable("id") long id, @RequestBody Type type) {
        Optional<Type> dbType = typeRepository.findById(id);

        if (dbType.isPresent()) {
            Type _type = dbType.get();
            _type.setName(type.getName());
            return new ResponseEntity<>(typeRepository.save(_type), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/types/{id}")
    public ResponseEntity<HttpStatus> deleteType(@PathVariable("id") long id) {
        try {
            typeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/types")
    public ResponseEntity<HttpStatus> deleteAllTypes() {
        try {
            typeRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
