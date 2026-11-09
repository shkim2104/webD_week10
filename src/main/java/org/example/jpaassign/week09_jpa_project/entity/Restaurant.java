package org.example.jpaassign.week09_jpa_project.entity;

import jakarta.persistence.*;
import org.example.jpaassign.week09_jpa_project.dto.RestaurantRequest;

import javax.naming.Name;

// Q1. @Entity: 이 클래스를 DB 테이블과 매핑되는 JPA 엔티티로 지정합니다.
//     @Id: 해당 필드를 테이블의 기본키(PK)로 지정합니다.
//     @GeneratedValue: PK 값을 직접 넣지 않고 DB가 자동 생성되게 만들어 집니다. (IDENTITY = AUTO_INCREMENT)
@Entity
@Table(name = "restaurants")
// Q2. 클래스는 테이블, 필드는 컬럼, 객체 하나는 행(row) 하나에 대응된다.
//     @Table(name = "...")으로 테이블 이름을, @Column으로 컬럼 설정을 지정하고,
//     Hibernate가 이 매핑을 보고 SQL을 만들어 실행한다. (ddl-auto=update면 테이블도 자동 생성)
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
