package ru.anfilofyev.anfilofyev.service;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.anfilofyev.anfilofyev.model.Product;
import ru.anfilofyev.anfilofyev.model.QProduct;
import ru.anfilofyev.anfilofyev.model.dto.ProductDto;
import ru.anfilofyev.anfilofyev.model.mapper.ProductDtoMapper;
import ru.anfilofyev.anfilofyev.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDtoMapper mapper;
    private final ProductRepository productRepository;

    public List<ProductDto> findAllByFilter(String titleFilter) {

        QProduct product = QProduct.product;
        BooleanBuilder predicate = new BooleanBuilder();

        if (titleFilter != null && !titleFilter.isBlank()) {
            predicate.and(product.title.contains(titleFilter.trim()));
        }

        return StreamSupport.stream(productRepository.findAll(predicate).spliterator(), true)
                .map(mapper::map).collect(Collectors.toList());
    }

    public Optional<ProductDto> findProductById(Long id) {
        return productRepository.findById(id).map(mapper::map);
    }

    public void save(ProductDto dto) {
        productRepository.save(mapper.map(dto));
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
