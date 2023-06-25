package ch.ffhs.library.library.service;

import ch.ffhs.library.library.dto.CustomerDto;
import ch.ffhs.library.library.model.Customer;

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

    /**
     * This method saves the information of the customer
     *
     * @param customer object
     * @return Customer object
     */
    Customer saveCustomerInfo(Customer customer);
}
