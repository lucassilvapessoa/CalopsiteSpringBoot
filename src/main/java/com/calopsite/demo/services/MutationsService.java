package com.calopsite.demo.services;

import com.calopsite.demo.domain.entities.Mutation;
import com.calopsite.demo.domain.entities.User;
import com.calopsite.demo.dto.UserDTO;
import com.calopsite.demo.repositories.MutationRepository;
import com.calopsite.demo.utils.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class MutationsService {
    @Autowired
    private MutationRepository mutRepository;

    @Autowired
    private UserService userService;

    public List<Mutation> findAll() {
        return mutRepository.findAll();
    }

    @GetMapping
    public Mutation findByID(long id) {
        Optional<Mutation> mutation = mutRepository.findById(id);
        if (mutation.isEmpty())
            throw new NotFoundException(HttpStatus.BAD_REQUEST, "A Mutação não existe!");
        return mutation.get();
    }

    public List<Mutation> findByUser(User user) {
        List<Mutation> mutations = mutRepository.findByUser(user);
        return mutations;
    }

    public void newMutation(String mutationName, Float basePrice, String description){
        UserDTO userDTO = userService.getLoggedUser();
        Mutation mutation = new Mutation(null,mutationName, basePrice,userService.findByID(userDTO.getId()),description);
        mutRepository.save(mutation);
    }
}

