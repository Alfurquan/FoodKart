package com.foodkart.controllers;

import com.foodkart.dao.RestaurantDao;
import com.foodkart.exceptions.ForbiddenException;
import com.foodkart.exceptions.RestaurantNotFoundException;
import com.foodkart.exceptions.UnauthorizedException;
import com.foodkart.models.*;
import com.foodkart.models.UserSession;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Controller class for Restaurants
 */
public class RestaurantController {
    private final RestaurantDao restaurantDao;
    private static final String SORT_BY_PRICE = "price";
    private static final String SORT_BY_RATING = "rating";

    /**
     * @param restaurantDao restaurant data access object
     */
    public RestaurantController(final RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    /**
     * @param name name of restaurant
     * @param pincodes list of pincodes where the restaurant provides service
     * @param foodItem food item that restaurant provides
     * @return id of restaurant registered
     * @throws UnauthorizedException
     */
    public String register(final String name, final List<String> pincodes, final FoodItem foodItem) throws UnauthorizedException {
        if(UserSession.noUserLoggedIn())
            throw new UnauthorizedException();

       return restaurantDao.register(name,pincodes,foodItem, UserSession.currentUser).getId();
    }

    /**
     * @param id id of restaurant
     * @param quantity quantity to be updated
     * @throws RestaurantNotFoundException
     * @throws UnauthorizedException
     * @throws ForbiddenException
     */
    public void updateQuantity(final String id, final int quantity) throws RestaurantNotFoundException, UnauthorizedException, ForbiddenException {
        Restaurant restaurant = restaurantDao.getRestaurant(id);

        //if restaurant with id is not found
        if(restaurant == null)
            throw new RestaurantNotFoundException();

        //if no user is logged in
        if(UserSession.noUserLoggedIn())
            throw new UnauthorizedException();

        //if logged in user is not owner of the restaurant
        if(!restaurant.getOwner().getId().equals(UserSession.currentUser.getId()))
            throw new ForbiddenException();

        restaurantDao.updateQuantity(id,quantity);
    }

    /**
     * @param restaurantId id of the restaurant
     * @param score score of the review
     * @param comment comment of the review
     * @throws RestaurantNotFoundException
     * @throws UnauthorizedException
     */
    public void rateRestaurant(final String restaurantId, final int score, final String comment) throws RestaurantNotFoundException, UnauthorizedException {
        Restaurant restaurant = restaurantDao.getRestaurant(restaurantId);

        // if restaurant not present
        if(restaurant == null)
            throw new RestaurantNotFoundException();

        // if no user is logged in
        if(UserSession.noUserLoggedIn())
            throw new UnauthorizedException();

        //add review
        restaurantDao.addReview(restaurantId,score,comment);

        //update the rating of restaurant
        int ratingScores = score;
        for(Review review : restaurant.getReviews())
            ratingScores += review.getScore();

        int rating = (ratingScores/restaurant.getReviews().size());

        restaurantDao.updateRating(restaurantId, rating);
    }

    /**
     * @param sortBy sort restaurants by this param (can be by price or rating)
     * @return List of restaurants sorted according to sortBy param
     * @throws UnauthorizedException
     */
    public List<Restaurant> showRestaurants(final String sortBy) throws UnauthorizedException {

        // get a list of all serviceable restaurants for the currently logged in user
        List<Restaurant> restaurants = getServiceableRestaurants();

        if(sortBy.equals(SORT_BY_PRICE))
            restaurants.sort((r1, r2) -> Double.compare(r2.getFoodItem().getPrice(),r1.getFoodItem().getPrice()));

        if(sortBy.equals(SORT_BY_RATING))
            restaurants.sort((r1,r2) -> Double.compare(r2.getFoodItem().getPrice(), r1.getFoodItem().getPrice()));

        return restaurants;

    }

    private List<Restaurant> getServiceableRestaurants() throws UnauthorizedException {
        // if no user is logged in
        if(UserSession.noUserLoggedIn())
            throw new UnauthorizedException();

        final List<Restaurant> restaurants = restaurantDao.getRestaurants();
        final User currentUser = UserSession.currentUser;
        return restaurants.stream()
                          .filter(restaurant -> restaurant.getServiceablePincodes().contains(currentUser.getPincode())
                                  && restaurant.getFoodItem().getQuantity() > 0)
                          .collect(Collectors.toList());
    }



}
