package com.jsonsbikes.shop.repositories;

import com.jsonsbikes.shop.models.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BikeRepository extends JpaRepository<Bike, Long> {

    Bike findByVin(String vin);

    List<Bike> findByMake(String make);

    List<Bike> findByType(String type);
}
