package org.example;

import org.example.ports.InventoryService;
import org.example.domain.ItemStatus;
import org.example.domain.OrderItem;
import org.example.ports.PaymentService;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    List<OrderItem> orders = new ArrayList<>();
    InventoryService inventoryService;
    PaymentService paymentService;

    public Cart(InventoryService inventoryService, PaymentService paymentService) {
        this.inventoryService = inventoryService;
        this.paymentService = paymentService;
    }



    public void addToCart(OrderItem orderItem) {
        if (!this.isOrderValid(orderItem))
            return;
        ItemStatus inventoryResponse = inventoryService.reserve(orderItem.getItemName(), orderItem.getQuantity());
        if (inventoryResponse.equals(ItemStatus.RESERVED)) {
            orders.add(orderItem);
        }
    }


    private boolean isOrderValid(OrderItem orderItem) {
        return orderItem != null && orderItem.getItemName() != null && !orderItem.getItemName().trim().isEmpty() && orderItem.getQuantity() > 0;
    }

    public int getCartSize() {
        return orders.size();
    }


    public List<OrderItem> fetch() {
        return orders;
    }


}


