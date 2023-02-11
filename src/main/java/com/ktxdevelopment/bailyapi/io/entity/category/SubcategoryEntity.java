package com.ktxdevelopment.bailyapi.io.entity.category;

import com.ktxdevelopment.bailyapi.io.entity.product.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "subcategories")
public class SubcategoryEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String categoryId;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "categories_categoryId")
    private CategoryEntity parentCategory;


    @OneToMany(mappedBy = "subcategory",cascade = CascadeType.ALL)
    private List<ProductEntity> products;
}
