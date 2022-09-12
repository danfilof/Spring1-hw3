package ru.anfilofyev.anfilofyev.persist;


import jdk.jfr.BooleanFlag;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Product {

    @NotNull
    private Long id;

    @NotBlank
    private String title;


    private float price;

    @NotNull
    private String color;

    @NotBlank(message = "Cannot be empty. The item is either in stock or not.")
    private boolean stockStatus;

    public Product(String title, float price, String color, boolean stockStatus) {
        this.title = title;
        this.price = price;
        this.color = color;
        this.stockStatus = stockStatus;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public float getPrice() {
//        return price;
//    }
//
//    public void setPrice(float price) {
//        this.price = price;
//    }
}
