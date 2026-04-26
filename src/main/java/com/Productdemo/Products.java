package com.Productdemo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Products {
    @Id
    @GeneratedValue
    private long id;
    private String pname;
    private String description;
    private double price;
    private int stock;
}
