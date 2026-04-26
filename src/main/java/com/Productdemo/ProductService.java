package com.Productdemo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProductService {

    private ProductRepo repo;

    public void save(ProductDto product) {
        Products newproduct=new Products();
        newproduct.setPname(product.getPname());
        newproduct.setDescription(product.getDescription());
        newproduct.setStock(product.getStock());
        newproduct.setPrice(product.getPrice());
        this.repo.save(newproduct);

    }
    public List<Products> getAllproducts() {
      List<Products> productsList=  this.repo.findAll();
      return productsList;
    }
    public Products getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Products update(long id, ProductDto p) {
            Products existing=this.repo.findById(id).orElseThrow(()-> new EntityNotFoundException("notfound"));
            existing.setPname(p.getPname());
            existing.setDescription(p.getDescription());
            existing.setStock(p.getStock());
            existing.setPrice(p.getPrice());
            return this.repo.save(existing);
        }
    public void reduceStock(Long id, int quantity) {

        Products product = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStock() < quantity) {
            throw new RuntimeException("Not enough stock");
        }

        product.setStock(product.getStock() - quantity);

        repo.save(product);
    }

    public void delete(long id) {
        Products existing=this.repo.findById(id).orElseThrow(()-> new EntityNotFoundException("notfound"));
        this.repo.delete(existing);
    }
}
