package com.jsonsbikes.shop.controllers;

import com.jsonsbikes.shop.models.Bike;
import com.jsonsbikes.shop.repositories.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bikes")
public class BikesController {
    //@Autowired
    private BikeRepository bikeRepository;

    @GetMapping("/")
    List<Bike> list() {
        return bikeRepository.findAll();
    }

    @GetMapping("/{vin}")
    Bike get(@RequestParam String vin) {
        return bikeRepository.findByVin(vin);
    }

    @GetMapping("/{make}")
    List<Bike> listMake(@RequestParam String make) {
        return bikeRepository.findByMake(make);
    }

    @GetMapping("/{type}")
    List<Bike> listType(@RequestParam String type) {
        return bikeRepository.findByType(type);
    }

    @PostMapping("/")
    Bike newBike(@RequestBody Bike newBike) {
        return bikeRepository.saveAndFlush(newBike);
    }

    @PutMapping("/{bike_id}")
    Bike update(@RequestBody Bike updatedBike, @PathVariable Long id) {
        return bikeRepository.findById(id)
                .map(bike -> {
                    bike.setMake(updatedBike.getMake());
                    bike.setPrice(updatedBike.getPrice());
                    bike.setType(updatedBike.getType());
                    bike.setPurchased(updatedBike.getPurchased());
                    bike.setVin(updatedBike.getVin());
                    return bikeRepository.saveAndFlush(bike);
                }).orElseGet(() -> {
                    updatedBike.setBike_id(id);
                    return bikeRepository.saveAndFlush(updatedBike);
                });
    }

    @DeleteMapping("/{bike_id}")
    void delete(@PathVariable Long id) {
        bikeRepository.deleteById(id);
    }


}
