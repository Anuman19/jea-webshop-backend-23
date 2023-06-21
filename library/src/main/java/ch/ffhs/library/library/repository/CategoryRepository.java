package ch.ffhs.library.library.repository;

import ch.ffhs.library.library.dto.CategoryDto;
import ch.ffhs.library.library.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c where c.is_activated = true")
    List<Category> findAllByActivated();

    /* Customer*/
    // todo Video 15 bei Minute 15
    //@Query("select new ch.ffhs.library.library.dto.CategoryDto(c.id, c.name, count(p.category.id)) from Category c inner join Product p on p.category.id = c.id where c.is_activated = true")
    @Query("select new ch.ffhs.library.library.dto.CategoryDto(c.id, c.name, count(p.category.id)) from Category c inner join Product p on p.category.id = c.id where c.is_activated = true group by c.id")
    List<CategoryDto> getCategoryAndProduct();


}
