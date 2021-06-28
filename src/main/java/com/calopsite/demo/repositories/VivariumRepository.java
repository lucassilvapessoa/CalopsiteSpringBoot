package com.calopsite.demo.repositories;

import com.calopsite.demo.domain.entities.Mutation;
import com.calopsite.demo.domain.entities.User;
import com.calopsite.demo.domain.entities.Vivarium;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface VivariumRepository extends JpaRepository<Vivarium, Long> {
    @Transactional
    List<Vivarium> findByUser(User id);
}
