package com.foodkart.controllers;

import com.foodkart.dao.OrderDao;
import com.foodkart.dao.RestaurantDao;
import com.foodkart.exceptions.QuantityNotPresentInRestaurantException;
import com.foodkart.exceptions.RestaurantNotFoundException;
import com.foodkart.exceptions.UnauthorizedException;
import com.foodkart.models.Order;
import com.foodkart.models.Restaurant;
import com.foodkart.models.UserSession;

import java.util.*;

/**
 * Controller class for Orders
 */
public class OrderController {
    private final OrderDao orderDao;
    private final RestaurantDao restaurantDao;

    /**
     * @param orderDao Order data access object
     * @param restaurantDao restaurant data access object
     *
     */
    public OrderController(OrderDao orderDao, RestaurantDao restaurantDao) {
        this.orderDao = orderDao;
        this.restaurantDao = restaurantDao;
    }

    /**
     * @param restaurantId id of the restaurant from which order was placed
     * @param quantity quantity of food item ordered
     * @return id of the order placed
     * @throws UnauthorizedException
     * @throws RestaurantNotFoundException
     * @throws QuantityNotPresentInRestaurantException
     */
    public String placeOrder(final String restaurantId, final int quantity) throws UnauthorizedException, RestaurantNotFoundException, QuantityNotPresentInRestaurantException {

        //check if user is logged in
        if(UserSession.noUserLoggedIn())
            throw new UnauthorizedException();

        //get the restaurant
        Restaurant restaurant = restaurantDao.getRestaurant(restaurantId);

        //if restaurant not found
        if (restaurant == null)
            throw new RestaurantNotFoundException();

        //if quantity ordered is greater than quantity present
        if(quantity > restaurant.getFoodItem().getQuantity())
            throw new QuantityNotPresentInRestaurantException();

        double totalAmount = quantity * restaurant.getFoodItem().getPrice();

        return orderDao.placeOrder(restaurant, UserSession.currentUser, totalAmount).getId();

    }

    /**
     * @return List of orders for the user logged in
     * @throws UnauthorizedException
     */
    public List<Order> getOrders() throws UnauthorizedException {
        //check if a user is logged in
        if(UserSession.noUserLoggedIn())
            throw new UnauthorizedException();

        return orderDao.fetchOrders(UserSession.currentUser.getId());
    }


}
