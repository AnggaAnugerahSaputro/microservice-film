package org.binar.microservice.filmservice.filmservice.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "orderDetail")
public class OrderDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String orderDetailId;

    @Column(name = "totalPrice")
    private BigDecimal totalPrice;

    @Column(name = "createdAt")
    private LocalTime createdAt;

    @Column(name = "updatedAt")
    private LocalDate updateAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private OrderEntity orderEntity;

}
