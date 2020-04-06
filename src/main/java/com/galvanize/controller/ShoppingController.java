package com.galvanize.controller;

import com.galvanize.dto.ShoppingDTO;
import com.galvanize.entity.Shopping;
import com.galvanize.service.ShoppingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/shop")
public class ShoppingController {
    ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @PostMapping
    public ShoppingDTO createItem(@RequestBody Shopping shopping){
        return shoppingService.createShopping(shopping);
    }

    @GetMapping
    public List<Shopping> getAllShops(){
        return shoppingService.getAllShops();
    }


    @GetMapping("/{id}")
    public ShoppingDTO getShoppingById(@PathVariable long id){
        return shoppingService.getShoppingById(id);
    }

    @PutMapping("/name/{shopperId}")
    public Shopping updateShopperById(@PathVariable long shopperId, @RequestBody Shopping shopping){
        return shoppingService.updateShopperById(shopperId, shopping);
    }

    @DeleteMapping("/{id}")
    public boolean updateShopping(@PathVariable long id){
        return shoppingService.deleteByShopperId(id);
    }
}
