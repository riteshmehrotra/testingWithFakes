package org.example;

import org.example.domain.ItemStatus;
import org.example.domain.OrderItem;
import org.example.ports.InventoryService;
import org.example.domain.Item;
import org.example.ports.PaymentService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class CartValueTest {

    Cart cart;
    InventoryService inventoryService;
    PaymentService paymentService;

    @Before
    public void setup(){
        inventoryService = mock(InventoryService.class);
        paymentService = mock(PaymentService.class);
    }

    @After
    public void teardown(){
        reset();
    }


    @Test
    public void should_AddToCart_WhenReservedByInventory(){
        Cart cart = new Cart(inventoryService,paymentService);

        OrderItem cleaningBrushes  = new OrderItem("Cleaning brush",1);

        when(inventoryService.reserve(anyString(),anyInt())).thenReturn(ItemStatus.RESERVED);
        //Act
        cart.addToCart(cleaningBrushes);

        //Assert
        assertThat(cart.getCartSize(),is(equalTo(1)));
        assertTrue(cart.fetch().contains(cleaningBrushes));
        verify(inventoryService,times(1)).reserve(anyString(),anyInt());
    }


    @Test
    public void shouldNot_AddToCart_WhenNotReservedByInventory(){
        Cart cart = new Cart(inventoryService,paymentService);

        OrderItem cleaningBrushes  = new OrderItem("Cleaning brush",1);

        when(inventoryService.reserve(anyString(),anyInt())).thenReturn(ItemStatus.NOT_RESERVED);
        //Act
        cart.addToCart(cleaningBrushes);

        //Assert
        assertThat(cart.getCartSize(),is(equalTo(0)));
        verify(inventoryService,times(1)).reserve(anyString(),anyInt());
    }


    @Test
    public void shouldNot_AddToCart_ItemsWith_QuantityLessThanEqualToZERO(){
        //Arrange
        Cart cart = new Cart(inventoryService,paymentService);

        OrderItem filters = new OrderItem("Filter",-10);
        OrderItem charger  = new OrderItem("Charger",0);

        when(inventoryService.reserve(anyString(),anyInt())).thenReturn(ItemStatus.RESERVED);

        //Act
        cart.addToCart(filters);
        cart.addToCart(charger);

        //Assert
        assertThat(cart.getCartSize(),is(equalTo(0)));
        verify(inventoryService, never()).reserve(anyString(),anyInt());
    }


    @Test
    public void shouldNot_AddToCart_ItemsWith_NameEmptyOrNull(){
        //Arrange
        Cart cart = new Cart(inventoryService,paymentService);

        OrderItem blankItem = new OrderItem("             ",-10);
        OrderItem nullItem  = new OrderItem(null,0);

        when(inventoryService.reserve(anyString(),anyInt())).thenReturn(ItemStatus.RESERVED);

        //Act
        cart.addToCart(blankItem);
        cart.addToCart(nullItem);

        //Assert
        assertThat(cart.getCartSize(),is(equalTo(0)));
        verify(inventoryService, never()).reserve(anyString(),anyInt());
    }

}
