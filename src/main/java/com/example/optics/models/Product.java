package com.example.optics.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "t_product")
public class Product {
    @Id
    private Long id;
    private String category;
    private String brandName;
    private int price;
    private String path;

}
