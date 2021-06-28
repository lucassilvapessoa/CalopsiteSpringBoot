package com.calopsite.demo.domain.entities;

import com.calopsite.demo.domain.enums.ProductType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Float quantity;
    private Long user;
    private Float price;
    private ProductType productType;
    public Product() {
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Product(Long id) {
        this.id = id;
    }

    public Product(Long id, String name, Float quantity, Long user, ProductType productType,Float price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.user = user;
        this.productType = productType;
        this.price = price;
    }


    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
