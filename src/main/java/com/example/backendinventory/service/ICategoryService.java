package com.example.backendinventory.service;

import com.example.backendinventory.model.Category;

import java.util.List;

public interface ICategoryService<T, ID> {
    List<T> getAll() throws Exception;
    T getById(ID id) throws Exception;
    T save(T t) throws Exception;
    T update(T t, ID id) throws Exception;
    void delete(ID id) throws Exception;
}
