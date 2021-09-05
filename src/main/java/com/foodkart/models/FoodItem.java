package com.foodkart.models;

/**
 * A model class for individual food items
 */
public class FoodItem {
    private final String name;
    private double price;
    private int quantity;

    /**
     * @param name name of food item
     * @param price price of food item
     * @param quantity quantity of food item
     * constructor
     */
    public FoodItem(final String name, final double price,final int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * @return name of food item
     */
    public String getName() {
        return name;
    }

    /**
     * @return price of food item
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return quantity of food item
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity quantity of food item
     * setter for quantity of food item
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
