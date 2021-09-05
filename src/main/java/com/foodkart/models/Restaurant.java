package com.foodkart.models;

import java.util.*;

/**
 * Model class for Restaurant
 */
public class Restaurant {
    private final String id;
    private final String name;
    private final List<String> serviceablePincodes;
    private final List<Review> reviews;
    private double rating;
    private final FoodItem foodItem;
    private final User owner;

    /**
     * @param id id of the restaurant
     * @param name name of the restaurant
     * @param serviceablePincodes list of pincodes where the restaurant provides service
     * @param foodItem food item present in restaurant
     * @param owner user who owns the restaurant
     */
    public Restaurant(final String id, final String name, final List<String> serviceablePincodes,
                      final FoodItem foodItem, final User owner) {
        this.id = id;
        this.name = name;
        this.serviceablePincodes = serviceablePincodes;
        this.reviews = new ArrayList<>();
        this.rating = 0.0;
        this.foodItem = foodItem;
        this.owner = owner;
    }

    /**
     * @return id of the restaurant
     */
    public String getId() {
        return id;
    }

    /**
     * @return name of the restaurant
     */
    public String getName() {
        return name;
    }

    /**
     * @return list of pincodes where the restaurant provides service
     */
    public List<String> getServiceablePincodes() {
        return serviceablePincodes;
    }

    /**
     * @return reviews provided to restaurant
     */
    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * @return rating of the restaurant
     */
    public double getRating() {
        return rating;
    }

    /**
     * @return food item present in the restaurant
     */
    public FoodItem getFoodItem() {
        return foodItem;
    }

    /**
     * @return owner of the restaurant
     */
    public User getOwner() {
        return owner;
    }

    /**
     * @param rating rating of the restaurant
     * setter for rating field
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", serviceablePincodes=" + serviceablePincodes +
                ", reviews=" + reviews +
                ", rating=" + rating +
                ", foodItem=" + foodItem +
                ", owner=" + owner +
                '}';
    }
}
