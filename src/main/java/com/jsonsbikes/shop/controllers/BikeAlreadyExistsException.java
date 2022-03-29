package com.jsonsbikes.shop.controllers;

public class BikeAlreadyExistsException extends RuntimeException{
    BikeAlreadyExistsException() {
        super("An item already exists with this data");
    }
}
