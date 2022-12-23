package com.example.demo1.demo.repository;

import com.example.demo1.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepostory extends JpaRepository<Product,Integer> {
    Optional<Product> findByName(String name);
    boolean existsByName(String name);
}

