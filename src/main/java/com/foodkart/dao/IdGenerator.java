package com.foodkart.dao;

import java.util.UUID;

/**
 * Simple class to generate id's for the entities of the system
 */
public class IdGenerator {
    /**
     * @return id for the entities
     */
    public static String generateId(){
        return UUID.randomUUID().toString();
    }
}
