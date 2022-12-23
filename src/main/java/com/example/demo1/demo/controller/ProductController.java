package com.example.demo1.demo.controller;

import com.example.demo1.demo.dto.Mesage;
import com.example.demo1.demo.dto.ProductDto;
import com.example.demo1.demo.entity.Product;
import com.example.demo1.demo.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("/Allproduct")
    public ResponseEntity<List<Product>> list(){
        List<Product> list = productService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Product>getById(@PathVariable("id") int id){
        if(!productService.existsById(id))
            return new ResponseEntity(new Mesage("Khong ton tai"),HttpStatus.NOT_FOUND);
        Product product = productService.getOne(id).get();
        return new ResponseEntity(product,HttpStatus.OK);
    }
    @GetMapping("/detailname/{name}")
    public ResponseEntity<Product>getByName(@PathVariable("name") String name){
        if(!productService.exitstByName(name))
            return  new ResponseEntity(new Mesage("Khong ton tai"),HttpStatus.NOT_FOUND);
        Product product = productService.getByName(name).get();
        return new ResponseEntity(product,HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> create (@RequestBody ProductDto productDto){
        if(StringUtils.isBlank(productDto.getName()))
            return new ResponseEntity(new Mesage("khong duoc de trong"),HttpStatus.BAD_REQUEST);
        if(productDto.getPrice()==null || productDto.getPrice()<0)
            return new ResponseEntity(new Mesage("khong hop le"),HttpStatus.BAD_REQUEST);
        if(productService.exitstByName(productDto.getName()))
            return new ResponseEntity(new Mesage("khong hop le"),HttpStatus.BAD_REQUEST);
        Product product = new Product(productDto.getName(),productDto.getPrice());
        productService.save(product);
        return new ResponseEntity(new Mesage("thah cong"),HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id , @RequestBody ProductDto productDto){
        if(!productService.existsById(id))
            return new ResponseEntity(new Mesage("khong ton tai"),HttpStatus.NOT_FOUND);
        if(productService.exitstByName(productDto.getName()) && productService.getByName(productDto.getName()).get().getId() != id)
            return new ResponseEntity(new Mesage("da ton tai"),HttpStatus.BAD_REQUEST);
       if(StringUtils.isBlank(productDto.getName()))
           return new ResponseEntity(new Mesage("khong de trong"),HttpStatus.BAD_REQUEST);
        if(productDto.getPrice()==null || productDto.getPrice()<0)
            return new ResponseEntity(new Mesage("khong hop le"),HttpStatus.BAD_REQUEST);
        Product product = productService.getOne(id).get();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        productService.save(product);
        return new ResponseEntity(new Mesage("update thanh cong "),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable("id")int id){
        if(!productService.existsById(id))
            return new ResponseEntity(new Mesage("khong hop le"),HttpStatus.NOT_FOUND);
        productService.delete(id);
        return new ResponseEntity(new Mesage("thanh cong"),HttpStatus.OK);

    }

}
