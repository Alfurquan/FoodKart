package com.foodkart.dao;

import com.foodkart.models.FoodItem;
import com.foodkart.models.Restaurant;
import com.foodkart.models.Review;
import com.foodkart.models.User;

import java.util.*;

/**
 * Interface for data access for Restaurants
 */
public interface RestaurantDao {
    /**
     * @param name name of the restaurant
     * @param pincodes List of pincodes where the restaurant provides service
     * @param foodItem food item present in the restaurant
     * @param owner owner of the restaurant
     * @return Restaurant that was created
     */
    Restaurant register(String name, List<String> pincodes, FoodItem foodItem, User owner);

    /**
     * @param id id of the restaurant
     * @param quantity quantity to be updated
     */
    void updateQuantity(String id, int quantity);

    /**
     * @param id id of restaurant
     * @param score score of review
     * @param comment comment of review
     */
    void addReview(String id, int score, String comment);

    /**
     * @param id id of the restaurant
     * @return restaurant having the id passed as param
     */
    Restaurant getRestaurant(String id);

    /**
     * @param id id of the restaurant
     * @param rating rating to be updated
     */
    void updateRating(String id, double rating);

    /**
     * @return get list of restaurants
     */
    List<Restaurant> getRestaurants();
}
