package com.ktxdevelopment.bailyapi.io.repo;

import com.ktxdevelopment.bailyapi.io.entity.category.SubcategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends JpaRepository<SubcategoryEntity, Long> {
    SubcategoryEntity findByCategoryId(String categoryId);
}
