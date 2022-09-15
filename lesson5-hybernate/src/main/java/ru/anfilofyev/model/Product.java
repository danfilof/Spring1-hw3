package ru.anfilofyev.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "findAllProducts", query = "Select p from Product p"),
        @NamedQuery(name = "countAllProducts", query = "Select count(p) from Product p"),
        @NamedQuery(name = "deleteProductById", query = "delete from Product p where p.id = :id")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 1024)
    private String title;

    @Column(nullable = false, unique = true)
    private float price;

    @Column(nullable = false)
    private String color;

    @OneToMany(mappedBy = "product",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
            orphanRemoval = true)
    private List<Good> goods;

    @OneToOne(mappedBy = "product",
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Customer customer;

    public Product(String title, List<Good> goods, float price, String color) {
        this.title = title;
        this.goods = goods;
        this.price = price;
        this.color = color;
    }
}
