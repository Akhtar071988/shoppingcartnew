package com.galvanize.service;

import com.galvanize.dto.ShoppingDTO;
import com.galvanize.entity.Shopping;
import com.galvanize.repository.ShoppingRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShoppingService {
    ShoppingRepo shoppingRepo;

    ModelMapper mapper = new ModelMapper();

    public ShoppingService(ShoppingRepo shoppingRepo) {
        this.shoppingRepo = shoppingRepo;
    }

    public ShoppingDTO createShopping(Shopping input) {
        return mapper.map(shoppingRepo.save(input), ShoppingDTO.class);
    }

    public ShoppingDTO getShoppingById(Long id) {
        return mapper.map(shoppingRepo.findById(id).orElse(null), ShoppingDTO.class);
    }

    // AML
    // this method doesn't need an argument
    public List<Shopping> getAllShops() {
        //this should call the find all method, not findById
        List<Shopping> shoppingList = shoppingRepo.findAll();
        // method should return a list, not an object
        return shoppingList;
    }
}

