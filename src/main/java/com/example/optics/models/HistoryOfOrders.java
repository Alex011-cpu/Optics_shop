package com.example.optics.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Date;

/**
 * Класс сущность для истории покупок
 */
@Data
@Entity
@Table(name = "t_history_of_orders")
@AllArgsConstructor
@NoArgsConstructor
public class HistoryOfOrders {
    @Id
    @SequenceGenerator(name = "history_seq", sequenceName = "history_sequence", allocationSize = 1)
    @GeneratedValue(generator = "history_seq",strategy = GenerationType.SEQUENCE)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;
    private int count;
    private int sum;
    private String creationDate;
}
