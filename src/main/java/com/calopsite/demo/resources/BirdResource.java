package com.calopsite.demo.resources;

import com.calopsite.demo.domain.entities.Bird;
import com.calopsite.demo.domain.entities.Mutation;
import com.calopsite.demo.domain.entities.User;
import com.calopsite.demo.domain.entities.Vivarium;
import com.calopsite.demo.dto.UserDTO;
import com.calopsite.demo.repositories.BirdRepository;
import com.calopsite.demo.services.BirdService;
import com.calopsite.demo.services.UserService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/birds")
public class BirdResource {
    @Autowired
    private BirdService birdService;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Bird>> findAll() {
        List<Bird> list = birdService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Bird> findByID(@PathVariable Long id) {
        Bird bird = birdService.findByID(id);
        return ResponseEntity.ok().body(bird);
    }


    @PostMapping(value ="/new")
    public void newBird(
            @RequestParam("mutation") @NotNull Long mutation,
            @RequestParam("vivarium") @NotNull Long vivarium,
            @RequestParam("gender") @NotNull String gender){
        UserDTO userDTO = userService.getLoggedUser();
        birdService.newBird(mutation,vivarium, userDTO.getId(),gender);
    }

    @GetMapping(value = "/user")
    public ResponseEntity<List<Bird>> findByUser() {
        UserDTO userDTO = userService.getLoggedUser();
        List<Bird> birds = birdService.findByUser(userService.findByID(userDTO.getId()));
        return ResponseEntity.ok().body(birds);
    }


}
