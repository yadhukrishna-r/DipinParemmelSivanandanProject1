package com.uase.shipping.containers;

import com.uase.shipping.item.ShippingItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShippingContainer {
    private String containerType;
    private double volumeOfItems;
    private double weightOfItems;
    private double costOfShipping;
    private List<ShippingItem> items;

    public ShippingContainer() {
    }

    public ShippingContainer(String containerType, double volumeOfItems, double weightOfItems, double costOfShipping, List<ShippingItem> items) {
        this.containerType = containerType;
        this.volumeOfItems = volumeOfItems;
        this.weightOfItems = weightOfItems;
        this.costOfShipping = costOfShipping;
        this.items = items;
    }
}
