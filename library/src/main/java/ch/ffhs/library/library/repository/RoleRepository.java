package ch.ffhs.library.library.repository;

import ch.ffhs.library.library.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * By using the RoleRepository, database operations such as saving,
 * updating, deleting and querying Role objects can be easily performed
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleById(Long id);

}
