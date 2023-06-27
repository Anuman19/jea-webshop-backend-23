package ch.ffhs.library.library.repository;

import ch.ffhs.library.library.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    /* Admin */

    @Query("select p from Product p")
    Page<Product> pageProduct(Pageable pageable);

    @Query("select p from Product p where p.description like %?1% or p.name like %?1%")
    Page<Product> searchProducts(String keyword, Pageable pageable);

    @Query("select p from Product p where  p.description like %?1% or p.name like %?1%")
    List<Product> searchProductsList(String keyword);

    /* Customer */

    @Query("select p from Product p where p.is_activated = true ")
    List<Product> getAllProducts();

    @Query(value = "select * from products p where p.is_activated = true order by rand() asc limit 4", nativeQuery = true)
    List<Product> listViewProducts();

    @Query(value = "select * from products p inner join categories c on c.category_id where p.category_id = ?1", nativeQuery = true)
    List<Product> geRelatedProducts(Long categoryId);

    @Query(value = "select p from Product p inner join Category c on c.id = p.category.id where c.id = ?1")
    List<Product> getProductsInCategory(Long categoryId);
}
