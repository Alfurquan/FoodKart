package com.foodkart.dao;

import com.foodkart.models.Order;
import com.foodkart.models.Restaurant;
import com.foodkart.models.User;

import java.util.*;

/**
 * interface for data access for Orders
 */
public interface OrderDao {
    /**
     * @param restaurant restaurant from which order is to be placed
     * @param user user who placed the order
     * @param totalAmount total amount of the order
     * @return Order object that was created
     */
    Order placeOrder(Restaurant restaurant, User user, double totalAmount);

    /**
     * @param userId id of the user whose orders need to be fetched
     * @return list of orders for the user
     */
    List<Order> fetchOrders(String userId);
}
