package org.binar.microservice.filmservice.filmservice.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Orders")
public class OrderEntity {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "order_time")
    private LocalDateTime orderTime;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(mappedBy = "orderEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderDetailEntity> orderDetailEntities;
}
