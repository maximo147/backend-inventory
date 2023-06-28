package com.example.backendinventory.service.impl;

import com.example.backendinventory.model.Category;
import com.example.backendinventory.model.Product;
import com.example.backendinventory.repository.IProductRepository;
import com.example.backendinventory.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService<Product, Integer> {
    @Autowired
    private IProductRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAll() throws Exception {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product getById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("No se encontró el producto"));
    }

    @Override
    @Transactional
    public Product save(Product product) throws Exception {
        return repository.save(product);
    }

    @Override
    @Transactional
    public Product update(Product product, Integer id) throws Exception {
        Product productAux = repository.findById(id).orElseThrow(() -> new Exception("No se encontró producto"));
        return repository.save(product);
    }

    @Override
    @Transactional
    public Product delete(Integer id) throws Exception {
        Product product = repository.findById(id).orElseThrow(() -> new Exception("No se encontró producto"));
        repository.deleteById(id);
        return product;
    }

    @Override
    @Transactional(readOnly = true)
    public Product getByName(String name) throws Exception {
        return repository.getByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductByIdOrName(Integer id, String name) throws Exception {
        return repository.getProductByIdOrName(id, name);
    }
}
