package com.jsonsbikes.shop.controllers;

public class BikeNotFoundException extends RuntimeException{
    BikeNotFoundException() {
        super("Item was not found in inventory!");
    }
}
