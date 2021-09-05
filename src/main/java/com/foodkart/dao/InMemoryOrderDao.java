package com.foodkart.dao;

import com.foodkart.models.Order;
import com.foodkart.models.Restaurant;
import com.foodkart.models.User;

import java.util.*;

/**
 * In memory data access class for Orders that implements OrderDao interface
 */
public class InMemoryOrderDao implements OrderDao {
    private final Map<String, List<Order>> userOrders;
    private final Map<String, Order> orders;

    /**
     * constructor
     */
    public InMemoryOrderDao() {
        userOrders = new HashMap<>();
        orders = new HashMap<>();
    }


    /**
     * @param restaurant  restaurant from which order is to be placed
     * @param user        user who placed the order
     * @param totalAmount total amount of the order
     * @return Order that was placed
     */
    @Override
    public Order placeOrder(final Restaurant restaurant, final User user, final double totalAmount) {
        final String orderId = IdGenerator.generateId();
        final Order order = new Order(orderId,user,restaurant,totalAmount);
        orders.put(orderId,order);
        userOrders.computeIfAbsent(user.getId(), k -> new ArrayList<>()).add(order);
        return order;
    }

    /**
     * @param userId id of the user whose orders need to be fetched
     * @return List of orders for the user
     */
    @Override
    public List<Order> fetchOrders(final String userId) {
        if(!userOrders.containsKey(userId))
            return new ArrayList<>();

        return userOrders.get(userId);
    }
}
