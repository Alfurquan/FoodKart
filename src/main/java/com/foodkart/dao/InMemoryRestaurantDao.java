package com.foodkart.dao;

import com.foodkart.models.FoodItem;
import com.foodkart.models.Restaurant;
import com.foodkart.models.Review;
import com.foodkart.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In Memory Data access for restaurants that implements RestaurantDao interface
 */
public class InMemoryRestaurantDao implements RestaurantDao {
    private final Map<String,Restaurant> restaurants;

    /**
     * constructor
     */
    public InMemoryRestaurantDao() {
        restaurants = new HashMap<>();
    }

    /**
     * @param name     name of the restaurant
     * @param pincodes List of pincodes where the restaurant provides service
     * @param foodItem food item present in the restaurant
     * @param owner    owner of the restaurant
     * @return Restaurant that was registered
     */
    @Override
    public Restaurant register(final String name, final List<String> pincodes, final FoodItem foodItem, final User owner) {
        String id = IdGenerator.generateId();
        Restaurant restaurant = new Restaurant(id, name, pincodes, foodItem, owner);
        restaurants.put(id, restaurant);
        return restaurant;
    }

    /**
     * @param id       id of the restaurant
     * @param quantity quantity to be updated
     */
    @Override
    public void updateQuantity(final String id, int quantity) {
        if(!restaurants.containsKey(id))
            return;

        Restaurant restaurant = getRestaurant(id);
        restaurant.getFoodItem().setQuantity(quantity);
        restaurants.put(id, restaurant);
    }

    @Override
    public void addReview(final String id, final int score, final String comment) {
        if(!restaurants.containsKey(id))
            return;

        String reviewId = IdGenerator.generateId();
        Review review = new Review(reviewId,score,comment);
        Restaurant restaurant = getRestaurant(id);
        restaurant.getReviews().add(review);
    }

    /**
     * @param id id of the restaurant
     * @return Restaurant that has the id passed in as param
     */
    @Override
    public Restaurant getRestaurant(final String id) {
        return restaurants.get(id);
    }

    /**
     * @param id     id of the restaurant
     * @param rating rating to be updated
     */
    @Override
    public void updateRating(final String id, final double rating) {
        if(!restaurants.containsKey(id))
            return;

        Restaurant restaurant = getRestaurant(id);
        restaurant.setRating(rating);
        restaurants.put(id, restaurant);
    }

    /**
     * @return List of restaurants in system
     */
    @Override
    public List<Restaurant> getRestaurants() {
        return new ArrayList<>(restaurants.values());
    }


}
