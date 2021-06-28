package com.calopsite.demo.domain.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class HistSeed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Vivarium vivarium;
    private Float quantity;
    private LocalDateTime dateTime;
    @ManyToOne
    private User user;

    public HistSeed(Long id, Long product, Long vivarium, Float quantity, User user) {
        this.id = id;
        this.product = new Product(product);
        this.vivarium = new Vivarium(vivarium);
        this.quantity = quantity;
        this.dateTime = LocalDateTime.now();
        this.user = user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public HistSeed() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Vivarium getVivarium() {
        return vivarium;
    }

    public void setVivarium(Vivarium vivarium) {
        this.vivarium = vivarium;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }
}
