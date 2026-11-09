package org.example.jpaassign.week09_jpa_project.dto;

import org.example.jpaassign.week09_jpa_project.entity.Restaurant;

import java.time.LocalDateTime;

public record RestaurantResponse(Long id, String name, String category , String address , Double rating , String signatureMenu,
                                 LocalDateTime createdAt, LocalDateTime updatedAt) {
    public static RestaurantResponse from(Restaurant restaurant) {
        return new RestaurantResponse(restaurant.getId(), restaurant.getName(), restaurant.getCategory(),
                restaurant.getAddress(), restaurant.getRating(), restaurant.getSignatureMenu(),
                restaurant.getCreatedAt(), restaurant.getUpdatedAt());
    }
}
