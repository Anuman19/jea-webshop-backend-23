package ch.ffhs.customer.controller;

import ch.ffhs.library.library.dto.AdminDto;
import ch.ffhs.library.library.dto.CustomerDto;
import ch.ffhs.library.library.dto.LoginDto;
import ch.ffhs.library.library.model.Admin;
import ch.ffhs.library.library.model.Customer;
import ch.ffhs.library.library.repository.CustomerRepository;
import ch.ffhs.library.library.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * LoginController is created to map incoming URL request related to the login
 * and is responsible for displaying the appropriate views and processing user interaction to perform
 * the desired actions (displaying pages, saving data and redirecting)
 */
@RestController
public class LoginController {

    // is used to encrypt passwords
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // injects service for administrators' management
    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    CustomerRepository customerRepository;

    /**
     * method is called when an HTTP GET request is sent to the /login URL
     *
     * @param model represents the data which is used to pass data to the view
     * @return String with view's name "login.html"
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        Customer customer;
        try {
            customer = customerService.findCustomerByEmail(loginDto.getEmail());
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.CONFLICT);
        }
        if (customer.getPassword().equals(loginDto.getPassword())) {
            return new ResponseEntity<>(customer.getId(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("password not matching", HttpStatus.FORBIDDEN);
        }
    }

    /**
     * method is called when an HTTP GET request is sent to the /register URL
     *
     * @param model represents the data which is used to pass data to the view
     * @return String with view's name "register.html"
     */
    @PostMapping("/register")
    public ResponseEntity<Customer> register(CustomerDto customerDto) {

        return new ResponseEntity<>(customerService.save(customerDto), HttpStatus.CREATED);
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {

        if (customerRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(customerRepository.findById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("not found", HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody CustomerDto customerDto) {

        if (customerRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(customerService.update(customerDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("not found", HttpStatus.BAD_REQUEST);
        }
    }

}
