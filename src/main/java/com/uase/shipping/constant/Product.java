package com.uase.shipping.constant;

import java.util.HashMap;
import java.util.Map;

public enum Product {


    DESKTOP("DESKTOP", Constants.DESKTOP_BOX_LENGTH, Constants.DESKTOP_BOX_HEIGHT, Constants.DESKTOP_BOX_WIDTH, Constants.DESKTOP_WEIGHT),
    LAPTOP("LAPTOP", Constants.LAPTOP_BOX_LENGTH, Constants.LAPTOP_BOX_HEIGHT, Constants.LAPTOP_BOX_WIDTH, Constants.LAPTOP_WEIGHT),
    LCD_SCREEN("LCD SCREEN", Constants.LCD_SCREEN_BOX_LENGTH, Constants.LCD_SCREEN_BOX_HEIGHT, Constants.LCD_SCREEN_BOX_WIDTH, Constants.LCD_SCREEN_WEIGHT),
    MOUSE("MOUSE", Constants.MOUSE_BOX_LENGTH, Constants.MOUSE_BOX_HEIGHT, Constants.MOUSE_BOX_WIDTH, Constants.MOUSE_WEIGHT);

    private String name;
    private double length;
    private double height;
    private double width;
    private double weight;

    Product(String name, double length, double height, double width, double weight) {
        this.name = name;
        this.length = length;
        this.height = height;
        this.width = width;
        this.weight = weight;
    }

    private static final Map<String, Product> productMap;

    static {
        productMap = new HashMap<String, Product>();
        for (Product product : Product.values()) {
            productMap.put(product.getName(), product);
        }
    }

    public static Product findByName(String name) {
        return productMap.get(name);
    }

    public String getName() {
        return name;
    }

    public double getLength() {
        return length;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getWeight() {
        return weight;
    }


}
