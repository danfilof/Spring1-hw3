package ru.anfilofyev.anfilofyev.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.anfilofyev.anfilofyev.model.Product;

import javax.validation.Valid;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

    Page<Product> findAllByTitleLike(String titleFilter, Pageable pageable);

    @Query(value = """
            select * from products p 
            where p.title like :title
            """,
            countQuery = """
            select count(*) from products p 
            where p.title like :title
                    """,
            nativeQuery = true)
    Page<Product> productsByFilter(String title, Pageable pageable);
}
