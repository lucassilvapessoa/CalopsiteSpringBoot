package com.calopsite.demo.repositories;

import com.calopsite.demo.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    User findByEmail(String email);


}
