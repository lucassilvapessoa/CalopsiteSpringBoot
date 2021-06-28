package com.calopsite.demo.domain.entities;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Vivarium implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("bird_child")
    private Integer birdChild;
    private String description;
    @OneToMany(mappedBy = "vivarium", cascade = CascadeType.REMOVE)
    private List<Bird> bird;
    @ManyToOne()
    private User user;

    public Vivarium() {
    }

    public Vivarium(Long id) {
        this.id = id;
    }

    public Vivarium(Long id, Integer birdChild, String description, Long user) {
        this.id = id;
        this.birdChild = birdChild;
        this.description = description;
        this.user = new User(user);
    }

    public Vivarium(String description, User user){
        this.user = user;
        this.description = description;
    }

    public List<Bird> getBird() {
        return bird;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBirdChild() {
        return birdChild;
    }

    public void setBirdChild(Integer bChild) {
        this.birdChild = bChild;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
