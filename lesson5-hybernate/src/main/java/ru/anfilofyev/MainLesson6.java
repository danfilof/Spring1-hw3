package ru.anfilofyev;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import ru.anfilofyev.model.Good;
import ru.anfilofyev.model.GoodType;
import ru.anfilofyev.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainLesson6 {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();


        Good prodName = new Good(GoodType.PRODUCER_NAME, "Nvidia");
        Good prodSupEmail = new Good(GoodType.PRODUCER_SUPPORT_EMAIL, "nvidia-support.com");
        List<Good> goodList = Arrays.asList(prodName, prodSupEmail);

        Product product = new Product("Samsung galaxy 100500",goodList , 100500f, "Black");

        entityManager.getTransaction().begin();
        goodList.forEach(good -> good.setProduct(product));

        entityManager.persist(product);

        entityManager.getTransaction().commit();



        List<Product> products = entityManager.createNamedQuery("findAllProducts", Product.class).getResultList();

        List<Product> users = entityManager.createQuery("select p from Product p " +
                "join fetch p.goods", Product.class).getResultList();

        List<Good> contacts = users.get(0).getGoods();
        for (Product product1 : products) {
            product1.getGoods().forEach(System.out::println);
        }

        Product product1 = new Product("Product2", goodList, 11F, "Black");
        Product product2 = new Product("Product2", goodList, 1F, "Default");
        product2.setPrice(2F);

        goodList.forEach(good -> good.setProduct(product2));
        entityManager.getTransaction().begin();
        entityManager.persist(product2);
        entityManager.getTransaction().commit();

                entityManager.close();
        entityManagerFactory.close();
    }
}
