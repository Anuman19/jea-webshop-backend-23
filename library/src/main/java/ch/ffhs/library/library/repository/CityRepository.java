package ch.ffhs.library.library.repository;

import ch.ffhs.library.library.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * By using the CityRepository, database operations such as saving,
 * updating, deleting and querying City objects can be easily performed
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
