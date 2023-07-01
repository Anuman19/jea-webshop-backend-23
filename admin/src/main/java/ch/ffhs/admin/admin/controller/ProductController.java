package ch.ffhs.admin.admin.controller;

import ch.ffhs.library.library.dto.ProductDto;
import ch.ffhs.library.library.repository.ProductRepository;
import ch.ffhs.library.library.service.CategoryService;
import ch.ffhs.library.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ProductController is created to map incoming URL request related to the products
 * and is responsible for displaying the appropriate views
 */
@RestController
@CrossOrigin
public class ProductController {
    // injects service for product management
    @Autowired
    private ProductService productService;

    // injects service for category management
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductRepository productRepository;

    /**
     * method is called when an HTTP GET request is sent to the /products URL
     *
     * @return String with view's name "shop.html"
     */
    @GetMapping("/products")
    public ResponseEntity<?> products() {

        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    /**
     * method is called when an HTTP GET request is sent to the /find-products/{id} URL
     *
     * @param id of the product
     * @return String with view's name "product-detail.html"
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<?> findProductById(@PathVariable("id") Long id) {

        try {
            return new ResponseEntity<>(productService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.CONFLICT);

        }
    }

    /**
     * method is called when an HTTP GET request is sent to the /products-in-category/{id} URL
     *
     * @param categoryId for the category
     */
    @GetMapping("/products-in-category/{id}")
    public ResponseEntity<?> getProductsInCategory(@PathVariable("id") Long categoryId) {

        try {
            return new ResponseEntity<>(productService.getProductsInCategory(categoryId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.CONFLICT);
        }
    }


    @PostMapping("/add-product")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto product) {
        try {
            product.setCategory(categoryService.findById(product.getCategory().getId()));
            return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.CONFLICT);

        }
    }

    /**
     * method is called when an HTTP PUT request is sent to the /update-product/{id} URL
     * and loads the form for updating an existing product
     *
     * @return String with attribute or redirected URL
     */
    @PutMapping("/update-product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {

        productDto.setId(id);
        try {
            productDto.setCategory(categoryService.findById(productService.getById(id).getCategory().getId()));
            return new ResponseEntity<>(productService.update(productDto), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.CONFLICT);
        }

    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {

        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}
