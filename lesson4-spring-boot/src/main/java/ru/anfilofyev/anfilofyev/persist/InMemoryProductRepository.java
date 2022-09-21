//package ru.anfilofyev.anfilofyev.persist;
//
//import org.springframework.stereotype.Repository;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.atomic.AtomicLong;
//
//@Repository
//public class InMemoryProductRepository implements ProductRepository{
//
//
//    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();
//
//    private final AtomicLong identity = new AtomicLong(0);
//
//    @PostConstruct
//    public void init() {
//        this.insert(new Product("Table lamp", 11f, "white", true));
//        this.insert(new Product("Milk", 1f, "Default", false));
//        this.insert(new Product("Headset", 0.7f, "black", false));
//        this.insert(new Product("Vacuum cleaner", 100500f, "red", true));
//        this.insert(new Product("Honey", 4f, "Default", true));
//    }
//
//    public List<Product> findAll() {
//        return new ArrayList<>(productMap.values());
//    }
//
//    public Optional<Product> productById(long id) {
//        return Optional.ofNullable(productMap.get(id));
//    }
//
//    public void insert(Product product) {
//        long id = identity.incrementAndGet();
//        product.setId(id);
//        productMap.put(id, product);
//    }
//
//    public Product save(Product product) {
//        if (product.getId() == null) {
//            product.setId(identity.incrementAndGet());
//        }
//        productMap.put(product.getId(), product);
//        return product;
//    }
//
//    public void deleteById(long id) {
//        productMap.remove(id);
//    }
//
//}
