package com.jsonsbikes.shop.repositories;

import com.jsonsbikes.shop.models.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {

    List<Bike> findByVin(String vin);

    List<Bike> findByMake(String make);

    List<Bike> findByType(String type);
}
