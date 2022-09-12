package ru.anfilofyev;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.anfilofyev.model.Product;

import java.util.List;

public class Main {

    public static void main(String[] args) {


//        INSERT
//        entityManager.getTransaction().begin();
//
//        entityManager.persist(new Product("Honey", 11f, "Yellowish"));
//        entityManager.persist(new Product("Samsung galaxy 100500", 100500f, "Black"));
//        entityManager.persist(new Product("Milk", 1f, "Default"));
//
//        entityManager.getTransaction().commit();



//        SELECT
//       Product product = entityManager.find(Product.class, 1L);



//        HQL
//        List <Product> products = entityManager.createQuery("select p from Product p where p.id in (2, 3)").getResultList();
//
//        for (Product productFromDB : products) {
//            System.out.println(productFromDB);
//        }

//        UPDATE
        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 1L);
        product.setTitle("RTX 2070ti");

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
