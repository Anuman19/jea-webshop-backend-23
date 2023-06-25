package ch.ffhs.library.library.service;

import ch.ffhs.library.library.dto.CategoryDto;
import ch.ffhs.library.library.model.Category;

import java.util.List;
/**
 * The CategoryService interface thus defines the basic
 * operations required for managing categories
 */
public interface CategoryService {
    /* Admin */
    List<Category> findAll();
    Category save(Category category);
    Category findById(Long id);
    Category update(Category category);
    void deletedById(Long id);
    void enabledById(Long id);
    List<Category> findAllByActivated();

    /* Customer */
    List<CategoryDto> getCategoryAndProduct();
}
