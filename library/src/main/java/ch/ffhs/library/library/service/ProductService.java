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
    Product save(ProductDto product);
    Product update(ProductDto product);
    void enableById(Long id);
    ProductDto getById(Long id);

    Page<ProductDto> pageProducts(int pageNo);

    Page<ProductDto> searchProducts(int pageNo, String keyword);

    /* Customer */
    List<Product> getAllProducts();

    List<Product> listViewProducts();

    Product getProductById(Long id);

    List<Product> getRelatedProducts(Long categoryId);

    List<Product> getProductsInCategory(Long categoryId);
}
