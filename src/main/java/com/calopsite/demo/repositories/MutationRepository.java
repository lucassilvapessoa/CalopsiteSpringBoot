package com.calopsite.demo.repositories;

import com.calopsite.demo.domain.entities.Mutation;
import com.calopsite.demo.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface MutationRepository extends JpaRepository<Mutation, Long> {
    @Transactional
    List<Mutation> findByUser(User id);
}
