package ch.ffhs.customer.controller;

import ch.ffhs.library.library.dto.CategoryDto;
import ch.ffhs.library.library.model.Category;
import ch.ffhs.library.library.model.Product;
import ch.ffhs.library.library.repository.ProductRepository;
import ch.ffhs.library.library.service.CategoryService;
import ch.ffhs.library.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @param model represents the data (list with categories and products) which is used to pass data to the view
     * @return String with view's name "shop.html"
     */
    @GetMapping("/products")
    public List<Product> products() {


        return productService.getAllProducts();
    }

    /**
     * method is called when an HTTP GET request is sent to the /find-products/{id} URL
     *
     * @param id    of the product
     * @param model represents the data which is used to pass data to the view
     * @return String with view's name "product-detail.html"
     */
    @GetMapping("/find-products/{id}")
    public String findProductById(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        Long categoryId = product.getCategory().getId();
        List<Product> products = productService.getRelatedProducts(categoryId);
        model.addAttribute("product", product);
        model.addAttribute("products", products);
        return "product-detail";
    }

    /**
     * method is called when an HTTP GET request is sent to the /products-in-category/{id} URL
     *
     * @param categoryId for the category
     * @param model      represents the data which is used to pass data to the view
     * @return String with view's name "products-in-category.html"
     */
    @GetMapping("/products-in-category/{id}")
    public String getProductsInCategory(@PathVariable("id") Long categoryId, Model model) {
        Category category = categoryService.findById(categoryId);
        List<CategoryDto> categoryDtoList = categoryService.getCategoryAndProduct();
        List<Product> products = productService.getProductsInCategory(categoryId);
        model.addAttribute("category", category);
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryDtoList);
        return "products-in-category";
    }

    /**
     * method is called when an HTTP GET request is sent to the /high-price URL
     * and is used as a filter for high prices
     *
     * @param model represents the data which is used to pass data to the view
     * @return String with view's name "filter-high-price.html"
     */
    @GetMapping("/high-price")
    public String filterHighPrice(Model model) {
        List<Category> categories = categoryService.findAllByActivated();
        List<CategoryDto> categoryDtoList = categoryService.getCategoryAndProduct();
        List<Product> products = productService.filterHighPrice();
        model.addAttribute("categoryDtoList", categoryDtoList);
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "filter-high-price";
    }

    /**
     * method is called when an HTTP GET request is sent to the /low-price URL
     * and is used as a filter for low prices
     *
     * @param model represents the data which is used to pass data to the view
     * @return String with view's name "filter-low-price.html"
     */
    @GetMapping("/low-price")
    public String filterLowPrice(Model model) {
        List<Category> categories = categoryService.findAllByActivated();
        List<CategoryDto> categoryDtoList = categoryService.getCategoryAndProduct();
        List<Product> products = productService.filterLowPrice();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("categoryDtoList", categoryDtoList);
        return "filter-low-price";
    }

    @PostMapping("/product")
    public void saveProduct(@RequestBody Product product) {
        System.out.println(product);
        productRepository.save(product);
    }
}
