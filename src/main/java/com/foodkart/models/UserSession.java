package com.foodkart.models;

/**
 * class for user session
 */
public class UserSession {
    /**
     * Instance of the currently logged in user
     */
    public static User currentUser;

    /**
     * method to logout a current user
     */
    public static void logout(){
        currentUser = null;
    }

    /**
     * @return status whether a user is logged in to the system or not
     */
    public static boolean noUserLoggedIn(){
        return currentUser == null;
    }
}
