package ch.ffhs.library.library.service.impl;

import ch.ffhs.library.library.dto.CustomerDto;
import ch.ffhs.library.library.model.Customer;
import ch.ffhs.library.library.repository.CustomerRepository;
import ch.ffhs.library.library.repository.RoleRepository;
import ch.ffhs.library.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public CustomerDto save(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setPassword(customerDto.getPassword());
        customer.setRoles(Arrays.asList(roleRepository.findByName("CUSTOMER")));

        Customer customerSave = customerRepository.save(customer);
        return mapperDTO(customerSave);

    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    private CustomerDto mapperDTO(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setPassword(customer.getPassword());
        customerDto.setUsername(customerDto.getUsername());
        return customerDto;
    }
}
