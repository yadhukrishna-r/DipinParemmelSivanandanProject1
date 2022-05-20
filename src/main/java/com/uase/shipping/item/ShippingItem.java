package com.uase.shipping.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingItem {
    private String name;
    private double number;
    private double totalVolume;
    private double totalWeight;

    public ShippingItem(String name, double number, double totalVolume, double totalWeight) {
        this.name = name;
        this.number = number;
        this.totalVolume = totalVolume;
        this.totalWeight = totalWeight;
    }

    public ShippingItem() {
    }
}
