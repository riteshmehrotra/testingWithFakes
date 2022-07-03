package org.example;

import java.util.concurrent.atomic.AtomicReference;

public class CartSummary {

    Cart cart;
    PrintPort port;

    public CartSummary(Cart cart, PrintPort printPort) {
        this.cart = cart;
        this.port= printPort;
    }

    public void computeTotal() {
        AtomicReference<Float> totalValue= new AtomicReference<>((float) 0);
       cart.fetch().forEach(item-> totalValue.updateAndGet(v -> v + item.getPrice()));
       port.print(totalValue.get());
    }



}
