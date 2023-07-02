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
    CategoryDto save(CategoryDto category);
    Category findById(Long id);
    CategoryDto update(CategoryDto category);

}
