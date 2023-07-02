package ch.ffhs.library.library.repository;

import ch.ffhs.library.library.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * By using the CategoryRepository, database operations such as saving,
 * updating, deleting and querying Category objects can be easily performed
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


}
