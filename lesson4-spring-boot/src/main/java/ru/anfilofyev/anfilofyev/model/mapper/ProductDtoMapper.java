package ru.anfilofyev.anfilofyev.model.mapper;

import org.mapstruct.Mapper;
import ru.anfilofyev.anfilofyev.model.Product;
import ru.anfilofyev.anfilofyev.model.dto.ProductDto;

@Mapper
public interface ProductDtoMapper {

    ProductDto map(Product product);

    Product map(ProductDto dto);
}
