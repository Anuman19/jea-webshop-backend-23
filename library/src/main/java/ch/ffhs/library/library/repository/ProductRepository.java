package ch.ffhs.library.library.repository;

import ch.ffhs.library.library.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * By using the OrderRepository, database operations such as saving,
 * updating, deleting and querying Order objects can be easily performed
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
     @Query(value = "select p from Product p inner join Category c on c.id = p.category.id where c.id = ?1")
    List<Product> getProductsInCategory(Long categoryId);
}
