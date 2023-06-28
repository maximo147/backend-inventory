package com.example.backendinventory.service.impl;

import com.example.backendinventory.model.Category;
import com.example.backendinventory.repository.ICategoryRepository;
import com.example.backendinventory.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService<Category, Integer> {
    @Autowired
    private ICategoryRepository repository;

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public List<Category> getAll() throws Exception {
        return repository.findAll();
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public Category getById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("No se encontró categoría"));
    }

    /**
     *
     * @param category
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public Category save(Category category) throws Exception {
        return repository.save(category);
    }

    /**
     *
     * @param category
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public Category update(Category category, Integer id) throws Exception {
        Category category1 = repository.findById(id).orElseThrow(() -> new Exception("No se encontró categoría"));
        return repository.save(category);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public Category delete(Integer id) throws Exception {
        Category category1 = repository.findById(id).orElseThrow(() -> new Exception("No se encontró categoría"));
        repository.deleteById(id);

        return category1;
    }

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public Category getByName(String name) throws Exception {
        return repository.getByName(name);
    }

    /**
     *
     * @param id
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public List<Category> getCategorieByIdOrName(Integer id, String name) throws Exception {
        return repository.getCategorieByIdOrName(id, name);
    }
}
