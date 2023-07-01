package ch.ffhs.customer.controller;

import ch.ffhs.library.library.dto.CategoryDto;
import ch.ffhs.library.library.model.Category;
import ch.ffhs.library.library.repository.CategoryRepository;
import ch.ffhs.library.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CategoryController, which is responsible for processing requests related to categories
 */
@RestController
public class CategoryController {
    // this annotation injects the CategoryService which allows the controller
    // to access the service to execute business logic related to categories
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * method is called when an HTTP GET request is sent to the /categories URL
     *
     * @return String with view's name "categories.html"
     */
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> categories() {

        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    /**
     * method is called when an HTTP POST request is sent to the /add-category URL and will add a new category
     *
     * @param category object to save new category
     * @return ResponseEntity newly created category and 201
     */
    @PostMapping("/add-category")
    public ResponseEntity<?> addCategory(@RequestBody CategoryDto category) {
        try {
            return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }


    }

    /**
     * method is called when an HTTP  GET request is sent to the /categories/{id} URL
     * and is searching for a category by its ID
     *
     * @param id of the category
     * @return ResponseEntity with category by id and 200
     */
    @GetMapping("/categories/{id}")
    public ResponseEntity<?> findCategoryById(@PathVariable("id") Long id) {

        try {
            return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.CONFLICT);

        }
    }

    /**
     * method is called when an HTTP GET request is sent to the /update-category URL
     *
     * @param id       of category
     * @param category DTO to update
     * @return ResponseEntity updated category and 201
     */
    @PutMapping("/update-category/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDto category) {

        category.setCategoryId(id);
        try {
            return new ResponseEntity<>(categoryService.update(category), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.CONFLICT);
        }
    }

    /**
     * method is called when an HTTP PUT or GET request is sent to the /deleted-category URL
     * and deletes a category by its ID
     *
     * @param id of the category
     * @return String "deleted" and 200
     */
    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {

        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}
