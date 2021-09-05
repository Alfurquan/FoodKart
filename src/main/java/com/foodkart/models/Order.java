package com.foodkart.models;

/**
 * Model class for Order
 */
public class Order {
    private final String id;
    private final User user;
    private final Restaurant restaurant;
    private final double totalAmount;

    /**
     * @param id id of the order
     * @param user user who made the order
     * @param restaurant restaurant from which order was made
     * @param totalAmount total amount of the order
     */
    public Order(final String id, final User user, final Restaurant restaurant, final double totalAmount) {
        this.id = id;
        this.user = user;
        this.restaurant = restaurant;
        this.totalAmount = totalAmount;
    }

    /**
     * @return id of the order
     */
    public String getId() {
        return id;
    }

    /**
     * @return user who made the order
     */
    public User getUser() {
        return user;
    }

    /**
     * @return restaurant from which the order was placed
     */
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * @return total amount of the order
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", restaurant=" + restaurant +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
