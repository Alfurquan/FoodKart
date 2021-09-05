package com.foodkart;

import com.foodkart.controllers.OrderController;
import com.foodkart.controllers.RestaurantController;
import com.foodkart.controllers.UserController;
import com.foodkart.dao.*;
import com.foodkart.exceptions.*;
import com.foodkart.models.FoodItem;
import com.foodkart.models.Gender;
import com.foodkart.models.Order;
import com.foodkart.models.Restaurant;

import java.util.*;

/**
 * Driver class for testing the flow 
 */
public class Driver {
    private UserController userController;
    private RestaurantController restaurantController;
    private OrderController orderController;
    private UserDao userDao;
    private RestaurantDao restaurantDao;
    private OrderDao orderDao;
    private List<String> registeredUserIds;
    private List<String> registeredRestaurantIds;

    public Driver() {
        registeredUserIds = new ArrayList<>();
        registeredRestaurantIds = new ArrayList<>();
    }

    public void runApp(){
        setUpControllers();

        registerUsers();
        loginUser(0);
        registerRestaurants();
        loginUser(1);

        updateQuantity(registeredRestaurantIds.get(0), 8);

        addReview(registeredRestaurantIds.get(0),4,"Good Quality food");
        addReview(registeredRestaurantIds.get(0),2,"Poor service");
        addReview(registeredRestaurantIds.get(1),5,"Excellent food");
        addReview(registeredRestaurantIds.get(1),5,null);

        showRestaurants("price");
        showRestaurants("rating");

        placeOrder(registeredRestaurantIds.get(0),2);

        fetchOrders();
    }

    private void fetchOrders() {
        try {
            List<Order> orders = orderController.getOrders();
            for(Order order : orders)
                System.out.println(order);
        } catch (UnauthorizedException e) {
            System.out.println("Login a user to see order history");
        }
    }

    private void placeOrder(final String restaurantId, final int quantity) {
        try {
            orderController.placeOrder(restaurantId,quantity);
            System.out.println("Order placed successfully");
        } catch (UnauthorizedException e) {
            System.out.println("Login a user to place order");
        } catch (RestaurantNotFoundException e) {
            System.out.println("Restaurant not found");
        } catch (QuantityNotPresentInRestaurantException e) {
            System.out.println("Quantity ordered is more than quantity present in restaurant");
        }
    }

    private void showRestaurants(final String showBy) {
        try {
            List<Restaurant> restaurants = restaurantController.showRestaurants(showBy);
            for (Restaurant restaurant : restaurants)
                System.out.println(restaurant);
        } catch (UnauthorizedException e) {
            System.out.println("Login a user to fetch restaurants");
        }
    }

    private void addReview(final String restaurantId,final int score, final String comment) {
        try {
            restaurantController.rateRestaurant(restaurantId,score,comment);
            System.out.println("Review added successfully");
        } catch (RestaurantNotFoundException e) {
            System.out.println("Restaurant does not exists");
        } catch (UnauthorizedException e) {
            System.out.println("Login to give review");
        }
    }

    private void updateQuantity(String restaurantId, int quantity) {
        try {
            restaurantController.updateQuantity(restaurantId,quantity);
            System.out.println("Quantity updated successfully");
        } catch (RestaurantNotFoundException e) {
            System.out.println("Restaurant does not exists");
        } catch (UnauthorizedException e) {
            System.out.println("Login to update quantity");
        } catch (ForbiddenException e) {
            System.out.println("User is not an owner of the restaurant");
        }
    }

    private void loginUser(final int index) {
        try {
            userController.login(registeredUserIds.get(index));
            System.out.println(registeredUserIds.get(index) + " logged in successfully");
        } catch (UserDoesNotExistsException e) {
            System.out.println("user with given id was not found.");
        }
    }

    private void registerRestaurants() {
        List<String> pincodes = new ArrayList<>(Arrays.asList("700014", "700024", "700012"));
        String restaurantId;
        try {
            restaurantId = restaurantController.register("Arsalan",pincodes, new FoodItem("Biryani",170,10));
            System.out.println("Restaurant Arsalan registered successfully");
            registeredRestaurantIds.add(restaurantId);
        } catch (UnauthorizedException e) {
            System.out.println("Login a user to register a restaurant");
        }

        pincodes = new ArrayList<>(Arrays.asList("700002", "700024", "700012"));
        try {
            restaurantId = restaurantController.register("India",pincodes, new FoodItem("Chicken Butter Masala",350,10));
            System.out.println("Restaurant India registered successfully");
            registeredRestaurantIds.add(restaurantId);
        } catch (UnauthorizedException e) {
            System.out.println("Login a user to register a restaurant");
        }

    }

    private void registerUsers() {
        String userId;
        userId = userController.register("Rohit","1234567890", Gender.MALE,"700024");
        System.out.println("Rohit registered successfully");
        registeredUserIds.add(userId);

        userId = userController.register("Asha","1234567891", Gender.FEMALE, "700014");
        System.out.println("Asha registered successfully");
        registeredUserIds.add(userId);

        userId = userController.register("Aman","1234567892", Gender.MALE, "700012");
        System.out.println("Aman registered successfully");
        registeredUserIds.add(userId);

        userId = userController.register("Khan","9007234220",Gender.MALE, "700002");
        System.out.println("Khan registered successfully");
        registeredUserIds.add(userId);
    }

    private void setUpControllers(){
        userDao = new InMemoryUserDao();
        restaurantDao = new InMemoryRestaurantDao();
        orderDao = new InMemoryOrderDao();
        userController = new UserController(userDao);
        restaurantController = new RestaurantController(restaurantDao);
        orderController = new OrderController(orderDao,restaurantDao);
    }
}
