package com.calopsite.demo.security;

import com.calopsite.demo.domain.enums.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserSS implements UserDetails {

    private Long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    public UserSS() {
    }

    public UserSS(Long id, String email, String password, Profile profile) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = Collections.singleton(new SimpleGrantedAuthority(profile.getDescription()));
    }

    public Long getId(){
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Boolean hasRole(Profile profile){
        return getAuthorities().contains(new SimpleGrantedAuthority(profile.getDescription()));
    }
}
