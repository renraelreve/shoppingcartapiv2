package com.example.shoppingcartapiv2.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.example.shoppingcartapiv2.entity.Catalogue;
import com.example.shoppingcartapiv2.entity.Cart;
import com.example.shoppingcartapiv2.repository.CatalogueRepository;
import com.example.shoppingcartapiv2.repository.CartRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CartController.class)
public class CatalogueControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CatalogueRepository mockCatalogueRepo;

  @MockBean
  private CartRepository mockCartRepo;

  @Test
  public void whenCreateCartWithValidCatalogueId_thenReturns200() throws Exception {
    // Given
    Catalogue catalogue = new Catalogue();
    catalogue.setId(1);
    catalogue.setName("Item 1");
    catalogue.setShortDesc("Description 1");
    catalogue.setPrice((float) 10.0);

    Cart cart = new Cart();
    cart.setId(1);
    cart.setItem(catalogue);
    cart.setQuantity(2);

    CreateCartModel createCartModel = new CreateCartModel();
    createCartModel.setItemId(1);
    createCartModel.setQuantity(2);

    when(mockCatalogueRepo.findById(1)).thenReturn(Optional.of(catalogue));
    when(mockCartRepo.save(any(Cart.class))).thenReturn(cart);

    // When
    ResultActions result = mockMvc.perform(
        post("/carts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(createCartModel)));

    // Then
    result.andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.item.id").value(1))
        .andExpect(jsonPath("$.quantity").value(2))
        .andDo(print());
  }
}
