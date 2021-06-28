package com.calopsite.demo.services;

import com.calopsite.demo.domain.entities.HistSeed;
import com.calopsite.demo.dto.UserDTO;
import com.calopsite.demo.repositories.HistSeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HistSeedService {

    @Autowired
    private VivariumService vivariumService;

    @Autowired
    private ProductService productService;

    @Autowired
    private HistSeedRepository histSeedRepository;

    @Autowired
    private UserService userService;

    public void seedVivarium(Long productId, Long vivariumId,  Float quantity){
        productService.checkProductQuantity(productId, quantity);
        vivariumService.getVivariumIfExist(vivariumId);
        UserDTO userDTO = userService.getLoggedUser();
        HistSeed histSeed = new HistSeed(null, productId, vivariumId, quantity, userService.findByID(userDTO.getId()));
        productService.updateQuantity(productId, quantity);
        histSeedRepository.save(histSeed);
    }

    public void getCost(){
        UserDTO userDTO = userService.getLoggedUser();
        List<HistSeed> histSeeds = histSeedRepository.findByUser(userService.findByID(userDTO.getId()));
        return;
    }
}
