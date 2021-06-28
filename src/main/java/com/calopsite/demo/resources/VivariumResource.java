package com.calopsite.demo.resources;

import com.calopsite.demo.domain.entities.Mutation;
import com.calopsite.demo.domain.entities.Vivarium;
import com.calopsite.demo.domain.enums.Profile;
import com.calopsite.demo.dto.UserDTO;
import com.calopsite.demo.services.UserService;
import com.calopsite.demo.services.VivariumService;
import com.calopsite.demo.utils.exceptions.AuthorizationException;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/vivarium")
public class VivariumResource {

    @Autowired
    private VivariumService vivariumService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Vivarium>> findAll(){
        UserDTO userDTO = userService.getLoggedUser();
        if(userDTO.getProfile() != Profile.ADMIN)
            throw new AuthorizationException(HttpStatus.FORBIDDEN,"você não tem permissão para visualizar");
        List<Vivarium> list = vivariumService.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Vivarium> findByID(@PathVariable Long id){
        Vivarium obj = vivariumService.findByID(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/new")
    public void newVivarium(@RequestParam("description") @NotNull String description) {
        vivariumService.newVivarium(description);
    }

    @GetMapping(value = "/user")
    public ResponseEntity<List<Vivarium>> findByUser() {
        UserDTO userDTO = userService.getLoggedUser();
        List<Vivarium> vivariums = vivariumService.findByUser(userService.findByID(userDTO.getId()));
        return ResponseEntity.ok().body(vivariums);
    }

}
