package com.example.backendinventory.controller;

import com.example.backendinventory.model.Category;
import com.example.backendinventory.response.GenericoResponse;
import com.example.backendinventory.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;


    @GetMapping
    public ResponseEntity<GenericoResponse<Category>> getCategories() throws Exception {
        List<Category> lista = categoryService.getAll();
        GenericoResponse<Category> genericoResponse = new GenericoResponse("GET", Integer.toString(HttpStatus.OK.value()), LocalDateTime.now().toString(), lista);
        return new ResponseEntity<>(genericoResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericoResponse<Category>> getCategoryById(@PathVariable(name = "id") Integer id) throws Exception {
        Category category = categoryService.getById(id);
        GenericoResponse<Category> genericoResponse =
                new GenericoResponse<>("GET", Integer.toString(HttpStatus.OK.value()), LocalDateTime.now().toString(), List.of(category));
        return new ResponseEntity<>(genericoResponse, HttpStatus.OK);

    }

}
