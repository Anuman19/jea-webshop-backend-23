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
    public List<Category> findAll(){
        return repository.findAll();
    }

    @Override
    public Category save(Category category){
        try {
            Category categorySave = new Category(category.getName());
            return repository.save(categorySave);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Category findById(Long id){
        return repository.findById(id).get();
    }

    @Override
    public Category update(Category category){
        Category categoryUpdate = null;
        try {
            categoryUpdate = repository.findById(category.getId()).get();
            categoryUpdate.setName(category.getName());
            categoryUpdate.set_activated(category.is_activated());
        }catch (Exception e){
            e.printStackTrace();
        }

        return repository.save(categoryUpdate);
    }

    @Override
    public void deletedById(Long id){
        Category category = repository.getReferenceById(id);
        category.set_activated(false);
        repository.save(category);
    }

    @Override
    public void enabledById(Long id){
        Category category = repository.getReferenceById(id);
        category.set_activated(true);
        repository.save(category);
    }

    @Override
    public List<Category> findAllByActivated(){
        return repository.findAllByActivated();
    }

    @Override
    public List<CategoryDto> getCategoryAndProduct() {
        return repository.getCategoryAndProduct();
    }

}
