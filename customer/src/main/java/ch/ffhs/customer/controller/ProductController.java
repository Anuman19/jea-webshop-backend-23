package ch.ffhs.customer.controller;

import ch.ffhs.library.library.repository.OrderItemRepository;
import ch.ffhs.library.library.repository.ProductRepository;
import ch.ffhs.library.library.service.CategoryService;
import ch.ffhs.library.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    private OrderItemRepository orderItemRepository;

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



}
