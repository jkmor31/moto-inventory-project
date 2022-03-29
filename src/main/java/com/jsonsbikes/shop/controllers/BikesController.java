package com.jsonsbikes.shop.controllers;

import com.jsonsbikes.shop.models.Bike;
import com.jsonsbikes.shop.repositories.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BikesController {
    @Autowired
    private BikeRepository bikeRepository;

    @GetMapping("/bikes")
    List<Bike> list() {
        return bikeRepository.findAll();
    }

    @GetMapping("/bikes/{bike_id}")
    Bike one(@PathVariable Long bike_id) {
        return bikeRepository.findById(bike_id)
                .orElseThrow(() -> new BikeNotFoundException());
    }

    @GetMapping("/bikes/search")
    List<Bike> lookup(
            @RequestParam(required = false) String vin,
            @RequestParam(required = false) String make,
            @RequestParam(required = false) String type) {
        if (vin != null) {
            return bikeRepository.findByVin(vin);
        } else if (make != null) {
            return bikeRepository.findByMake(make);
        } else if (type != null) {
            return bikeRepository.findByType(type);
        } else {
            throw new BikeNotFoundException();
        }
    }

    @PostMapping("/bikes")
    Bike newBike(@RequestBody Bike newBike) {
        return bikeRepository.saveAndFlush(newBike);
    }

    @PutMapping("/bikes/{bike_id}")
    Bike update(@RequestBody Bike updatedBike, @PathVariable Long bike_id) {
        return bikeRepository.findById(bike_id)
                .map(bike -> {
                    bike.setMake(updatedBike.getMake());
                    bike.setPrice(updatedBike.getPrice());
                    bike.setType(updatedBike.getType());
                    bike.setPurchased(updatedBike.getPurchased());
                    bike.setVin(updatedBike.getVin());
                    return bikeRepository.saveAndFlush(bike);
                }).orElseThrow(() -> new BikeNotFoundException());
    }

    @DeleteMapping("/bikes/{bike_id}")
    void delete(@PathVariable Long bike_id) {
        bikeRepository.deleteById(bike_id);
    }

    @PatchMapping("/bikes/{bike_id}")
    // Request body isn't really needed for an unsold -> sold toggle
    Bike purchase(/*@RequestBody Bike purchasedBike, */@PathVariable Long bike_id) {
        return bikeRepository.findById(bike_id)
                .map(bike -> {
                    bike.setPurchased(true);
                    return bikeRepository.saveAndFlush(bike);
                })
                .orElseThrow(() -> new BikeNotFoundException());
    }
}
