package com.Productdemo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProductDto {
    private String pname;
    private String description;
    private double price;
    private int stock;
}
