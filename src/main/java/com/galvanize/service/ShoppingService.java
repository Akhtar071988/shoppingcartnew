package com.galvanize.service;

import com.galvanize.dto.ShoppingDTO;
import com.galvanize.entity.Shopping;
import com.galvanize.repository.ShoppingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;

@Service
@Transactional
public class ShoppingService {
    ShoppingRepository shoppingRepository;

    ModelMapper mapper = new ModelMapper();

    public ShoppingService(ShoppingRepository shoppingRepository) {
        this.shoppingRepository = shoppingRepository;
    }

    public ShoppingDTO createShopping(Shopping input) {
        return mapper.map(shoppingRepository.save(input), ShoppingDTO.class);
    }

    public ShoppingDTO getShoppingById(Long id) {
        return mapper.map(shoppingRepository.findById(id).orElse(null), ShoppingDTO.class);
    }

    // AML
    // this method doesn't need an argument
    public List<Shopping> getAllShops() {
        //this should call the find all method, not findById
        List<Shopping> shoppingList = shoppingRepository.findAll();
        // method should return a list, not an object
        return shoppingList;
    }

    public Shopping updateShopperById(Long id, Shopping activity) {
        Shopping shopper = shoppingRepository.findById(id).orElse(null);
        shopper.getActivity();
        return mapper.map(shoppingRepository.save(shopper), (Type) ShoppingDTO.class);
    }

    public boolean deleteByShopperId(long shopperId) {
        return shoppingRepository.deleteByShopperId(shopperId)==1;
    }
}