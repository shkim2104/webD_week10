package org.example.jpaassign.week09_jpa_project.repository;

import org.example.jpaassign.week09_jpa_project.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Q3. JpaRepository<Restaurant, Long>
//     Restaurant: 이 Repository가 다루는 엔티티 타입입니다.
//     Long: 그 엔티티의 기본키(@Id) 타입입니다.

// Q4. JpaRepository가 기본으로 제공하는 CRUD 메서드입니다. (직접 선언하지 않아도 사용할 수 있습니다.)
//     save(entity)     : 엔티티를 저장합니다. 새 데이터면 INSERT, 기존 데이터면 UPDATE 합니다.
//     findById(id)     : 기본키로 엔티티 1개를 조회합니다. (Optional 반환)
//     findAll()        : 테이블의 모든 데이터를 조회합니다.
//     existsById(id)   : 해당 id의 데이터가 존재하는지 true/false로 확인합니다.
//     deleteById(id)   : 해당 id의 데이터를 삭제합니다.
//     count()          : 전체 데이터 개수를 반환합니다.
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

    // Q6. 기본 제공 메서드 vs Query Method
    //     기본 제공 메서드(save, findById 등)는 JpaRepository에 이미 구현되어 있어서 바로 사용할 수 있습니다.
    //     Query Method는 개발자가 규칙에 맞게 메서드 이름만 선언하면(findBy + 필드명 + 조건)
    //     Spring Data JPA가 이름을 분석해서 SQL을 자동으로 만들어 줍니다.

    // 단일 조건 검색: WHERE category = ?
    List<Restaurant> findByCategory(String category);

    // 복합 조건 검색: WHERE rating BETWEEN ? AND ?
    List<Restaurant> findByRatingBetween(double min, double max);

    // 정렬 포함 검색: WHERE category = ? ORDER BY rating DESC
    List<Restaurant> findByCategoryOrderByRatingDesc(String category);
}
