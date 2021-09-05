package com.foodkart.dao;

import com.foodkart.models.Gender;
import com.foodkart.models.User;

import java.util.HashMap;
import java.util.Map;

/**
 * In memory data access class that implements the UserDao interface
 */
public class InMemoryUserDao implements UserDao{
    private final Map<String, User> users;

    /**
     * constructor
     */
    public InMemoryUserDao() {
        users = new HashMap<>();
    }

    /**
     * @param name    name of user
     * @param phoneNo phone number of user
     * @param gender  gender of user
     * @param pincode pincode of user
     * @return User that was registered
     */
    @Override
    public User register(final String name, final String phoneNo, final Gender gender, final String pincode) {
        String id = IdGenerator.generateId();
        User user = new User(id,name,phoneNo,pincode,gender);
        users.put(id, user);
        return user;
    }

    /**
     * @param id id of the used
     * @return user that logged in
     */
    @Override
    public User login(final String id) {
        return getUser(id);
    }

    /**
     * @param id id of the user
     * @return user that has the id passed in as param
     */
    @Override
    public User getUser(final String id) {
        return users.get(id);
    }

}
