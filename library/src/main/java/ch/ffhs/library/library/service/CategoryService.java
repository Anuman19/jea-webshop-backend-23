package ch.ffhs.library.library.service;

import ch.ffhs.library.library.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category save(Category category);
    Category getById(Long id);
    Category update(Category category);
    void deletedById(Long id);
    void enabledById(Long id);
}
