package com.example.backendinventory.service.impl;

import com.example.backendinventory.model.Category;
import com.example.backendinventory.repository.ICategoryRepository;
import com.example.backendinventory.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService<Category, Integer> {
    @Autowired
    private ICategoryRepository repository;

    @Override
    public List<Category> getAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public Category getById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception(new Exception("No se encontró categoría")));
    }

    @Override
    public Category save(Category category) throws Exception {
        return repository.save(category);
    }

    @Override
    public Category update(Category category, Integer id) throws Exception {
        Category category1 = repository.findById(id).orElseThrow(() -> new Exception(new Exception("No se encontró categoría")));
        return repository.save(category);
    }

    @Override
    public void delete(Integer id) throws Exception {
        repository.deleteById(id);
    }
}
