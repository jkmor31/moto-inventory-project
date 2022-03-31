package com.jsonsbikes.shop.controllers;

import com.jsonsbikes.shop.models.Bike;
import com.jsonsbikes.shop.repositories.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
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
        List<Bike> foundBikes = bikeRepository.findByVin(newBike.getVin());
        if (foundBikes.isEmpty()) {
            return bikeRepository.saveAndFlush(newBike);
        } else {
            throw new BikeAlreadyExistsException();
        }
    }

    @PutMapping("/bikes/{bike_id}")
    Bike update(@RequestBody Bike updatedBike, @PathVariable Long bike_id) {
        Optional<Bike> foundBike = bikeRepository.findById(bike_id);
        if (foundBike.isPresent()) {
            if (!foundBike.get().getVin().equals(updatedBike.getVin())){
                List<Bike> foundBikesWithSameVin = bikeRepository.findByVin(updatedBike.getVin());
                if (!foundBikesWithSameVin.isEmpty()) {
                    throw new BikeAlreadyExistsException();
                } else {
                    foundBike.get().setVin(updatedBike.getVin().toUpperCase(Locale.ROOT));
                }
            }
            foundBike.get().setMake(updatedBike.getMake());
            foundBike.get().setPrice(updatedBike.getPrice());
            foundBike.get().setType(updatedBike.getType());
            foundBike.get().setPurchased(updatedBike.getPurchased());
            return bikeRepository.saveAndFlush(foundBike.get());
        } else {
                throw new BikeNotFoundException();
            }
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
