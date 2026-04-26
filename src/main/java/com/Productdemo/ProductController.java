package com.Productdemo;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> save(@RequestBody ProductDto product){
        this.productService.save(product);
        return ResponseEntity.ok(product);
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> geAll(){
      List<Products> productlist= this.productService.getAllproducts();
        return ResponseEntity.ok(productlist);

    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> getById(@PathVariable Long id) {

        Products product = productService.getById(id);
        return ResponseEntity.ok(product);
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable ("id") long id,@RequestBody ProductDto p){
        Products updatedProduct=  this.productService.update(id, p);
        return ResponseEntity.ok(updatedProduct);
    }
    @PutMapping("/reduce/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> reduceStock(@PathVariable Long id,
                                         @RequestParam int quantity) {

        productService.reduceStock(id, quantity);
        return ResponseEntity.ok("Stock updated");
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        this.productService.delete(id);
        return ResponseEntity.ok("deleted");
    }


}
