package com.galvanize.service;

import com.galvanize.dto.ShoppingDTO;
import com.galvanize.entity.Activity;
import com.galvanize.entity.Expense;
import com.galvanize.entity.Shopping;
import com.galvanize.repository.ShoppingRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class ShoppingServiceTest {
    @MockBean
    ShoppingRepository shoppingRepository;

    ModelMapper mapper = new ModelMapper();

    @Autowired
    ShoppingService shoppingService;
    Shopping testShopping;

    @Test
    public void createShopping(){
        ShoppingService shoppingService = new ShoppingService(shoppingRepository);
        Shopping input = new Shopping(Expense.EXPENSIVE, "Description", "Name", Activity.ACTIVE, 101);
        ShoppingDTO expected = new ShoppingDTO(input.getName(), input.getDescription(), input.getPrice());
        Shopping received = new Shopping(input.getExpense(), input.getDescription(), input.getName(), input.getActivity(), input.getPrice());
        received.setShopperId(1L);
        when(shoppingRepository.save(any(Shopping.class))).thenReturn(received);
        assertEquals(expected, shoppingService.createShopping(input));
    }

    @Test
    public void getAllShops(){
        // Create a test customer
        testShopping = new Shopping(Expense.EXPENSIVE,"test", "customer", Activity.ACTIVE, 10 );
        testShopping.setName("customer");
        shoppingRepository.save(testShopping);
        assertNotNull(testShopping.getShopperId());


        List<Shopping> shopping = shoppingService.getAllShops();
        assertTrue(shopping.isEmpty());

        System.out.println(shopping);
    }

    @Test
    public void getShopById(){
        ShoppingService shoppingService = new ShoppingService(shoppingRepository);
        Shopping expected = new Shopping(Expense.EXPENSIVE, "Description", "Name", Activity.ACTIVE, 101);
        expected.setShopperId(1L);
        when(shoppingRepository.findById(anyLong())).thenReturn(Optional.of(expected));
        assertEquals(mapper.map(expected,ShoppingDTO.class),shoppingService.getShoppingById(expected.getShopperId()));
    }

    @Test
    public void updateShopperById(){
        ShoppingService shoppingService = new ShoppingService(shoppingRepository);
        Shopping expected = new Shopping((Expense.EXPENSIVE), "desccriptive", "new name", Activity.ACTIVE, 101);
        expected.setShopperId(1L);
        when(shoppingRepository.findById(anyLong())).thenReturn(Optional.of(expected));
        assertEquals(mapper.map(expected, ShoppingDTO.class),shoppingService.getShoppingById(expected.getShopperId()));
    }

    @Test
    public void deleteByShopperId(){
        ShoppingService shoppingService = new ShoppingService(shoppingRepository);
        when(shoppingRepository.deleteByShopperId(anyLong())).thenReturn(1);
        assertTrue(shoppingService.deleteByShopperId(1L));
    }
}
