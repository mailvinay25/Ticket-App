/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walmart.ticketingapp.model;

/**
 *
 * @author Vinay Chaitankar
 * 
 * Enum "Level" created to represent venue levels and their related constants 
 */
public enum Level {
    ORCHESTRA(1, "Orchestra", 100.0),
    MAIN(2, "Main", 75.0),
    BALCONY_1(3, "Balcony 1", 50.0),
    BALCONY_2(4, "Balcony 2", 40.0);

    private final int levelId;
    private final String levelName;
    private final double price;

    private Level(int levelId, String levelName, double price) {
        this.levelId = levelId;
        this.levelName = levelName;
        this.price = price;
    }

    public int getLevelId() {
        return this.levelId;
    }

    public String getLevelName() {
        return this.levelName;
    }

    public double getPrice() {
        return this.price;
    }
}
