package com.uase.shipping.constant;

public enum ContainerType {


    BIG_CONTAINER("BIG CONTAINER", Constants.BIG_CONTAINER_LENGTH, Constants.BIG_CONTAINER_HEIGHT, Constants.BIG_CONTAINER_WIDTH),
    SMALL_CONTAINER("SMALL CONTAINER", Constants.SMALL_CONTAINER_LENGTH, Constants.SMALL_CONTAINER_HEIGHT, Constants.SMALL_CONTAINER_WIDTH);

    private String name;
    private double length;
    private double height;
    private double width;

    ContainerType(String name, double length, double height, double width) {
        this.name = name;
        this.length = length;
        this.height = height;
        this.width = width;
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
}
