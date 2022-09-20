package ru.anfilofyev.anfilofyev.persist;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAll();

    Optional<Product> productById(long id);

    Product save (Product product);

    void deleteById(long id);
}
