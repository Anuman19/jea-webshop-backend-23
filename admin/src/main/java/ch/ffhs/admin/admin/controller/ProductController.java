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
     * @return ResponseEntity with all products and 200
     */
    @GetMapping("/products")
    public ResponseEntity<?> products() {

        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    /**
     * method is called when an HTTP GET request is sent to the /find-products/{id} URL
     *
     * @param id of the product
     * @return ResponseEntity product by id and 200
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
     * @return ResponseEntity products in a category and 200
     */
    @GetMapping("/products-in-category/{id}")
    public ResponseEntity<?> getProductsInCategory(@PathVariable("id") Long categoryId) {

        try {
            return new ResponseEntity<>(productService.getProductsInCategory(categoryId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.CONFLICT);
        }
    }


    /**
     * method is called when an HTTP POST request is sent to the /add-product URL
     *
     * @param product DTO
     * @return ResponseEntity new product DTO and 201
     */
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
     * @param id         of product
     * @param productDto DTO of product
     * @return ResponseEntity with updated Product and 201
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

    /**
     * method is called when an HTTP DELETE request is sent to the /delete-product/{id} URL
     * and loads the form for updating an existing product
     *
     * @param id of product
     * @return ResponseEntity with String "deleted" and 201
     */
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
