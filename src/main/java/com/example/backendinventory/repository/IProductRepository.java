package com.example.backendinventory.repository;

import com.example.backendinventory.model.Category;
import com.example.backendinventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Integer> {
    @Query(nativeQuery = true, value = "select * from product where name like lower(?1)")
    Product getByName(String name) throws Exception;

    @Query(nativeQuery = true, value = "select * from product where id = ?1 or name like lower(concat('%',?2,'%'))")
    List<Product> getProductByIdOrName(Integer id, String name) throws Exception;
}
