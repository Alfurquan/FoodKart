package com.foodkart.controllers;

import com.foodkart.dao.UserDao;
import com.foodkart.exceptions.UserDoesNotExistsException;
import com.foodkart.models.Gender;
import com.foodkart.models.UserSession;
import com.foodkart.models.User;

/**
 * Controller class for users
 */
public class UserController {
    private final UserDao userDao;

    /**
     * @param userDao user data access object
     */
    public UserController(final UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * @param name name of the user
     * @param phoneNo phone number of the user
     * @param gender gender of the user
     * @param pincode pincode of the user
     * @return id of the user registered
     */
    public String register(final String name, final String phoneNo, final Gender gender, final String pincode){
        return userDao.register(name,phoneNo,gender,pincode).getId();
    }

    /**
     * @param userId id of the user
     * @throws UserDoesNotExistsException
     */
    public void login(final String userId) throws UserDoesNotExistsException {
        User user = userDao.login(userId);

        if (user == null)
            throw new UserDoesNotExistsException();

        // logout a previously logged in user
        if (UserSession.currentUser != null)
            UserSession.logout();

        //set current user
        UserSession.currentUser = user;

    }
}
