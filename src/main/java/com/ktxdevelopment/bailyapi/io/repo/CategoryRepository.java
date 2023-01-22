package com.ktxdevelopment.bailyapi.io.repo;

import com.ktxdevelopment.bailyapi.io.entity.category.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    CategoryEntity findByCategoryId(String categoryId);

    CategoryEntity findByName(String name);
}
