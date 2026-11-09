package org.example.jpaassign.week09_jpa_project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;


public record RestaurantRequest(
        @NotBlank @Size(max = 200) String name,
        @NotBlank @Size(max = 100) String category,
        @NotBlank @Size(max = 50) String address,
        @NotNull @PositiveOrZero double rating,
        @NotBlank @Size(max = 50) String signatureMenu
){}
