package org.example.ports;

import org.example.domain.ItemStatus;

public interface InventoryService {
    ItemStatus reserve(String itemName, int quantity);

}
