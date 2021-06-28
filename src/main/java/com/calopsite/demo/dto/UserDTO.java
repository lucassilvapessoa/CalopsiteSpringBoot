package com.calopsite.demo.dto;

import com.calopsite.demo.domain.entities.User;
import com.calopsite.demo.domain.enums.Profile;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private Long id;
    private String email;
    private Profile profile;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.profile = user.getProfile();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Profile getProfile() {
        return profile;
    }
}
