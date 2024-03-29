package com.example.optics.models;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

/**
 * Класс-сущность для корзины
 */
@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "t_basket")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "count")
    private int count;
    @Column(name = "sum")
    private int sum;
}
