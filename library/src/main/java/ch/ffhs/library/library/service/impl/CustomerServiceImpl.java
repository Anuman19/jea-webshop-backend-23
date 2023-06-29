package ch.ffhs.library.library.service.impl;

import ch.ffhs.library.library.dto.AdminDto;
import ch.ffhs.library.library.dto.CustomerDto;
import ch.ffhs.library.library.model.Admin;
import ch.ffhs.library.library.model.Customer;
import ch.ffhs.library.library.model.Role;
import ch.ffhs.library.library.repository.CustomerRepository;
import ch.ffhs.library.library.repository.RoleRepository;
import ch.ffhs.library.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private RoleServiceImpl roleRepository;

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer save(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setPassword(customerDto.getPassword());
        List<Role> roleList = new ArrayList<>();
        roleList.add(roleRepository.findRoleById(2L));

        return customerRepository.save(customer);

    }

    @Override
    public CustomerDto update(CustomerDto customerDto) {
        if (customerRepository.findById(customerDto.getId()).isPresent()) {
            Customer customer = customerRepository.findById(customerDto.getId()).get();
            customer.setFirstName(customerDto.getFirstName());
            customer.setEmail(customerDto.getEmail());
            customer.setLastName(customerDto.getLastName());
            customer.setUsername(customerDto.getUsername());
            customer.setPassword(customerDto.getPassword());
            List<Role> roleList = new ArrayList<>();
            roleList.add(roleService.findRoleById(1L));
            System.out.println(roleList);
            customer.setRoles(roleList);
            return mapperDTO(customerRepository.save(customer));
        } else {
            return new CustomerDto();
        }
    }


    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    /**
     * @return todo
     */
    @Override
    public List<CustomerDto> findAllDto() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDto> dtoList = new ArrayList<>();
        for (Customer c : customerList) {
            dtoList.add(mapperDTO(c));
        }

        return dtoList;
    }

    private CustomerDto mapperDTO(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setPassword(customer.getPassword());
        customerDto.setUsername(customerDto.getUsername());
        return customerDto;
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findCustomerById(id);
    }

    /**
     * @param email
     * @return
     */
    @Override
    public Customer findCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }


}
