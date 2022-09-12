package ru.anfilofyev;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import ru.anfilofyev.model.Product;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class ProductRepository {

    EntityManagerFactory entityManagerFactory = (EntityManagerFactory) new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    EntityManager entityManager = entityManagerFactory.createEntityManager();

    @PostConstruct
    public void init () {
        entityManager.getTransaction().begin();

        entityManager.persist(new Product("Honey", 11f, "Yellowish"));
        entityManager.persist(new Product("Samsung galaxy 100500", 100500f, "Black"));
        entityManager.persist(new Product("Milk", 1f, "Default"));

        entityManager.getTransaction().commit();
    }

    public Product findByID(Long id) {
         return entityManager.find(Product.class, 1L);
    }

    public List<Product> findAll() {
        List <Product> products = entityManager.createQuery("select p from Product p").getResultList();

        for (Product productFromDB : products) {
            System.out.println(productFromDB);
        }
        return products;
    }

    public void deleteById(Long id) {

        entityManager.getTransaction().begin();

        Product user = entityManager.find(Product.class, id);
        entityManager.remove(user);
        entityManager.createQuery("delete from Product p where p.id = " + id).executeUpdate();

        entityManager.getTransaction().commit();
    }


    public Product saveOrUpdate(Product product) {
        entityManager.getTransaction().begin();

        Product productToChange = entityManager.find(Product.class, product.getId());
        productToChange.setTitle(product.getTitle());
        productToChange.setPrice(product.getPrice());
        productToChange.setColor(product.getColor());

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
