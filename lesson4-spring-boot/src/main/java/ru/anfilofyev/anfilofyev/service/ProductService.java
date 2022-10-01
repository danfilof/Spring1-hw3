package ru.anfilofyev.anfilofyev.service;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.anfilofyev.anfilofyev.model.Product;
import ru.anfilofyev.anfilofyev.model.QProduct;
import ru.anfilofyev.anfilofyev.model.dto.ProductDto;
import ru.anfilofyev.anfilofyev.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDto> findAllByFilter(String titleFilter) {

        QProduct product = QProduct.product;
        BooleanBuilder predicate = new BooleanBuilder();

        if (titleFilter != null && !titleFilter.isBlank()) {
            predicate.and(product.title.contains(titleFilter.trim()));
        }

        return StreamSupport.stream(productRepository.findAll(predicate).spliterator(), true)
                .map(productFromDb -> {
                    ProductDto dto = new ProductDto();
                    dto.setId(productFromDb.getId());
                    dto.setTitle(productFromDb.getTitle());
                    dto.setPrice(productFromDb.getPrice());
                    dto.setColor(productFromDb.getColor());
                    return dto;
                }).collect(Collectors.toList());
    }

    public Optional<ProductDto> findProductById(Long id) {
        return productRepository.findById(id).map(ProductService::productToDto);
    }

    public ProductDto save(ProductDto dto) {
        return productToDto(productRepository.save(new Product(dto.getId(), dto.getTitle(), dto.getPrice(), dto.getColor(), dto.isStockStatus())));
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public static ProductDto productToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice(), product.getColor(), product.isStockStatus());
    }
}
