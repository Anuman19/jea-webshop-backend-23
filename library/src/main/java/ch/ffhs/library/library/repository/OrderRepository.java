package ch.ffhs.library.library.repository;

import ch.ffhs.library.library.model.Customer;
import ch.ffhs.library.library.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByUserOrderByCreatedDateDesc(Customer user);

}