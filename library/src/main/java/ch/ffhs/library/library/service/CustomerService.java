package ch.ffhs.library.library.service;

import ch.ffhs.library.library.dto.CustomerDto;
import ch.ffhs.library.library.model.Customer;

import java.util.List;

/**
 * The CustomerService interface thus defines the basic
 * operations required for managing customers
 */
public interface CustomerService {
    /**
     * It stores the customer in the system and returns a CustomerDto object representing the stored customer
     *
     * @param customerDto represents customer
     * @return CustomerDto
     */
    CustomerDto save(CustomerDto customerDto);

    /**
     * This method searches for a customer based on the username
     *
     * @param username of the customer
     * @return Customer object
     */
    Customer findByUsername(String username);

    List<CustomerDto> findAllDto();

    Customer findCustomerById(Long id);
}
