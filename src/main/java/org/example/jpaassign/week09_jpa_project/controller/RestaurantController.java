package org.example.jpaassign.week09_jpa_project.controller;

import jakarta.validation.Valid;
import org.example.jpaassign.week09_jpa_project.dto.RestaurantRequest;
import org.example.jpaassign.week09_jpa_project.dto.RestaurantResponse;
import org.example.jpaassign.week09_jpa_project.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    private final RestaurantService service;

    public RestaurantController(RestaurantService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<RestaurantResponse> create(@Valid @RequestBody RestaurantRequest request) {
        RestaurantResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/restaurants/" + created.id())).body(created);
    }

    @GetMapping
    public List<RestaurantResponse> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public RestaurantResponse findById(@PathVariable Long id) { return service.findById(id); }

    @PutMapping("/{id}")
    public RestaurantResponse update(@PathVariable Long id, @Valid @RequestBody RestaurantRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}