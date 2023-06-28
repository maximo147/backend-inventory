package com.example.backendinventory.service;

import java.util.List;

public interface IProductService<T, ID> {
    List<T> getAll() throws Exception;
    T getById(ID id) throws Exception;
    T save(T t) throws Exception;
    T update(T t, ID id) throws Exception;
    T delete(ID id) throws Exception;

    T getByName(String name) throws Exception;

    List<T> getProductByIdOrName(Integer id, String name) throws Exception;
}
