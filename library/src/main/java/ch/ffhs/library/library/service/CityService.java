package ch.ffhs.library.library.service;

import ch.ffhs.library.library.model.City;

import java.util.List;

/**
 * The CityService interface thus defines the basic
 * operations required for managing cities
 */
public interface CityService {
    List<City> getAll();
}
