package ch.ffhs.library.library.service;

import ch.ffhs.library.library.dto.CustomerDto;
import ch.ffhs.library.library.model.Customer;

public interface CustomerService {
    CustomerDto save(CustomerDto customerDto);

    Customer findByUsername(String username);

    Customer saveCustomerInfo(Customer customer);
}
