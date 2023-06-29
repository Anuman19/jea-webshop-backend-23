package ch.ffhs.library.library.repository;

import ch.ffhs.library.library.dto.CategoryDto;
import ch.ffhs.library.library.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * By using the CategoryRepository, database operations such as saving,
 * updating, deleting and querying Category objects can be easily performed
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    //defines a custom query to retrieve all enabled categories
    @Query("select c from Category c where c.activated = true")
    List<Category> findAllByActivated();

    /* Customer*/
    // todo Video 15 bei Minute 15
    // todo Achtung funktioniert nicht
    //@Query("select new ch.ffhs.library.library.dto.CategoryDto(c.id, c.name, count(p.category.id)) from Category c inner join Product p on p.category.id = c.id where c.is_activated = true")
    // defines a custom query to retrieve all categories with amount of products
    /**@Query("select new ch.ffhs.library.library.dto.CategoryDto(c.id, c.name, count(p.category.id)) from Category c inner join Product p on p.category.id = c.id where c.activated = true group by c.id")
    List<CategoryDto> getCategoryAndProduct();**/


}
