package org.example.jpaassign.week09_jpa_project.repository;

import org.example.jpaassign.week09_jpa_project.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

}
