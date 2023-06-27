package optional;

import ch.ffhs.library.library.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * By using the OrderRepository, database operations such as saving,
 * updating, deleting and querying Order objects can be easily performed
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
