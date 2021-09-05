package com.foodkart.dao;

import com.foodkart.models.Gender;
import com.foodkart.models.User;

/**
 * Interface for data access for Users
 */
public interface UserDao {
    /**
     * @param name name of user
     * @param phoneNo phone number of user
     * @param gender gender of user
     * @param pincode pincode of user
     * @return User that was registered
     */
    User register(String name, String phoneNo, Gender gender, String pincode);

    /**
     * @param id id of the used
     * @return User that logged in
     */
    User login(String id);

    /**
     * @param id id of the user
     * @return User having the id passed in as param
     */
    User getUser(String id);

}
