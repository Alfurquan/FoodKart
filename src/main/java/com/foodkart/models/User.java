package com.foodkart.models;

/**
 * Model class for User entity
 */
public class User {
    private final String id;
    private final String name;
    private final String phoneNo;
    private final String pincode;
    private final Gender gender;

    /**
     * @param id id of the user
     * @param name name of the user
     * @param phoneNo phone number of the user
     * @param pincode pincode of the user
     * @param gender gender of the user
     * constructor of the User class
     */
    public User(final String id, final String name, final String phoneNo, final String pincode, final Gender gender) {
        this.id = id;
        this.name = name;
        this.phoneNo = phoneNo;
        this.pincode = pincode;
        this.gender = gender;
    }

    /**
     * @return id of the user
     */
    public String getId() {
        return id;
    }

    /**
     * @return name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * @return phone number of the user
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * @return pincode of the user
     */
    public String getPincode() {
        return pincode;
    }

    /**
     * @return gender of the user
     */
    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", pincode='" + pincode + '\'' +
                ", gender=" + gender +
                '}';
    }
}
