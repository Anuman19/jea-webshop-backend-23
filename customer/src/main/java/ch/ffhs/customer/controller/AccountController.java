package ch.ffhs.customer.controller;

import ch.ffhs.library.library.dto.CustomerDto;
import ch.ffhs.library.library.model.Customer;
import ch.ffhs.library.library.repository.CustomerRepository;
import ch.ffhs.library.library.service.CityService;
import ch.ffhs.library.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

/**
 * AccountController, which is responsible for processing requests related to accounts
 */
@RestController
public class AccountController {
    // injects service for customer's management
    @Autowired
    private CustomerService customerService;

    // injects service for city management
    @Autowired
    private CityService cityService;

    @Autowired
    private CustomerRepository customerRepository;


    /**
     * method is called when an HTTP GET request is sent to the /account URL
     *
     * @param model     represents the data which is used to pass data to the view
     * @param principal represents the current logged-in user
     * @return String with view's name "account.html"
     */
    @GetMapping("/account")
    public Customer accountHome(Model model, Principal principal) {
        /**if(principal == null){
         return "redirect:/login";
         }

         String username = principal.getName();
         Customer customer = customerService.findByUsername(username);
         model.addAttribute("customer", customer);**/
        System.out.println("TEST OUT " + customerRepository.findAll() + customerRepository.findAll().size());
        return customerRepository.findByUsername("test");
    }

    /**
     * method is called when an HTTP GET and PUT request are sent to the /update-information URL
     *
     * @param customer   object to save information
     * @param attributes flash attributes show error or success messages
     * @param principal  represents the current logged-in user
     * @return String with redirected pages
     */
    @RequestMapping(value = "/update-information", method = {RequestMethod.GET, RequestMethod.PUT})
    public CustomerDto updateCustomer(@RequestBody CustomerDto customer) {

        /**if (principal == null) {
         return "redirect:/login";
         }**/
        System.out.println(customer);
        return customerService.save(customer);
    }

    @PostMapping("/addAccount")
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto) {
        System.out.println(customerDto);
        CustomerDto save = customerService.save(customerDto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping("/accountList")
    public ResponseEntity<List<CustomerDto>> accountList() {
        return new ResponseEntity<>(customerService.findAllDto(), HttpStatus.OK);
    }
}
