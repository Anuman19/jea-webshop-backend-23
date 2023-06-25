package ch.ffhs.customer.controller;

import ch.ffhs.library.library.dto.CustomerDto;
import ch.ffhs.library.library.model.Customer;
import ch.ffhs.library.library.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * AuthController, which is responsible for processing requests related to the authentication
 */
@RestController
public class AuthController {

    // injects service for customer's management
    @Autowired
    private CustomerService customerService;

    // is used to encrypt passwords
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * method is called when an HTTP GET request is sent to the /login URL
     *
     * @return String with view's name "login.html"
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    /**
     * method is called when an HTTP GET request is sent to the /register URL
     *
     * @param model represents the data which is used to pass data to the view
     * @return String with view's name "register.html"
     */
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("customerDto", new CustomerDto());
        return "register";
    }

    /**
     * method is called when an HTTP POST request is sent to the /do-register URL
     * and validates whether there are all necessary information to register a customer
     *
     * @param customerDto data transfer object with the customer data
     * @param result object to save validation errors
     * @param model represents the data which is used to pass data to the view
     * @param attributes flash attributes show error or success messages
     * @return String with view's name "register.html"
     */
    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute("customerDto")CustomerDto customerDto, BindingResult result, Model model, RedirectAttributes attributes){
        try {
            if(result.hasErrors()){
                model.addAttribute("customerDto", customerDto);
                return "register";
            }
            Customer customer = customerService.findByUsername(customerDto.getUsername());
            if(customer != null) {
                attributes.addFlashAttribute("username", "Username has been registered");
                model.addAttribute("username", "Username has been registered");
                model.addAttribute("customerDto", customerDto);
                return "regsiter";
            }
                if(customerDto.getPassword().equals(customerDto.getRepeatPassword())){
                    customerDto.setPassword(passwordEncoder.encode(customerDto.getPassword()));
                    customerService.save(customerDto);
                    model.addAttribute("success", "Registered successfully");
                } else{
                    attributes.addFlashAttribute("password", "Password is not the same");
                    model.addAttribute("password", "Password is not the same");
                    model.addAttribute("customerDto", customerDto);
                }
            return "register";
        }catch (Exception e){
            attributes.addFlashAttribute("error", "Server has som problems");
            model.addAttribute("error", "Server has som problems");
            model.addAttribute("customerDto", customerDto);
        }
        return "register";
    }
}
