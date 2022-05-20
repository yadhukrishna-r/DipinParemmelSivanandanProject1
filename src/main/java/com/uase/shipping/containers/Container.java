package com.uase.shipping.containers;

import com.uase.shipping.constant.ContainerType;
import com.uase.shipping.method.Calcutator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Container {
    private String name;
    private double length;
    private double height;
    private double width;
    private double volume;

    public Container(String name, double length, double height, double width) {
        this.name = name;
        this.length = length;
        this.height = height;
        this.width = width;
        this.volume = Calcutator.getVolume(length, height, width);
    }

    public Container() {
    }

    public Container(ContainerType containerType) {
        this.name = containerType.getName();
        this.length = containerType.getLength();
        this.height = containerType.getHeight();
        this.width = containerType.getWidth();
        this.volume = Calcutator.getVolume(length, height, width);
    }
}
