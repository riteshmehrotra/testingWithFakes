package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class OrderItem {
    String itemName;
    int quantity;
}
