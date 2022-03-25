package com.jsonsbikes.shop.controllers;

import com.jsonsbikes.shop.repositories.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bikes")
public class BikesController {
    @Autowired
    private BikeRepository bikeRepository;
}
