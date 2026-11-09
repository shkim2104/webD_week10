package org.example.jpaassign.week09_jpa_project.service;

import jakarta.validation.Valid;
import org.example.jpaassign.week09_jpa_project.dto.RestaurantRequest;
import org.springframework.stereotype.Service;
import org.example.jpaassign.week09_jpa_project.dto.RestaurantResponse;
import org.example.jpaassign.week09_jpa_project.entity.Restaurant;
import org.example.jpaassign.week09_jpa_project.repository.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public RestaurantResponse create(RestaurantRequest request) {
        Restaurant saved = repository.save(new Restaurant(request)); // INSERT
        return RestaurantResponse.from(saved);
    }

    public List<RestaurantResponse> findAll() {
        return repository.findAll().stream().map(RestaurantResponse::from).toList();
    }

    public RestaurantResponse findById(Long id) {
        return RestaurantResponse.from(findEntity(id));
    }

    public RestaurantResponse update(Long id, @Valid RestaurantRequest request) {
        Restaurant restaurant = findEntity(id);
        restaurant.update(request);
        // Week 09: explicitly save (Week 10 changes this to Dirty Checking).
        return RestaurantResponse.from(repository.save(restaurant));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found: " + id);
        }
        repository.deleteById(id);
    }

    private Restaurant findEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Restaurant not found: " + id));
    }
}
