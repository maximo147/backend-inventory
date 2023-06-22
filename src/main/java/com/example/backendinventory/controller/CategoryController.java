package com.example.backendinventory.controller;

import com.example.backendinventory.model.Category;
import com.example.backendinventory.response.GenericoResponse;
import com.example.backendinventory.service.impl.CategoryServiceImpl;
import com.example.backendinventory.util.ConverterString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        GenericoResponse<Category> genericoResponse =
                new GenericoResponse("GET", Integer.toString(HttpStatus.OK.value()), LocalDateTime.now().toString(), lista);
        return new ResponseEntity<>(genericoResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericoResponse<Category>> getCategoryById(@PathVariable(name = "id") Integer id) throws Exception {
        Category category = categoryService.getById(id);
        GenericoResponse<Category> genericoResponse =
                new GenericoResponse<>("GET", Integer.toString(HttpStatus.OK.value()), LocalDateTime.now().toString(), List.of(category));
        return new ResponseEntity<>(genericoResponse, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<GenericoResponse<Category>> postCategory(@RequestBody Category category) throws Exception {
        if(category == null){
            throw new Exception("No se encontró objeto");
        }
        // Convertir a Lower los campos en String
        ConverterString<Category> converterLower = new ConverterString<>();
        Category categoryLower =  converterLower.ConverterLower(category);

        // Verificar si no se repite un ´name´
        if(categoryService.getByName(category.getName()) != null) {
            throw new Exception("El nombre ya existe");
        }

        Category auxCategory = categoryService.save(categoryLower);
        if(auxCategory == null){
            throw new Exception("No se pudo guardar objeto");
        }
        GenericoResponse<Category> genericoResponse =
                new GenericoResponse<>("POST", Integer.toString(HttpStatus.CREATED.value()), LocalDateTime.now().toString(), List.of(category));
        return new ResponseEntity<>(genericoResponse, HttpStatus.CREATED);
    }

}
