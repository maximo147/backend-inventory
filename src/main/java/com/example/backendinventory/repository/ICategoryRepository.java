package com.example.backendinventory.repository;

import com.example.backendinventory.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Integer>{
}
