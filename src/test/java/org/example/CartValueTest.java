package org.example;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CartValueTest {

    Cart cart = mock(Cart.class);
    PrintPort printPort = mock(PrintPort.class);


    @Test
    public void testCartValueZeroWithNoItems(){
        CartSummary cartSummary = new CartSummary(cart,printPort);
        when(cart.fetch()).thenReturn(fakeCartEmpty());
        cartSummary.computeTotal();
        verify(printPort,times(1)).print(0);
    }


    @Test
    public void testCartValueWithItems(){
        CartSummary cartSummary = new CartSummary(cart,printPort);
        when(cart.fetch()).thenReturn(fakeCartWithValues());
        cartSummary.computeTotal();
        verify(printPort,times(1)).print(550);
    }


    private List<Item> fakeCartWithValues(){
        List<Item> cart = new ArrayList<>();

        Item firstItem = new Item();
        firstItem.setTitle("Item A");
        firstItem.setPrice(100);
        cart.add(firstItem);

        Item secondTime = new Item();
        secondTime.setTitle("Item B");
        secondTime.setPrice(200);
        cart.add(secondTime);

        Item thirdItem = new Item();
        thirdItem.setTitle("Item B");
        thirdItem.setPrice(250);
        cart.add(thirdItem);

        return cart;
    }


    private List<Item>  fakeCartEmpty(){
        return new ArrayList<>();
    }
}
