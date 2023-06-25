package ch.ffhs.library.library.repository;

import ch.ffhs.library.library.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * By using the AdminRepository, database operations such as saving,
 * updating, deleting, and querying can be easily performed.
 * The Spring Data Repository framework takes care of implementing
 * these operations based on the methods defined in the repository interface.
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);

}
