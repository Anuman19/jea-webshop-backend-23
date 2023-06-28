package ch.ffhs.library.library.repository;

import ch.ffhs.library.library.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}