package com.jsonsbikes.shop.models;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity(name = "bikes")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Bike {
    @Id
    @SequenceGenerator(name = "seqAfterBatch", initialValue = 251)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAfterBatch")
    private Long bike_id;
    // Need to make vin unique
    private String vin;
    private String make;
    private String type;
    private Float price;
    private Boolean purchased;

    public Bike() {

    }

    public Long getBike_id() {
        return bike_id;
    }

    public void setBike_id(Long bike_id) {
        this.bike_id = bike_id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Boolean getPurchased() {
        return purchased;
    }

    public void setPurchased(Boolean purchased) {
        this.purchased = purchased;
    }
}
