package ch.ffhs.library.library.repository;

import ch.ffhs.library.library.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * By using the CartItemRepository, database operations such as saving,
 * updating, deleting and querying CartItem objects can be easily performed
 */
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
