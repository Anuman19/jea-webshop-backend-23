package ch.ffhs.library.library.service;

import ch.ffhs.library.library.dto.ProductDto;
import ch.ffhs.library.library.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * The ProductService interface thus defines the basic
 * operations required for managing products
 */
public interface ProductService {
    /* Admin */
    List<ProductDto> findAll();

    ProductDto save(ProductDto product);

    ProductDto update(ProductDto product);


    ProductDto getById(Long id);


    Product getProductById(Long id);


    List<ProductDto> getProductsInCategory(Long categoryId);


}
