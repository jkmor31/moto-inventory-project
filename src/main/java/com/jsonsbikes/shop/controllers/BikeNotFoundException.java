package com.jsonsbikes.shop.controllers;

public class BikeNotFoundException extends RuntimeException{
    BikeNotFoundException(Long bike_id) {
        super("Item was not found in inventory!");
    }
}
