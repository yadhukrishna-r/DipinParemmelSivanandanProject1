package com.uase.shipping.item;

import com.uase.shipping.constant.Product;
import com.uase.shipping.method.Calcutator;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Item {

    private String name;
    private double length;
    private double height;
    private double width;
    private double weight;
    private double volume;
    private double numberOfItems;

    public Item(String name, double length, double height, double width, double weight, double numberOfItems) {
        this.length = length;
        this.height = height;
        this.width = width;
        this.weight = weight;
        this.volume = Calcutator.getVolume(length, height, width);
        this.name = name;
        this.numberOfItems = numberOfItems;
    }

    public Item(Product product, double numberOfItems) {
        this.length = product.getLength();
        this.height = product.getHeight();
        this.width = product.getWidth();
        this.weight = product.getWeight();
        this.volume = Calcutator.getVolume(this.length, this.height, this.width);
        this.name = product.getName();
        this.numberOfItems = numberOfItems;
    }


}
