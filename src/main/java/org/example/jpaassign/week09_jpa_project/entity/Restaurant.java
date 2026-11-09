package org.example.jpaassign.week09_jpa_project.entity;

import jakarta.persistence.*;
import org.example.jpaassign.week09_jpa_project.dto.RestaurantRequest;

import javax.naming.Name;

@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false, length = 200)
    private String category;

    @Column(nullable = false, length = 200)
    private String address;

    @Column(nullable = false)
    private double rating;

    @Column(nullable = false, length = 200)
    private String signatureMenu;

    protected Restaurant(){}

    public Restaurant(RestaurantRequest request){
        this.name = request.name();
        this.category = request.category();
        this.address = request.address();
        this.rating = request.rating();
        this.signatureMenu = request.signatureMenu();
    }

    public void update(RestaurantRequest request){
        this.name = request.name();
        this.category = request.category();
        this.address = request.address();
        this.rating = request.rating();
        this.signatureMenu = request.signatureMenu();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getAddress() {
        return address;
    }

    public double getRating() {
        return rating;
    }

    public String getSignatureMenu() {
        return signatureMenu;
    }

}
