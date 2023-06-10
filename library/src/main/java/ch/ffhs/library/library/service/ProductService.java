package ch.ffhs.library.library.service;

import ch.ffhs.library.library.dto.ProductDto;
import ch.ffhs.library.library.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();
    Product save(MultipartFile imageProduct, ProductDto product);
    Product update(MultipartFile imageProduct, ProductDto product);
    void enableById(Long id);
    ProductDto getById(Long id);
}
