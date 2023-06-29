package com.example.backendinventory.repository;

import com.example.backendinventory.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<Category, Integer>{


    @Query(nativeQuery = true, value = "select * from category where name like lower(?1)")
    Category getByName(String name) throws Exception;

    @Query(nativeQuery = true, value = "select * from category where id = ?1 or name like lower(concat('%',?2,'%'))")
    List<Category> getCategorieByIdOrName(Integer id, String name) throws Exception;
}
