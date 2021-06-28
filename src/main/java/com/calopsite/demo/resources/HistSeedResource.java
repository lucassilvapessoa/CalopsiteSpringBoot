package com.calopsite.demo.resources;

import com.calopsite.demo.services.HistSeedService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/seed")
public class HistSeedResource {

    @Autowired
    private HistSeedService histSeedService;

    @PostMapping("/{vivarium_id}/{product_id}")
    public void seedVivarium(@PathVariable("vivarium_id") @NotNull Long vivariumId,
                             @PathVariable("product_id") @NotNull Long productId,
                             @RequestParam("quantity") @NotNull Float quantity){
        histSeedService.seedVivarium(productId, vivariumId, quantity);
    }

    @GetMapping(value = "/summarize")
    public void summarizeVivarium(){
        histSeedService.getCost();

    }


}
