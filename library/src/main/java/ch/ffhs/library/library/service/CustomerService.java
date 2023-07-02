package ch.ffhs.library.library.service;

import ch.ffhs.library.library.dto.CustomerDto;
import ch.ffhs.library.library.model.Customer;

import java.util.List;

/**
 * The CustomerService interface thus defines the basic
 * operations required for managing customers
 */
public interface CustomerService {
    Customer save(CustomerDto customerDto);

    Customer findByUsername(String username);

    List<CustomerDto> findAllDto();

    Customer findCustomerById(Long id);

    Customer findCustomerByEmail(String email);

    CustomerDto update(CustomerDto customerDto);
}
