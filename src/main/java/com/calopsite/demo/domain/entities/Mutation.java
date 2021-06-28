package com.calopsite.demo.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Mutation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mutation;
    @JsonProperty("base_price")
    private Float basePrice;
    @ManyToOne
    private User user;
    private String description;
    public Mutation() {
    }

    public Mutation(Long id, String mutation, Float basePrice, User user, String description) {
        this.id = id;
        this.mutation = mutation;
        this.basePrice = basePrice;
        this.user = user;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mutation mutation = (Mutation) o;
        return id.equals(mutation.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMutation() {
        return mutation;
    }

    public void setMutation(String mutation) {
        this.mutation = mutation;
    }

    public Float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Float basePrice) {
        this.basePrice = basePrice;
    }
}
