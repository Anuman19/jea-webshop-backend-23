package ch.ffhs.library.library.repository;

import ch.ffhs.library.library.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * By using the CustomerRepository, database operations such as saving,
 * updating, deleting and querying Customer objects can be easily performed
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
}
