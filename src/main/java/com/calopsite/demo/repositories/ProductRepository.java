package com.calopsite.demo.repositories;

import com.calopsite.demo.domain.entities.Mutation;
import com.calopsite.demo.domain.entities.Product;
import com.calopsite.demo.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Transactional
    List<Product> findByUser(Long id);
}
