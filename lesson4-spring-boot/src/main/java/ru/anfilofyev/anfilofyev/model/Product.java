package ru.anfilofyev.anfilofyev.model;


import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 1024)
    private String title;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private boolean stockStatus;

    public Product(String title, float price, String color, boolean stockStatus) {
        this.title = title;
        this.price = price;
        this.color = color;
        this.stockStatus = stockStatus;
    }
}
