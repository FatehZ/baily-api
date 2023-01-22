package com.ktxdevelopment.bailyapi.io.repo;


import com.ktxdevelopment.bailyapi.io.entity.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, PagingAndSortingRepository<ProductEntity, Long> {

}
