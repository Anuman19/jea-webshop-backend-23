package ch.ffhs.library.library.service.impl;

import ch.ffhs.library.library.dto.CategoryDto;
import ch.ffhs.library.library.model.Category;
import ch.ffhs.library.library.repository.CategoryRepository;
import ch.ffhs.library.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository repository;

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public CategoryDto save(CategoryDto category) {
        try {

            return mapperDto(repository.save(DtoMapper(category)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Category findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        try {

            repository.getReferenceById(categoryDto.getId());

        } catch (Exception e) {
            e.printStackTrace();

        }
        return mapperDto(repository.save(DtoMapper(categoryDto)));
    }

    private CategoryDto mapperDto(Category category) {
        return new CategoryDto(category.getId(), category.getName(), category.isActivated());
    }

    private Category DtoMapper(CategoryDto categoryDto) {

        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setActivated(categoryDto.isActivated());

        return category;
    }
}
