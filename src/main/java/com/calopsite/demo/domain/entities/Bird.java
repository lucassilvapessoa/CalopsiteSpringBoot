package com.calopsite.demo.domain.entities;

import com.calopsite.demo.domain.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

@Entity
public class Bird implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Mutation mutation;
    @JsonProperty("father_id")
    private Long fatherId;
    @JsonProperty("mother_id")
    private Long motherId;
    @ManyToOne
    @JsonIgnore
    private Vivarium vivarium;
    private Gender gender;
    @ManyToOne
    private User user;

    public Bird() {
    }

    public Bird(Long id, Mutation mutation, Long fatherId, Long motherId, Vivarium vivarium) {
        this.id = id;
        this.mutation = mutation;
        this.fatherId = fatherId;
        this.motherId = motherId;
        this.vivarium = vivarium;
        this.gender = Gender.UNDETERMINED;
    }

    public Bird(Long id, Mutation mutation, Long fatherId, Long motherId, Vivarium vivarium, Gender gender) {
        this.id = id;
        this.mutation = mutation;
        this.fatherId = fatherId;
        this.motherId = motherId;
        this.vivarium = vivarium;
        this.gender = gender;
    }
    public Bird(Long id, Mutation mutation, Vivarium vivarium,User user) {
        this.id = id;
        this.mutation = mutation;
        this.vivarium = vivarium;
        this.gender = Gender.UNDETERMINED;
        this.user = user;
    }

    public Bird(Mutation mutation, Vivarium vivarium, User user, Gender gender) {
        this.mutation = mutation;
        this.vivarium = vivarium;
        this.user = user;
        this.gender = gender;
    }


    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mutation getMutation() {
        return mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    public Long getFatherId() {
        return fatherId;
    }

    public void setFatherId(Long idFather) {
        this.fatherId = idFather;
    }

    public Long getMotherId() {
        return motherId;
    }

    public void setIdMother(Long motherId) {
        this.motherId = motherId;
    }

    public Vivarium getVivarium() {
        return vivarium;
    }

    public void setVivarium(Vivarium vivarium) {
        this.vivarium = vivarium;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bird bird = (Bird) o;
        return id.equals(bird.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
