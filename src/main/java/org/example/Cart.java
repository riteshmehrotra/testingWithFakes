package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cart {

    private List<Item> shoppingCart = new ArrayList<>();


    public List<Item> fetch() {
        return shoppingCart;
    }
}
