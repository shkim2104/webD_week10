package org.example.jpaassign.week09_jpa_project.service;

import jakarta.validation.Valid;
import org.example.jpaassign.week09_jpa_project.dto.RestaurantRequest;
import org.springframework.stereotype.Service;
import org.example.jpaassign.week09_jpa_project.dto.RestaurantResponse;
import org.example.jpaassign.week09_jpa_project.entity.Restaurant;
import org.example.jpaassign.week09_jpa_project.repository.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    // Q7. save()는 엔티티의 id 값으로 새 데이터인지 판단합니다.
    //     id가 없으면(새 엔티티) persist → INSERT 쿼리가 실행됩니다.
    //     id가 있으면(기존 엔티티) merge → DB 값과 합쳐서 UPDATE 쿼리가 실행됩니다.
    //     여기서는 new Restaurant(request)로 id가 없는 객체를 넘기므로 INSERT가 실행됩니다.
    public RestaurantResponse create(RestaurantRequest request) {
        Restaurant saved = repository.save(new Restaurant(request));
        return RestaurantResponse.from(saved);
    }

    public List<RestaurantResponse> findAll() {
        return repository.findAll().stream().map(RestaurantResponse::from).toList();
    }

    public RestaurantResponse findById(Long id) {
        return RestaurantResponse.from(findEntity(id));
    }

    public List<RestaurantResponse> searchByCategory(String category) {
        return repository.findByCategory(category).stream().map(RestaurantResponse::from).toList();
    }

    public List<RestaurantResponse> searchByRating(double min, double max) {
        return repository.findByRatingBetween(min, max).stream().map(RestaurantResponse::from).toList();
    }

    public List<RestaurantResponse> searchTopRated(String category) {
        return repository.findByCategoryOrderByRatingDesc(category).stream().map(RestaurantResponse::from).toList();
    }

    // Q8. @Transactional과 Dirty Checking
    //     @Transactional이 붙으면 메서드 전체가 하나의 트랜잭션으로 실행되고,
    //     조회한 엔티티는 영속성 컨텍스트가 관리하는 상태(영속 상태)가 됩니다.
    //     JPA는 조회 시점의 상태(스냅샷)를 저장해 두었다가, 트랜잭션이 끝날 때(커밋 시점)
    //     현재 엔티티와 비교해서 바뀐 필드가 있으면 UPDATE 쿼리를 자동으로 실행합니다. 이것이 Dirty Checking입니다.
    //     따라서 save()를 호출하지 않아도 변경 내용이 DB에 반영됩니다.
    //     @Transactional이 없으면 트랜잭션이 바로 끝나 엔티티 변경을 감지하지 못하므로 DB에 반영되지 않습니다.
    @Transactional
    public RestaurantResponse update(Long id, @Valid RestaurantRequest request) {
        Restaurant restaurant = findEntity(id); // 영속 상태로 조회합니다.
        restaurant.update(request);             // 필드 값만 변경합니다. (save() 호출 없음)

        return RestaurantResponse.from(restaurant);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found: " + id);
        }
        repository.deleteById(id);
    }

    // Q5. findById()가 Optional을 반환하는 이유
    //     해당 id의 데이터가 DB에 없을 수도 있기 때문입니다.
    //     null을 그대로 반환하면 NullPointerException이 날 수 있으므로,
    //     Optional로 감싸서 "값이 없을 수 있음"을 명확히 하고 orElseThrow()처럼 처리 방법을 강제합니다.
    //     여기서는 값이 없으면 404 NOT_FOUND 예외를 발생시킵니다.
    private Restaurant findEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Restaurant not found: " + id));
    }
}
