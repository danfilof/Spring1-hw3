package ru.anfilofyev.anfilofyev.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByTitleLike(String titleFilter);

    @Query(value = """
            select * from products p 
            where p.title like :title
            """, nativeQuery = true)
    List<Product> productByTitle(String title);
}
