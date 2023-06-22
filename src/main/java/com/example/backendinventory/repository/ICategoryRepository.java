package com.example.backendinventory.repository;

import com.example.backendinventory.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICategoryRepository extends JpaRepository<Category, Integer>{

    @Query(nativeQuery = true, value = "select * from category where name like lower(?1)")
    Category getByName(String name) throws Exception;

}
