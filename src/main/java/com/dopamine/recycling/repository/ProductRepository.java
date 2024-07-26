package com.dopamine.recycling.repository;

import com.dopamine.recycling.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("select p from Product p where p.isDenied = TRUE")
    List<Product> findProductsAvailable();

    @Modifying
    @Query("update Product p set p.name = :name, p.price = :price, p.content = :content, p.imagePath = :imagePath, p.updatedAt = :now where p.id = :id")
    void updateProductById(@Param("id") long id, @Param("name") String name, @Param("price") long price, @Param("content") String content, @Param("imagePath") String imagePath, @Param("now") LocalDateTime now);
}
