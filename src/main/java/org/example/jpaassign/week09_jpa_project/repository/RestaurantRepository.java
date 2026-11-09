package org.example.jpaassign.week09_jpa_project.repository;

import org.example.jpaassign.week09_jpa_project.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{


    List<Restaurant> findByCategory(String category);

    List<Restaurant> findByRatingBetween(double min, double max);

    List<Restaurant> findByCategoryOrderByRatingDesc(String category);
}
