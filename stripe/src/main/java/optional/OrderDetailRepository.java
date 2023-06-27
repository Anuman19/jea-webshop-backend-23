package optional;

import ch.ffhs.library.library.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * By using the OrderDetailRepository, database operations such as saving,
 * updating, deleting and querying OrderDetail objects can be easily performed
 */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
