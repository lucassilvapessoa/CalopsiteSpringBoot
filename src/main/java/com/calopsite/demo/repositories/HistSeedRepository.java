package com.calopsite.demo.repositories;

import com.calopsite.demo.domain.entities.HistSeed;
import com.calopsite.demo.domain.entities.Product;
import com.calopsite.demo.domain.entities.User;
import com.calopsite.demo.domain.entities.Vivarium;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface HistSeedRepository extends JpaRepository<HistSeed, Long> {
    @Transactional
    List<HistSeed> findByUser(User user);

    @Transactional
    List<HistSeed> findByVivarium(Vivarium vivarium);
}
