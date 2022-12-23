package com.example.demo1.demo.service;

import com.example.demo1.demo.entity.Product;
import com.example.demo1.demo.repository.ProductRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    @Autowired
    ProductRepostory productRepostory;

    public List<Product> list(){
        return productRepostory.findAll();
    }
    public Optional<Product> getOne(int id){
        return productRepostory.findById(id);
    }
    public Optional<Product> getByName(String name){
        return productRepostory.findByName(name);
    }
    public void save(Product product){
        productRepostory.save(product);
    }
    public void delete(int id){
        productRepostory.deleteById(id);
    }
    public boolean existsById(int id){
        return productRepostory.existsById(id);
    }
    public boolean exitstByName(String name){
        return productRepostory.existsByName(name);
    }
}
