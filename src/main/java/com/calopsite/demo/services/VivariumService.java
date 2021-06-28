package com.calopsite.demo.services;

import com.calopsite.demo.domain.entities.Mutation;
import com.calopsite.demo.domain.entities.User;
import com.calopsite.demo.domain.entities.Vivarium;
import com.calopsite.demo.dto.UserDTO;
import com.calopsite.demo.utils.exceptions.NotFoundException;
import com.calopsite.demo.repositories.VivariumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VivariumService {

    @Autowired
    private VivariumRepository vivariumRepository;

    @Autowired
    UserService userService;

    public Vivarium getVivariumIfExist(Long VivariumId){
        Optional<Vivarium> vivarium = vivariumRepository.findById(VivariumId);
        if(vivarium.isEmpty())
            throw new NotFoundException(HttpStatus.BAD_REQUEST,"A gaiola não existe");
        return vivarium.get();
    }

    public List<Vivarium> findAll(){
        return vivariumRepository.findAll();
    }

    public Vivarium findByID(Long id){
        Optional<Vivarium> obj = vivariumRepository.findById(id);
        return obj.get();
    }

    public void newVivarium(String description){
        UserDTO userDTO = userService.getLoggedUser();
        Vivarium vivarium = new Vivarium(description, userService.findByID(userDTO.getId()));
        vivariumRepository.save(vivarium);
    }

    public List<Vivarium> findByUser(User user) {
        List<Vivarium> vivariums = vivariumRepository.findByUser(user);
        return vivariums;
    }



}
